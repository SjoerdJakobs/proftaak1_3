package MainPackage;

import Data.Lesson;
import Data.Rooms.ClassRoom;
import Data.Teacher;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.TeacherData;
import OOFramework.Renderable;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class HourBlock extends Renderable
{
    private LocalTime beginTime;
    private LocalTime endTime;
    private String classId;
    private String teacher;
    private int roomNr;
    private Color color;
    private LessonData lessonData;

    private String  text;

    private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);

    public HourBlock(Shape shape, Point2D position, LessonData lesson, Color color) {
        super(shape, position, 0, 1);
        this.lessonData = lesson;
        this.beginTime = lesson.getBeginTime();
        this.endTime = lesson.getEndTime();
        this.classId = SavedData.INSTANCE.findStudentGroupDataByID(lesson.getStudentGroup()).name;
        this.teacher = SavedData.INSTANCE.findTeacherDataByID(lesson.getTeacher()).name;
        this.roomNr = lesson.getClassRoom();
        this.color = color;
        this.text = "\n" +classId+"\n"+teacher+"\n"+"LA"+roomNr+"\n"+ beginTime +" - "+endTime;
    }

    @Override
    public void draw(FXGraphics2D g2d) {
        g2d.draw(shape);
        super.draw(g2d);
        g2d.setColor(this.color);
        //System.out.println("draw1");
        g2d.fill(shape);
        g2d.setFont(sanSerifFont);
        FontMetrics fm = g2d.getFontMetrics();
//        int w = fm.stringWidth(text);
//        int h = fm.getAscent();
        g2d.setColor(Color.black);
        g2d.drawString(text, (int)position.getX(), (int) position.getY());
    }

    public LocalTime getBeginTime() {
        return lessonData.getBeginTime();
    }

    public void setBeginTime(LocalTime beginTime) {
        lessonData.setBeginTime(beginTime);
    }

    public LocalTime getEndTime() {
        return lessonData.getEndTime();
    }

    public void setEndTime(LocalTime endTime) { lessonData.setEndTime(endTime);
    }

    public String getClassId() {
        return lessonData.getStudentGroup().getName();
    }

    public void setClassId(String classId) { lessonData.getStudentGroup().setName(classId);
    }

    public TeacherData getTeacher() {
        return lessonData.getTeacher();
    }

    public void setTeacher(TeacherData teacher) { lessonData.setTeacher(teacher);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static Font getSanSerifFont() {
        return sanSerifFont;
    }

    public static void setSanSerifFont(Font sanSerifFont) {
        HourBlock.sanSerifFont = sanSerifFont;
    }

    public ClassRoom getRoomNr() {
        return lessonData.getClassRoom();
    }

    public void setRoomNr(ClassRoom room) {
        lessonData.setClassRoom(room);
    }


    public LessonData getLessonData() {
        return lessonData;
    }
}
