package com.realMoney.utils;

import java.util.Stack;

public class BaseNUtil {
    private static char[] array = "4605312897CGWOXUNFDAKQPZSTBRVMJIHYLEnsgylokdqbrtjvzpxhewucafmi".toCharArray();
    private static String numStr = "4605312897CGWOXUNFDAKQPZSTBRVMJIHYLEnsgylokdqbrtjvzpxhewucafmi";

    //10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(long number, int N) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[new Long((rest % N)).intValue()]);
            rest = rest / N;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0":result.toString();

    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N) {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }

        return result;
    }


    public static void main(String[] args) {
        String tmp2 = _10_to_N(10000000L, 34);
        Long tmp= N_to_10(tmp2, 34);

        System.out.println(tmp);
        System.out.println(tmp2);

    }
}

