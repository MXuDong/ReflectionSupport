package io.github.mxudong.rs.base.strings;

import io.github.mxudong.rs.base.randoms.BaseRandom;
import io.github.mxudong.rs.exceptions.NullParamException;

import java.util.Stack;
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

    /**
     * create a random String
     *
     * @return a random string
     */
    public static String createRandomString() {
        return createRandomString(getAllCharacter());
    }

    /**
     * Generate according to format requirements
     * <p>
     * format chars
     * c - char contain upper char and lower char
     * u - upper case char
     * l - lower case char
     * s - all symbol
     * n - number
     * () - create () with group
     * &lt;, &gt; Represents generation limits， use wilt |
     * - : escape character
     * {} - use this data to generate
     * <p>
     * and characters followed by numbers represent several characters
     * such as:
     * 1.c3 :Three English characters will be created
     * 2.lc3 : Three lower-case English characters will be created
     * <p>
     * you can create by () also:
     * 1.(a3n)3 will create like : abc1dAc3dDC5
     * <p>
     * you create by {} :
     * 1. {test}1 will create like : t
     * <p>
     * you can use [] to print data:
     * 1. [test] will create : test
     * <p>
     *
     * @param chars        create chars
     * @param formatRegion format
     * @return a random string
     */
    public static String createRandomString(String chars, String formatRegion) {
        StringBuilder result = new StringBuilder();
        char[] innerChars = chars.toCharArray();
        Stack<Character> characterStack = new Stack<>();
        String preFix = "";
        String sufFix = "1";
        StringBuilder preData = new StringBuilder();

    }

    /**
     * String turn to number
     *
     * @param number be turn number
     * @return a number
     */
    public static int convent(String number) {
        if (number.length() == 0) {
            return 1;
        }

        if (number.startsWith("<")) {
            if (number.length() <= 3) {
                return -1;
            }
            StringBuilder firstNumber = new StringBuilder();
            StringBuilder secondNumber = new StringBuilder();
            char[] chars = number.toCharArray();
            int i = 1;
            for (; i < chars.length; i++) {
                if (chars[i] == '|') {
                    break;
                }
                firstNumber.append(chars[i]);
            }
            i++;
            for (; i < chars.length; i++) {
                if (chars[i] == '>') {
                    break;
                }
                secondNumber.append(chars[i]);
            }

            int first = convent(firstNumber.toString());
            int second;
            if(secondNumber.length() == 0){
                second = Integer.MAX_VALUE;
            }else {
                second = convent(secondNumber.toString());
            }

            if (first >= second) {
                return -1;
            }
            if(first == -1 || second == -1){
                return -1;
            }
            return BaseRandom.getRandomInt(first, second);

        } else {
            int x;
            try {
                x = Integer.parseInt(number);
            } catch (Exception e) {
                x = -1;
            }
            return x;
        }
    }
}