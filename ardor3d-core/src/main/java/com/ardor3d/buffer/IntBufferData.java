/**
 * Copyright (c) 2008-2024 Bird Dog Games, Inc.
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <https://git.io/fjRmv>.
 */

package com.ardor3d.buffer;

import java.io.IOException;
import java.nio.IntBuffer;

import com.ardor3d.util.export.InputCapsule;
import com.ardor3d.util.export.OutputCapsule;
import com.ardor3d.util.export.Savable;

/**
 * Simple data class storing a buffer of ints
 */
public class IntBufferData extends IndexBufferData<IntBuffer> implements Savable {

  /**
   * Instantiates a new IntBufferData.
   */
  public IntBufferData() {}

  /**
   * Instantiates a new IntBufferData with a buffer of the given size.
   */
  public IntBufferData(final int size) {
    this(BufferUtils.createIntBuffer(size));
  }

  /**
   * Creates a new IntBufferData.
   *
   * @param buffer
   *          Buffer holding the data. Must not be null.
   */
  public IntBufferData(final IntBuffer buffer) {
    if (buffer == null) {
      throw new IllegalArgumentException("Buffer can not be null!");
    }

    _buffer = buffer;
  }

  @Override
  public Class<? extends IntBufferData> getClassTag() { return getClass(); }

  @Override
  public void read(final InputCapsule capsule) throws IOException {
    super.read(capsule);
    _buffer = capsule.readIntBuffer("buffer", null);
  }

  @Override
  public void write(final OutputCapsule capsule) throws IOException {
    super.write(capsule);
    capsule.write(_buffer, "buffer", null);
  }

  @Override
  public int get() {
    return _buffer.get();
  }

  @Override
  public int get(final int index) {
    return _buffer.get(index);
  }

  @Override
  public IntBufferData put(final int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Invalid value passed to int buffer: " + value);
    }
    _buffer.put(value);
    return this;
  }

  @Override
  public IntBufferData put(final int index, final int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Invalid value passed to int buffer: " + value);
    }
    _buffer.put(index, value);
    return this;
  }

  @Override
  public IntBufferData put(final IndexBufferData<?> buf) {
    if (buf instanceof IntBufferData) {
      _buffer.put((IntBuffer) buf.getBuffer());
    } else {
      while (buf.getBuffer().hasRemaining()) {
        put(buf.get());
      }
    }
    return this;
  }

  @Override
  public IntBufferData put(final byte[] array) {
    for (int i = 0; i < array.length; i++) {
      put(array[i]);
    }
    return this;
  }

  @Override
  public IntBufferData put(final byte[] array, final int offset, final int length) {
    for (int i = offset, max = offset + length; i < max; i++) {
      put(array[i]);
    }
    return this;
  }

  @Override
  public IntBufferData put(final short[] array) {
    for (int i = 0; i < array.length; i++) {
      put(array[i]);
    }
    return this;
  }

  @Override
  public IntBufferData put(final short[] array, final int offset, final int length) {
    for (int i = offset, max = offset + length; i < max; i++) {
      put(array[i]);
    }
    return this;
  }

  @Override
  public IntBufferData put(final int[] array) {
    _buffer.put(array);
    return this;
  }

  @Override
  public IntBufferData put(final int[] array, final int offset, final int length) {
    _buffer.put(array, offset, length);
    return this;
  }

  @Override
  public IntBufferData put(final IntBuffer buffer) {
    while (buffer.hasRemaining()) {
      put(buffer.get());
    }
    return this;
  }

  @Override
  public int getByteCount() { return 4; }

  @Override
  public IntBuffer getBuffer() { return _buffer; }

  @Override
  public IntBuffer asIntBuffer() {
    final IntBuffer source = getBuffer().duplicate();
    source.rewind();
    final IntBuffer buff = BufferUtils.createIntBufferOnHeap(source.limit());
    buff.put(source);
    buff.flip();
    return buff;
  }

  @Override
  public IntBufferData makeCopy() {
    final IntBufferData copy = new IntBufferData();
    copy._buffer = BufferUtils.clone(_buffer);
    copy._vboAccessMode = _vboAccessMode;
    return copy;
  }
}
