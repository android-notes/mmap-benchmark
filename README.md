## mmap/read 性能测试
测试机型：小米1

CPU：双核1.5G

内存：1GB

可用内存大小：<400M

系统版本：Android 4.1.2   MIUI-4.12.5

内核版本：3.4.0

剩余内置存储容量：>2GB

### 测试方式
由于mmap和read都会优先从pagecache（内存）里读写数据，所以我们每次测试读取速度前会先重启手机一次。
为了避免手机刚重启时大量的APP启动导致的文件读写性能下降，我们会在手机重启一分钟后开始读取数据。
我们采用顺序读写整个文件而不是随机读写。


### 数据分析

#### 第一组数据
生成新文件    数量：800, 总大小：1.97MB

·------------------------------------------------------------------------

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:941.7132ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:246.87985ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:212.8877ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:217.43808ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:218.7489ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:279.9566ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:248.11171ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:262.9701ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:199.49629ms

JavaBuffRead:  文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:222.94623ms

·------------------------------------------------------------------------

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:822.48267ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:144.88919ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:145.90964ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:145.51067ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:145.27896ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:148.18845ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:162.05777ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:150.64282ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:146.28073ms

JavaMMap:      文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:373.04608ms

·------------------------------------------------------------------------

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:605.766000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:46.655000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:43.007000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.778000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:34.577000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.830000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:34.414000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:52.481000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.598000ms

C++MMap:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:34.114000ms

·------------------------------------------------------------------------

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:559.669000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:34.191000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.094000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.579000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.302000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:34.682000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.357000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:32.990000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.351000ms

C++Read:       文件数量:800, 总大小:2070863, 文件大小:1.00KB ~ 4.00KB, 时间:33.342000ms


我们生成了800个文件，每个文件在1.00KB ~ 4.00KB之间，然后重启手机，使用JavaBuffRead读取所有文件，重复读取10次。
接着重启手机，使用JavaMMap读取所有文件，重复读取10次。C++MMap、C++Read处理方式类似。

可以看到重启手机后第一次读取时不管使用什么读都很慢，但第二到第十次读取时会快很多。原因很简单，重启手机后pagecache里没有
缓存这些文件，所以第一次读取很慢，当第一次读取后这些文件会缓存在pagecache里，所以之后读取快很多。

可以看到在读取800个1.00KB ~ 4.00KB的文件时JavaBuffRead性能始终不如JavaMMap，而C++MMap和C++Read性能差不多。但C++优于Java

#### 第二组数据：

由于Java MMap没有释放内存，当重复读取时会导致oom，所以下面的数据没有测试JavaMMap

生成新文件    数量：7, 总大小：443MB

·------------------------------------------------------------------------

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13196.812ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12936.321ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12845.117ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12745.423ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12764.272ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12753.567ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12775.691ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12835.54ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12789.5625ms

JavaBuffRead:  文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12921.611ms

·------------------------------------------------------------------------

·------------------------------------------------------------------------

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13096.722000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13112.188000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13472.156000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13267.084000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13384.470000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13313.636000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13240.341000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13473.497000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13185.855000ms

C++MMap:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13035.580000ms

·------------------------------------------------------------------------

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:13303.030000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12772.999000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12616.530000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12579.744000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12501.277000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12485.761000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12538.512000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12490.923000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12523.119000ms

C++Read:       文件数量:7, 总大小:464923544, 文件大小:42.63MB ~ 93.76MB, 时间:12768.424000ms


当文件总大小400多MB时第一次和第二到第十次性能差不多。由于手机可用内存不到400MB，所以读取400多MB文件时pagecache里
肯定放不下了，所以第二次读取时依旧是从硬盘里读取，所以性能和第一次差不多。
上面数据中C++MMap竟然是最慢的还不如JavaBuffRead

### 完整数据
[mmap-read性能数据.txt](https://github.com/android-notes/mmap-benchmark/blob/master/mmap-read%E6%80%A7%E8%83%BD%E6%95%B0%E6%8D%AE.txt)

#### 备注：
所有数据仅限于该款机型，不代表所有机型。
