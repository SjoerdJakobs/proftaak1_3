package MainPackage;

import Data.Lesson;
import Data.Rooms.ClassRoom;
import Data.Teacher;
import MainPackage.ReadWriteData.SavedData;
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
    private Lesson lesson;

    private String  text;

    private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);

    public HourBlock(Shape shape, Point2D position, Lesson lesson, Color color) {


        super(shape, position, 0, 1);
        this.lesson = lesson;
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
        return lesson.getBeginTime();
    }

    public void setBeginTime(LocalTime beginTime) {
        lesson.setBeginTime(beginTime);
    }

    public LocalTime getEndTime() {
        return lesson.getEndTime();
    }

    public void setEndTime(LocalTime endTime) { lesson.setEndTime(endTime);
    }

    public int getClassId() {
        return lesson.getStudentGroup();
    }

    public void setClassId(int classId) { lesson.setStudentGroup(classId);
    }

    public int getTeacher() {
        return lesson.getTeacher();
    }

    public void setTeacher(int teacher) { lesson.setTeacher(teacher);
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

    public int getRoomNr() {
        return lesson.getClassRoom();
    }

    public void setRoomNr(int room) {
        lesson.setClassRoom(room);
    }




}
