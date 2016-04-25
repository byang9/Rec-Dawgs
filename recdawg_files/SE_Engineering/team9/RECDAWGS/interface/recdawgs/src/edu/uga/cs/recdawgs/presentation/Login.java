package edu.uga.cs.recdawgs.presentation;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.recdawgs.logic.LogicLayer;
import edu.uga.cs.recdawgs.session.Session;
import edu.uga.cs.recdawgs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Login extends HttpServlet {
        
    	private static final long serialVersionUID = 1L;
        
        static  String  templateDir = "WEB-INF/templates";
        static  String  resultTemplateName = "StudentMainWindow.ftl";

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

        public void doPost( HttpServletRequest req, HttpServletResponse res )
                throws ServletException, IOException
        {
            Template       resultTemplate = null;
            HttpSession    httpSession = null;
            BufferedWriter toClient = null;
            String         username = null;
            String         password = null;
            String         ssid = null;
            Session        session = null;
            LogicLayer     logicLayer = null;

            httpSession = req.getSession();
            ssid = (String) httpSession.getAttribute( "ssid" );
            if( ssid != null ) {
                System.out.println( "Already have ssid: " + ssid );
                session = SessionManager.getSessionById( ssid );
                System.out.println( "Connection: " + session.getConnection() );
            }
            else
                System.out.println( "ssid is null" );

            // Prepare the HTTP response:
            // - Use the charset of template for the output
            // - Use text/html MIME-type
            //
            toClient = new BufferedWriter( new OutputStreamWriter( res.getOutputStream()) );
            res.setContentType("text/html;");
            
            if( session == null ) {
                try {
                    session = SessionManager.createSession();
                }
                catch ( Exception e ) {
                    RDError.error( cfg, toClient, e );
                    return;
                }
            }
            
            logicLayer = session.getLogicLayer();

            // Get the parameters
            //
            username = req.getParameter( "username" );
            password = req.getParameter( "password" );

            if( username == null || password == null ) {
                RDError.error( cfg, toClient, "Username or password is blank!" );
                return;
            }

            try {          
                ssid = logicLayer.login( session, username, password );
                if (!session.getUser().getIsStudent()) resultTemplateName = "AdminMainWindow.ftl";
                System.out.println( "Obtained ssid: " + ssid );
                httpSession.setAttribute( "ssid", ssid );
                System.out.println( "Connection: " + session.getConnection() );
            } 
            catch ( Exception e ) {
                RDError.error( cfg, toClient, e );
                return;
            }
            
            // Load templates from the WEB-INF/templates directory of the Web app.
            //
            try {
                resultTemplate = cfg.getTemplate( resultTemplateName );
            } 
            catch (IOException e) {
                throw new ServletException( "Login.doPost: Can't load template in: " + templateDir + ": " + e.toString());
            }

            // Setup the data-model
            //
            Map<String, String> root = new HashMap<String, String>();

            // Build the data-model
            //
            root.put( "firstname", session.getUser().getFirstName() );
            root.put( "username", session.getUser().getUserName() );

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
