import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Debris {
    int init_x, init_y, fin_x, fin_y;
    int x, y;
    int radius = 10;
    int speed = 1;
    int pointsWorth;
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;

    int m;
    int c;

    Ellipse2D.Double shape;
    boolean visible;
    
    public Debris(int init_x, int init_y, int fin_x, int fin_y) {
        this.init_x = init_x;
        this.init_y = init_y;
        this.fin_x = fin_x;
        this.fin_y = fin_y;
        this.x = init_x;
        this.y = init_y;
        this.visible = true;

        // Make debris a shape that can have collisions
        
        this.shape = new Ellipse2D.Double(x, y, radius, radius);
    
        //Trajectory
        //y = mx + c

        m = (fin_y - init_y) / (fin_x - init_x);
        c = init_y - (m * init_x);
    }

    public void move() {
        y += speed;
        x = (y - c) / m;
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

    public void collisionDetection(SpaceShip object) {
        if (shape.intersects(object.shape.getBounds2D())) {
            System.out.println("Collision detected");
            this.shape = null;
            this.visible = false;
            
        }
    }
}
