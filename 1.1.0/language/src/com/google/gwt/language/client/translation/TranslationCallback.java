/*
 * Copyright 2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.language.client.translation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

/**
 * Callback for translate api. The onCallback() method must be overridden by
 * clients.
 */
public abstract class TranslationCallback {

  /**
   * This wraps onCallback method and provides a framework for catching
   * exceptions in callbacks.
   * 
   * @param result the result of a translation request.
   */
  public final void onCallbackWrapper(TranslationResult result) {
    UncaughtExceptionHandler exceptionHandler = GWT.getUncaughtExceptionHandler();
    if (exceptionHandler != null) {
      try {
        onCallback(result);
      } catch (Exception e) {
        exceptionHandler.onUncaughtException(e);
      }
    } else {
      onCallback(result);
    }
  }

  /**
   * This must be overridden by the client to deal with asynchronous feedback
   * containing translation results.
   * 
   * @param result the result of a translation request.
   */
  protected abstract void onCallback(TranslationResult result);
}
