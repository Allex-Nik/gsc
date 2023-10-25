import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.lang.Math;

import javax.swing.ImageIcon;

/**
 * Contains classes of all the entities.
 */

public class Alien {
    public double x = 0.0;
    public double y;
    private int startY;
    private double deltaX = 0.5;
    public int width;
    public int height;
    private String direction;
    Rectangle bounds;
    Image imageAlien;

    public Alien(int x, int startY, String direction) {
        this.x = x;
        this.y = startY;
        this.startY = startY;
        this.deltaX = deltaX;
        this.width = 50;
        this.height = 50;
        this.direction = direction;
        this.bounds = new Rectangle(x, startY, this.width, this.height);
        ImageIcon imageAlienSource = new ImageIcon("Assets/Alien.png");
        imageAlien = imageAlienSource.getImage();
        imageAlien = imageAlien.getScaledInstance(width, height, Image.SCALE_DEFAULT);
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
        g.drawImage(imageAlien, (int)this.x, (int)this.y, null);
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
        bounds.setLocation((int) x, (int) y);
    }

    public Projectile fire() {
        // Create an instance of class Projectile
        return new Projectile((int) this.x + this.width / 2, (int) this.y + this.height, "DOWN", true);
    }

    public void die() {
        // if a projectile hits an alien, the alien dies (delete the instance of class Alien)
    }

    public boolean collisionDetection(Projectile p) {
        // if a projectile hits an alien, the alien dies (delete the instance of class Alien)
        return this.bounds.intersects(p.bounds);
    }

};
