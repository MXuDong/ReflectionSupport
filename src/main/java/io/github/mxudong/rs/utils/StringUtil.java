package io.github.mxudong.rs.utils;

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

}
