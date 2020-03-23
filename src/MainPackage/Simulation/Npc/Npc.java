package MainPackage.Simulation.Npc;

import MainPackage.Simulation.Logic.Direction;
import MainPackage.Simulation.Logic.LogicalTile;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Npc extends StandardObject {
    protected LogicalTile currentTile;
    protected Point2D position;
    protected BufferedImage[] spriteSheet = SPRITESHEET.Sprites;
    protected BufferedImage[] mySprites = new BufferedImage[12];
    protected FXGraphics2D graphics2D;
    protected int walkcyle = 0;
    protected double timePassed = 0;
    protected Direction direction = Direction.DOWN;
    protected double speed = 100;
    protected double straightspeed = speed;
    protected double diagonalSpeed = speed/2;
    protected Point2D target = new Point2D.Double(1300, 300);
    protected double cycleTime = 0.3;

    protected Npc(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D, Point2D position) {
        super(frameworkProgram);
        this.graphics2D = graphics2D;
        this.position = position;
        initializeSprites();
    }


    @Override
    protected void InputLoop(double deltaTime) {

    }

    @Override
    protected void MainLoop(double deltaTime) {
        testCycle(deltaTime);
        if(moveTo(deltaTime, target)){
            position.setLocation(target);
        }
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        draw();
    }

    protected void initializeSprites() {
    }


    public BufferedImage getImageToDraw(Direction direction, boolean isWalking) {
        /* this method returns the image needed to draw the Npc given the direction and if the npc is walking or not, the cycle
        is handled internally so it returns a natural walkcycle.
         */
        int spriteLayer = 0;
        switch (direction) {


            case UP:
                spriteLayer = 3;
                break;
            case DOWN:
                spriteLayer = 0;
                break;
            case LEFT:
                spriteLayer = 1;
                break;
            case RIGHT:
                spriteLayer = 2;
                break;
        }


        if (!isWalking) {
            return mySprites[spriteLayer * 3 + 1];
        } else {
            switch (walkcyle) {
                case 0:
                    System.out.println("0");
                case 2:
                    System.out.println("0/2");
                    return mySprites[spriteLayer * 3 + 1];
                case 1:
                    System.out.println("1");
                    return mySprites[spriteLayer * 3];

                case 3:
                    System.out.println(2);
                    return mySprites[spriteLayer * 3 + 2];

                default:
                    return mySprites[spriteLayer * 3 + 1];


            }
        }
    }
    protected void testCycle(double deltatime) {

     //Handles the walkcylcle so walking looks natural

        timePassed += deltatime;


        if (timePassed > cycleTime) {
            timePassed = 0;
            walkcyle++;
            if (walkcyle == 4) {
                walkcyle = 0;
            }
        }
    }

    protected boolean moveTo(double deltaTime, Point2D target) {
        //moves the npc tp a certain target


        //check if the npc is moving right or left relatively to itself
        int moveDirectionX, moveDirectionY;
        if((int)target.getX() - (int)position.getX() < 0) {
            moveDirectionX = -1;
            direction = Direction.LEFT;
        } else if((int)target.getX() - (int)position.getX() > 0) {
            moveDirectionX = 1;
            direction = Direction.RIGHT;
        } else {
            moveDirectionX = 0;
        }

        //checks if the npc needs to move up or down relatively
        if((int)target.getY() - (int)position.getY() < 0) {
            moveDirectionY = -1;
            direction = Direction.UP;
        } else if ((int)target.getY() - (int)position.getY() > 0) {
            moveDirectionY = 1;
            direction = Direction.DOWN;
        } else {
            moveDirectionY = 0;
        }

        if(moveDirectionX != 0 && moveDirectionY != 0) this.speed = diagonalSpeed;
        else this.speed = straightspeed;

        position.setLocation(position.getX() + moveDirectionX * deltaTime * speed, position.getY() + moveDirectionY * deltaTime * speed);

        if(target.getX() - position.getX() < 10 && target.getX() - position.getX() > -10 &&
            target.getY() - position.getY() < 10 && target.getY() - position.getY() > -10){
            return true;
        }
        return false;
    }

    protected void draw(){
        graphics2D.drawImage(getImageToDraw(direction, true), (int)position.getX(), (int)position.getY(), null);
    }


}

