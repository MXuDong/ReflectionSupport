package io.github.mxudong.beans;

/**
 * Class Name : Classic
 * Create Time : 15:58
 * Create Date : 2019/6/25
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since 2.1
 */

public class Classic {
    Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Classic{" +
                "student=" + student +
                '}';
    }
}
