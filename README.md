# Java 多线程和高并发
什么是进程：资源分配的基本单位（静态概念）
什么是线程：资源调度的基本单位（动态概念）通俗说：一个程序中不同的执行路径

# 创建线程
1.Java线程创建的几种方式 
- 继承Thread类，重写run方法，直接调用start方法。
- 实现Runnable接口，重写run方法，通过Thread创建线程，调用start方法。
- 实现Callable接口，重写call方法，又返回值，通过FutureTask包装，通过Thread创建线程，调用start方法。
- 线程池方式

# 线程运行状态
1. NEW 新建状态
2. Ready 就绪状态  等待CPU资源
3. RUNNABLE 运行状态 获得CPU资源
4. BLOCKED/WAITING/TIMED_WAITING 阻塞状态
5. TERMINATED 终止状态 SHUTDOWN 线程抛出异常或者任务执行完成，线程就会结束



## 常用线程池体系结构
Executor：线程池顶级接口
Executors：线程池工具类
ExecutorService：线程池次顶级接口
ThreadPoolExecutor：普通线程池类
ForkJoinPool：
ScheduledExecutorService：
ScheduledThreadPoolExecutor：


# Synchronized 

# Volatile
Volatile有两个作用：
1. 保证线程间的可见性；
2. 内存屏障，可以禁止指令重排序

# CAS 

# JUC 常用类库
## CountDownLatch

## CyclicBarrier

## Phaser
## Semaphore
## Exchange
## LockSupport




# Collection 容器 
List：ArrayList、Vector、LinkedList
Set：HashSet -> LinkedHashSet、TreeSet
Queue：BlockingQueue   重要
key:value
Map：HashMap -> LinkedHashMap、Hashtable、TreeMap

Hashtable ->  HashMap  -> synchronizedMap() -> ConcurrentHashMap
Vector -> ArrayList -> ConcurrentLinkedQueue

Queue和List的区别？
    Queue增加了对线程友好的API offer、poll、peek等
    BlockingQueue：put（）、take（）实现阻塞
# List
List集合的特点是元素有序，常用实现类有 ArrAyList、LinkedList、Vector
## ArrayList 
线程不安全，查询和更新效率高，
通过数组实现初始容量为16，扩容为原数组的1.5倍
## LinkedList
线程不安全，新增和删除效率高
通过链表实现，改变空间结构比较容易
## Vector 
底层通过数组实现，数组长度为10，扩容为原数组的2倍
现成安全的，内部方法使用synchronized修饰 ，效率低，已弃用
# Set 
Set集合的特点是可以实现元素去重，常用实现类与 HashSet、TreeSet
## HashSet
通过HashMap Key实现
### LinkedHashSet
LinkedHashSet是HashSet的子类，引入了链表结构
## TreeSet
通过TreeMap实现
通过红黑树实现，可以实现元素排序。

# Queue
Queue 队列继承自Collection接口
阻塞队列是线程池中重要的参数
Collection <- Queue <- BlockingQueu

常见的队列有：
1. PriorityQueue 优先级队列/小根堆/大跟堆 可以实现排序 必须实现比较器方法
2. ConcurrentLinkedQueue 线程安全的 使用链表实现的 无界的队列
3. ArrayBlockingQueue 数组实现的 有界的 阻塞队列
4. LinkedBlockingQueue 链表实现的 无界的 阻塞队列
5. SynchronousQueue 同步队列 用于两个线程传递消息 容量为0 先取后放 阻塞队列
6. TransferQueue 交换队列 阻塞队列 一个线程放入队列的数据，在被取走之前都是阻塞的

阻塞队列的put()方法和take（）方法是阻塞的：
1. put（）如果队列满了，则会阻塞等待
2. take（） 如果队列为空，则会阻塞等待


# Map
## Hashtable
线程安全，内部方法用 synchronized修饰，效率低
## HashMap
效率高，线程不安全

jdk1.7 通过数组+链表实现，链表采用头插法

jdk1.8 通过数组+链表+红黑树实现，链表采用尾插法，
在链表长度超过8以后，并且数组元素少于64，会转化为红黑树结构
红黑树长度小于6则恢复链表结构。
使用红黑树是因为查询效率高，红黑树是一种弱平衡树，自旋次数少。

数组扩容阈值为0.75，每次扩容2的次幂，是为了避免哈希碰撞

数组初始长度为16，在第一次put元素的时候才初始化数组长度
### LinkedHashMap
LinkedHashMap是HashMap的子类

## TreeMap
底层通过红黑树实现，可以实现元素排序

## ConcurrentHashMap
线程安全的，效率高（读的效率高）
使用分段锁实现，降低锁粒度
ConcueenrtHashMap在多线程下写的效率没有Hashtable/synchronizedMap/高，但是读的效率是最高的


## ConcurrentSkipListMap
线程安全的 跳表实现的 双列结合
（由于CAS实现红黑树的难度很大，所以没有ConcurrentTreeMap,采用跳表实现）

## ConcurrentSkipListSet
线程安全的 跳表实现的 去重的 单列集合

## ConcurrentLinkedQueue

线程安全的 链表实现的 无界的（Integer最大值）队列
不是阻塞队列

# COW 容器
写时复制容器，适合读多写少的场景
## CopyOnWriteArrayList

## CopyOnWriteArraySet


# ThreadPoolExecutor 常用线程池
## SingleThreadPool 单例线程池
## FixedThreadPool 固定数量线程池
## CacheThreadPool 缓存线程池 
可以一直增加线程数
## ScheduledThreadPool 定时任务线程池


# ForkJoinPool  线程池
## ForkJoinPool
## WorkStealingPool 工作窃取线程池
WorkStealingPool 工作窃取线程池 通过 ForkJoinPool实现
## ParallelStream 并行流处理
ParallelStream 并行流处理 通过 ForkJoinPool实现


# JMH
Java Microbenchmark Harness   Java 基准测试工具  2013年发布

# disruptor
内存中
速度最快的mq
性能极高，无锁CAS，单机支持高并发
一个线程中每秒处理600万订单




# 多线程面试题
1. 两个线程交叉打印A1B2C3D4...Z26
2. 写一个固定容量同步容器，拥有put和get以及getCount方法，能够支持两个生产者和10个消费者线程的阻塞调用

