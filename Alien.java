import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

/**
 * Contains classes of all the entities.
 */

public class Alien {
    public double x = 0.0;
    public double y;
    private int startY;
    private double deltaX = 0.2;
    public int width;
    public int height;
    private String direction;

    public Alien(int x, int startY, String direction) {
        this.x = x;
        this.y = startY;
        this.startY = startY;
        this.deltaX = deltaX;
        this.width = 30;
        this.height = 30;
        this.direction = direction;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX() {
        this.x = x;
    }

    public void setY() {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect((int) x, (int) y, width, height);
    }
    
    // tragectory: y = sin(x)
    public void move() {
        if (direction.equals("LEFT")) {
            x += deltaX;
        } else {
            x -= deltaX;
        }
        y = (100 * Math.sin(x * Math.PI / 180)) + startY;
    }

    public Projectile fire() {
        // Create an instance of class Projectile
        return new Projectile((int) this.x + this.width / 2, (int) this.y + this.height, "DOWN");
    }

    public void die() {
        // if a projectile hits an alien, the alien dies (delete the instance of class Alien)
    }

};
