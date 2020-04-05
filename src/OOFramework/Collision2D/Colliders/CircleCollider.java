package OOFramework.Collision2D.Colliders;

import OOFramework.Collision2D.CollisionSystem;

import java.awt.geom.Point2D;

public class CircleCollider extends Collider2D
{
    public double  radius;

    public CircleCollider(Point2D pos, double radius)
    {
        this.radius = radius;
        this.pos = pos;

        collisionCallback = this::UpdateCollisons;
        CollisionSystem.getInstance().allColliders.add(this);
    }


    //the check for collision call
    @Override
    public boolean Collide(Collider2D other)
    {
        return CircleCollision((CircleCollider)other);
    }

    
    @Override
    public boolean CircleCollision(CircleCollider other)
    {
        double xDist = other.getPos().getX() - this.pos.getX();
        double yDist = other.getPos().getY() - this.pos.getY();
        return ((this.radius + other.radius) > Math.sqrt(xDist*xDist + yDist*yDist));
    }
}
