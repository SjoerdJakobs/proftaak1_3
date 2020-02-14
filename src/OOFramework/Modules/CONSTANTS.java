package OOFramework.Modules;

public class CONSTANTS
{
    public static final String TITLE = "School simulation";        // title of the game
    public static final int CANVAS_WIDTH = 1920;    // width and height of the drawing canvas
    public static final int CANVAS_HEIGHT = 1080;

    public static final String STANDARD_SAVE_FILE_PATH = "SchoolSimulationSaveFile";
    public static final int UPDATES_PER_SEC = 60;  // number of game update per second
    public static final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SEC;  // nanoseconds

    public static final boolean DEBUG_MODE = true;
}
