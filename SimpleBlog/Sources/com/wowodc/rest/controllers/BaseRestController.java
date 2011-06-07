package com.wowodc.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXDefaultRouteController;

public class BaseRestController extends ERXDefaultRouteController {

  public BaseRestController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults newAction() throws Throwable {
    return errorResponse("", 405);
  }

  @Override
  public WOActionResults updateAction() throws Throwable {
    return errorResponse("", 405);
  }

  @Override
  public WOActionResults destroyAction() throws Throwable {
    return errorResponse("", 405);
  }

  @Override
  public WOActionResults showAction() throws Throwable {
    return errorResponse("", 405);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    return errorResponse("", 405);
  }

  @Override
  public WOActionResults indexAction() throws Throwable {
    return errorResponse("", 405);
  }

  @Override
  protected ERXRestFormat defaultFormat() {
    return ERXRestFormat.json();
  }
  
  @Override
  protected boolean isAutomaticHtmlRoutingEnabled() {
    return true;
  }
  
}
