public class MembershipManager(){
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public MembershipManager(Connection conn, ObjectLayer objectLayer){
	this.conn = conn;
	this.objectLayer = objectLayer;
    }

    //TODO throws Exception                                                                                                                             
    public void save(Membership membership){
	
    }

    public Iterator<Person> restore(Membership membership){

    }

    public void delete(Membership membership){
         
    }


}

