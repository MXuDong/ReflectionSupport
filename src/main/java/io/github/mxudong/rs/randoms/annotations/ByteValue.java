package io.github.mxudong.rs.randoms.annotations;

/**
 * specify the generated byte data value
 * <p>
 * so it only affects byte type data, and it has no impact on any other type of data.
 * <p>
 * priority is higher than Random Limit and byte Limit
 *
 * @author Dong
 * @see RandomLimit
 * @see ByteLimit
 */
public @interface ByteValue {
    byte value();
}
