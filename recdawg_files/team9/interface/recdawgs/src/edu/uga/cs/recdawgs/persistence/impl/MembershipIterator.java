import java.util.Iterator;

public class MembershipIterator implements Iterator <Membership>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public MembershipIterator( ResultSet rs, ObjectLayer objectLayer){
        this.rs = rs;
        this.objectLayer = objectLayer;
        try{
            this.more = rs.next();
        }
        catch( Exception e ) {  // just in case...                                                                                                     \
                                                                                                                                                      
            throw new RecDawgsException( "MembershipIterator: Cannot create Membership iterator; root cause: " + e );
        }

    }

    public boolean hasNext(){
        return more;
    }

    public Membership next(){

    }

    public void remove(){

    }