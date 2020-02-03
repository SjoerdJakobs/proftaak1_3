package Data;

import OOFramework.FrameworkProgram;

public class Student extends Person {
    private SchoolClass schoolClass;

    public Student(FrameworkProgram frameworkProgram, String name, SchoolClass schoolClass) {
        super(frameworkProgram, name);
        this.schoolClass = schoolClass;
    }

    // Getters and Setters
    public SchoolClass getSchoolClass() { return this.schoolClass; }
}
