package com.wowodc.model;

import org.apache.log4j.Logger;

import er.extensions.foundation.ERXStringUtilities;

public class BlogCategory extends com.wowodc.model.generated._BlogCategory {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BlogCategory.class);
	
	@Override
	public void willInsert() {
	  String shortName = ERXStringUtilities.toLowerCase(ERXStringUtilities.removeCharacters(categoryDescription(), " "));
	  setUrlFriendlyDescription(shortName);
	  super.willInsert();
	}
}
