package TiledParser;

public class Camera {
    private int xOffset, yOffset;
    private double zoom;

    private double lastMouseX, lastMouseY;

    public Camera(int xOffset, int yOffset, double zoom) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zoom = zoom;
    }

    public Camera() {
        this(0, 0, 0.5);
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public double getZoom() {
        return zoom;
    }

    public void zoom(double zoomAmount) {
        this.zoom *= 1 + zoomAmount / 1000.0;
        //System.out.println(zoom);
    }

    public void pan(double mouseX, double mouseY) {
        double panAmountX = mouseX - lastMouseX;
        double panAmountY = mouseY - lastMouseY;

        this.xOffset += panAmountX;
        this.yOffset += panAmountY;

        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;

    }
}
