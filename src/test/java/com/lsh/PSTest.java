package com.lsh;

import com.lsh.class11.forkjoinpool.Code03_ParallelStream;
import org.openjdk.jmh.annotations.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/24 11:44 下午
 * @desc ：@Benchmark 为JMH的注解
 *
 * 测试报告：
 * /Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/bin/java -Dfile.encoding=UTF-8 -classpath /Users/LiuShihao/IdeaProjects/multi-thread/target/test-classes:/Users/LiuShihao/IdeaProjects/multi-thread/target/classes:/Users/LiuShihao/Maven/apache-maven-3.5.0/repository/com/google/code/google-collections/google-collect/snapshot-20080530/google-collect-snapshot-20080530.jar:/Users/LiuShihao/Maven/apache-maven-3.5.0/repository/org/openjdk/jol/jol-core/0.9/jol-core-0.9.jar:/Users/LiuShihao/Maven/apache-maven-3.5.0/repository/org/openjdk/jmh/jmh-core/1.21/jmh-core-1.21.jar:/Users/LiuShihao/Maven/apache-maven-3.5.0/repository/net/sf/jopt-simple/jopt-simple/4.6/jopt-simple-4.6.jar:/Users/LiuShihao/Maven/apache-maven-3.5.0/repository/org/apache/commons/commons-math3/3.2/commons-math3-3.2.jar:/Users/LiuShihao/Maven/apache-maven-3.5.0/repository/org/openjdk/jmh/jmh-generator-annprocess/1.21/jmh-generator-annprocess-1.21.jar org.openjdk.jmh.Main com.lsh.PSTest.testForEach
 * # JMH version: 1.21
 * # VM version: JDK 1.8.0_211, Java HotSpot(TM) 64-Bit Server VM, 25.211-b12
 * # VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/bin/java
 * # VM options: -Dfile.encoding=UTF-8
 * # Warmup: 1 iterations, 3 s each
 * # Measurement: 2 iterations, 3 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Throughput, ops/time
 * # Benchmark: com.lsh.PSTest.testForEach
 *
 * # Run progress: 0.00% complete, ETA 00:00:45
 * # Fork: 1 of 5
 * # Warmup Iteration   1: 0.849 ops/s
 * Iteration   1: 0.923 ops/s
 * Iteration   2: 0.964 ops/s
 *
 * # Run progress: 20.00% complete, ETA 00:00:43
 * # Fork: 2 of 5
 * # Warmup Iteration   1: 0.968 ops/s
 * Iteration   1: 0.932 ops/s
 * Iteration   2: 0.974 ops/s
 *
 * # Run progress: 40.00% complete, ETA 00:00:31
 * # Fork: 3 of 5
 * # Warmup Iteration   1: 0.992 ops/s
 * Iteration   1: 0.967 ops/s
 * Iteration   2: 0.972 ops/s
 *
 * # Run progress: 60.00% complete, ETA 00:00:20
 * # Fork: 4 of 5
 * # Warmup Iteration   1: 0.988 ops/s
 * Iteration   1: 0.992 ops/s
 * Iteration   2: 0.922 ops/s
 *
 * # Run progress: 80.00% complete, ETA 00:00:10
 * # Fork: 5 of 5
 * # Warmup Iteration   1: 1.021 ops/s
 * Iteration   1: 0.954 ops/s
 * Iteration   2: 0.990 ops/s
 *
 *
 * Result "com.lsh.PSTest.testForEach":
 *   0.959 ±(99.9%) 0.039 ops/s [Average]
 *   (min, avg, max) = (0.922, 0.959, 0.992), stdev = 0.026
 *   CI (99.9%): [0.920, 0.998] (assumes normal distribution)
 *
 *
 * # Run complete. Total time: 00:00:51
 *
 * REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
 * why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
 * experiments, perform baseline and negative tests that provide experimental control, make sure
 * the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
 * Do not assume the numbers tell you what you want them to tell.
 *
 * Benchmark            Mode  Cnt  Score   Error  Units
 * PSTest.testForEach  thrpt   10  0.959 ± 0.039  ops/s
 *
 * Process finished with exit code 0
 *
 */
public class PSTest {

    @Benchmark
    @Warmup(iterations = 1,time = 3)//预热 迭代一次  等待3秒钟
    @Fork(5)//多少个线程执行
    @BenchmarkMode(Mode.Throughput)//模式 吞吐量  每秒钟方法执行多少次
    @Measurement(iterations = 2,time = 3)//整个方法调用多少次
    public void testForEach() {
        Code03_ParallelStream.foreach();
    }
}
