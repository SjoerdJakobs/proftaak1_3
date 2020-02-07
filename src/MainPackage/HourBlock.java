package MainPackage;

import OOFramework.Renderable;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;

public class HourBlock extends Renderable
{
    private int     beginTime;
    private int     endTime;
    private String  classId;
    private String  teacher;
    private String  roomNr;
    private Color   color;

    private String  text;

    private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);

    public HourBlock(Shape shape, Point2D position, int beginTime, int endTime,String classId,String roomNr,String teacher, Color color) {
        super(shape, position, 0, 1);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.classId = classId;
        this.teacher = teacher;
        this.roomNr = roomNr;
        this.color = color;
        this.text = classId+"\n"+teacher+"\n"+roomNr+"\n"+ beginTime +" - "+endTime;
    }

    @Override
    public void draw(FXGraphics2D g2d) {
        g2d.setColor(this.color);
        super.draw(g2d);
        //System.out.println("draw1");
        g2d.fill(getTransformedShape());
        g2d.setFont(sanSerifFont);
        FontMetrics fm = g2d.getFontMetrics();
        int w = fm.stringWidth(text);
        int h = fm.getAscent();
        g2d.setColor(Color.black);
        g2d.drawString(text, (int)position.getX() - (w / 2), (int) position.getY() + (h*-1));
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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

    public String getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(String roomNr) {
        this.roomNr = roomNr;
    }
}
