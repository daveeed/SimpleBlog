package com.wowodc.model;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSLog;

import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.foundation.ERXThreadStorage;

public class Person extends com.wowodc.model.generated._Person {
  @SuppressWarnings("unused")
  private static Logger log = Logger.getLogger(Person.class);

  /**
   * Gets the current user.
   * 
   * @return current user for the thread
   */
  public static Person currentUser() {
    return (Person) ERXThreadStorage.valueForKey("user");
  }

  /**
   * Gets the user as a local instance in the given context.
   * 
   * @param ec
   *          editing context to pull a local copy of the user into
   * @return user instance in the given editing context
   */
  public static Person currentUser(EOEditingContext ec) {
    Person currentUser = currentUser();
    if (currentUser != null && currentUser.editingContext() != ec) {
      EOEditingContext currentUserEc = currentUser.editingContext();
      currentUserEc.lock();
      try {
        Person localUser = (Person) ERXEOControlUtilities.localInstanceOfObject(ec, currentUser);
        currentUser = localUser;
      }
      finally {
        currentUserEc.unlock();
      }
    }
    return currentUser;
  }

  public String fullName() {
    return this.firstName() + " " + this.lastName();
  }
  
  public static Person validateLogin(EOEditingContext editingContext, String username, String password) {
    EOQualifier qual = Person.LOGIN.eq(username).and(Person.PASSWORD.eq(password));
    Person user = Person.fetchRequiredPerson(editingContext, qual);
    NSLog.out.appendln("The user is " + user);
    return user;
  }
  
  public void init(EOEditingContext ec) {
    super.init(ec);
    this.setLastName("New Person");
    // Default Role
    Role aRole = Role.fetchRole(ec,"roleDescription","Normal");
    this.addObjectToBothSidesOfRelationshipWithKey(aRole, "roles");  
  }
 
  public boolean isAdmin() {
    Role aRole = Role.fetchRole(this.editingContext(),"roleDescription","Admin");
    return this.roles().contains(aRole);   
  }
}
