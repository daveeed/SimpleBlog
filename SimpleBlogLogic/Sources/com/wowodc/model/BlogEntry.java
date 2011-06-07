package com.wowodc.model;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

public class BlogEntry extends com.wowodc.model.generated._BlogEntry {
  @SuppressWarnings("unused")
  private static Logger log = Logger.getLogger(BlogEntry.class);

  public void init(EOEditingContext ec) {
    super.init(ec);
    setTimestampCreation(new NSTimestamp());
    setPersonRelationship(Person.currentUser(ec));
    setLastModifed(new NSTimestamp());
  }
 
}
