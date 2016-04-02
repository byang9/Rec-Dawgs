// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:	ClubImpl
//
// K.J. Kochut
//
//
//

package edu.uga.clubs.entity.impl;


import java.util.Date;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Person;
import edu.uga.clubs.persistence.impl.Persistent;


public class ClubImpl
    extends Persistent
    implements Club
{
    // Club attributes
    private  String  name;  
    private  String  address;
    private  Date    establishedOn;
    private  Person  founder;

    // when creating a brand new Club object, the founder must already be persistent
    //
    public ClubImpl( String name, String address, Date established, Person founder ) 
        //throws ClubsException
    {
        super( -1 );
        /*
        if( founder == null )
            throw new ClubsException( "The club's founder is null" );
        if( !founder.isPersistent() )
            throw new ClubsException( "The club's founder is not persistent" );
            */
        this.name = name;
        this.address = address;
        this.establishedOn = established;
        this.founder = founder;
    }
    
    // when creating a proxy or a model for a persistent Club object
    //
    /*
    public ClubImpl( String name, String address, Date established, long founderId ) 
    {
        super( -1 );
        this.name = name;
        this.address = address;
        this.establishedOn = established;
        this.founder = founderId;
    }
    */
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    public Date getEstablishedOn()
    {
        return establishedOn;
    }

    public void setEstablishedOn( Date establishedOn )
    {
        this.establishedOn = establishedOn;
    }

    public Person getFounder()
    {
        return founder;
    }

    public void setFounder( Person founder )
            throws ClubsException
    {
        /*
        if( founder == null )
            throw new ClubsException( "The founder is null" );
        if( !founder.isPersistent() )
            throw new ClubsException( "The founder is not persistent" );
        */
        this.founder = founder;
    }
    
    public String toString()
    {
        return "Club[" + getId() + "] " + getName() + " " + getAddress() + " " + getEstablishedOn();
    }
    
    public boolean equals( Object otherClub )
    {
        if( otherClub == null )
            return false;
        if( otherClub instanceof Club ) // name is a unique attribute
            return getName().equals( ((Club)otherClub).getName() );
        return false;        
    }

};
