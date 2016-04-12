// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:	FindAllClubs
//
// Type:	Servlet
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.logic.LogicLayer;
import edu.uga.clubs.session.Session;
import edu.uga.clubs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


// Boundary class FindAllClubs (servlet)
//
// doGet starts the execution of the List All Clubs Use Case
//
//   parameters:
//
//	none
//
public class FindAllClubs

extends HttpServlet 

{
    private static final long serialVersionUID = 1L;

    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "FindAllClubs-Result.ftl";

    private Configuration     cfg;

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
    }

    public void doGet( HttpServletRequest  req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template            resultTemplate = null;
        BufferedWriter      toClient = null;
        LogicLayer          logicLayer = null;
        List<Club>          rv = null;
        List<List<Object>>  clubs = null;
        List<Object>        club = null;
        Club   	            c  = null;
        HttpSession         httpSession;
        Session             session;
        String              ssid;

        
        // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        } 
        catch( IOException e ) {
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
        
        httpSession = req.getSession();
        if( httpSession == null ) {       // assume not logged in!
            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }
        
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
        
        // Get the servlet parameters
        //
        // No parameters here

        // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();
        
        try {
            rv = logicLayer.findAllClubs();

            // Build the data-model
            //
            clubs = new LinkedList<List<Object>>();
            root.put( "clubs", clubs );

            for( int i = 0; i < rv.size(); i++ ) {
                c = (Club) rv.get( i );
                //Person founder = objectModel.findEstablishedBy( c );
                Person founder = c.getFounder();
                club = new LinkedList<Object>();
                club.add( c.getId() );
                club.add( c.getName() );
                club.add( c.getAddress() );
                club.add( c.getEstablishedOn().toString() );
                club.add( founder.getFirstName() + " " + founder.getLastName() );
                clubs.add( club );
            }
        } 
        catch( Exception e) {
            ClubsError.error( cfg, toClient, e );
            return;
        }

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

