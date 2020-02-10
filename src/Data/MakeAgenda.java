package Data;

import Data.Rooms.ClassRoom;
import OOFramework.FrameworkProgram;

import java.time.LocalTime;

public class MakeAgenda {

    Agenda agenda;

    public MakeAgenda(FrameworkProgram frameworkProgram) {
        this.agenda = new Agenda();
        for(int i=0; i<4; i++){
            agenda.addLesson(new Lesson(new StudentGroup("B"),new Teacher(frameworkProgram,"Johan"),
                    LocalTime.of(i+8,0),LocalTime.of(i+9,30),new ClassRoom("LA301")));
            System.out.println("les");
        }


    }

    public Agenda getAgenda(){
        return agenda;
    }
}
