package com.example.io_performance;

import android.text.format.Formatter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

  public static final File dir = new File(MainActivity.context.getFilesDir(), "file-rw-speed-test");
  private static List<FileInfo> fileInfos = new ArrayList<>();

  static {
    boolean b = dir.mkdirs();
    if (dir.exists() == false || dir.isFile()) {
      throw new RuntimeException("创建文件夹失败 " + dir);
    }

    int minSize, maxSize, countList[];
    FileInfo info;

    //0~1024B 10,100,300,800,1500,2000个 max=1MB
    minSize = 0;
    maxSize = 1024;
    countList = new int[]{10, 100, 300, 800, 1500, 2000};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //1KB~4KB 10,100,300,800,1500,2000个 max=5MB
    minSize = 1024;
    maxSize = 4 * 1024;
    countList = new int[]{10, 100, 300, 800, 1500, 2000};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }


    //4KB~30KB 10,100,300,800,1500,2000个 max=34MB
    minSize = 4 * 1024;
    maxSize = 30 * 1024;
    countList = new int[]{10, 100, 300, 800, 1500, 2000};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //30KB~100KB 10,100,300,800,1500,2000个 max=130MB
    minSize = 30 * 1024;
    maxSize = 100 * 1024;
    countList = new int[]{10, 100, 300, 800, 1500, 2000};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //100KB~1024KB 10,100,300,750个 max=421MB
    minSize = 100 * 1024;
    maxSize = 1024 * 1024;
//    countList = new int[]{10, 100, 300, 750};
    countList = new int[]{300, 750};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //1MB~5MB  2,10,50,140 max=420MB
    minSize = 1 * 1024 * 1024;
    maxSize = 5 * 1024 * 1024;
    countList = new int[]{2, 10, 50, 140};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //5MB~20MB 2,10,20,33 max=412MB
    minSize = 5 * 1024 * 1024;
    maxSize = 20 * 1024 * 1024;
    countList = new int[]{2, 10, 20, 33};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //20MB~100MB 1,3,7 max=420MB
    minSize = 20 * 1024 * 1024;
    maxSize = 100 * 1024 * 1024;
    countList = new int[]{1, 3, 7};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //100MB~200MB 1,2,3 max=450MB
    minSize = 100 * 1024 * 1024;
    maxSize = 200 * 1024 * 1024;
    countList = new int[]{1, 2, 3};
    for (int count : countList) {
      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
        info = new FileInfo(minSize, maxSize, count, type);
        fileInfos.add(info);
      }
    }
    //200MB~500MB 1,2 max=700MB
