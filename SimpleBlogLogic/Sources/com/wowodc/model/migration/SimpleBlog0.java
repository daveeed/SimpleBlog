package com.wowodc.model.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class SimpleBlog0 extends ERXMigrationDatabase.Migration {
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
    ERXMigrationTable xPersonRoleTable = database.newTableNamed("XPersonRole");
    xPersonRoleTable.newIntegerColumn("personId", false);
    xPersonRoleTable.newIntegerColumn("roleId", false);
    xPersonRoleTable.create();
    xPersonRoleTable.setPrimaryKey("roleId", "personId");

    ERXMigrationTable blogEntryTable = database.newTableNamed("BlogEntry");
    blogEntryTable.newStringColumn("body", 10000000, false);
    blogEntryTable.newIntegerColumn("id", false);
    blogEntryTable.newTimestampColumn("lastModifed", false);
    blogEntryTable.newIntegerColumn("personID", false);
    blogEntryTable.newTimestampColumn("timestampCreation", false);
    blogEntryTable.newStringColumn("title", 255, false);
    blogEntryTable.create();
    blogEntryTable.setPrimaryKey("id");

    ERXMigrationTable roleTable = database.newTableNamed("Role");
    roleTable.newIntegerColumn("id", false);
    roleTable.newStringColumn("roleDescription", 255, false);
    roleTable.create();
    roleTable.setPrimaryKey("id");

    ERXMigrationTable blogCategoryEntryTable = database.newTableNamed("BlogCategoryEntry");
    blogCategoryEntryTable.newIntegerColumn("blogCategoryId", false);
    blogCategoryEntryTable.newIntegerColumn("blogEntryId", false);
    blogCategoryEntryTable.create();
    blogCategoryEntryTable.setPrimaryKey("blogEntryId", "blogCategoryId");

    ERXMigrationTable blogCategoryTable = database.newTableNamed("BlogCategory");
    blogCategoryTable.newStringColumn("categoryDescription", 100, false);
    blogCategoryTable.newIntegerColumn("id", false);
    blogCategoryTable.newStringColumn("urlFriendlyDescription", 50, true);
    blogCategoryTable.create();
    blogCategoryTable.setPrimaryKey("id");

    ERXMigrationTable personTable = database.newTableNamed("Person");
    personTable.newStringColumn("email", 255, true);
    personTable.newStringColumn("firstName", 255, true);
    personTable.newIntegerColumn("id", false);
    personTable.newStringColumn("lastName", 255, true);
    personTable.newStringColumn("login", 255, true);
    personTable.newStringColumn("password", 255, true);
    personTable.create();
    personTable.setPrimaryKey("id");

    xPersonRoleTable.addForeignKey("personId", "Person", "id");
    xPersonRoleTable.addForeignKey("roleId", "Role", "id");
    blogEntryTable.addForeignKey("personID", "Person", "id");
    blogCategoryEntryTable.addForeignKey("blogCategoryId", "BlogCategory", "id");
    blogCategoryEntryTable.addForeignKey("blogEntryId", "BlogEntry", "id");
  }
}