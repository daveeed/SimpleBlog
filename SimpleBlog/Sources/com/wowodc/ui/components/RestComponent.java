package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.rest.routes.IERXRouteComponent;

public abstract class RestComponent extends ERXComponent implements IERXRouteComponent {

  private EOEditingContext editingContext;

  public RestComponent(WOContext context) {
    super(context);
  }

  public EOEditingContext editingContext() {
    if (editingContext == null) {
      editingContext = ERXEC.newEditingContext();
    }
    return editingContext;
  }

}
