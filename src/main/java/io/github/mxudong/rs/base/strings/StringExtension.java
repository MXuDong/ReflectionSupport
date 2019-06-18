package io.github.mxudong.rs.base.strings;

import io.github.mxudong.rs.base.randoms.BaseRandom;
import io.github.mxudong.rs.exceptions.NullParamException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Name : StringExtension
 * Create Time : 15:36
 * Create Date : 2019/6/2
 * Project : ReflectionSupport
 * <p>
 * the String's extension, and provides
 * encapsulation of basic usage methods.
 *
 * @author Dong
 * @see io.github.mxudong.rs.base.methods.Invoker
 * @since 2.0
 */

public class StringExtension {

    public static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBER_LETTERS = "0123456789";
    public static final String OTHER_TYPE_LETTERS = "{}[]()<>,.':;?/=+-*&|!^%#@~`\"\\";
    public static final char SPACE_CHAR = ' ';
    public static final char DOLLAR_CHAR = '$';
    public static final char UNDER_LINE_CHAR = '_';
    public static final char POINT_CHAR = '.';


    /**
     * to match one character in upper little.
     */
    public final static String UPPER_LITTLE_REGION = "[A-Z]";

    /**
     * Get all characters
     *
     * @return a string of all characters
     */
    public static String getAllCharacter() {
        return UPPER_CASE_LETTERS +
                LOWER_CASE_LETTERS +
                NUMBER_LETTERS +
                OTHER_TYPE_LETTERS +
                SPACE_CHAR +
                DOLLAR_CHAR +
                UNDER_LINE_CHAR +
                POINT_CHAR;
    }

    /**
     * Obtain symbols supported by naming conventions
     *
     * @return string
     */
    public static String getClassSupportLetters() {
        return UPPER_CASE_LETTERS +
                LOWER_CASE_LETTERS +
                NUMBER_LETTERS +
                UNDER_LINE_CHAR +
                DOLLAR_CHAR;
    }

    /**
     * get all symbols
     *
     * @return string
     */
    public static String getAllSymbols() {
        return OTHER_TYPE_LETTERS + DOLLAR_CHAR + POINT_CHAR + SPACE_CHAR + UNDER_LINE_CHAR;
    }

    /**
     * count a string has how many region
     *
     * @param region be counted strings, it's a regular
     * @param string base string, to count
     * @return number of occurrences of region in string
     */
    public static int StringCount(String string, String region) {
        Pattern pattern = Pattern.compile(region);
        Matcher matcher = pattern.matcher(string);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    /**
     * turn )StringBuffer[]  to )String[]
     *
     * @param stringBuffers be turned param
     * @return string[]
     */
    public static String[] toStringArray(StringBuffer[] stringBuffers) {
        if (stringBuffers == null) {
            throw new NullParamException("toStringArray", "stringBuffers");
        }
        String[] strings = new String[stringBuffers.length];
        for (int i = 0; i < stringBuffers.length; i++) {
            strings[i] = stringBuffers[i].toString();
        }
        return strings;
    }

    /**
     * to get getter property name
     *
     * @param getterMethodName method name
     * @return property name
     */
    public static String getGetterMethodProperty(String getterMethodName) {
        int index = 0;
        if (getterMethodName.startsWith("get")) {
            index = 3;
        } else {
            index = 2;
        }

        if (getterMethodName.length() <= index) {
            return getterMethodName;
        }
        char[] chars = getterMethodName.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        chars[index] = (char) (chars[index] - 'A' + 'a');
        for (int i = index; i < getterMethodName.length(); i++) {
            stringBuffer.append(chars[i]);
        }

        return stringBuffer.toString();
    }

    /**
     * to get setter property name
     *
     * @param setterMethodName method name
     * @return property name
     */
    public static String getSetterMethodProperty(String setterMethodName) {
        char[] chars = setterMethodName.toCharArray();
        chars[0] = 'g';
        return getGetterMethodProperty(new String(chars));
    }


    /**
     * Create a random string with characters from chars.
     *
     * @param chars  string's characters from it.
     * @param length the string's length
     * @return a random string
     */
    public static String createRandomString(String chars, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        if (chars.length() == 0) {
            return "";
        }
        for (int i = 0; i < length; i++) {
            stringBuilder.append(BaseRandom.getRandomChar(chars));
        }
        return stringBuilder.toString();
    }

    private static final int DEFAULT_MIN_STRING_LEGNTH = 10;
    private static final int DEFAULT_MAX_STRING_LENGTH = 15;

    /**
     * Create a random String with characters from
     *
     * @param chars string's characters from it
     * @return a random string and it length is random too.
     */
    public static String createRandomString(String chars) {
        return createRandomString(chars, BaseRandom.getRandomInt(DEFAULT_MIN_STRING_LEGNTH, DEFAULT_MAX_STRING_LENGTH));
    }

}
