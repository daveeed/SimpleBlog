package com.wowodc.model;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

public class BlogComment extends com.wowodc.model.generated._BlogComment {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BlogComment.class);
	
  public void init(EOEditingContext ec) {
    super.init(ec);
    setTimestampCreation(new NSTimestamp());
    setPersonRelationship(Person.currentUser(ec));
    setLastModifed(new NSTimestamp());
  }
}