//    minSize = 200 * 1024 * 1024;
//    maxSize = 500 * 1024 * 1024;
//    countList = new int[]{1, 2};
//    for (int count : countList) {
//      fileInfos.add(new FileInfo(minSize, maxSize, count, FileInfo.TYPE_GRN_FILE));
//      for (int type = FileInfo.TYPE_JAVA_BUFF_READ; type <= FileInfo.TYPE_C_MMAP; type++) {
//        info = new FileInfo(minSize, maxSize, count, type);
//        fileInfos.add(info);
//      }
//    }

  }

  public static void gen() {

    int curIndex = getCurIndex();
    if (curIndex >= fileInfos.size()) {
      System.out.println("全部读写完毕");
      return;
    }
    FileInfo info = fileInfos.get(curIndex);
    System.out.println("读取中。。。index=" + curIndex + ":" + info);
    if (info.getType() == FileInfo.TYPE_GRN_FILE) {
      reGenFile(info, curIndex);
      ReBoot.run();
    }
    File[] files = dir.listFiles();
    long min = Integer.MAX_VALUE;
    long max = Integer.MIN_VALUE;
    long totalSize = 0;
    for (File file : files) {
      long len = file.length();
      if (len < min) {
        min = len;
      }
      if (len > max) {
        max = len;
      }
      totalSize += len;
    }

    File record = new File(MainActivity.context.getFilesDir(), "file-rw-speed-test-record");
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(record, true));
      writer.write("------------------------------------------------------------------------");
      writer.newLine();
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    String minSize = Formatter.formatFileSize(MainActivity.context, min).replace(" ", "");
    String maxSize = Formatter.formatFileSize(MainActivity.context, max).replace(" ", "");
    if (info.getType() == FileInfo.TYPE_JAVA_BUFF_READ) {
      for (int i = 0; i < 10; i++) {
        System.out.println("java read准备读取：" + info);
        javaBuffRead(minSize, maxSize, files.length, totalSize, files);
      }
    } else if (info.getType() == FileInfo.TYPE_JAVA_MMAP) {
      for (int i = 0; i < 10; i++) {
        System.out.println("java mmap准备读取：" + info);
        javaMmapRead(minSize, maxSize, files.length, totalSize, files);
      }
    } else if (info.getType() == FileInfo.TYPE_C_READ) {
      for (int i = 0; i < 10; i++) {
        System.out.println("c++ read准备读取：" + info);
        cRead(dir.getAbsolutePath(), record.getAbsolutePath(), minSize, maxSize, files.length, totalSize);
      }
    } else if (info.getType() == FileInfo.TYPE_C_MMAP) {
      for (int i = 0; i < 10; i++) {
        System.out.println("c++ mmap准备读取：" + info);
        cMmap(dir.getAbsolutePath(), record.getAbsolutePath(), minSize, maxSize, files.length, totalSize);
      }
    }
    ReBoot.run();
  }

  private static void reGenFile(FileInfo info, int curIndex) {
    File[] children = dir.listFiles();
    if (children != null) {
      for (File file : children) {
        boolean b = file.delete();
        if (b == false) {
          throw new RuntimeException("删除失败：" + file.getAbsolutePath());
        }
      }
    }
    int minSize = info.getMinSize();
    int maxSize = info.getMaxSize();
    System.out.println("准备生成文件。。。size:" + Formatter.formatFileSize(MainActivity.context, minSize) + " - " + Formatter.formatFileSize(MainActivity.context, maxSize) + ", count:" + info.getCount());
    for (int i = 0, count = info.getCount(); i < count; i++) {
      int fileSize = (int) (Math.random() * (maxSize - minSize) + minSize);
      File file = new File(dir, Formatter.formatFileSize(MainActivity.context, fileSize).replace(" ", "") + "-" + i);
      try {
        long s = System.nanoTime();
        boolean b = file.createNewFile();
        if (b == false) {
          throw new RuntimeException("创建文件失败：" + file);
        }
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        for (int j = 0; j < fileSize; j++) {
          outputStream.write(j);
        }
        outputStream.close();
        long e = System.nanoTime();
        System.out.println("写入完成 " + file.getName() + " " + (e - s) / 1000000f + "ms");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    if (info.getCount() != dir.listFiles().length) {
      throw new RuntimeException("生成文件错误：" + info);
    }
    System.out.println("生成文件结束，文件数量：" + dir.listFiles().length + ", 总大小：" + getTotalSize(dir));

    File record = new File(MainActivity.context.getFilesDir(), "file-rw-speed-test-record");
    BufferedWriter writer;
    try {
      writer = new BufferedWriter(new FileWriter(record, true));
      writer.newLine();
      writer.newLine();
      writer.newLine();
      writer.write(curIndex / 5 + ".生成新文件    数量：" + dir.listFiles().length + ", 总大小：" + getTotalSize(dir));
      writer.newLine();
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private static String getTotalSize(File dir) {
    long size = 0;
    for (File file : dir.listFiles()) {
      size += file.length();
    }
    return Formatter.formatFileSize(MainActivity.context, size).replace(" ", "");


  }

  private static int getCurIndex() {
    int curIndex = 0;
    final File index = new File(MainActivity.context.getFilesDir(), "file-rw-speed-test-index");
    if (index.isDirectory()) {
      throw new RuntimeException("存在同名目录：" + index.getAbsolutePath());
    }
    if (index.exists() == false) {
      try {
        DataOutputStream stream = new DataOutputStream(new FileOutputStream(index));
        stream.writeInt(0);
        stream.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      DataInputStream input = new DataInputStream(new FileInputStream(index));
      curIndex = input.readInt();
      input.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      DataOutputStream stream = new DataOutputStream(new FileOutputStream(index));
      stream.writeInt(curIndex + 1);
      stream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return curIndex;
  }

  public static void javaBuffRead(String min, String max, int fileCount, long totalSize, File[] files) {

    long start = System.nanoTime();
    byte[] bytes = new byte[8 * 1024];
    for (File file : files) {
      try {
//        long s = System.nanoTime();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        long len = 0;
        long tmp = 0;
        while (true) {
          int count = inputStream.read(bytes);
          if (count == -1) {
            break;
          }
//          for (int i = 0; i < count; i++) {
//            if (bytes[i] != (byte) tmp) {
//              throw new RuntimeException(dir.getAbsolutePath() + " io读取失败,字符" + len + i);
//            }
//            tmp++;
//          }

          len += count;
        }
        inputStream.close();
//        if (len != file.length()) {
//          throw new RuntimeException(dir.getAbsolutePath() + " io读取失败");
//        }
//        long e = System.nanoTime();
//        System.out.println("IO读取完成 " + file.getName() + " " + (e - s) / 1000000f + "ms");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    long end = System.nanoTime();
//    System.out.println("java IO读取结束 " + (end - start) / 1000000f + "ms");
    File record = new File(MainActivity.context.getFilesDir(), "file-rw-speed-test-record");
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(record, true));
      String result = "JavaBuffRead:  文件数量:" + fileCount + ", 总大小:" + totalSize + ", 文件大小:" + min + " ~ " + max + ", 时间:" + (end - start) / 1000000f + "ms";
      writer.write(result);
      writer.newLine();
      writer.close();
      System.out.println(result);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public static void javaMmapRead(String min, String max, int fileCount, long totalSize, File[] files) {

    long start = System.nanoTime();
    byte[] bytes = new byte[8 * 1024];
    for (File file : files) {
      try {
//        long s = System.nanoTime();
        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
        ByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        long len = 0;
        long tmp = 0;
        while (true) {
          int remain = buffer.remaining();
          if (remain <= 0) {
            break;
          }
          int count = remain < bytes.length ? remain : bytes.length;
          buffer.get(bytes, 0, count);
//          for (int i = 0; i < count; i++) {
//            if (bytes[i] != (byte) tmp) {
//              throw new RuntimeException(dir.getAbsolutePath() + " mmap读取失败,字符" + len + i);
//            }
//            tmp++;
//          }
          len += count;
        }
        fileChannel.close();
        release(buffer);
//        if (len != file.length()) {
//          throw new RuntimeException(dir.getAbsolutePath() + " mmap读取失败");
//        }
//
//        long e = System.nanoTime();
//        System.out.println("mmap读取完成 " + file.getName() + " " + (e - s) / 1000000f + "ms");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    long end = System.nanoTime();
//    System.out.println("java mmap读取结束 " + (end - start) / 1000000f + "ms");
    File record = new File(MainActivity.context.getFilesDir(), "file-rw-speed-test-record");
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(record, true));
      String result = "JavaMMap:      文件数量:" + fileCount + ", 总大小:" + totalSize + ", 文件大小:" + min + " ~ " + max + ", 时间:" + (end - start) / 1000000f + "ms";
      writer.write(result);
      writer.newLine();
      writer.close();
      System.out.println(result);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void release(ByteBuffer buffer) {

  }

  public static native void cRead(String dir, String record, String min, String max, int fileCount, long totalSize);

  public static native void cMmap(String dir, String record, String min, String max, int fileCount, long totalSize);

  public static void deleteAll() {
    delete(dir);
    delete(new File(MainActivity.context.getFilesDir(), "file-rw-speed-test-record"));
    delete(new File(MainActivity.context.getFilesDir(), "file-rw-speed-test-index"));
    boolean b = dir.mkdirs();
  }

  private static void delete(File file) {
    if (file.exists() == false) {
      return;
    }
    if (file.isFile()) {
      if (file.delete() == false) {
        throw new RuntimeException("删除失败:" + file.getAbsolutePath());
      }
    } else {
      File[] children = file.listFiles();
      if (children != null) {
        for (File child : children) {
          delete(child);
        }
      }

      if (file.delete() == false) {
        throw new RuntimeException("删除失败:" + file.getAbsolutePath());
      }
    }
  }
}