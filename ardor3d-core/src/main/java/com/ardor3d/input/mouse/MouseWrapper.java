/**
 * Copyright (c) 2008-2024 Bird Dog Games, Inc.
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <https://git.io/fjRmv>.
 */

package com.ardor3d.input.mouse;

import com.ardor3d.util.PeekingIterator;

/**
 * Defines the API for mouse wrappers.
 */
public interface MouseWrapper {
  /**
   * Allows the mouse wrapper implementation to initialize itself.
   */
  void init();

  /**
   * Returns a peeking iterator that allows the client to loop through all mouse events that have not
   * yet been handled.
   *
   * @return an iterator that allows the client to check which events have still not been handled
   */
  PeekingIterator<MouseState> getMouseEvents();

  void setIgnoreInput(boolean ignore);

  boolean isIgnoreInput();
}
