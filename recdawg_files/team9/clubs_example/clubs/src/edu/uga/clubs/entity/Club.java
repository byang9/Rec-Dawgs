// Gnu Emacs C++ mode:  -*- Java -*-
//
// Interface:	Club
//
// K.J. Kochut
//
//
//

package edu.uga.clubs.entity;


import java.util.*;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.persistence.Persistable;



public interface Club
    extends Persistable
{  
    public String       getName();
    public String	getAddress();
    public Date		getEstablishedOn();
    public void		setName( String name );

    public void		setAddress( String address );
    public void		setEstablishedOn( Date established );
    public Person       getFounder();
    public void         setFounder( Person founder ) throws ClubsException;    
};
