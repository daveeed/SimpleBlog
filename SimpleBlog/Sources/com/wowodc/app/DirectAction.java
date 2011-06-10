package com.wowodc.app;

import java.util.NoSuchElementException;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WORequest;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSLog;
import com.wowodc.model.Person;
import com.wowodc.ui.components.Main;

import er.directtoweb.ERD2WDirectAction;
import er.extensions.eof.ERXEC;

public class DirectAction extends ERD2WDirectAction {
  public DirectAction(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults defaultAction() {
    return pageWithName(Main.class.getName());
  }

  /**
   * Checks if a page configuration is allowed to render.
   * Provide a more intelligent access scheme as the default just returns false.
   * And
   * be sure to read the javadoc to the super class.
   * 
   * @param pageConfiguration
   * @return
   */
  protected boolean allowPageConfiguration(String pageConfiguration) {
    return false;
  }

  public WOActionResults loginAction() {
    WOComponent nextPage = null;
    String username = request().stringFormValueForKey("username");
    String password = request().stringFormValueForKey("password");

    NSLog.out.appendln("***DirectAction.loginAction - username: " + username + " : password: " + password + "***");
    String errorMessage = null;
    if (S.isEmpty(username) || S.isEmpty(password)) {
      errorMessage = "Please enter a username and password.";
    }
    else {
      try {
        Person user = Person.validateLogin(ERXEC.newEditingContext(), username, password);
        ((Session) session()).setUser(user);
        nextPage = ((Session) session()).navController().listBlogAction();
      }
      catch (NoSuchElementException e) {
        errorMessage = "No user found for that combination of username and password.";
      }
      catch (Exception e) {
        // TODO: handle exception
      }
    }
    if (S.notEmpty(errorMessage)) {
      nextPage = pageWithName(Main.class.getName());
      nextPage.takeValueForKey(errorMessage, "errorMessage");
      nextPage.takeValueForKey(username, "username");
      nextPage.takeValueForKey(password, "password");
    }
    // return the session user
    NSLog.out.appendln("The user is " + ((Session) session())._user);
    return nextPage;
  }

}
