package com.lsh.class00.createthread;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/24 6:54 下午
 * @desc ：
 */
public class Demo {

    public static void main(String[] args) {
        giveMeANum(10);
    }

    /**
     *   *
     *  ***       3+1 * 2        5个*     n=1    2n+1+(2n-1)*2
     *   *
     *
     *   *
     *  ***
     * *****      5+（3+1）*2     13个*     n=2   2n+1+(2n-1+2n-3)*2
     *  ***
     *   *
     *   ……
     *
     *   1  3   5
     *   2  5   5+(3+1)*2
     *   3  7   7+(5+3+1)*2
     *   4  9   9+(7+5+3+1)*2          2n+1 +(2n-1+1)*n/2*2  =    2n+1 +(2n-1+1)*n
     *
     *   依次类推   中间最长的一行为：3,5,7,9,11... 的奇数数列   n = 2n+1   n>=1
     *   所以 形成菱形需要的*的总个数为：5,13,25...的数列,
     *
     * 形成菱形的规律为
     * @param num
     */
    public static void giveMeANum(int num){
        int n = 1;
        while (true){
            int sum = 2*n+1+(2*n-1+1)*n;
            System.out.println("打印第"+n+"个菱形需要的*的个数为："+sum);
            if (num <sum){
                n = n-1;
                sum = 2*n+1+(2*n-1+1)*n;
                makeRhombus(n+1);
                System.out.println("输入："+num+"，可以打印第"+(n)+"个菱形，需要的*的个数为："+sum+"，还剩下"+(num-sum)+"个");
              break;
            }
            n = n+1;
        }
    }

    /**
     * 制作菱形
     * @param n   最长的一行的行号
     */
    public static void makeRhombus(int n){
        int size = n;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print('*');
            }
            System.out.println();
        }
        for (int i = 1; i <= size-1; i++) {
            for (int j = 1; j <= i; j++){
                System.out.print(" ");
            }
            for (int k = 2*size-3; k >= 2 * i - 1; k--){
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
