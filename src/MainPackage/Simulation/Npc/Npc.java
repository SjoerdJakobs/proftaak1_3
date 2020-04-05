package MainPackage.Simulation.Npc;

import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.Simulation.Logic.LogicalTile;

import OOFramework.Collision2D.Colliders.CircleCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import gridMaker.Direction;
import gridMaker.Tile;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Npc extends StandardObject {
    protected LogicalTile currentTile;
    protected BufferedImage[] spriteSheet = SPRITESHEET.Sprites;
    protected BufferedImage[] mySprites = new BufferedImage[12];
    private FXGraphics2D graphics2D;
    private int walkcyle = 0;
    private double timePassed = 0;
    private Direction direction = Direction.DOWN;
    private double speed = 100;
    private Point2D target = null;
    private double cycleTime = 0.3;
    private ArrayList<Npc> npcs = new ArrayList();
    public boolean waiting = false;
    private Point2D seat = null;

    private boolean hasBreak = false;
    private ArrayList<LessonData> lessons = new ArrayList<>();

    private StudentData studentData;
    private boolean atTarget = false;
    private int targetRoom;

    ////////////////////// added for movement
    private CircleCollider collider;
    private Point2D position;
    private int gridPosX = 0;
    private int gridPosY = 0;
    private int nextGridPosX = 0;
    private int nextGridPosY = 0;
    private double turnDelay;
    private double radius;
    private Point2D movDirection;
    private Point2D avoidUnitAdjustment;
    private Point2D avoidWallAdjustment;
    private String routeName = "canteen";

    private Tile[][] movementGrid;
    private boolean startCheckingForWallCollision;
    private int movSpeed = 100;
    /////////////////////////////////////////





    protected Npc(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D, Point2D position, StudentData studentData, Tile[][] movementGrid) {
        super(frameworkProgram);
        this.graphics2D = graphics2D;
        this.position = position;
        this.studentData = studentData;
        this.movementGrid = movementGrid;
        initializeSprites();

        movDirection = new Point2D.Double();
        avoidUnitAdjustment = new Point2D.Double();
        avoidWallAdjustment = new Point2D.Double();

        //this is all the collision code you need to set it up/
        this.collider = new CircleCollider(this.position,0);      //
        this.collider.collisionCallback = this::OnCollision; //
        this.collider.setOwnerObject(this);                  //
        ///////////////////////////////////////////////////////
        AlignWithGridPos(100);
        turnDelay = 0.1;
    }


    private boolean hasAllteredDirection = false;
    private double alignCounter = 0;

    public void AlignWithGridPos(double deltaTime)
    {
        nextGridPosX = (int)Math.floor(position.getX()/16);
        nextGridPosY = (int)Math.floor(position.getY()/16);

        if(nextGridPosY != gridPosY || gridPosX != nextGridPosX|| hasAllteredDirection)
        {
            alignCounter += deltaTime;
            if(alignCounter >= turnDelay)
            {
                gridPosY = nextGridPosY;
                gridPosX = nextGridPosX;
                if(gridPosX < 0)
                {
                    gridPosX = 0;
                }
                if(gridPosY < 0)
                {
                    gridPosY = 0;
                }
                hasAllteredDirection = false;
                alignCounter = 0;
            }
        }
    }

    public void AvoidWalls()
    {
        nextGridPosX = (int)Math.round((position.getX()-16)/16);
        nextGridPosY = (int)Math.round((position.getY()-16)/16);
        avoidWallAdjustment.setLocation(0,0);
        if(movementGrid[nextGridPosX][nextGridPosY].isHasWalToTheLeft())
        {
            if(position.getX() < nextGridPosX*16 + 32*0.6)
            {
                avoidWallAdjustment.setLocation(10+ avoidWallAdjustment.getX(),0+ avoidWallAdjustment.getY());
            }
        }
        if(movementGrid[nextGridPosX][nextGridPosY].isHasWalToTheRight())
        {
            if(position.getX() > nextGridPosX*16+ 32*0.6)
            {
                avoidWallAdjustment.setLocation(-10+ avoidWallAdjustment.getX(),0+ avoidWallAdjustment.getY());
            }
        }
        if(movementGrid[nextGridPosX][nextGridPosY].isHasWalBelow())
        {
            if(position.getY() > nextGridPosY*16+ 32*0.6)
            {
                avoidWallAdjustment.setLocation(0+ avoidWallAdjustment.getX(),-10+ avoidWallAdjustment.getY());
            }
        }
        if(movementGrid[nextGridPosX][nextGridPosY].isHasWalAbove())
        {
            if(position.getY() < nextGridPosY*16+ 32*0.6)
            {
                avoidWallAdjustment.setLocation(0+ avoidWallAdjustment.getX(),10+ avoidWallAdjustment.getY());
            }
        }
    }

    public void OnCollision(Collider2D other)
    {
            double newX = this.position.getX() - other.getPos().getX();
            double newY = this.position.getY() - other.getPos().getY();
            if(newX == 0)
            {
                newX = 0.1;
            }
            if (newY == 0)
            {
                newY = 0.1;
            }

            double magnitude = Math.sqrt(Math.pow(newX, 2) + Math.pow(newY, 2));
            newX /= magnitude;
            newY /= magnitude;
            avoidUnitAdjustment.setLocation(newX,newY);
            hasAllteredDirection = true;
            alignCounter += turnDelay*0.5;
    }


    @Override
    protected void InputLoop(double deltaTime) {

    }

    @Override
    protected void MainLoop(double deltaTime) {
        testCycle(deltaTime);
        //AvoidWalls();
        if(movementGrid[gridPosX][gridPosY].getDirections().get(routeName) != null)
        {
            direction = movementGrid[gridPosX][gridPosY].getDirections().get(routeName);

            double newX = 0;
            double newY = 0;
            switch (direction) {
                case UP:
                    newY = -1;
                    break;
                case DOWN:
                    newY = 1;
                    break;
                case LEFT:
                    newX = -1;
                    break;
                case RIGHT:
                    newX = 1;
                    break;
                case ENDPOINT:
                    newX = 0;
                    newY = 0;
                    break;
            }

            newX += avoidUnitAdjustment.getX();
            newY += avoidUnitAdjustment.getY();
            newX += avoidWallAdjustment.getX();
            newY += avoidWallAdjustment.getY();

            double magnitude = Math.sqrt(Math.pow(newX, 2) + Math.pow(newY, 2));
            newX /= magnitude;
            newY /= magnitude;
            if (direction == Direction.ENDPOINT){
                movDirection.setLocation(0, 0);
            }
            else{
                movDirection.setLocation(newX, newY);
            }
            position.setLocation(position.getX() + movDirection.getX() * deltaTime*movSpeed,position.getY() + movDirection.getY() * deltaTime*movSpeed);
            collider.setPos(position);
            AlignWithGridPos(deltaTime);
            movDirection.setLocation(0,0);
            avoidUnitAdjustment.setLocation(0,0);


        }
        else
        {
            System.out.println("out of range" + routeName);
        }
    }

    //////////////////

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

    public void setAtTarget(boolean atTarget) {
        this.atTarget = atTarget;
    }

    public boolean getAtTarget() {
        return atTarget;
    }

    public void goToSeat() {
        if (this.seat == null) {
            this.seat = getRandomSeat();
        } else {
            position.setLocation(seat);
        }
    }

    public void clearSeat() {
        seat = null;
    }

    private Point2D getRandomSeat() {
        switch (targetRoom) {
            case 301:
                return SeatsHelper.getRandomSeatLA301();
            case 302:
                return SeatsHelper.getRandomSeatLA302();
            case 303:
                return SeatsHelper.getRandomSeatLA303();
            case 304:
                return SeatsHelper.getRandomSeatLA304();
            case 305:
                return SeatsHelper.getRandomSeatLA305();
            default:
                return SeatsHelper.getRandomSeatCanteen();
        }
    }


    protected void draw() {
        graphics2D.drawImage(getImageToDraw(direction, true), getTransform(), null);
    }

    private AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX()-16, position.getY()-16);
        //  tx.rotate(0, 16, 16);
        return tx;
    }

    public void setNpcs(ArrayList<Npc> npcs) {
        this.npcs = npcs;
    }

    public Point2D getPosition() {
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

    public String getNameStudent() {
        return this.studentData.getName();
    }


    public ArrayList<LessonData> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<LessonData> lessons) {
        this.lessons = lessons;
    }


    public void setTargetRoom(int roomName) {
        this.targetRoom = roomName;
    }

    public void sortList(LocalTime time) {

        Collections.sort(this.lessons, new Comparator<LessonData>() {
            @Override
            public int compare(LessonData o1, LessonData o2) {
                int lesson1 = (o1.getBeginTime().getHour() * 60) + o1.getBeginTime().getMinute();
                int lesson2 = (o2.getBeginTime().getHour() * 60) + o2.getBeginTime().getMinute();


                return lesson1 - lesson2;
            }
        });

        for (LessonData lessonData : this.lessons) {
            if (time.isAfter(lessonData.getEndTime())) {
                this.lessons.remove(lessonData);
            }
        }


    }

    public boolean hasLessons() {
        if (this.lessons.size() >= 1) {
            return true;
        } else {
            return false;
        }

    }

    public String getRouteName()
    {
        return routeName;
    }

    public void setRouteName(String routeName)
    {
        this.routeName = routeName;
    }
}

