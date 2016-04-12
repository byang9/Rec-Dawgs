import java.util.Iterator;

public class PersonIterator implements Iterator <Person>{
    private ResultSet rs = null;
    private ObjectLayer objectLayer = null;
    private boolean more = false;

    public PersonIterator( ResultSet rs, ObjectLayer objectLayer){
	this.rs = rs;
	this.objectLayer = objectLayer;
	try{
	    this.more = rs.next();
	}
        catch( Exception e ) {  // just in case...                                                                                                      
            throw new RecDawgsException( "PersonIterator: Cannot create Person iterator; root cause: " + e );
        }

    }

    public boolean hasNext(){
	return more;
    }

    public Person next(){

    }

    public void remove(){
    
}


}