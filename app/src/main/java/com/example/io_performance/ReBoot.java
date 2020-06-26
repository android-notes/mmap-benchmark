package com.example.io_performance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReBoot {
  public static void run() {

    try {
      final Process process = Runtime.getRuntime().exec("su -c \"/system/bin/reboot\"");
      read(process.getErrorStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void read(InputStream stream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    String line;

    while (((line = reader.readLine()) != null)) {
      System.out.println(line);
    }

  }
}
