package com.xiazhiri.practice.util;

/**
 * Created by liuwencai on 16/4/1.
 */
public class StackTrace {

    /**
     * return ClassName > MethodName LineNumber ThreadName
     * @param depth
     * @return
     */
    public static String getTrace(int depth) {
        depth++;
        return String.format("%s > %s %d %s ",
                StackTrace.getClassName(depth),
                StackTrace.getMethodName(depth),
                StackTrace.getLineNum(depth),
                Thread.currentThread().getName());
    }

    public static String getClassName(int depth) {
        return new Exception().getStackTrace()[depth].getClassName().replaceAll(".*\\.", "");
    }

    public static String getMethodName(int depth) {
        return new Exception().getStackTrace()[depth].getMethodName();
    }

    public static int getLineNum(int depth) {
        return new Exception().getStackTrace()[depth].getLineNumber();
    }

}
