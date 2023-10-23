import java.awt.Color;
import java.awt.Graphics;

public class Projectile {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed = 5;

    public Projectile(SpaceShip ship) {
        this.speed = speed;
        this.x = ship.getX() + ship.getWidth() / 2;
        this.y = ship.getY();
        this.width = 5;
        this.height = 10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY() {
        this.y = y;
    }

    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
