package Data;

import OOFramework.FrameworkProgram;

public class Student extends Person {
    private StudentGroup studentGroup;

    public Student(FrameworkProgram frameworkProgram, String name, StudentGroup studentGroup) {
        super(frameworkProgram, name);
        this.studentGroup = studentGroup;
    }

    // Getters and Setters
    public StudentGroup getStudentGroup() { return this.studentGroup; }
}
