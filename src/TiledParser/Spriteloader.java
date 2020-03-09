package TiledParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spriteloader {

    public static BufferedImage[] getImages(BufferedImage sourceImage, int singleWidth, int singleHeight) throws IOException {
        //Calculate the amount of sprites we need and set the array size accordingly
        int amount = ((sourceImage.getWidth() / singleWidth) * (sourceImage.getHeight() / singleHeight));
        BufferedImage[] images = new BufferedImage[amount];

        int id = 0;
        for (int i = 0; i < sourceImage.getHeight(); i += singleHeight) {
            for (int j = 0; j < sourceImage.getWidth(); j += singleWidth) {
                //Crop the source image in many individual sprites
                //And put them in an array with their ID according to their index
                images[id] = sourceImage.getSubimage(j, i, singleWidth, singleHeight);
                id++;
            }
        }
        return images;
    }


}
