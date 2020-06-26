#include <jni.h>
#include <string>
#include <android/log.h>
#include <dirent.h>
#include <vector>
#include <jni.h>
#include <string>

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <android/log.h>
#include <experimental/filesystem>

#include <jni.h>
#include <string>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>

#include <android/log.h>
#include"time.h"

#include <iostream>
#include <stdio.h>
#include <unistd.h>
#include <dirent.h>
#include <stdlib.h>
#include <sys/stat.h>


std::vector<std::string> getFiles(const char* rootDir) {
  DIR* dir;
  struct dirent* ptr;
  char base[1000];

  if ((dir = opendir(rootDir)) == NULL) {
    perror("Open dir error...");
    exit(1);
  }
  std::vector<std::string> files;
  while ((ptr = readdir(dir)) != NULL) {
    if (strcmp(ptr->d_name, ".") == 0 ||
        strcmp(ptr->d_name, "..") == 0)    ///current dir OR parrent dir
      continue;
    else if (ptr->d_type == 8)    ///file
      //printf("d_name:%s/%s\n",basePath,ptr->d_name);
      files.push_back(std::string(rootDir) + "/" + ptr->d_name);
    else if (ptr->d_type == 10)    ///link file
      //printf("d_name:%s/%s\n",basePath,ptr->d_name);
      continue;
    else if (ptr->d_type == 4)    ///dir
    {

    }
  }
  closedir(dir);
  return files;
}

void read(const char* rootDir, std::string record, std::string min, std::string max, int file_count,
          long total_size) {
  std::vector<std::string> files = getFiles(rootDir);
  clock_t begin = clock();
  int bufLength = 8 * 1024;
  char* buf = new char[bufLength];
  for (auto itr = files.begin(); itr != files.end(); itr++) {
    std::string f = *itr;
    FILE* file = fopen(f.c_str(), "rb");
    int count;
    int total = 0;
    while ((count = fread(buf, 1, bufLength, file)) > 0) {
//      for (int i = 0; i < count; ++i) {
//        assert(buf[i] == char(total + i));
//      }
//      total += count;
    }

//    fseek(file, 0, SEEK_END);  //先用fseek将文件指针移到文件末尾
//    int n = ftell(file);
    fclose(file);
//    __android_log_print(ANDROID_LOG_DEBUG, "io", "长度：%d %d", total, n);
//    assert(total == n);
  }
  delete[](buf);
  clock_t end = clock();
  double elapsed_secs = static_cast<double>(end - begin) / CLOCKS_PER_SEC * 1000;
//  __android_log_print(ANDROID_LOG_DEBUG, "system.out", "c++ read执行时间：%f  文件数量：%d", elapsed_secs,
//                      files.size());
//   file-rw-speed-test-record
//   writer.write("JavaMMap:      文件数量:" + fileCount + ", 总大小:" + totalSize + ", 文件大小:" + min + "~" + max + ", 时间:" + (end - start) / 1000000f + "ms");
//
  FILE* recordFile = fopen(record.c_str(), "a");
  std::string txt =
      "C++MMap:       文件数量:" + std::to_string(file_count) + ", 总大小:" + std::to_string(total_size) +
      ", 文件大小:" +
      min + " ~ " + max + ", 时间:" + std::to_string(elapsed_secs) + "ms\n";
  const char* result = txt.c_str();
  fwrite(result, 1, strlen(result), recordFile);
  if (fclose(recordFile)) {
    __android_log_print(ANDROID_LOG_DEBUG, "system.out", "记录错误：%s", strerror(errno));
    abort();
  }
  __android_log_print(ANDROID_LOG_DEBUG, "system.out", "c++ read 读取结束：%s", result);
}

