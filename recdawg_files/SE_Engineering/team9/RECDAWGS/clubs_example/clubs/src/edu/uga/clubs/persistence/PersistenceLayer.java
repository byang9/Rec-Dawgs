// Gnu Emacs C++ mode:  -*- Java -*-
//
// Interface:	PersistenceModule.java
//
// K.J. Kochut
//
//
//

package edu.uga.clubs.persistence;


import java.util.Iterator;

import edu.uga.clubs.ClubsException;
import edu.uga.clubs.entity.Club;
import edu.uga.clubs.entity.Membership;
import edu.uga.clubs.entity.Person;

// This class is a version of a Data Access Layer
public interface PersistenceLayer {

  public void                    saveClub( Club club ) throws ClubsException;
  public Iterator<Club>          restoreClub( Club club ) throws ClubsException;
  public void		         deleteClub( Club c ) throws ClubsException;
  
  public void                    savePerson( Person person ) throws ClubsException;
  public Iterator<Person>        restorePerson( Person person ) throws ClubsException;
  public void                    deletePerson( Person person ) throws ClubsException;

  public void                    saveMembership( Membership membership ) throws ClubsException;
  public Iterator<Membership>    restoreMembership( Membership membership ) throws ClubsException;
  public void                    deleteMembership( Membership membership ) throws ClubsException;

  public void                    saveFounderFoundedClub( Club club, Person person ) throws ClubsException;
  public Person                  restoreFounderFoundedClub( Club club ) throws ClubsException;
  public Iterator<Club>          restoreFounderFoundedClub( Person person ) throws ClubsException;

};
