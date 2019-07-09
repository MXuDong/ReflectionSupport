package io.github.mxudong.rs.bean;

/**
 * @author Dong
 * @since 3.0
 */

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
