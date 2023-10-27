import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.tools.Tool;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MainGameScreen extends JPanel {

    int score;
    private int ammunition = 100;
    private int health = 100;
    private SpaceShip spaceShip;
    private List<Alien> aliens = new ArrayList<>();
    private List<Debris> debris = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private final int SCREEN_WIDTH = 1920;
    private final int SCREEN_HEIGHT = 1080;
    JFrame frame = new JFrame();

    public Image backgroundImage;

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

        ImageIcon backgroundImageSource = new ImageIcon("Assets/Space Background.png");
        backgroundImage = backgroundImageSource.getImage();

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
                if (e.getButton() == MouseEvent.BUTTON1 && ammunition > 0) {
                    Projectile projectile = spaceShip.fire();
                    projectiles.add(projectile);
                    ammunition -= 1;
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
            }
        });
        gameLoop.start();

        Timer debrisTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debris pieceOfDebris = new Debris();
                debris.add(pieceOfDebris);
            }
        });
        debrisTimer.start();

        Timer aliensTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String direction = (random.nextInt(2) == 0) ? "LEFT" : "RIGHT";
                int startX = (direction.equals("LEFT") ? (SCREEN_WIDTH / 4) - 51 : ((SCREEN_WIDTH * 3) / 4) + 51);
                Alien alien = new Alien(startX, random.nextInt(100, 300), direction);
                aliens.add(alien);
            }
        });
        aliensTimer.start();

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setBounds(0, 0, SCREEN_WIDTH / 4, SCREEN_HEIGHT);
        this.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setBounds((SCREEN_WIDTH / 4) * 3, 0, SCREEN_WIDTH / 4, SCREEN_HEIGHT);
        this.add(rightPanel);
    }

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
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Amunition : " + ammunition, (SCREEN_WIDTH / 4 + 10), 15);
        g.drawString("Health: " + health, (SCREEN_WIDTH / 4 + 10), 40);
        g.drawString("Score: " + score, (SCREEN_WIDTH / 4 + 10), 65);
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
        List<Debris> debrisToRemove = new ArrayList<>();
        
        for (Projectile p : projectiles) {
            if (spaceShip.collisionDetection(p) && p.direction.equals("DOWN")) {
                projectilesToRemove.add(p);
                health -= 10;
            }
            for (Alien a : aliens) {
                if (a.collisionDetection(p) && p.direction.equals("UP")) {
                    projectilesToRemove.add(p);
                    aliensToRemove.add(a);
                    score += 10;
                }
            }
        }
        
        for (Debris d : debris) {
            if (d.collisionDetection(spaceShip)) {
                debrisToRemove.add(d);
                ammunition += d.getPoints();
            }
        }

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

