package Data;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

public abstract class Person extends StandardObject {
    private String name;
    private int age;
    private Gender gender;


    protected Person(FrameworkProgram frameworkProgram, String name, int age, Gender gender) {
        super(frameworkProgram);
        this.name = name;
        this.age = age;
        this.gender = gender;
    }


    // Getters and Setters
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
