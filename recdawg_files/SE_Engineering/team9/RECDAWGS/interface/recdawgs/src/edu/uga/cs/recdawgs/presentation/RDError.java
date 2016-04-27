// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:       RDError
//
// Type:        Servlet utility class
//
// K.J. Kochut - Logan Jahnke
//
//
//

package edu.uga.cs.recdawgs.presentation;



import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class RDError {
    static  String   errorTemplateName = "RDError-Result.ftl";

    public static void error( Configuration cfg, BufferedWriter toClient, Exception e )
            throws ServletException
    {
        error( cfg, toClient, e.toString() );
    }

    public static void error( Configuration cfg, BufferedWriter toClient, String msg )
            throws ServletException
    {
        Template	    errorTemplate = null;
        Map<String, String> root = new HashMap<String, String>();

        // Load the error template from the WEB-INF/templates directory of the Web app
        //
        try {
            errorTemplate = cfg.getTemplate( errorTemplateName );
        } 
        catch( Exception e ) {
            throw new ServletException( "Can't load template: " + errorTemplateName + ": " + e.toString() );
        }

        if (msg.equals("java.lang.NullPointerException")) msg = "Mega-weird internal error. This shouldn't happen.";
        root.put( "reason", msg );

        try {
            errorTemplate.process( root, toClient );
            toClient.flush();
            toClient.close();
        } 
        catch( Exception e ) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        return;
    }
}
