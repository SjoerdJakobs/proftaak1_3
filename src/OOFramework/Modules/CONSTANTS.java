package OOFramework.Modules;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CONSTANTS
{
    public static final String TITLE = "SNAVA simulation";        // title of the game
    public static final String PROGRAM_ICON = "/icon.png"; //Remember to mark resources as "resources root" otherwise it wont work

    public static final String MAP_JSONFILE = "resources/mapTest.json";
    public static final String DIRLAYER_TOSHOW = "";

    public static final float CAMERAZOOM_SENSITIVIY = 350.0f; //Sensitivity of the zooming in the simulation. Higher = less zoom per step

    public static final String STANDARD_SAVE_FILE_PATH = "SchoolSimulationSaveFile";

    public static final int UPDATES_PER_SEC = 60;  // number of game update per second
    public static final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SEC;  // nanoseconds

    public static final boolean DEBUG_MODE = true;

    public static final long TEACHER_SERIAL_VERSION_UID = 44;
}
