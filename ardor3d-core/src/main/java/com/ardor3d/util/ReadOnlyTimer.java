/**
/ * Copyright (c) 2008-2024 Bird Dog Games, Inc.
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <https://git.io/fjRmv>.
 */

package com.ardor3d.util;

/**
 * <code>ReadOnlyTimer</code> is the base interface for all Ardor3D timer implementations. Used
 * throughout Ardor3D for framerate and time dependent calculations.
 */
public interface ReadOnlyTimer {
  /**
   * Get elapsed time in seconds since this timer was created or reset.
   *
   * @see #getTime()
   *
   * @return Time in seconds
   */
  double getTimeInSeconds();

  /**
   * Get elapsed time since this timer was created or reset, in the resolution specified by the
   * implementation (usually in nanoseconds).
   *
   * @see #getResolution()
   * @see #getTimeInSeconds()
   *
   * @return Time in resolution specified by implementation
   */
  long getTime();

  /**
   * Get the resolution used by this timer. Nanosecond resolution would return 10^9
   *
   * @return Timer resolution
   */
  long getResolution();

  /**
   * Get the current number of frames per second (fps).
   *
   * @return Current frames per second (fps)
   */
  double getFrameRate();

  /**
   * @return Time between last two frames, in seconds.
   */
  double getTimePerFrame();

  /**
   * @return Time, in resolution specified by implementation, when last time per frame and frame rate
   *         was updated.
   */
  long getPreviousFrameTime();

}
