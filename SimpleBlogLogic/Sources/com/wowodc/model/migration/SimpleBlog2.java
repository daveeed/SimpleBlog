package com.wowodc.model.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class SimpleBlog2 extends ERXMigrationDatabase.Migration {
  @Override
  public NSArray<ERXModelVersion> modelDependencies() {
    return null;
  }
  
  @Override
  public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
    // DO NOTHING
  }

  @Override
  public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
    ERXMigrationTable blogCommentTable = database.newTableNamed("BlogComment");
    blogCommentTable.newIntegerColumn("blogEntryID", false);
    blogCommentTable.newStringColumn("commentText", 10000000, true);
    blogCommentTable.newIntegerColumn("id", false);
    blogCommentTable.newTimestampColumn("lastModifed", true);
    blogCommentTable.newIntegerColumn("personID", false);
    blogCommentTable.newIntegerColumn("rating", true);
    blogCommentTable.newTimestampColumn("timestampCreation", true);
    blogCommentTable.create();
    blogCommentTable.setPrimaryKey("id");

    blogCommentTable.addForeignKey("blogEntryID", "BlogEntry", "id");
    blogCommentTable.addForeignKey("personID", "Person", "id");
  }

}