/**
 * Copyright (c) 2008-2024 Bird Dog Games, Inc.
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <https://git.io/fjRmv>.
 */

/**
 *
 */

package com.ardor3d.scenegraph.hint;

public enum PickingHint {
  /**
   * Scene object can be included in results from pick operations.
   */
  Pickable,

  /**
   * Scene object can be included in results from collision checks.
   */
  Collidable
}
