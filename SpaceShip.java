import java.awt.*;

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

    public SpaceShip(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 725 && x > 25) {
            this.x = x;
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
        return new Projectile(this.x + this.width / 2, this.y, "UP");
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
