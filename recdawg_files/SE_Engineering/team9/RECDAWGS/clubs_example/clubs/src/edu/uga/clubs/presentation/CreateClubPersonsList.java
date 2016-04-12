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

import edu.uga.clubs.entity.Person;
import edu.uga.clubs.logic.LogicLayer;
import edu.uga.clubs.session.Session;
import edu.uga.clubs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


// doGet starts the execution of the Create Club, selecting from a persons list Use Case
// it invokes the findAllPersons use case (using its control class)
//
// parameters:
//
// none
//
public class CreateClubPersonsList 
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    static  String         templateDir = "WEB-INF/templates";
    static  String         resultTemplateName = "CreateClubPersonsList-Result.ftl";

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

    public void doGet( HttpServletRequest req, HttpServletResponse res )
            throws ServletException, IOException
    {
        Template               resultTemplate = null;
        BufferedWriter         toClient = null;
        LogicLayer             logicLayer = null;
        List<Person>           rvPerson = null;
        List<List<Object>>     persons = null;
        List<Object>           person = null;
        Person                 p  = null;
        HttpSession            httpSession;
        Session                session;
        String                 ssid;


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
        
        httpSession = req.getSession();
        if( httpSession == null ) {       // not logged in!
            ClubsError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }
        
        ssid = (String) httpSession.getAttribute( "ssid" );
        if( ssid == null ) {       // assume not logged in!
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

        // Get the parameters
        //
        // No parameters here


        // Setup the data-model
        //
        Map<String,Object> root = new HashMap<String,Object>();

        // Build the data-model
        //
        persons = new LinkedList<List<Object>>();

        try {
            rvPerson = logicLayer.findAllPersons();
        } 
        catch( Exception e ) {
            ClubsError.error( cfg, toClient, e );
            return;
        }

        root.put( "persons", persons );

        for( int i = 0; i < rvPerson.size(); i++ ) {
            p = (Person) rvPerson.get( i );
            person = new LinkedList<Object>();
            person.add( new Long( p.getId() ) );
            person.add( p.getFirstName() );
            person.add( p.getLastName() );
            persons.add( person );
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
