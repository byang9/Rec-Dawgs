// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:       CreatePerson
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





// Servlet class CreatePerson
//
// doPost starts the execution of the Create Club Use Case
//
//   parameters:
//
//      user_name       string
//      password        string
//      first_name      string
//	last_name	string
//	address		string
//	phone		string
//	email		string
//
public class CreatePerson
    extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
static  String         templateDir = "WEB-INF/templates";
  static  String         resultTemplateName = "CreatePerson-Result.ftl";

  private Configuration  cfg; 

  public void init() 
  {
      // Prepare the FreeMarker configuration;
      // - Load templates from the WEB-INF/templates directory of the Web app.
      //
      cfg = new Configuration();
      cfg.setServletContextForTemplateLoading( getServletContext(), "WEB-INF/templates" );
  }

  public void doPost( HttpServletRequest req, HttpServletResponse res )
          throws ServletException, IOException
  {
      Template       resultTemplate = null;
      BufferedWriter toClient = null;
      String         user_name = null;
      String         password = null;
      String         first_name = null;
      String	     last_name = null;
      String	     address = null;
      String	     phone = null;
      String	     email = null;
      long	     person_id = 0;
      LogicLayer     logicLayer = null;
      HttpSession    httpSession;
      Session        session;
      String         ssid;


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

      // Get the form parameters
      //
      user_name = req.getParameter( "user_name" );
      password = req.getParameter( "password" );
      email = req.getParameter( "email" );
      first_name = req.getParameter( "first_name" );
      last_name = req.getParameter( "last_name" );
      address = req.getParameter( "address" );
      phone = req.getParameter( "phone" );

      // validate the parameters
      if( user_name == null ) {
          ClubsError.error( cfg, toClient, "Unspecified user name" );
          return;
      }

      if( password == null ) {
          ClubsError.error( cfg, toClient, "Unspecified password" );
          return;
      }

      if( first_name == null ) {
          ClubsError.error( cfg, toClient, "Unspecified first name" );
          return;
      }

      if( last_name == null ) {
          ClubsError.error( cfg, toClient, "Unspecified last name" );
          return;
      }

      if( address == null )
          address = "";

      if( phone == null )
          phone = "";

      if( email == null ) {
          ClubsError.error( cfg, toClient, "Unspecified email" );
          return;
      }

      try {
          person_id = logicLayer.createPerson( user_name, password, email, first_name, last_name, address, phone );
      } 
      catch ( Exception e ) {
          ClubsError.error( cfg, toClient, e );
          return;
      }

      // Setup the data-model
      //
      Map<String,Object> root = new HashMap<String,Object>();

      // Build the data-model
      //
      root.put( "first_name", first_name );
      root.put( "last_name", last_name );
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

