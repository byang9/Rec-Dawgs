// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:       JoinClub
//
// Type:        Servlet
//
// K.J. Kochut
//
//
//


package edu.uga.clubs.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.clubs.logic.LogicLayer;
import edu.uga.clubs.session.Session;
import edu.uga.clubs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;






// Servlet class JoinClub
//
// doPost starts the execution of the Join a Club Use Case
//
//   parameters:
//
//	person_id   string representing a person id (long value)
//	club_name   string
//
public class JoinClub
    extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "JoinClub-Result.ftl";

    private Configuration  cfg;

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(), 
                "WEB-INF/templates"
                );
    }

    public void doPost( HttpServletRequest  req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template       resultTemplate = null;
        BufferedWriter toClient = null;
        LogicLayer     logicLayer = null;
        String	       person_id_str = null;
        long	       person_id = 0;
        String         club_name  = null;
        HttpSession    httpSession;
        Session        session;
        String         ssid;

        httpSession = req.getSession();

        ssid = (String) httpSession.getAttribute( "ssid" );

        if( ssid == null ) {       // not logged in!
            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }

        session = SessionManager.getSessionById( ssid );
        if( session == null ) {
            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }
        
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }

        // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        } 
        catch (IOException e) {
            throw new ServletException( 
                    "Can't load template in: " + templateDir + ": " + e.toString());
        }

        // Prepare the HTTP response:
        // - Use the charset of template for the output
        // - Use text/html MIME-type
        //
        toClient = new BufferedWriter(
                new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() )
                );

        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());

        // Get the form parameters
        //
        person_id_str = req.getParameter( "person_id" );

        if( person_id_str == null ) {
            ClubsError.error( cfg, toClient, "Unspecified person_id" );
            return;
        }

        try {
            person_id = Long.parseLong( person_id_str );
        }
        catch( Exception e ) {
            ClubsError.error( cfg, toClient, "person_id should be a number and is: " + person_id_str );
            return;
        }

        if( person_id <= 0 ) {
            ClubsError.error( cfg, toClient, "Non-positive person_id: " + person_id );
            return;
        }

        club_name = req.getParameter( "club_name" );

        if( club_name == null ) {
            ClubsError.error( cfg, toClient, "Unspecified club name or club address" );
            return;
        }

        try {
            logicLayer.joinClub( person_id, club_name );
        } 
        catch( Exception e ) {
            ClubsError.error( cfg, toClient, e );
            return;
        }

        // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();

        // Build the data-model
        //
        root.put( "club_name", club_name );
        root.put( "person_id", new Long( person_id ) );

        // Merge the data-model and the template
        //
        try {
            resultTemplate.process( root, toClient );
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();

    }
}