package edu.uga.cs.recdawgs;


/**
 * This class represents a basic exception to be thrown and handled by the RecDawgs system.
 */
public class RDException 
    extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Create a new RDException object.
     * @param message the message explaining the exception
     */
    public RDException( String message )
    {
      super( message );
    }

    /**
     * Create a new RDException object.
     * @param cause the cause of the exception
     */
    public RDException( Throwable cause )
    {
      super( cause );
    }
}
