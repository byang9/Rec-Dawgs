public class TeamManager(){
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;

    public PersonManager(Connection conn, ObjectLayer objectLayer){
	this.conn = conn;
	this.objectLayer = objectLayer;
    }

    //TODO throws Exception                                                                                                                             
    public void save(Team team){
	
    }

    public Iterator<Team> restore(Team team){

    }

    public void delete(Team team){

    }


}

