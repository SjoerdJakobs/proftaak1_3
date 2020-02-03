package Data;

public class Student extends Person {
    private StudentClass studentClass;

    public Student(String name, StudentClass studentClass) {
        super(name);
        this.studentClass = studentClass;
    }
}
