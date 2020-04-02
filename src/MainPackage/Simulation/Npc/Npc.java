package MainPackage.Simulation.Npc;

import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.Simulation.Logic.Direction;
import MainPackage.Simulation.Logic.LogicalTile;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    protected Point2D target = null;
    protected double cycleTime = 0.3;
    protected ArrayList<Npc> npcs = new ArrayList();
    public boolean waiting = false;

    protected boolean hasBreak = false;

    protected StudentData studentData;

    protected Npc(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D, Point2D position, StudentData studentData) {
        super(frameworkProgram);
        this.graphics2D = graphics2D;
        this.position = position;
        this.studentData = studentData;
        initializeSprites();
    }


    @Override
    protected void InputLoop(double deltaTime) {

    }

    @Override
    protected void MainLoop(double deltaTime) {
        testCycle(deltaTime);
        if(target != null){
            if(moveTo(deltaTime, target)){
                position.setLocation(target);
            }
        }

    }

    @Override
    protected void RenderLoop(double deltaTime) {
        draw();
    }

    protected void initializeSprites() {
    }


    public BufferedImage getImageToDraw(Direction direction, boolean isWalking) {
     //   System.out.println("drawing!");
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
                    //System.out.println("0");
                case 2:
                    //System.out.println("0/2");
                    return mySprites[spriteLayer * 3 + 1];
                case 1:
                    //System.out.println("1");
                    return mySprites[spriteLayer * 3];

                case 3:
                    //System.out.println(2);
                    return mySprites[spriteLayer * 3 + 2];

                default:
                    return mySprites[spriteLayer * 3 + 1];


            }
        }
    }

    protected void testCycle(double deltatime) {
        timePassed += deltatime;

        if (timePassed > cycleTime) {
            timePassed = 0;
            walkcyle++;
            if (walkcyle == 4) {
                walkcyle = 0;
            }
        }
    }

    public boolean moveTo(double deltaTime, Point2D target) {
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
//        Point2D.Double newPosition = new Point2D.Double(position.getX() + moveDirectionX * deltaTime * speed, position.getY() + moveDirectionY * deltaTime * speed);
//
//        boolean collided = false;
//
//        for(Npc npc : npcs){
//            if(npc != this && newPosition.distance(npc.position) < 32){
//                collided = true;
//                if(npc.waiting){
//                    waiting = false;
//                }
//                else {
//                    waiting = true;
//                }
//            }
//        }
//        if(!collided){
//            position.setLocation(position.getX() + moveDirectionX * deltaTime * speed, position.getY() + moveDirectionY * deltaTime * speed);
//        }
//        else {
//            if(!waiting){
//                position.setLocation(position.getX() + moveDirectionX * deltaTime * speed, position.getY() + moveDirectionY * deltaTime * speed);
//            }
//        }

        if(target.getX() - position.getX() < 10 && target.getX() - position.getX() > -10 &&
            target.getY() - position.getY() < 10 && target.getY() - position.getY() > -10){
            return true;
        }
        return false;
    }

    protected void draw(){
        graphics2D.drawImage(getImageToDraw(direction, true), getTransform(), null);
    }

    private AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() + 8, position.getY() + 8);
      //  tx.rotate(0, 16, 16);
        return tx;
    }

    public void setNpcs(ArrayList<Npc> npcs){
        this.npcs = npcs;
    }

    public Point2D getPosition(){
        return position;
    }


    public StudentData getStudentData() {
        return studentData;
    }

    public boolean isHasBreak() {
        return hasBreak;
    }

    public void setHasBreak(boolean hasBreak) {
        this.hasBreak = hasBreak;
    }

    public String getNameStudent(){
        return this.studentData.getName();
    }
}

