This is the main directory of a small example system for
maintaining Clubs, Persons, and Club memberships.

The directory structure is as follows:

  src		contains all sources of the system; 
                all sources are in a few sub-packages of edu.uga.clubs
                and include:

		edu/uga/clubs/session          includes the session management package
		edu/uga/clubs/presentation     includes the presentation layer 
                                                 subsystem (boundaries, i.e., servlets)
		edu/uga/clubs/logic            includes the logic layer subsystem code
		edu/uga/clubs/object           includes the object model layer subsystem
		edu/uga/clubs/entity           includes the entity classes (data model)
		edu/uga/clubs/persistence      includes the persistence layer subsystem code
		edu/uga/clubs/test             includes a few test programs

                edu/uga/clubs/ClubsException.java 
                                               is the main exception class

  db		database schema

  lib		a few necessary jar files (libraries)

  WebContent	directory for the assembly of the Web Application

  build.xml     Ant build script for the system

The system utilizes an Ant build script (build.xml) to build and install this
application.  In order to be able to compile the whole system, examine 
the macro definitions at the top of the build.xml file and update them
as needed.


IMPORTANT: before you can compile and install this example on your
own system, you will have to:

0. Install the Java JDK, the WildFly AppServer, MySQL database, and
   Ant;  if you'd like to experiment on uml, you do not need to
   install these system
1. Create a database and install the schema using the SQL script in
   the db directory. 
2. If needed, modify build.xml to update the macro definitions; also, make
   sure you set the war file name which is different than clubs.war
   and adheres to the naming conventions described on our Web site, 
   if you plan to deply on uml.cs.uga.edu.
3. If needed, modify the HTML forms and templates (with suffix .ftl).
   These files are in in the directory WebContent and in 
   WebContent/WEB-INF/templates.  
4. Update the file src/edu/uga/clubs/persistence/impl/DbAccessConfig.java
   to set the name of your database, database user, and the password, as
   selected in 1. above.


In order to compile and install the system, type:

1. ant build
2. ant deploy
