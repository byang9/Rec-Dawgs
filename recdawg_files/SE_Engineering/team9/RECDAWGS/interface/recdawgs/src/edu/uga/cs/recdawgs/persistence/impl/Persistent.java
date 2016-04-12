package edu.uga.cs.recdawgs.persistence.impl;

import edu.uga.cs.recdawgs.persistence.Persistable;

'''This  class   implements  the Persistable interface from the 
edu.uga.cs.recdawgs.persistence package. This class will provide a 
common root class for all entity class implementations.'''

public abstract class Persistent implements Persistable{
    private long id;

    public Persistenct(){
	this.id = -1;
    }

    public Persistent(long id)
    {
	this.id = id;
    }

    public long getId()
    {
	return id;
    }

    public void setId(long id)
    {
	this.id = id;
    }
    public boolean isPersistent()
    {
	return id >= 0;
    }


}