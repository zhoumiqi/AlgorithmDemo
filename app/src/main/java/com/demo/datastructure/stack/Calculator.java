package com.demo.datastructure.stack;

import java.util.Stack;

/**
 * 通过计算器的设计原来来理解栈(stack)这种数据结构
 */
public class Calculator {
    //计算器：实现9+（3-1）*3+10/2 = 20这个计算逻辑

    /**
     * 中缀表达式 9+（3-1）*3+10/2 = 20 转后缀931-3*+10 2/+
     *
     * 931-3*+10 2/+
     * 923*+10 2/ +
     * 96+10 2/ +
     * 15 10 2/ +
     * 15 5 +
     * 20
     * @param args
     */
    public static void main(String[] args){
        //1、中缀表达式转换为后缀表达式
        //数字输出，运算符进栈，括号匹配出栈，栈顶优先级低出栈
        //2、根据后缀表达式转换为数学计算


    }

    /**
     * 判断是否是数字
     * @param s
     * @return
     */
    public static boolean isDigital(String s){
        return false;
    }

    /**
     * 判断是否是运算符
     * @param s
     * @return
     */
    public static boolean isOperator(String s){
        return false;
    }
    /**
     * 判断是否匹配括号
     * @param s
     * @return
     */
    public static boolean isMatchBrackets(Stack<String> stack, String s){
        return false;
    }
    /**
     * 判断是否栈顶优先级低
     * @param s
     * @return
     */
    public static boolean isStackTopLower(Stack<String> stack,String s){
        return false;
    }
}
