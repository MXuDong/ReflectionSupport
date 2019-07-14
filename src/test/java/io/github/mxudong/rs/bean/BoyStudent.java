package io.github.mxudong.rs.bean;

import io.github.mxudong.rs.annotations.TestAnnotation;
import io.github.mxudong.rs.randoms.annotations.RandomLimit;

/**
 * @author Dong
 * @since 3.0
 */

@TestAnnotation()
@RandomLimit(defaultChars = "aaaaaa", defaultFormat = "c3c3c3")
public class BoyStudent extends Man implements SpeakAble {

    private String grade;

    @Override
    public String toString() {
        return "BoyStudent{" +
                "grade='" + grade + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public void printWord() {
        System.out.println("Test");
    }
}
