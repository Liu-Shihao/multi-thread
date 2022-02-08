# Java 多线程和高并发

# Day01
1.Java线程创建的几种方式 
- 继承Thread类，重写run方法，直接调用start方法。
- 实现Runnable接口，重写run方法，通过Thread创建线程，调用start方法。
- 实现Callable接口，重写call方法，又返回值，通过FutureTask包装，通过Thread创建线程，调用start方法。