package com.wowodc.ui.components;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEnterpriseObject;

public class DHRating extends WOComponent{
  
    public DHRating(WOContext context) {
        super(context);
    }
    
    public EOEnterpriseObject object() {
      return (EOEnterpriseObject) valueForBinding("object");
    }

    public String key() {
      return (String) valueForBinding("key");
    }
    
    public Integer value() {
      Integer value = null;
      if (object() != null && key() != null)
        value = (Integer) object().valueForKeyPath(key());
      return value;
    }
    
    public void setValue(Integer newValue) {
      if (object() != null && key() != null)
      object().takeValueForKeyPath(newValue, key());
      }

    public boolean isStateless() { return true; }
}