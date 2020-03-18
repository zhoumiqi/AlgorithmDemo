package com.demo.algorithm.easy;

public class TwoNumExchange {
    /**
     * 位运算实现交换(按位异或) a^b = b^a,不需要引入第三个是数
     * 运算规则：0^0=0；  0^1=1；  1^0=1；   1^1=0；
     * 异或运算的逆运算是它本身，也就是说两次异或同一个数最后结果不变 a^b^b= a;
     *
     * @param args
     */
    public static void main(String[] args) {
        int a = 3, b = 5;
        System.out.println("a^b=" + (a ^ b) + ",b^a = " + (b ^ a));
        a ^= b;//a = a^b
        b ^= a;//b = b^a = b^(a^b)
        a ^= b;//a = a^b = (a^b)^(b^(a^b))
        System.out.println("交换后：a = " + a + ",b = " + b);
    }
}
