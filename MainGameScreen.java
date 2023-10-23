import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MainGameScreen extends JPanel {

    int score;
    private int AMUNITION = 100;
    private int HEALTH = 100;
    private SpaceShip spaceShip;
    private List<Alien> aliens = new ArrayList<>();
    private List<Debris> debris = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private final int SCREEN_WIDTH = 1920;
    private final int SCREEN_HEIGHT = 1080;
    JFrame frame = new JFrame();

    public MainGameScreen() {
        Random random = new Random();
        ImageIcon backgroundImage = new ImageIcon("Space Background.png");
        spaceShip = new SpaceShip(900, 1000, 50, 50);
        debris = new Debris(random.nextInt(SCREEN_WIDTH/4, SCREEN_WIDTH*3/4), 0, random.nextInt(SCREEN_WIDTH/4, SCREEN_WIDTH*3/4), SCREEN_HEIGHT);

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setUndecorated(true);
        frame.setVisible(true);
        Random random = new Random();

        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setBounds(0, 0, 480, SCREEN_HEIGHT);
        this.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setBounds(SCREEN_WIDTH*3/4, 0, SCREEN_WIDTH/4, SCREEN_HEIGHT);
        this.add(rightPanel);


        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                spaceShip.setX(mouseX - spaceShip.getWidth() / 2);
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && AMUNITION > 0) {
                    Projectile projectile = spaceShip.fire();
                    projectiles.add(projectile);
                    AMUNITION -= 1;
                }
            }
        });

        Timer alienShootTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Alien a : aliens) {
                    Projectile projectile = a.fire();
                    projectiles.add(projectile);
                }
            }
        });
        alienShootTimer.start();

        Timer gameLoop = new Timer((1000 / 60), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDebris();
                updateAliens();
                updateProjectiles();
                hitDetection();
                //Add hit-detection for Debris using debris.collisionDetection(spaceShip)
            }
        });
        gameLoop.start();

        Timer debrisTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debris pieceOfDebris = new Debris(random.nextInt(SCREEN_WIDTH), 
                    0, random.nextInt(SCREEN_WIDTH), SCREEN_HEIGHT);
                debris.add(pieceOfDebris);
            }
        });
        debrisTimer.start();

        Timer aliensTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String direction = (random.nextInt(2) == 0) ? "LEFT" : "RIGHT";
                int startX = (direction.equals("LEFT") ? 0 : SCREEN_WIDTH);
                Alien alien = new Alien(startX, random.nextInt(100, 300), direction);
                aliens.add(alien);
            }
        });
        aliensTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backgroundImage = new ImageIcon("Space Background.png");
        g.drawImage(backgroundImage.getImage(), 0, 0, null);
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
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Amunition : " + AMUNITION, 10, 15);
        g.drawString("Health: " + HEALTH, 10, 40);
    }

    public void updateProjectiles() {
        for (Projectile p : projectiles) {
            p.move();
        }
        repaint();
    }

    public void updateDebris() {
        for (Debris d : debris) {
            d.move();
        }
        repaint();
    }

    public void updateAliens() {
        for (Alien a : aliens) {
            a.move();
        }
        repaint();
    }

    public void hitDetection() {
        List<Projectile> projectilesToRemove = new ArrayList<>();
        List<Alien> aliensToRemove = new ArrayList<>();
        
        for (Projectile p : projectiles) {
            if (p.x < spaceShip.x + spaceShip.width & p.x + p.width > spaceShip.x
                && p.y + p.height > spaceShip.y && p.y < spaceShip.y + spaceShip.height
                && p.direction.equals("DOWN")) {
                projectilesToRemove.add(p);
                HEALTH -= 10;
            }
            for (Alien a : aliens) {
                if (p.x < a.x + a.width && p.x + p.width > a.x 
                    && p.y < a.y + a.height && p.y + p.height > a.y && p.direction.equals("UP")) {
                    projectilesToRemove.add(p);
                    aliensToRemove.add(a);
                }
            }
        }
        projectiles.removeAll(projectilesToRemove);
        aliens.removeAll(aliensToRemove);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGameScreen::new);
    }
}
