package com.wowodc.rest.controllers;

import java.io.IOException;
import java.util.NoSuchElementException;

import sun.misc.BASE64Decoder;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.wowodc.model.Person;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXRouteController;

public class BaseRestController extends ERXRouteController {
  
  public BaseRestController(WORequest request) {
    super(request);
  }

  public WOActionResults updateAction() throws Throwable {
    return errorResponse(ERXHttpStatusCodes.METHOD_NOT_ALLOWED);
  }

  public WOActionResults createAction() throws Throwable {
    return errorResponse(ERXHttpStatusCodes.METHOD_NOT_ALLOWED);
  }

  @Override
  protected ERXRestFormat defaultFormat() {
    return ERXRestFormat.json();
  }

  @Override
  protected boolean isAutomaticHtmlRoutingEnabled() {
    return true;
  }
  
  protected boolean isHTMlFormat() {
    return (ERXRestFormat.html().name().equals(this.format().name())) ? true: false;
  }

}
