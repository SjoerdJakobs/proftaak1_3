package MainPackage.Simulation.Npc;

import java.awt.*;
import java.awt.geom.Point2D;

//Succes

public class SeatsHelper {
    public static Point2D getRandomSeatLA301() {
        int[] seatXs = {26, 28, 30, 32, 34, 36};
        int[] seatYs = {55, 57, 60, 62};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA302() {
        int[] seatXs = {63, 65, 67, 69, 71, 73};
        int[] seatYs = {55, 57, 60, 62};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA303() {
        int[] seatXs = {26, 28, 30, 32, 34, 36};
        int[] seatYs = {30, 32, 35, 37};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA304() {
        int[] seatXs = {63, 65, 67, 69, 71, 73};
        int[] seatYs = {30, 32, 35, 37};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatLA305() {
        int[] seatXs = {27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72};
        int[] seatYs = {4, 6, 8, 10};
        return getPoint2D(seatXs, seatYs);
    }

    public static Point2D getRandomSeatCanteen() {
        int[] seatXs = {25, 29, 31, 35, 64, 68, 70, 74};
        int[] seatYs = {71, 72, 73, 74, 75};
        return getPoint2D(seatXs, seatYs);
    }

    private static Point2D getPoint2D(int[] seatXs, int[] seatYs) {
        int seatX = seatXs[(int) (Math.random() * seatXs.length)];
        int seatY = seatYs[(int) (Math.random() * seatYs.length)];
        return new Point2D.Double(seatX * 16, seatY * 16);
    }
}
