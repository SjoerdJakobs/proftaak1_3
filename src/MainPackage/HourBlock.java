package MainPackage;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;

public class HourBlock extends Renderable
{
    private int     beginTime;
    private int     endTime;
    private String  classId;
    private String  teacher;
    private Color   color;

    public HourBlock(Shape shape, Point2D position, int beginTime, int endTime,String classId,String teacher, Color color) {
        super(shape, position, 0, 1);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.classId = classId;
        this.teacher = teacher;
        this.color = color;
    }

    @Override
    public void draw(FXGraphics2D g2d) {
        super.draw(g2d);
        g2d.fill(getTransformedShape());
    }
}
