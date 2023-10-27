import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import java.util.Random;


public class Debris {
    int initX, initY, finX, finY;
    int x, y;
    int radius = 10;
    int speed = 1;
    int pointsWorth;
    final int SCREEN_WIDTH = 1920;
    final int SCREEN_HEIGHT = 1080;

    int m;
    int c;

    Ellipse2D.Double bounds;
    boolean visible;

    Image imageDebris;

    
    public Debris() {
        Random random = new Random();
        this.initX = random.nextInt(SCREEN_WIDTH / 4, (SCREEN_WIDTH * 3) / 4);
        this.initY = 0;
        this.finX = random.nextInt(SCREEN_WIDTH / 4, (SCREEN_WIDTH * 3) / 4);
        this.finY = SCREEN_HEIGHT;
        this.x = initX;
        this.y = initY;
        this.visible = true;
        this.pointsWorth = 1;

        imageDebris = new ImageIcon("Assets/Debris.png").getImage();
        imageDebris = imageDebris.getScaledInstance(radius, radius, Image.SCALE_DEFAULT);

        // Make debris a shape that can have collisions
        
        this.bounds = new Ellipse2D.Double(x, y, radius, radius);
    
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

        bounds.setFrame(x, y, radius, radius);
    }

    public void draw(Graphics g){
        g.drawImage(imageDebris, x, y, null);
        //g.setColor(Color.WHITE);
        //g.fillOval(x, y, radius, radius);

    }

    public int getPoints() {
        return pointsWorth;
    }

    public boolean collisionDetection(SpaceShip object) {
        return this.bounds.intersects(object.bounds);
    }
}
