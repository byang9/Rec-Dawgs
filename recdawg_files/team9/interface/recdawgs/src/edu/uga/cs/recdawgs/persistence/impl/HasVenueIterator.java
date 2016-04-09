import java.util.Iterator;

public class HasVenueIterator implements Iterator <HasVenue>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public HasVenueIterator(ResultSet rs, ObjectLayer objectLayer) {
        this.rs = rs;
        this.objectLayer = objectLayer;
        try{
            this.more = rs.next();
        }
        catch( Exception e ) {  // just in case...
            throw new RecDawgsException( "HasVenueIterator: Cannot create HasVenue iterator; root cause: " + e );
        }

    }

    public boolean hasNext(){
        return more;
    }

    public HasVenue next(){

    }

    public void remove(){

    }
}