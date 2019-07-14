package io.github.mxudong.rs.randoms;

import static io.github.mxudong.rs.utils.StringUtil.*;

/**
 * packing some useful method of string format.
 *
 * @author Dong
 * @since 3.0
 */

public class FormatString {

    /**
     * Generate according to format requirements
     * <p>
     * format chars
     * * - any chas
     * c - char contain upper char and lower char
     * u - upper case char
     * l - lower case char
     * s - all symbol
     * n - number
     * () - create () with group
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
     * @param formatRegion format
     * @return a random string
     */
    public static String createRandomStringBase(String formatRegion) {
        char[] chars = formatRegion.toCharArray();
        StringBuilder preFix = new StringBuilder();
        StringBuilder sufFix = new StringBuilder();
        StringBuilder result = new StringBuilder();
        boolean isBreak = false;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '*':
                case 'c':
                case 'u':
                case 'l':
                case 's':
                case 'n': {
                    if (preFix.length() != 0) {
                        int count = sufFix.length() == 0 ? 1 : convent(sufFix.toString());
                        result.append(beAppends(preFix, count));
                        preFix = new StringBuilder();
                        sufFix = new StringBuilder();
                    }
                    preFix.append(chars[i]);
                    break;
                }
                case '{':
                case '[': {
                    char endFlag = '}';
                    if (chars[i] == '[') {
                        endFlag = ']';
                    }
                    if (preFix.length() != 0) {
                        int count = sufFix.length() == 0 ? 1 : convent(sufFix.toString());
                        result.append(beAppends(preFix, count));
                    }
                    preFix = new StringBuilder();
                    sufFix = new StringBuilder();
                    boolean isTurnMean = false;
                    for (; i < chars.length; i++) {
                        preFix.append(chars[i]);
                        if (chars[i] == '-' && !isTurnMean) {
                            isTurnMean = true;
                            continue;
                        }
                        if (isTurnMean) {
                            isTurnMean = false;
                        } else {
                            if (chars[i] == endFlag) {
                                break;
                            }
                        }
                    }
                    if (i == chars.length) {
                        return "";
                    }
                    break;
                }
                case '(': {
                    if (preFix.length() != 0) {
                        int count = sufFix.length() == 0 ? 1 : convent(sufFix.toString());
                        result.append(beAppends(preFix, count));
                    }
                    StringBuilder sb = new StringBuilder();
                    int count = 0;
                    try {
                        do {
                            if (chars[i] == '(') {
                                count++;
                            }
                            if (chars[i] == ')') {
                                count--;
                            }
                            sb.append(chars[i]);
                            i++;
                        } while (count != 0);
                        i--;
                    } catch (Exception e) {
                        return "";
                    }
                    preFix = sb;
                    sufFix = new StringBuilder();
                    break;
                }
                // numbers
                default: {
                    boolean isNumber = (chars[i] >= '0' && chars[i] <= '9')
                            || chars[i] == '<'
                            || chars[i] == '>'
                            || chars[i] == '|';
                    if (isNumber) {
                        sufFix.append(chars[i]);
                    } else {
                        return "";
                    }
                }
            }
        }

        if (preFix.length() != 0) {
            int count = sufFix.length() == 0 ? 1 : convent(sufFix.toString());
            result.append(beAppends(preFix, count));
        }

        return result.toString();
    }

    /**
     * Detection of added parameters
     *
     * @param preFix insert data
     * @param count  insert count
     * @return the string be created
     */
    private static String beAppends(StringBuilder preFix, int count) {
        StringBuilder result = new StringBuilder();
        if (preFix.charAt(0) == '(') {
            preFix.deleteCharAt(0);
            preFix.deleteCharAt(preFix.length() - 1);
            for (int j = 0; j < count; j++) {
                result.append(createRandomStringBase(preFix.toString()));
            }
        } else {
            result.append(createSimpleString(preFix.toString(), count));
        }
        return result.toString();
    }

    /**
     * create inner random string for format create.
     *
     * @param region aim format
     * @param count  create number
     * @return a random string
     */
    private static String createSimpleString(String region, int count) {
        if (region.length() == 0 || count == 0 || count == -1) {
            return "";
        }
        if (region.length() == 1) {
            switch (region.charAt(0)) {
                case 'c': {
                    return createRandomString(UPPER_CASE_LETTERS + LOWER_CASE_LETTERS, count);
                }
                case 'u': {
                    return createRandomString(UPPER_CASE_LETTERS, count);
                }
                case 'l': {
                    return createRandomString(LOWER_CASE_LETTERS, count);
                }
                case 's': {
                    return createRandomString(OTHER_TYPE_LETTERS, count);
                }
                case 'n': {
                    return createRandomString(NUMBER_LETTERS, count);
                }
                default: {
                    return createRandomString(getAllCharacter(), count);
                }
            }
        } else {
            if (region.startsWith("{") ||
                    region.startsWith("[")) {
                StringBuilder results = new StringBuilder();
                char[] chars = region.toCharArray();
                boolean isTurnMean = false;
                for (int i = 1; i < chars.length - 1; i++) {
                    if (chars[i] == '-' && !isTurnMean) {
                        isTurnMean = true;
                        continue;
                    }
                    results.append(chars[i]);
                    if (!isTurnMean) {
                        if (chars[i] == '}') {
                            break;
                        }
                        continue;
                    }
                    isTurnMean = false;
                }
                if (region.startsWith("{")) {
                    return createRandomString(results.toString(), count);
                } else {
                    StringBuilder temp = new StringBuilder();
                    for (int i = 0; i < count; i++) {
                        temp.append(results);
                    }
                    return temp.toString();
                }
            } else {
                return "===========";
            }
        }
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
            if (secondNumber.length() == 0) {
                second = DEFAULT_MAX_STRING_LENGTH;
            } else {
                second = convent(secondNumber.toString());
            }

            if (first >= second) {
                return -1;
            }
            if (first == -1 || second == -1) {
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
