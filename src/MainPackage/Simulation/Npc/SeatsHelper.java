package MainPackage.Simulation.Npc;

import java.awt.geom.Point2D;
import java.util.ArrayList;

//Succes
public class SeatsHelper {
    private static ArrayList<Point2D> takenSeats = new ArrayList<>();

    public static Point2D getRandomSeatLA301(boolean isTeacher) {
        if (isTeacher) return new Point2D.Double(45, 60);
        int[] seatXs = {26, 28, 30, 32, 34, 36};
        int[] seatYs = {55, 57, 60, 62};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA302(boolean isTeacher) {
        if (isTeacher) return new Point2D.Double(54, 60);
        int[] seatXs = {63, 65, 67, 69, 71, 73};
        int[] seatYs = {55, 57, 60, 62};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA303(boolean isTeacher) {
        if (isTeacher) return new Point2D.Double(45, 35);
        int[] seatXs = {26, 28, 30, 32, 34, 36};
        int[] seatYs = {30, 32, 35, 37};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA304(boolean isTeacher) {
        if (isTeacher) return new Point2D.Double(54, 35);
        int[] seatXs = {63, 65, 67, 69, 71, 73};
        int[] seatYs = {30, 32, 35, 37};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA305(boolean isTeacher) {
        if (isTeacher) return new Point2D.Double(52, 1);
        int[] seatXs = {27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72};
        int[] seatYs = {4, 6, 8, 10};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatCanteen() {
        int[] seatXs = {25, 29, 31, 35, 64, 68, 70, 74};
        int[] seatYs = {71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82};
        return getPoint2D(seatXs, seatYs);
    }

    private static Point2D getPoint2D(int[] seatXs, int[] seatYs) {
        int seatX = seatXs[(int) (Math.random() * seatXs.length)];
        int seatY = seatYs[(int) (Math.random() * seatYs.length)];
        Point2D seat = new Point2D.Double(seatX * 16, seatY * 16);
        System.out.println(takenSeats.size());
        for (Point2D takenSeat : takenSeats) {
            System.out.println(takenSeat.getX() + " " + seat.getX() + " " + takenSeat.getY() + " " + seat.getY());
            if (takenSeat.getX() == seat.getX() && takenSeat.getY() == seat.getY()) {
                System.out.println("seat taken!");
                return getPoint2D(seatXs, seatYs);
            }
        }
        takenSeats.add(seat);
        return seat;
    }
}
