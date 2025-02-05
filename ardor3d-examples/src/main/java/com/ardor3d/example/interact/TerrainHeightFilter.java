/**
 * Copyright (c) 2008-2024 Bird Dog Games, Inc.
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <https://git.io/fjRmv>.
 */

package com.ardor3d.example.interact;

import com.ardor3d.extension.interact.InteractManager;
import com.ardor3d.extension.interact.data.SpatialState;
import com.ardor3d.extension.interact.filter.UpdateFilterAdapter;
import com.ardor3d.extension.interact.widget.AbstractInteractWidget;
import com.ardor3d.extension.terrain.client.Terrain;
import com.ardor3d.math.type.ReadOnlyVector3;

public class TerrainHeightFilter extends UpdateFilterAdapter {

  protected Terrain _terrain;
  protected double _offsetHeight;

  public TerrainHeightFilter(final Terrain sourceTerrain, final double offsetHeight) {
    _terrain = sourceTerrain;
    _offsetHeight = offsetHeight;
  }

  @Override
  public void applyFilter(final InteractManager manager, final AbstractInteractWidget widget) {
    final SpatialState state = manager.getSpatialState();
    final ReadOnlyVector3 trans = state.getTransform().getTranslation();
    final double height = _terrain.getHeightAt(trans.getX(), trans.getZ());
    if (height != trans.getY()) {
      state.getTransform().translate(0, _offsetHeight + height - trans.getY(), 0);
    }
  }
}
