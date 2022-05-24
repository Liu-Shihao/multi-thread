package com.lsh.class11.forkjoinpool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/24 10:40 下午
 * @desc ：ForkJoinPool 继承了 AbstractExecutorService
 *
 * 用ForkJoinPool 实现1000000个数的累加和
 * 分叉。。。分叉。。。归并
 */
public class Code02_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAV_VALUE = 50000;
    static Random random = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }
    /**
     * 无返回值
     * 自定义任务类AddTask继承RecursiveAction； RecursiveAction继承ForkJoinTask<Void>
     *
     * 类似归并排序
     */
    static class AddTask extends RecursiveAction{
        int start,end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end-start)<= MAV_VALUE){
                long sum = 0L;
                for (int i = start; i < end; i++) sum+=nums[i];

            }else {
                //继续拆分
                int mid = start + (start+end)/2;
                AddTask addTask1 = new AddTask(start, mid);//[start,mid)
                AddTask addTask2 = new AddTask(mid, end);//[mid,end)
                addTask1.fork();
                addTask2.fork();

            }

        }
    }

    static class AddTaskReturn extends RecursiveTask<Long>{
        int start,end;

        public AddTaskReturn(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            if ((end-start)<= MAV_VALUE){
                long sum = 0L;
                for (int i = start; i < end; i++) sum+=nums[i];
                return sum;
            }else {
                //继续拆分
                int mid = start + (end - start)/2;
                AddTaskReturn addTask1 = new AddTaskReturn(start, mid);//[start,mid)
                AddTaskReturn addTask2 = new AddTaskReturn(mid, end);//[mid,end)
                addTask1.fork();
                addTask2.fork();

                return addTask1.join() + addTask2.join();
            }
        }
    }

    public static void main(String[] args) {
//        long sum = 0L;
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < nums.length; i++) {
//            sum+=nums[i];
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(sum);
//        System.out.println(end-start);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        AddTask addTask = new AddTask(0, 1000000);//无返回值
//        forkJoinPool.execute(addTask); //无返回值
//        addTask.join()//无返回值

        AddTaskReturn addTaskReturn = new AddTaskReturn(0, nums.length);
        forkJoinPool.execute(addTaskReturn);
        Long join = addTaskReturn.join();
        System.out.println(join);

//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
