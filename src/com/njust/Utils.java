package com.njust;

/**
 * 工具类
 *
 * @author tomato
 * @create 2018-03-21 下午8:06
 */
public class Utils {
    /**
     * 是否是数字
     *
     * @param a 检测的字符
     * @return 是-true, 否-false
     */
    public static boolean isInteger(char a) {
        return a >= '0' && a <= '9';
    }

    /**
     * 是否是字母
     *
     * @param a 检测的字符
     * @return 是-true, 否-false
     */
    public static boolean isLetter(char a) {
        return a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z';
    }

    /**
     * 是否是小写字母
     *
     * @param a 检测的字符
     * @return 是-true, 否-false
     */
    public static boolean isLowerLetter(char a) {
        return a >= 'a' && a <= 'z';
    }

    /**
     * 是否是大写字母
     *
     * @param a 检测的字符
     * @return 是-true, 否-false
     */
    public static boolean isUpperLetter(char a) {
        return a >= 'A' && a <= 'Z';
    }

    /**
     * 是否不是大写字母
     *
     * @param a 检测的字符
     * @return 是-true, 否-false
     */
    public static boolean isNotUpperLetter(char a) {
        return !(a >= 'A' && a <= 'Z');
    }

}
