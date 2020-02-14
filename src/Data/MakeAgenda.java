package Data;

import Data.Rooms.ClassRoom;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import OOFramework.FrameworkProgram;

import java.time.LocalTime;

public class MakeAgenda {

    Agenda agenda;

    public MakeAgenda() {
        this.agenda = new Agenda();
        for(int i=0; i<4; i++){

            agenda.addLesson(new Lesson(new LessonData(0, 20,
                    LocalTime.of(i+8,0),LocalTime.of(i+9,0), (300 + i), 2)));
        }

//        this.agenda.addLesson(new Lesson(new StudentGroup("B"),new Teacher("Johan"),
//                LocalTime.of(17,0),LocalTime.of(19,0),new ClassRoom(300 )));

    }

    public Agenda getAgenda(){
        return agenda;
    }
}