void
mmpRead(const char* rootDir, std::string record, std::string min, std::string max, int file_count,
        long total_size) {
  std::vector<std::string> files = getFiles(rootDir);
  clock_t begin = clock();
  int bufLength = 8 * 1024;
  char* buf = new char[bufLength];
  for (auto itr = files.begin(); itr != files.end(); itr++) {
    std::string f = *itr;
    int fd = open(f.c_str(), O_CREAT | O_RDWR | O_CLOEXEC, 0666);
    long fileLength = lseek(fd, 0, SEEK_END);
    char* address = (char*) mmap(NULL, fileLength, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    close(fd);
//    assert(address != MAP_FAILED);
    int remain = fileLength;
    char* start = address;
    int total = 0;
    while (remain > 0) {
      int c = remain > bufLength ? bufLength : remain;
      memcpy(buf, start, c);
      remain -= bufLength;
      start += bufLength;

//      for (int i = 0; i < c; ++i) {
//        assert(buf[i] == char(total + i));
//      }

//      total += c;
    }
//    assert(total == fileLength);
    int result = munmap(address, fileLength);

//    __android_log_print(ANDROID_LOG_DEBUG, "io", "mmap长度：%d", fileLength);
  }
  delete[](buf);
  clock_t end = clock();
  double elapsed_secs = static_cast<double>(end - begin) / CLOCKS_PER_SEC * 1000;
//  __android_log_print(ANDROID_LOG_DEBUG, "system.out", "c++ mmap执行时间：%f  文件数量:%d", elapsed_secs,
//                      files.size());
//  file-rw-speed-test-record
//  writer.write("JavaMMap:      文件数量:" + fileCount + ", 总大小:" + totalSize + ", 文件大小:" + min + "~" + max + ", 时间:" + (end - start) / 1000000f + "ms");

  FILE* recordFile = fopen(record.c_str(), "a");
  std::string txt =
      std::string() + "C++Read:       文件数量:" + std::to_string(file_count) + ", 总大小:" +
      std::to_string(total_size) + ", 文件大小:" +
      min + " ~ " + max + ", 时间:" + std::to_string(elapsed_secs) + "ms\n";
  const char* result = txt.c_str();
  fwrite(result, 1, strlen(result), recordFile);
  if (fclose(recordFile)) {
    __android_log_print(ANDROID_LOG_DEBUG, "system.out", "记录错误：%s", strerror(errno));
    abort();
  }
  __android_log_print(ANDROID_LOG_DEBUG, "system.out", "c++ mmap 读取结束：%s", result);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_io_1performance_FileUtils_cRead(JNIEnv* env, jclass clazz, jstring dir,
                                                 jstring record,
                                                 jstring min, jstring max, jint file_count,
                                                 jlong total_size) {

  const char* d = env->GetStringUTFChars(dir, 0);
  const char* minChar = env->GetStringUTFChars(min, 0);
  const char* maxChar = env->GetStringUTFChars(max, 0);
  const char* recordChar = env->GetStringUTFChars(record, 0);
  read(d, std::string(recordChar), std::string(minChar), std::string(maxChar), file_count,
       total_size);
  env->ReleaseStringUTFChars(record, recordChar);
  env->ReleaseStringUTFChars(max, maxChar);
  env->ReleaseStringUTFChars(min, minChar);
  env->ReleaseStringUTFChars(dir, d);
}extern "C"
JNIEXPORT void JNICALL
Java_com_example_io_1performance_FileUtils_cMmap(JNIEnv* env, jclass clazz, jstring dir,
                                                 jstring record,
                                                 jstring min, jstring max, jint file_count,
                                                 jlong total_size) {
  const char* d = env->GetStringUTFChars(dir, 0);
  const char* minChar = env->GetStringUTFChars(min, 0);
  const char* maxChar = env->GetStringUTFChars(max, 0);
  const char* recordChar = env->GetStringUTFChars(record, 0);
  mmpRead(d, std::string(recordChar), std::string(minChar), std::string(maxChar), file_count,
          total_size);
  env->ReleaseStringUTFChars(record, recordChar);
  env->ReleaseStringUTFChars(max, maxChar);
  env->ReleaseStringUTFChars(min, minChar);
  env->ReleaseStringUTFChars(dir, d);
}