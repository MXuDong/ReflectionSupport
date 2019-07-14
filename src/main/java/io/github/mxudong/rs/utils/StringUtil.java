package io.github.mxudong.rs.utils;

import io.github.mxudong.rs.randoms.BaseRandom;

/**
 * provide some operation about string
 *
 * @author Dong
 * @since 3.0
 */

public class StringUtil {

    public static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBER_LETTERS = "0123456789";
    public static final String OTHER_TYPE_LETTERS = "{}[]()<>,.':;?/=+-*&|!^%#@~`\"\\";
    public static final char SPACE_CHAR = ' ';
    public static final char DOLLAR_CHAR = '$';
    public static final char UNDER_LINE_CHAR = '_';
    public static final char POINT_CHAR = '.';

    /**
     * limit random string length
     */
    private static final int DEFAULT_MIN_STRING_LEGNTH = 10;
    private static final int DEFAULT_MAX_STRING_LENGTH = 15;

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
     * get all symbols, not include word or number
     *
     * @return string
     */
    public static String getAllSymbols() {
        return OTHER_TYPE_LETTERS + DOLLAR_CHAR + POINT_CHAR + SPACE_CHAR + UNDER_LINE_CHAR;
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
}
