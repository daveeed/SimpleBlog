package com.wowodc.ui.components;

import com.webobjects.appserver.WOContext;
import com.wowodc.model.BlogEntry;
import com.wowodc.model.Person;

import er.rest.routes.ERXRouteParameter;

public class PersonShowPage extends RestComponent {
  
  private Person person;
  private BlogEntry entryItem;
  
  public PersonShowPage(WOContext context) {
    super(context);
  }
  
  @ERXRouteParameter
  public void setPerson(Person person) {
    this.person = person;
  }
  
  public Person person() {
    return person;
  }
  
  public void setEntryItem(BlogEntry entryItem) {
    this.entryItem = entryItem;
  }
  
  public BlogEntry entryItem() {
    return entryItem;
  }
}