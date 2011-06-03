package com.wowodc.app;

import er.extensions.foundation.ERXStringUtilities;

public class S {
  public S() {
  }

  public static boolean isEmpty(String s) {
    return ERXStringUtilities.stringIsNullOrEmpty(s);
  }

  public static boolean notEmpty(String s) {
    return !ERXStringUtilities.stringIsNullOrEmpty(s);
  }
}