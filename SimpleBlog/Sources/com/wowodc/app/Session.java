package com.wowodc.app;

import com.wowodc.model.Person;

import er.extensions.appserver.ERXSession;
import er.extensions.foundation.ERXThreadStorage;

public class Session extends ERXSession {
  private static final long serialVersionUID = 1L;

  private MainNavigationController _navController;

  public Session() {
  }

  public MainNavigationController navController() {
    if (_navController == null) {
      _navController = new MainNavigationController(this);
    }
    return _navController;
  }

  protected Person _user;

  public Person user() {
    return _user;
  }

  public void setUser(Person user) {
    _user = user;
    ERXThreadStorage.takeValueForKey(user(), "user");
  }

  public void awake() {
    super.awake();
    // if we have a user, keep track of them
    if (user() != null) {
      ERXThreadStorage.takeValueForKey(user(), "user");
    }
  }

  public boolean hasValidUser() {
    return user() != null;
  }

  public void sleep() {
    ERXThreadStorage.takeValueForKey(null, "user");
    super.sleep();
  }
}
