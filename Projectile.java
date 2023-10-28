import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Represents the class of projectiles that can be fired by either the ship or an alien.
 * A projectile has coordinates, direction, speed, and an associated image.
 */
public class Projectile {
    private static final int SPEED_SHIPS_PROJECTILES = 15; // Speed of ship's projectiles.
    private static final int SPEED_ALIENS_PROJECTILES = 6; // Speed of alien's projectiles.
    private static final int PROJECTILE_WIDTH = 5; // Width of a projectile.
    private static final int PROJECTILE_HEIGHT = 10; // Height of a projectile.
    private int x; // Horizontal position of a projectile.
    private int y; // Vertical position of a projectile.
    private Image imageAlienProjectile; // Alien's projectile image.
    private Image imageShipProjectile; // Ship's projectile image.
    public String direction; // Direction of a projectile ("UP" or "DOWN").
    public Rectangle bounds; // Rectangle to determine boundaries of the projectile.
    private boolean laserFlag; // true: alien laser, false: ship laser.

    /**
     * Constructs new projectiles.
     * @param startX Initial horizontal position.
     * @param startY Initial vertical position.
     * @param direction Movement direction of the projectile ("UP" or "DOWN").
     * @param laserFlag Projectile type flag: true - alien, false - ship.
     */
    public Projectile(int startX, int startY, String direction, boolean laserFlag) {
        this.x = startX;
        this.y = startY;
        this.direction = direction;
        this.bounds = new Rectangle(x, y, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.laserFlag = laserFlag;

        // Sets up the image of aliens' projectiles.
        imageAlienProjectile = new ImageIcon("Assets/AlienLaser.png").getImage();
        imageAlienProjectile = imageAlienProjectile.getScaledInstance(
            PROJECTILE_WIDTH, PROJECTILE_HEIGHT, Image.SCALE_DEFAULT);

        // Sets up the image of the ship's projectiles.
        imageShipProjectile = new ImageIcon("Assets/SpaceShipLaser.png").getImage();
        imageShipProjectile = imageShipProjectile.getScaledInstance(
            PROJECTILE_WIDTH, PROJECTILE_HEIGHT, Image.SCALE_DEFAULT);
    }

    /**
     * Moves the projectile based on its direction and speed.
     */
    public void move() {
        if (direction.equals("DOWN")) {
            y += SPEED_ALIENS_PROJECTILES;
        } else if (direction.equals("UP")) {
            y -= SPEED_SHIPS_PROJECTILES;
        }
        bounds.setLocation(x, y);
    }
    
    /**
     * Draws the projectile on the screen using the provided graphics context.
     * @param g Graphics context used for drawing.
     */
    public void draw(Graphics g) {
        if (laserFlag) {
            g.drawImage(imageAlienProjectile, x, y, null);
        } else {
            g.drawImage(imageShipProjectile, x, y, null);
        }
    }
}
