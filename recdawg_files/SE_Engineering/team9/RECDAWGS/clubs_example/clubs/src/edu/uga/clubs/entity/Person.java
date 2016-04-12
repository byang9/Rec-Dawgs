// Gnu Emacs C++ mode:  -*- Java -*-
//
// Interface:	Person
//
// K.J. Kochut
//
//
//


package edu.uga.clubs.entity;

import edu.uga.clubs.persistence.Persistable;



// Interface to Person representing the Person class from the UML object model
//
public interface Person
    extends Persistable
{  
    public String         getUserName();
    public String         getPassword();
    public String         getEmail();
    public String         getFirstName();
    public String	  getLastName();
    public String	  getAddress();
    public String	  getPhone();
  
    public void           setUserName( String userName );
    public void           setPassword( String password );
    public void           setEmail( String password );
    public void	          setFirstName( String firstName );
    public void	          setLastName( String lastName );
    public void	          setAddress( String address );
    public void	          setPhone( String phone );
};
