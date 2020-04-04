package MainPackage.Simulation.Npc;

import TiledParser.Spriteloader;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SPRITESHEET {
    public static BufferedImage spriteSheet = null;

    static {
        try {
            spriteSheet = ImageIO.read(new File("resources/Sprites.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage[] Sprites = null;

    static {
        try {
            Sprites = Spriteloader.getImages(spriteSheet,32,32);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
