import java.util.Iterator;

public class TeamIterator implements Iterator <Team>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public TeamIterator( ResultSet rs, ObjectLayer objectLayer){
        this.rs = rs;
        this.objectLayer = objectLayer;
        try{
            this.more = rs.next();
        }
        catch( Exception e ) {                   
             throw new RecDawgsException( "TeamIterator: Cannot create Team iterator; root cause: " + e );
        }

    }

    public boolean hasNext(){
        return more;
    }

    public Team next(){

    }

    public void remove(){

    }