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
import java.util.Date;
import java.text.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.entity.Student;
import edu.uga.cs.recdawgs.entity.League;
import edu.uga.cs.recdawgs.entity.Team;
import edu.uga.cs.recdawgs.entity.Match;
import edu.uga.cs.recdawgs.entity.ScoreReport;
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
public class CreateScoreReport extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    static  String            templateDir = "WEB-INF/templates";
    static  String            resultTemplateName = "CreateScoreReport-Result.ftl";

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
        List<League>        rv = null;
        List<Match>         listOfMatches = null;
        List<List<Object>>  leagues = null;
        List<Object>        league = null;
        LinkedList<List<Object>> teams = null;
        League              l  = null;
        HttpSession         httpSession;
        Session             session;
        String              ssid;

        resultTemplateName = "CreateScoreReport-Result.ftl";
        
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

        try {
            //logicLayer.deleteTeam(name);

            listOfMatches = logicLayer.findMyMatch((Student)session.getUser());

             // Build the data-model
            //
            teams = new LinkedList<List<Object>>();
            root.put( "teams", teams );

            for( int i = 0; i < rv.size(); i++ ) {
                Match t = (Match) listOfMatches.get( i );
                Team homeTeam = t.getHomeTeam();
                Team awayTeam = t.getAwayTeam();
                LinkedList<Object> team = new LinkedList<Object>();
                team.add( t.getId() );
                team.add( homeTeam.getName() + " vs " + awayTeam.getName() );
                teams.add( team );
            }
        } 
        catch( Exception e) {
            e.printStackTrace();
            RDError.error( cfg, toClient, e );
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

    public void doPost( HttpServletRequest  req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template            resultTemplate = null;
        BufferedWriter      toClient = null;
        LogicLayer          logicLayer = null;
        List<League>        rv = null;
        List<List<Object>>  leagues = null;
        List<Object>        league = null;
        League              l  = null;
        HttpSession         httpSession;
        Session             session;
        String              ssid;

        // Get Parameters
        String homeScore = req.getParameter("homeScore");
        String awayScore = req.getParameter("awayScore");
        String matchId = req.getParameter("team");
        String homePoints = req.getParameter("homeTeam");
        String awayPoints = req.getParameter("awayTeam");
        String date = req.getParameter("matchDate");
        String homeTeam = req.getParameter("homeTeam");
        String awayTeam = req.getParameter("awayTeam");


        resultTemplateName = "FindLeagueResult-Result.ftl";
        
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
                
        root.put("league", "League");
        root.put("title", "Score Reports");
        Student reportingStudent = (Student)session.getUser(); 
        //find the student's match
        List<Match> matches = null;
        try{
            matches = logicLayer.findMyMatch(reportingStudent);
        }catch(Exception e){
            e.printStackTrace();
        }
        Match modelMatch = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date matchDate = null;
            try{
                matchDate = formatter.parse(date); 
            }catch(ParseException e){
                e.printStackTrace();
            }

            for (Match m : matches){
                if (m.getDate() == matchDate){
                    modelMatch = m;
                    break;
                }
            }


            logicLayer.createScoreReport(homeTeam, awayTeam, Integer.parseInt(homeScore),Integer.parseInt(awayScore),matchDate,reportingStudent, modelMatch);
           
            
            //**********Update when show all ScoreReports 
            rv = logicLayer.findAllLeagues();

            // Build the data-model
            //
            leagues = new LinkedList<List<Object>>();
            root.put( "leagues", leagues );

            for( int i = 0; i < rv.size(); i++ ) {
                l = (League) rv.get( i );
                Team team = l.getWinnerOfLeague();
                league = new LinkedList<Object>();
                league.add( l.getId() );
                league.add( l.getName() );
                if (team != null)
                    league.add( team.getName() );
                else 
                    league.add( "None" );
                if (l.getIsIndoor())   
                    league.add( "Yes" );
                else 
                    league.add("No");
                league.add( l.getMinTeams() );
                league.add( l.getMaxTeams() );
                league.add( l.getMinMembers() );
                league.add( l.getMaxMembers() );
                league.add( l.getMatchRules() );
                league.add( l.getLeagueRules() );
                leagues.add( league );
            }
        } 
        catch( Exception e) {
            e.printStackTrace();
            RDError.error( cfg, toClient, e );
            return;
        }

        if (session.getIsStudent()) {
            root.put("visible", "hidden");
        } else {
            root.put("visible", "visible");
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

