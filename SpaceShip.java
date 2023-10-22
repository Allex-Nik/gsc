import java.awt.*;

import javax.swing.JPanel;

/**
 * Describes the class for the spaceship.
 */

public class SpaceShip {
    int x = 0;
    int y = 0;
    int width;
    int height;
    int speed = 0;
    int health;
    int ammunition;
    Rectangle shape;

    public SpaceShip(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //this.speed = speed;
        //this.health = health;
        //this.ammunition = ammunition;
        this.shape = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 1390 && x > 480) {
            this.x = x;
            shape.setLocation(x, y);
        }
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public Projectile fire() {
        return new Projectile(this);
    }

    public void collectDebris() {

    }

    public void takeDamage() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
