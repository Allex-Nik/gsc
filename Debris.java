import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Represents the class of Debris. 
 */
public class Debris {
    private static final int RADIUS = 10; // The size of the debris.
    private static final int SPEED = 2;
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    private int initX;
    private int initY;
    private int finX;
    private int finY;
    private int x; // Current position.
    private int y; // Current position.
    private int m; // The slope of the trajectory.
    private int c; // y-intercept.
    private int pointsWorth; // The points awarded for collecting the debris.
    private Ellipse2D.Double bounds; // Collision boundary for the debris.
    boolean visible; // Indicates if the debris is visible on the screen.
    private Image imageDebris; // The image representing the debris.

    /**
     * Constructs a debris object. Calculates the trajectory.
     */
    public Debris() {
        Random random = new Random();
        this.initX = random.nextInt(SCREEN_WIDTH / 4, (SCREEN_WIDTH * 3) / 4);
        this.initY = 0;
        this.finX = random.nextInt(SCREEN_WIDTH / 4, (SCREEN_WIDTH * 3) / 4);
        this.finY = SCREEN_HEIGHT;
        this.x = initX;
        this.y = initY;
        this.visible = true;
        this.pointsWorth = 1;

        // Load and scale the image for debris.
        imageDebris = new ImageIcon("Assets/Debris.png").getImage();
        imageDebris = imageDebris.getScaledInstance(RADIUS, RADIUS, Image.SCALE_DEFAULT);

        // Make debris a shape that can have collisions
        this.bounds = new Ellipse2D.Double(x, y, RADIUS, RADIUS);
    
        // Calculates the slope (m) and y-intercept (c) for the debris' linear trajectory.
        //y = mx + c
        m = (finY - initY) / (finX - initX);
        c = initY - (m * initX);
    }

    /**
     * Updates the position of the debris based on its trajectory and speed.
     */
    public void move() {
        y += SPEED;
        try {
            x = (y - c) / m;
        } catch (ArithmeticException e) {
            // Handles the case of division by zero.
            x = (y - c) / (m + 1);
        }
        
        // Updates the collision boundary for the debris.
        bounds.setFrame(x, y, RADIUS, RADIUS);
    }

    /**
     * Draws the debris on the screen.
     * @param g object of Graphics that enables drawing.
     */
    public void draw(Graphics g) {
        g.drawImage(imageDebris, x, y, null);
    }

    /**
     * Returns the points associated with the debris.
     * @return Points for the debris.
     */
    public int getPoints() {
        return pointsWorth;
    }

    /**
     * Checks if the debris collides with the spaceship.
     * @param object The object to check collision against.
     * @return True if the debris collides with the object, otherwise false.
     */
    public boolean collisionDetection(SpaceShip object) {
        return this.bounds.intersects(object.bounds);
    }
}
