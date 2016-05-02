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

import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.Student;
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
public class ViewStudentsOfTeam extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "FindAllTeamMembers-Result.ftl";

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
        List<Student>       rv = null;
        List<List<Object>>  users = null;
        List<Object>        user = null;
        Student			    u = null;
        List<List<Object>>  matches = null;
        List<Object>        match = null;
        Match               m = null;
        String				nameOfTeam = req.getParameter("team");
        nameOfTeam = nameOfTeam.replace('_', ' ');
        HttpSession         httpSession;
        Session             session;
        String              ssid;
        String              action = "JoinTeam";
        String              preAction = "Join";

        
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
        //
        Map<String,Object> root = new HashMap<String,Object>();
        
        try {
            rv = logicLayer.findTeamMembers(nameOfTeam);
            root.put( "team", nameOfTeam );

            if (logicLayer.findTeam(nameOfTeam).getCaptain().getUserName().equals(session.getUser().getUserName())) {
                root.put("button", "visible");
            } else {
                root.put("button", "hidden");
            }

            // Build the data-model
            //
            users = new LinkedList<List<Object>>();
            root.put( "users", users );

            for( int i = 0; i < rv.size(); i++ ) {
                u = (Student) rv.get( i );
                if (u.getUserName().equals(session.getUser().getUserName())) {
                    action = "LeaveTeam";
                    preAction = "Leave";
                }
                user = new LinkedList<Object>();
                user.add(u.getId());
                user.add(u.getFirstName() + " " + u.getLastName());
                user.add(u.getUserName());
                user.add(u.getEmailAddress());
                user.add(u.getStudentId());
                user.add(u.getMajor());
                user.add(u.getAddress());
                users.add(user);
            }

            // List<Match> rv1 = logicLayer.findTeamMatch(nameOfTeam);

            // // Build the data-model
            // //
            // matches = new LinkedList<List<Object>>();
            // root.put( "matches", matches );

            // for( int i = 0; i < rv1.size(); i++ ) {
            //     m = (Match) rv1.get( i );
            //     match = new LinkedList<Object>();
            //     match.add(m.getId());
            //     System.out.println("Added id: " + m.getId());
            //     match.add(m.getHomeTeam().getName() + " vs. " + m.getAwayTeam().getName());
            //     System.out.println("Added home team: " + m.getHomeTeam().getName());
            //     System.out.println("Added away team: " + m.getAwayTeam().getName());
            //     match.add(m.getDate());
            //     System.out.println("Added date: " + m.getDate());
            //     matches.add(match);
            // }
        } 
        catch( Exception e) {
            e.printStackTrace();
            RDError.error( cfg, toClient, e );
            return;
        }

        root.put("action", action);
        root.put("preaction", preAction);

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

