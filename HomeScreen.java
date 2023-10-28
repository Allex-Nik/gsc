import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;

/**
 * Represents the home screen of the game, containing the "Start" button.
 */
public class HomeScreen extends JPanel {
    private static final String GAME_NAME = "Galactic Salvage Crew";
    private static final Color BACKGROUND_COLOR = new Color(102, 98, 98);
    private static final Color TITLE_COLOR = Color.BLUE;
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    private Color buttonColor = Color.BLUE;
    public boolean startFlag = false;
    public Image imageSpaceShip;
    
    /**
     * Constructs the home screen of the game.
     */
    HomeScreen() {
        // Loads the spaceship's image.
        ImageIcon imageSpaceShipSource = new ImageIcon("Assets/SpaceShip.png");
        imageSpaceShip = imageSpaceShipSource.getImage();

        // Sets up the frame.
        JFrame frame = new JFrame("Home Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Sets up the panel.
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        frame.add(panel);

        // Sets up the title label.
        JLabel title = new JLabel(GAME_NAME, JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(TITLE_COLOR);
        panel.add(title, BorderLayout.CENTER);

        // Sets up the button panel.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 100, 100, 100);;
        buttonPanel.setBackground(BACKGROUND_COLOR);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Sets up the "Start" button.
        JLabel start = new JLabel("Start", JLabel.CENTER);
        start.setFont(new Font("Serif", Font.BOLD, 60));
        start.setForeground(buttonColor);
        buttonPanel.add(start);

        // Adds key listener to the frame to detect ESCAPE press.
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("Escape pressed");
                    System.exit(0);
                }
            }
        });
        
        // Adds mouse listener to the "start" button.
        start.addMouseListener(new MouseAdapter() {
           
            // Starts the game if the mouse is pressed.
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (!startFlag) {
                    startFlag = true;
                    frame.dispose();
                    SwingUtilities.invokeLater(() -> {
                        new MainGameScreen();
                    });
                }
            }

            // Changes the button's color when the mouse enters.
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                System.out.println("Mouse entered");
                buttonColor = Color.RED;
                start.setForeground(buttonColor);
                buttonPanel.repaint();
            }

            // Returns the initial color of the button when the mouse exits.
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                System.out.println("Mouse exited");
                buttonColor = Color.BLUE;
                start.setForeground(buttonColor);
                buttonPanel.repaint();
            }
        });
    }

    public static void main(String[] args) {
        // Launches the HomeScreen.
        SwingUtilities.invokeLater(() -> {
            new HomeScreen();
        });
    }
}
