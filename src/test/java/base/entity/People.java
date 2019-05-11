package base.entity;

/**
 * @author Dong
 * <p>
 * Class Name : People
 * Create Time : 20:18
 * Create Date : 2019/5/11
 * Project : ReflectionSupport
 */

public class People {
    private String name;
    private String number;
    private int age;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
