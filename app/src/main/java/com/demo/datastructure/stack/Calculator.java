package com.demo.datastructure.stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 通过计算器的设计原来来理解栈(stack)这种数据结构
 * 参考：https://www.cnblogs.com/hapjin/p/4740801.html
 * <p>
 * 计算机计算后缀表达式的过程如下----后缀表达式的计算机求值：
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次栈顶元素 op 栈顶元素），并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。
 * (3+4)*5-6
 * 例如后缀表达式“3 4 + 5 × 6 -”：
 * (1) 从左至右扫描，将3和4压入堆栈；
 * (2) 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素，注意与前缀表达式做比较），计算出3+4的值，得7，再将7入栈；
 * (3) 将5入栈；
 * (4) 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * (5) 将6入栈；
 * (6) 最后是-运算符，计算出35-6的值，即29，由此得出最终结果。
 * <p>
 * 中缀表达式转换为后缀表达式算法：
 * <p>
 * 这里只用了一个栈来保存扫描中缀表达式时遇到的运算符。扫描过程中运算的操作数则直接 append 到输出表达式的末尾
 * <p>
 * ❶运算符在何种情况下压入栈？
 * <p>
 * 若当前扫描的运算符的优先级大于栈顶运算符的优先级，则进行入栈。
 * <p>
 * 若当前扫描的运算符的优先级与栈顶运算符的优先级相同，则需要判断当前扫描的运算符运算时的结合方向，若结合方向为从左至右，则不需要入栈；若结合方向为从右至左，则入栈。其中，加、减、乘、除 运算符的结合方向为从左至右，而求幂运算符的结合方向为从右至左。由于求幂运算符的最优级最高且它的结合方向为从右至左，故扫描遇到求幂运算符时直接将其入栈。
 * <p>
 * ❷对于中缀表达式中的括号的处理
 * <p>
 * 左括号总是被压入栈。一旦左括号在栈中，就被当作优先级最低的运算符来对待，即：任何一个后继的运算符都将被压入栈。在遇到一个右括号时，从栈中弹出运算符并将它们添加到输出表达式末尾，直至弹出一个左括号为止（后缀表达式中没有括号，当然括号也就不需要添加到输出表达式了）。然后，算法再继续....
 * <p>
 * <p>
 * <p>
 * 在从左向右处理中缀表达式的过程中，根据遇到的符号，执行下列动作：
 * <p>
 * ①操作数         每个操作数都添加到输出表达式末尾（输出表达式就是最终得到的后缀表达式结果）
 * <p>
 * ②运算符 ^（求幂运算）       ^ 压入栈（因为在所有的运算符中（加、减、乘、除、求幂）求幂运算的优先级最高，且求幂运算的结合方式为从右至左）
 * <p>
 * ③运算符 + -  * /     从栈中弹出运算符，并将它们添加到输出表达式末尾，直至栈空或者栈顶优先级比新的运算符低，然后再将新的运算符压入栈
 * <p>
 * ④左括号 (      压入栈
 * <p>
 * ⑤右括号 )     从栈中弹出运算符，将它们添加到输出表达式末尾，直至弹出一个左括号，丢弃这两个括号
 */
public class Calculator {
    private static List<String> OPERATORS = new ArrayList<>();

    static {
        OPERATORS.add("+");
        OPERATORS.add("-");
        OPERATORS.add("*");
        OPERATORS.add("/");
    }
    //计算器：实现9+（3-1）*3+10/2 = 20这个计算逻辑

    /**
     * 中缀表达式 9+（3-1）*3+10/2 = 20 转后缀931-3*+10 2/+
     * <p>
     * 931-3*+10 2/+
     * 923*+10 2/ +
     * 96+10 2/ +
     * 15 10 2/ +
     * 15 5 +
     * 20
     *
     * @param args
     */
    public static void main(String[] args) {
        //1、中缀表达式转换为后缀表达式
        //数字输出，运算符进栈，括号匹配出栈，栈顶优先级低出栈
        //2、根据后缀表达式转换为数学计算
        List<String> target = new ArrayList<>();
        //931-3*+10 2/+
        target.add("9");
        target.add("3");
        target.add("1");
        target.add("-");
        target.add("3");
        target.add("*");
        target.add("+");
        target.add("10");
        target.add("2");
        target.add("/");
        target.add("+");
        int result = calculate(target);
        System.out.println("计算结果为: " + result);

    }

    /**
     * 判断是否是数字
     *
     * @param s
     * @return
     */
    public static boolean isDigital(String s) {
        return false;
    }

    /**
     * 判断是否是运算符
     *
     * @param s
     * @return
     */
    public static boolean isOperator(String s) {
        return OPERATORS.indexOf(s) != -1;
    }

    /**
     * 判断是否匹配括号
     *
     * @param s
     * @return
     */
    public static boolean isMatchBrackets(Stack<String> stack, String s) {
        return false;
    }

    /**
     * 判断是否栈顶优先级低
     *
     * @param s
     * @return
     */
    public static boolean isStackTopLower(Stack<String> stack, String s) {
        return false;
    }

    public static int calculate(List<String> target) {
        int result = 0;
        List<String> temp = new ArrayList<>(target);
        Iterator<String> iterator = target.iterator();
        while (iterator.hasNext()) {
            String operator = iterator.next();
            if (isOperator(operator)) {//如果是操作符,取出前两个数字计算
                int index = target.indexOf(operator);
                //操作数
                String operatorNum = target.get(index - 1);
                //被操作数
                String operandNum = target.get(index - 2);
                result = executeCalculate(operandNum, operatorNum, operator);
                temp.set((index - 2), String.valueOf(result));
                temp.remove(index);
                temp.remove(index - 1);
                if (temp.size() == 1) {//计算完成
                    result = Integer.parseInt(temp.get(0));
                    return result;
                } else {
                    calculate(temp);
                }
            }
        }

        return result;
    }

    private static int executeCalculate(String operandNum, String operatorNum, String operator) {
        int operandNumber = Integer.parseInt(operandNum);
        int operatorNumber = Integer.parseInt(operatorNum);
        switch (operator) {
            case "+":
                return operandNumber + operatorNumber;
            case "-":
                return operandNumber - operatorNumber;
            case "*":
                return operandNumber * operatorNumber;
            case "/":
                return operandNumber / operatorNumber;
        }
        return 0;
    }
}
