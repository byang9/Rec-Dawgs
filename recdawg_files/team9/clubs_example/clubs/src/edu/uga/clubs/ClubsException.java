package edu.uga.clubs;

public class ClubsException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ClubsException( String msg )
    {
      super( msg );
    }

    public ClubsException( Throwable cause )
    {
      super( cause );
    }
}
