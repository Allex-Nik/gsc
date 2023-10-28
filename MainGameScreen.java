import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

/**
 * Represents the main game screen. 
 * Contains the primary game interface where all gameplay takes place.
 * Handles the rendering and logic of the spaceship, aliens, debris, and projectiles.
 */
public class MainGameScreen extends JPanel {

    // Sets up the game metrics.
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    public int score;
    private int amunition = 10;
    private int health = 100;
    private int time = 120;
    private int minutes = time / 60;
    private int seconds = time % 60;
    private Timer gameLoop;

    // Game objects.
    private SpaceShip spaceShip;
    private List<Alien> aliens = new ArrayList<>();
    private List<Debris> debris = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private JFrame frame = new JFrame();

    public Image backgroundImage;

    /**
     * Constructs the main game screen.
     * Initializes the game setup, game mechanics and event listeners.
     */
    public MainGameScreen() {
        spaceShip = new SpaceShip();

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setUndecorated(true);
        frame.setVisible(true);

        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        
        Random random = new Random();

        // Sets up the image of the background.
        ImageIcon backgroundImageSource = new ImageIcon("Assets/Space Background.png");
        backgroundImage = backgroundImageSource.getImage();

        this.addMouseMotionListener(new MouseAdapter() {
            // Enables movement of the ship when the mouse button is not pressed.
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                spaceShip.setX(mouseX - spaceShip.getWidth() / 2);
                repaint();
            }

            // Enables movement of the ship when the mouse button is pressed.
            @Override
            public void mouseDragged(MouseEvent e) {
                int mouseX = e.getX();
                spaceShip.setX(mouseX - spaceShip.getWidth() / 2);
                repaint();
            }
        });

        // Enables firing projectiles from the ship and subtracting of the amunition.
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && amunition > 0) {
                    Projectile projectile = spaceShip.fire();
                    projectiles.add(projectile);
                    amunition -= 1;
                }
            }
        });

        // Sets the frequency with which the aliens shoot.
        Timer alienShootTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Alien a : aliens) {
                    Projectile projectile = a.fire();
                    projectiles.add(projectile);
                }
            }
        });
        alienShootTimer.start();

        // The main loop. Updates all the objects during the game.
        // Checks if the time is expired or if the health is 0.
        gameLoop = new Timer((1000 / 60), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (health <= 0 || time <= 0) {
                    gameLoop.stop();
                    frame.dispose();
                    new FinalScreen(health > 0, score);
                }
                updateDebris();
                updateAliens();
                updateProjectiles();
                hitDetection();
            }
        });
        gameLoop.start();

        // Sets the frequency with which the debris appear.
        Timer debrisTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debris pieceOfDebris = new Debris();
                debris.add(pieceOfDebris);
            }
        });
        debrisTimer.start();

        // Sets the frequency with which the aliens appear. 
        // Randomly determines their direction (LEFT or RIGHT).
        Timer aliensTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String direction = (random.nextInt(2) == 0) ? "LEFT" : "RIGHT";
                int startX = (direction.equals("LEFT") 
                    ? (SCREEN_WIDTH / 4) - 51 : ((SCREEN_WIDTH * 3) / 4) + 51);
                Alien alien = new Alien(startX, random.nextInt(100, 300), direction);
                aliens.add(alien);
            }
        });
        aliensTimer.start();

        // Sets up the timer for the game on the format mm:ss.
        Timer gameTimeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                minutes = time / 60;
                seconds = time % 60;
            }
        });
        gameTimeTimer.start();

        // Sets up the black panels on the both sides to restrict the field for the game.
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setBounds(0, 0, SCREEN_WIDTH / 4, SCREEN_HEIGHT);
        this.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setBounds((SCREEN_WIDTH / 4) * 3, 0, SCREEN_WIDTH / 4, SCREEN_HEIGHT);
        this.add(rightPanel);

        // Enables to quit the game when Escape if pressed.
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }

    // Draws the elements of the game that are needed.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        spaceShip.draw(g);
        for (Projectile p : projectiles) {
            p.draw(g);
        }
        for (Debris d : debris) {
            d.draw(g);
        }
        for (Alien a : aliens) {
            a.draw(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 14));
        g.drawString("Amunition : " + amunition, (SCREEN_WIDTH / 4 + 10), 15);
        g.drawString("Health: " + health, (SCREEN_WIDTH / 4 + 10), 40);
        g.drawString("Score: " + score, (SCREEN_WIDTH / 4 + 10), 65);
        g.drawString(String.format("Time left: %02d:%02d", minutes, seconds), 
            (SCREEN_WIDTH / 4 + 10), 100);
    }

    /**
     * Updates the position of all the projectiles on the screen.
     */
    public void updateProjectiles() {
        for (Projectile p : projectiles) {
            p.move();
        }
        repaint();
    }

    /**
     * Updates the position of all the pieces of debris on the screen.
     */
    public void updateDebris() {
        for (Debris d : debris) {
            d.move();
        }
        repaint();
    }

    /**
     * Updates the position of all the aliens on the screen.
     */
    public void updateAliens() {
        for (Alien a : aliens) {
            a.move();
        }
        repaint();
    }

    /**
     * Handles hit detection logic between game objects.
     */
    public void hitDetection() {
        // Creates lists to collect the objects that are needed to be removed.
        List<Projectile> projectilesToRemove = new ArrayList<>();
        List<Alien> aliensToRemove = new ArrayList<>();
        List<Debris> debrisToRemove = new ArrayList<>();
        
        // If a projectile hits the ship, points of health are subtracted.
        for (Projectile p : projectiles) {
            if (spaceShip.collisionDetection(p) && p.direction.equals("DOWN")) {
                projectilesToRemove.add(p);
                health -= 20;
            }

            // If a projectile hits an alien, the score is increased.
            for (Alien a : aliens) {
                if (a.collisionDetection(p) && p.direction.equals("UP")) {
                    projectilesToRemove.add(p);
                    aliensToRemove.add(a);
                    score += 10;
                }
            }
        }
        
        // If the ship collects debris, the amunition is increased.
        for (Debris d : debris) {
            if (d.collisionDetection(spaceShip)) {
                debrisToRemove.add(d);
                amunition += d.getPoints();
            }
        }

        // Removes collided projectiles and aliens.
        projectiles.removeAll(projectilesToRemove);
        aliens.removeAll(aliensToRemove);
        debris.removeAll(debrisToRemove);

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGameScreen();
        });
    }
}
