import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Represents the final screen of the game, showing the player's score
 * and a message depending on whether they won or lost. Contains "restart" button.
 */
public class FinalScreen {
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    private static final Color BACKGROUND_COLOR = new Color(102, 98, 98);
    private static final Color TITLE_COLOR = Color.BLUE;
    private static final Color MESSAGE_COLOR = Color.BLUE;
    private String maxScore = "The highest score: ";
    private String feedback;
    private Color buttonColor = Color.BLUE;
    
    /**
     * Constructs the final screen of the game.
     * @param isVictory Indicates whether the player won or lost.
     * @param score The player's final score.
     */
    FinalScreen(boolean isVictory, int score) {

        // Sets the feedback message based on the player's result.
        if (isVictory) {
            feedback = "Congratulations! You won.";
        } else {
            feedback = "Sorry, you lost. Try again.";
        }

        // Sets up the main frame for the screen.
        JFrame frame = new JFrame("Final Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);

        // Sets up the main panel to hold all UI components.
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        frame.add(panel);

        // Sets up the top panel to show the maximum score and the player's score.
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);

        // Sets up the label to display the highest score.
        JLabel highestScore = new JLabel(maxScore, JLabel.CENTER);
        highestScore.setFont(new Font("Serif", Font.BOLD, 40));
        highestScore.setForeground(TITLE_COLOR);
        topPanel.add(highestScore, BorderLayout.EAST);

        // Sets up the label to display the player's score.
        String mark = "Score: " + score;
        JLabel grade = new JLabel(mark, JLabel.CENTER);
        grade.setFont(new Font("Serif", Font.BOLD, 40));
        grade.setForeground(Color.BLUE);
        topPanel.add(grade, BorderLayout.WEST);

        // Adds the top panel to the main panel.
        panel.add(topPanel, BorderLayout.NORTH);

        // Sets up the label to display feedback to the player
        JLabel message = new JLabel(feedback, JLabel.CENTER);
        message.setFont(new Font("Serif", Font.BOLD, 40));
        message.setForeground(MESSAGE_COLOR);
        panel.add(message, BorderLayout.CENTER);

        // Sets up the panel containing the restart button.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 100, 100, 100);
        buttonPanel.setBackground(BACKGROUND_COLOR);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Sets up the label acting as a restart button.
        JLabel restart = new JLabel("Restart", JLabel.CENTER);
        restart.setFont(new Font("Serif", Font.BOLD, 60));
        restart.setForeground(buttonColor);
        buttonPanel.add(restart);

        // Creates the event listener for restart button interactions.
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // On left-click, restart the game
                if (e.getButton() == MouseEvent.BUTTON1) {
                    frame.dispose();
                    new MainGameScreen();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Changes the button's color when the mouse enters.
                buttonColor = Color.RED;
                restart.setForeground(buttonColor);
                buttonPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Returns the initial color of the button when the mouse exits.
                buttonColor = Color.BLUE;
                restart.setForeground(buttonColor);
                buttonPanel.repaint();
            }
        });
        
        // Adds the key adapter to detect the ESCAPE key press.
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Closes the game if ESCAPE is pressed.
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }
}
