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

package edu.uga.cs.recdawgs.presentation;



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

import edu.uga.cs.recdawgs.entity.SportsVenue;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
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
public class CreateVenue extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "CreateVenue-Result.ftl";

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
        List<SportsVenue>       rv = null;
        List<List<Object>>  users = null;
        List<Object>        user = null;
        SportsVenue             u = null;
        // String              name = req.getParameter("name");
        // String              indoorString = req.getParameter("indoor");
        // String              address = req.getParameter("address");
        // boolean             isIndoor = false;
        // if (name == null || name == "" || indoorString == null || indoorString == "" || address == null || address == "") {
        //     resultTemplateName = "CreateVenue-Result.ftl";
        // } else {
        //     resultTemplateName = "FindAllVenues-Result.ftl";
        //     if (indoorString.equalsIgnoreCase("yes")) isIndoor = true;
        // }
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
            RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }
        
        ssid = (String) httpSession.getAttribute( "ssid" );
        if( ssid == null ) {       // not logged in!
        	RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }

        session = SessionManager.getSessionById( ssid );
        if( session == null ) {
        	RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }
        
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
        	RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }
        
        // Get the servlet parameters
        //
        // No parameters here

        // Setup the data-model
        Map<String,Object> root = new HashMap<String,Object>();
        
        // if (nameOfTeam != null) {
        //     if (name != null) {
        //         try {
        //             logicLayer.createSportsVenue(name, address, isIndoor);
        //             rv = logicLayer.findAllSportsVenues();

        //             // Build the data-model
        //             //
        //             venues = new LinkedList<List<Object>>();
        //             root.put( "venues", venues );

        //             for( int i = 0; i < rv.size(); i++ ) {
        //                 sv = (SportsVenue) rv.get( i );
        //                 venue = new LinkedList<Object>();
        //                 venue.add( sv.getId() );
        //                 venue.add( sv.getName() );
        //                 venue.add( sv.getAddress() );
        //                 if (sv.getIsIndoor()) 
        //                     venue.add( "Yes" );
        //                 else 
        //                     venue.add("No");
        //                 venues.add( venue );
        //             }
        //     } catch( Exception e) {
        //         RDError.error( cfg, toClient, e );
        //         return;
        //     }
        // } else {
        //     root.put("league", nameOfLeague);
        // }

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

    public void doPost( HttpServletRequest  req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template            resultTemplate = null;
        BufferedWriter      toClient = null;
        LogicLayer          logicLayer = null;
        List<SportsVenue>       rv = null;
        List<List<Object>>  venues = null;
        List<Object>        venue = null;
        SportsVenue             sv = null;
        String              name = req.getParameter("name");
        String              indoorString = req.getParameter("indoor");
        String              address = req.getParameter("address");
        boolean             isIndoor = false;
        if (name == null || name == "" || indoorString == null || indoorString == "" || address == null || address == "") {
            resultTemplateName = "CreateVenue-Result.ftl";
        } else {
            resultTemplateName = "FindAllVenues-Result.ftl";
            if (indoorString.equalsIgnoreCase("yes")) isIndoor = true;
        }
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
            RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }
        
        ssid = (String) httpSession.getAttribute( "ssid" );
        if( ssid == null ) {       // not logged in!
            RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }

        session = SessionManager.getSessionById( ssid );
        if( session == null ) {
            RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }
        
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
            RDError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return; 
        }
        
        // Get the servlet parameters
        //
        // No parameters here

        // Setup the data-model
        Map<String,Object> root = new HashMap<String,Object>();

        if (name != null) {
            try {
                logicLayer.createSportsVenue(name, address, isIndoor);
                rv = logicLayer.findAllSportsVenues();

                // Build the data-model
                //
                venues = new LinkedList<List<Object>>();
                root.put( "venues", venues );

                for( int i = 0; i < rv.size(); i++ ) {
                    sv = (SportsVenue) rv.get( i );
                    venue = new LinkedList<Object>();
                    venue.add( sv.getId() );
                    venue.add( sv.getName() );
                    venue.add( sv.getAddress() );
                    if (sv.getIsIndoor()) 
                        venue.add( "Yes" );
                    else 
                        venue.add("No");
                    venues.add( venue );
                }
            } catch( Exception e) {
                RDError.error( cfg, toClient, e );
                return;
            }
        } else {
            // root.put("league", nameOfLeague);
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