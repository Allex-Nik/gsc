import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.lang.Math;
import javax.swing.ImageIcon;

/**
 * Represents the class of Aliens.
 */

public class Alien {
    
    private static final double DELTA_X = 0.7; // Determines the speed of aliens.
    private static final int AMPLITUDE = 100;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private double x;
    private double y;
    private int startY;
    private String direction;
    private Rectangle bounds;
    private Image imageAlien;

    /**
     * Construct objects of the class Aliens.
     * @param x determines the position of an alien on x-axis.
     * @param startY determines the start position of an alien on y-axis.
     * @param direction is either left or right. Determined randomly in MainGameScreen.
     */
    public Alien(int x, int startY, String direction) {
        this.x = x;
        this.y = startY;
        this.startY = startY;
        this.direction = direction;
        this.bounds = new Rectangle(x, startY, WIDTH, HEIGHT);

        // Loads the alien's image.
        ImageIcon imageAlienSource = new ImageIcon("Assets/Alien.png");
        imageAlien = imageAlienSource.getImage();
        imageAlien = imageAlien.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
    }
    
    /**
     * Draws the objects of the class Alien.
     * @param g is an object of the class Graphics that enables to draw objects.
     */
    public void draw(Graphics g) {
        g.drawImage(imageAlien, (int) this.x, (int) this.y, null);
        g.setColor(Color.GREEN);
        g.drawRect((int) x, (int) y, WIDTH, HEIGHT);
    }
    
    /**
     * Updates the alien's position based on its trajectory and speed.
     * The trajectory is described by the equation: y = amplitude * sin(x) + startY.
     * amplitude is fixed.
     * startY (equilibrium) is determined randomly in the bounds described in MainGameScreen. 
     */
    public void move() {
        if (direction.equals("LEFT")) {
            x += DELTA_X;
        } else {
            x -= DELTA_X;
        }
        y = (AMPLITUDE * Math.sin(x * Math.PI / 180)) + startY;
        bounds.setLocation((int) x, (int) y);
    }

    /**
     * Creates an instance of the class Projectile. Fires a projectile from the alien.
     * @return returns an instance of the class Projectile fired from the alien's current position.
     */
    public Projectile fire() {
        return new Projectile((int) this.x + WIDTH / 2, 
        (int) this.y + HEIGHT, "DOWN", true);
    }

    /**
     * Detects collision of an alien with a projectile.
     * @param p The projectile to check for collision.
     * @return True if the alien collides with the projectile, otherwise false.
     */
    public boolean collisionDetection(Projectile p) {
        return this.bounds.intersects(p.bounds);
    }

}
