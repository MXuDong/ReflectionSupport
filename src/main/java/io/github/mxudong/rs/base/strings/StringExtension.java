package io.github.mxudong.rs.base.strings;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
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
 * @since 2.0
 */

public class StringExtension {

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
        while(matcher.find()){
            count++;
        }

        return count;
    }

    /**
     * turn <tt>StringBuffer[]</tt> to <tt>String[]</tt>
     *
     * @param stringBuffers be turned param
     * @return string[]
     */
    public static String[] toStringArray(StringBuffer [] stringBuffers){
        if(stringBuffers == null){
            throw new NullParamException("toStringArray", "stringBuffers");
        }
        String[] strings = new String[stringBuffers.length];
        for(int i = 0; i < stringBuffers.length; i++){
            strings[i] = stringBuffers[i].toString();
        }
        return strings;
    }
}
