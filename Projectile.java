import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Projectile {
    public int x;
    public int y;
    public int width;
    public int height;
    private int speedShipsProjectiles = 15;
    private int speedAlienProjectiles = 6;
    public String direction;
    Rectangle bounds;

    Image imageAlienProjectile;
    Image imageShipProjectile;

    boolean laserFlag; // true: alien laser, false: ship laser

    public Projectile(int startX, int startY, String direction, boolean laserFlag) {
        this.x = startX;
        this.y = startY;
        this.direction = direction;
        this.width = 5;
        this.height = 10;
        this.bounds = new Rectangle(x, y, width, height);
        this.laserFlag = laserFlag;

        imageAlienProjectile = new ImageIcon("Assets/AlienLaser.png").getImage();
        imageAlienProjectile = imageAlienProjectile.getScaledInstance(width, height, Image.SCALE_DEFAULT);

        imageShipProjectile = new ImageIcon("Assets/SpaceShipLaser.png").getImage();
        imageShipProjectile = imageShipProjectile.getScaledInstance(width, height, Image.SCALE_DEFAULT);
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
        bounds.setLocation(x, y);
    }
    

    public void draw(Graphics g) {
        if (laserFlag) {
            g.drawImage(imageAlienProjectile, x, y, null);
        } else {
            g.drawImage(imageShipProjectile, x, y, null);
        }
        //g.setColor(Color.BLUE);
        //g.fillRect(x, y, width, height);
    }
}
