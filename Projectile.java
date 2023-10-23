import java.awt.Color;
import java.awt.Graphics;

public class Projectile {
    public int x;
    public int y;
    public int width;
    public int height;
    private int speedShipsProjectiles = 15;
    private int speedAlienProjectiles = 6;
    public String direction;

    public Projectile(int startX, int startY, String direction) {
        this.x = startX;
        this.y = startY;
        this.direction = direction;
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
        if (direction.equals("DOWN")) {
            y += speedAlienProjectiles;
        } else if (direction.equals("UP")) {
            y -= speedShipsProjectiles;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
