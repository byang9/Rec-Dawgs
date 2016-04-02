// Gnu Emacs C++ mode:  -*- Java -*-
//
// Interface:	Persistable
//
// K.J. Kochut
//
//
//

package edu.uga.cs.recdawgs.persistence;


/**
 * This interface is the root interface to be implemented by all entity classes.  It has methods to read/write 
 * the persistent identifier of an entity object. Typically, it is a database key of the 
 * row, representing the entity object. Initially, when a new entity object is created, the identifier is set 
 * to -1 (which indicates that the entity object has not been stored in the persistent data store, yet).  However, 
 * once the entity object has been stored in the persistent data store (using a suitable store operation), this 
 * value is set to the actual identifier (persistent store key).
 *
 */
public interface Persistable {
    
    /**
     * Return the persistent identifier of this entity object instance. The value of -1 indicates that 
     * the object has not been stored in the persistent data store, yet. 
     * 
     * @return persistent identifier of an entity object instance
     */
    public long getId();

    /**
     * Set the persistent identifier for this entity object.  This method is typically used by the persistence
     * subsystem when creating a proxy object for an entity object already residing in the persistent data store.
     * 
     * @param id the persistent object key
     */
    public void setId( long id );
  
    /**
     * Check if this entity object has been stored in the the persistent data store (for the first time).
     * Note that the value is isPersistent() may be true, even though the entity object may need to be saved 
     * in the persistent data store again, after an update to its state.
     * 
     * @return true if this entity object has already been stored in the persistent data store, false otherwise.
     */
    public boolean isPersistent();

};
