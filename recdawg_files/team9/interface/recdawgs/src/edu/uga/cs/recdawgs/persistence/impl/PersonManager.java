public class PersonManager(){
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public PersonManager(Connection conn, ObjectLayer objectLayer){
	this.conn = conn;
	this.objectLayer = objectLayer;
    }

    //TODO throws Exception
    public void save(Person person){
	
    }

    public Iterator<Person> restore(Person person){

    }

    public void delete(Person person){

    }


}