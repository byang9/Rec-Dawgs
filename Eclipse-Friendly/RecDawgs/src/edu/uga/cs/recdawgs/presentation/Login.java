package logan_jahnke_currency;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Converter
    extends HttpServlet
{
    public void doGet (HttpServletRequest req, HttpServletResponse res)
         throws ServletException, IOException
    {
        float dollars = Float.parseFloat(req.getParameter( "dollars" ));
        String  convert = req.getParameter("currency");

	PrintWriter toClient = res.getWriter();
	res.setContentType("text/html");
	
	toClient.println("<html>");
	toClient.println("<title>Currency Converter - by Logan Jahnke</title>");
	toClient.println("<body>");
	
	toClient.println("<P><B>Running query: $</B>" + dollars + "<P><P>" );
	
	Vector result = AccessMySQL.Execute("select dollarvalue from currency where currsymbol=\"" + convert + "\"");
	
	toClient.println("<P><P><B>Conversion Rate: </B><P><P>");

	float rate = Float.parseFloat((String)result.elementAt(0));

	toClient.println("<p>$<tt>" + dollars + "</tt> is <tt>" + (rate*dollars) + "</tt> " + convert);
	
	//	int conversion = Integer.parseInt(conversionString);
	
	//System.out.println("<P><P><B>$" + dollars + " is " + (dollars * conversion) + " in " + convert + "</B><P><P>");
	
	toClient.println( "</body>" );
	toClient.println("</html>");
	
	toClient.close();
    }

}
