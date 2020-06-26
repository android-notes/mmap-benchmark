package com.example.io_performance;

import androidx.annotation.NonNull;

public class FileInfo implements Cloneable {
  public static final int TYPE_JAVA_BUFF_READ = 1;
  public static final int TYPE_JAVA_MMAP = 2;
  public static final int TYPE_C_READ = 3;
  public static final int TYPE_C_MMAP = 4;
  public static final int TYPE_GRN_FILE = 5;
  private int minSize;
  private int maxSize;

  private int count;
  private int type;

  private long totalSize;

  public FileInfo(int minSize, int maxSize, int count, int type) {
    this.minSize = minSize;
    this.maxSize = maxSize;
    this.count = count;
    this.type = type;
  }

  public int getMinSize() {
    return minSize;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public int getCount() {
    return count;
  }

  public int getType() {
    return type;
  }


  @NonNull
  @Override
  protected FileInfo clone() throws CloneNotSupportedException {
    return (FileInfo) super.clone();
  }

  @Override
  public String toString() {
    return "FileInfo{" +
        "minSize=" + minSize +
        ", maxSize=" + maxSize +
        ", count=" + count +
        ", type=" + type +
        ", totalSize=" + totalSize +
        '}';
  }
}
