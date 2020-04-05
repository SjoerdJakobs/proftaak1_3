package OOFramework.Collision2D.Colliders;

import OOFramework.Collision2D.CollisionCallback;
import OOFramework.Collision2D.CollisionSystem;
import OOFramework.Modules.ASSERT_MSG;

import java.awt.geom.Point2D;

public abstract class Collider2D
{
    protected Object ownerObject = null;
    protected boolean isColliding;

    public CollisionCallback collisionCallback;

    protected Point2D pos;

    //check for collision
    public boolean Collide(Collider2D other)
    {
        return false;
    }

    //collisionChecks
    protected boolean CircleCollision(CircleCollider other)
    {
        ASSERT_MSG.ASSERT_MSG(true, "the collision checks should not call the parent class");
        return false;
    }
    /////////////////

    public void OnDestroy()
    {
        CollisionSystem.getInstance().allColliders.remove(this);
    }

    public void UpdateCollisons(Collider2D other)
    {

    }

    public Object getOwnerObject()
    {
        return ownerObject;
    }

    public void setOwnerObject(Object ownerObject)
    {
        this.ownerObject = ownerObject;
    }

    public CollisionCallback getCollisionCallback()
    {
        return collisionCallback;
    }

    public boolean getIsColliding()
    {
        return isColliding;
    }

    public void setIsColliding(boolean isColl)
    {
        this.isColliding = isColl;
    }

    public Point2D getPos()
    {
        return pos;
    }

    public void setPos(Point2D pos)
    {
        this.pos = pos;
    }
}
