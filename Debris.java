import java.awt.*;
import java.awt.geom.Ellipse2D;


public class Debris {
    int initX, initY, finX, finY;
    int x, y;
    int radius = 10;
    int speed = 1;
    int pointsWorth;

    int m;
    int c;

    Ellipse2D.Double shape;
    boolean visible;

    
    public Debris(int initX, int initY, int finX, int finY) {
        this.initX = initX;
        this.initY = initY;
        this.finX = finX;
        this.finY = finY;
        this.x = initX;
        this.y = initY;
        this.visible = true;

        // Make debris a shape that can have collisions
        
        this.shape = new Ellipse2D.Double(x, y, radius, radius);
    
        //Trajectory
        //y = mx + c

        m = (finY - initY) / (finX - initX);
        c = initY - (m * initX);
    }

    public void move() {
        y += speed;
        try {
            x = (y - c) / m;
        } catch (ArithmeticException e) {
            x = (y - c) / (m + 1);
        }

        shape.setFrame(x, y, radius, radius);
    }

    public void draw(Graphics g){
        if (visible) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, radius, radius);
        } else {
            g.dispose();
        }

    }

    public int getPoints() {
        return pointsWorth;
    }

    public boolean collisionDetection(SpaceShip object) {
        boolean collisionFlag = false;
        if (shape.intersects(object.shape.getBounds2D())) {
            System.out.println("Collision detected");
            this.visible = false;
            collisionFlag = true;
        }
        return collisionFlag;
    }
}
