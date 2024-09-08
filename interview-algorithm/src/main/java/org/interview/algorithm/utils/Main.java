package org.interview.algorithm.utils;

import java.io.*;

/**
 * 快速输入输出
 */
public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 可以读取所有字符，但一次必须读满取一行数据,包括空格
    static StreamTokenizer in = new StreamTokenizer(reader);
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static int getInt() throws IOException {
        if (in.nextToken() != StreamTokenizer.TT_EOF) {
            return (int) in.nval;
        }
        return 0;
    }

    public static long getLong() throws IOException {
        if (in.nextToken() != StreamTokenizer.TT_EOF) {
            return (long) in.nval;
        }
        return 0L;
    }

    public static String getString() throws IOException { //无法获取除字母以及数字外的字符，且第一个字符不能是数字字符, 空格会分隔开多个输入
        if (in.nextToken() != StreamTokenizer.TT_EOF) {
            return in.sval;
        }
        return "";
    }

    public static void main(String[] args) {
        out.println("快速输入输出");
        out.flush();
        out.close();
    }
}
