package com.example.io_performance;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  public static Context context;

  static {
    System.loadLibrary("native-lib");
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    context = getApplicationContext();
    setContentView(R.layout.activity_main);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
          || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
      }
    }
    Button tv = findViewById(R.id.sample_text);

    try {
      Class.forName(FileUtils.class.getName());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        FileUtils.gen();
      }
    }, 60 * 1000);
    tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setTitle("重置所有数据？").setNegativeButton("否", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        }).setPositiveButton("是", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            FileUtils.deleteAll();
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
          }
        }).create();

        dialog.show();
      }
    });
  }


}
