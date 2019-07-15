package io.github.mxudong.rs.bean;

import io.github.mxudong.rs.randoms.annotations.*;

/**
 * @author Dong
 * @since 3.0
 */


@RandomLimit(defaultFormat = "n10")
public class TestRandomBean {

    @ByteLimit(minValue = (byte) 15, maxValue = (byte) 17)
    private byte bytes;
    private short shorts;
    @IntegerLimit(minValue = 90, maxValue = 100)
    private int ints;
    @IntegerValue(15)
    private int ints1;
    private long longs;
    private double doubles;
    private float floats;
    private char chars;
    private boolean booleans;
    @StringValue("test time date : 2019-7-15 22-51")
    private String timeData;

    public byte getBytes() {
        return bytes;
    }

    public void setBytes(byte bytes) {
        this.bytes = bytes;
    }

    public short getShorts() {
        return shorts;
    }

    public void setShorts(short shorts) {
        this.shorts = shorts;
    }

    public int getInts() {
        return ints;
    }

    public void setInts(int ints) {
        this.ints = ints;
    }

    public int getInts1() {
        return ints1;
    }

    public void setInts1(int ints1) {
        this.ints1 = ints1;
    }

    public long getLongs() {
        return longs;
    }

    public void setLongs(long longs) {
        this.longs = longs;
    }

    public double getDoubles() {
        return doubles;
    }

    public void setDoubles(double doubles) {
        this.doubles = doubles;
    }

    public float getFloats() {
        return floats;
    }

    public void setFloats(float floats) {
        this.floats = floats;
    }

    public char getChars() {
        return chars;
    }

    public void setChars(char chars) {
        this.chars = chars;
    }

    public boolean isBooleans() {
        return booleans;
    }

    public void setBooleans(boolean booleans) {
        this.booleans = booleans;
    }

    public String getTimeData() {
        return timeData;
    }

    public void setTimeData(String timeData) {
        this.timeData = timeData;
    }

    public String getQQEmail() {
        return QQEmail;
    }

    public void setQQEmail(String QQEmail) {
        this.QQEmail = QQEmail;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }

    public TestRandomBean getTestRandomBean() {
        return testRandomBean;
    }

    public void setTestRandomBean(TestRandomBean testRandomBean) {
        this.testRandomBean = testRandomBean;
    }

    @StringFormatValue("n<7|12>[@qq.com]")
    private String QQEmail;
    private String randomString;

    private TestRandomBean testRandomBean;

    @Override
    public String toString() {
        return "TestRandomBean{" +
                "bytes=" + bytes +
                ", shorts=" + shorts +
                ", ints=" + ints +
                ", ints1=" + ints1 +
                ", longs=" + longs +
                ", doubles=" + doubles +
                ", floats=" + floats +
                ", chars=" + chars +
                ", booleans=" + booleans +
                ", timeData='" + timeData + '\'' +
                ", QQEmail='" + QQEmail + '\'' +
                ", randomString='" + randomString + '\'' +
                ", testRandomBean=" + testRandomBean +
                '}';
    }
}
