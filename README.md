# Java 多线程和高并发
什么是进程：资源分配的基本单位（静态概念）
什么是线程：资源调度的基本单位（动态概念）通俗说：一个程序中不同的执行路径

# Day01
1.Java线程创建的几种方式 
- 继承Thread类，重写run方法，直接调用start方法。
- 实现Runnable接口，重写run方法，通过Thread创建线程，调用start方法。
- 实现Callable接口，重写call方法，又返回值，通过FutureTask包装，通过Thread创建线程，调用start方法。

## 常用线程池体系结构
Executor：线程池顶级接口
Executors：线程池工具类
ExecutorService：线程池次顶级接口
ThreadPoolExecutor：普通线程池类
ForkJoinPool：
ScheduledExecutorService：
ScheduledThreadPoolExecutor：


# 容器
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




# 多线程面试题
1. 两个线程交叉打印A1B2C3D4...Z26
2. 写一个固定容量同步容器，拥有put和get以及getCount方法，能够支持两个生产者和10个消费者线程的阻塞调用

