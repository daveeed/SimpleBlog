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
    ERXMigrationTable blogEntryTable = database.existingTableNamed("BlogEntry");
    blogEntryTable.newTimestampColumn("lastModifed", false);

    ERXMigrationTable blogCategoryEntryTable = database.newTableNamed("BlogCategoryEntry");
    blogCategoryEntryTable.newIntegerColumn("blogCategoryId", false);
    blogCategoryEntryTable.newIntegerColumn("blogEntryId", false);
    blogCategoryEntryTable.create();
    blogCategoryEntryTable.setPrimaryKey("blogCategoryId", "blogEntryId");

    ERXMigrationTable blogCategoryTable = database.newTableNamed("BlogCategory");
    blogCategoryTable.newIntegerColumn("id", false);
    blogCategoryTable.newStringColumn("name", 100, false);
    blogCategoryTable.newStringColumn("shortName", 50, false);
    blogCategoryTable.create();
    blogCategoryTable.setPrimaryKey("id");

    blogCategoryEntryTable.addForeignKey("blogCategoryId", "BlogCategory", "id");
    blogCategoryEntryTable.addForeignKey("blogEntryId", "BlogEntry", "id");
  }
}