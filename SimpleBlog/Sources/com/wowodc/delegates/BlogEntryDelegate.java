package com.wowodc.delegates;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.foundation.NSArray;
import com.wowodc.app.Factory;
import com.wowodc.model.BlogEntry;

/**
 * @author dholt
 *         If you've used the ERDControllerButton you may have noticed your
 *         branch choices tend to cascade down into your
 *         embedded page configurations. You can block this by using a rule
 *         like:
 * 
 *         100: pageConfiguration = 'ListEmbeddedMovie' => branchChoices = ()
 * 
 *         If you have lots of nested configurations in a page, that can be a
 *         bit of a nuisance. However, there is another way.
 *         You can prevent a branch choice from showing up unless it is
 *         explicitly named in the rules by beginning the method name
 *         with an underscore like:
 * 
 *         public WOComponent _save(WOComponent sender) { ...
 * 
 *         Now, in order to see the _save action's button, you need to name it
 *         explicitly in a rule like:
 * 
 *         100: pageConfiguration = 'EditRole' => branchChoices = ("_save")
 * 
 */
public class BlogEntryDelegate extends BranchDelegate {

  protected NSArray defaultBranchChoices(D2WContext context) {
    NSArray result = super.defaultBranchChoices(context);
    log.debug("in: " + result);
    BlogEntry aBlogEntry = (BlogEntry) object(context);
    // AK: this is just an illustration
    if (aBlogEntry != null) {
      result = choiceByRemovingKeys(new NSArray("delete"), result);
      result = choiceByRemovingKeys(new NSArray("view"), result);

      log.debug("out: " + result);
    }
    else {
      result = choiceByLeavingKeys(new NSArray(new Object[] { "create" }), result);
    }
    return result;
  }
  
  /**
   * Create new Blog Comment
   * 
   * @return
   */
  public WOComponent _comment(WOComponent sender) {
    // Get the objects we need
    BlogEntry aBlogEntry = (BlogEntry) object(sender);
    WOComponent nextPage = Factory.simpleBlog().newBlogComment(aBlogEntry);
    return nextPage;
  }
}