package com.wowodc.model;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;

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
}
