import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Describes the class of the ship.
 */
public class SpaceShip {
    // Constants for the dimensions of the ship.
    private static final int SHIPS_WIDTH = 50;
    private static final int SHIPS_HEIGHT = 50;

    // Starting position of the ship.
    private int x = 900;
    private int y = 1000;
    
    // The bounding box of the ship for collision detection.
    public Rectangle bounds;

    // The image representation of the ship.
    private Image imageSpaceShip;

    /**
     * Constructs the ship in the game.
     * The ship has a position, size, image representation, and can fire projectiles.
     */
    public SpaceShip() {
        this.bounds = new Rectangle(x, y, SHIPS_WIDTH, SHIPS_HEIGHT);

        // Loads the ship's image.
        ImageIcon imageSpaceShipSource = new ImageIcon("Assets/SpaceShip.png");
        imageSpaceShip = imageSpaceShipSource.getImage();
    }

    /**
     * Sets the horizontal position of the ship, with boundaries.
     * @param x Horizontal position.
     */
    public void setX(int x) {
        if (x < 1390 && x > 480) {
            this.x = x;
            bounds.setLocation(x, y);
        }
    }

    /**
     * Returns the width of the ship.
     * @return Width of the ship.
     */
    public int getWidth() {
        return SHIPS_WIDTH;
    }

    /**
     * Fires a projectile from the spaceship's position upwards.
     * @return The fired projectile.
     */
    public Projectile fire() {
        return new Projectile(this.x + SHIPS_WIDTH / 2, this.y, "UP", false);
    }

    /**
     * Draws the ship on the screen using the provided graphics context.
     * @param g Graphics context used for drawing.
     */
    public void draw(Graphics g) {  
        g.drawImage(imageSpaceShip, this.x - 20, this.y, null);
        g.setColor(Color.BLUE);
        g.drawRect(this.x, this.y, SHIPS_WIDTH, SHIPS_HEIGHT);
    }

    /**
    * Detects collision of the ship with a projectile.
     * @param p The projectile to check for collision.
     * @return True if the ship collides with the projectile, otherwise false.
     */
    public boolean collisionDetection(Projectile p) {
        return this.bounds.intersects(p.bounds);
    }
}
