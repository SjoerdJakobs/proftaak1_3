package Data;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

public class Person extends StandardObject {
    private String name;

    protected Person(FrameworkProgram frameworkProgram, String name) {
        super(frameworkProgram);
        this.name = name;
    }


    // Getters and Setters
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
}
