import java.awt.*;
import javax.swing.ImageIcon;

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
    Rectangle bounds;
    Image imageSpaceShip;

    public SpaceShip(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //this.speed = speed;
        //this.health = health;
        //this.ammunition = ammunition;
        this.bounds = new Rectangle(x, y, width, height);

        ImageIcon imageSpaceShipSource = new ImageIcon("Assets/SpaceShip.png");
        imageSpaceShip = imageSpaceShipSource.getImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 1390 && x > 480) {
            this.x = x;
            bounds.setLocation(x, y);
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
        return new Projectile(this.x + this.width / 2, this.y, "UP", false);
    }

    public void collectDebris() {

    }

    public void takeDamage() {

    }

    public void draw(Graphics g) {  
        g.drawImage(imageSpaceShip, this.x-20, this.y, null);
        g.setColor(Color.BLUE);
        g.drawRect(this.x, this.y, width, height);
    }

    public boolean collisionDetection(Projectile p) {
        return this.bounds.intersects(p.bounds);
    }
}
