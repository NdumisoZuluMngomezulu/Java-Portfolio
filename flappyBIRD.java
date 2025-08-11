import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    // Game variables (bird, pipes, score, game state, etc.)
    private Rectangle bird;
    private ArrayList<Rectangle> columns;
    private int yMotion, score;
    private boolean gameOver, started;
    private Random rand;

    public FlappyBird() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this); // Add the panel to the frame
        frame.addKeyListener(this); // Add key listener
        frame.setResizable(false);
        frame.setVisible(true);

        // Initialize game elements
        bird = new Rectangle(100, frame.getHeight() / 2 - 10, 20, 20);
        columns = new ArrayList<>();
        rand = new Random();

        // Game timer
        Timer timer = new Timer(20, this); // 20ms delay for updates
        timer.start();

        addColumns(true);
        addColumns(true);
        addColumns(true);
        addColumns(true);
    }

    // Method to add new columns (pipes)
    public void addColumns(boolean start) {
        int space = 300;
        int width = 100;
        int height = 50 + rand.nextInt(300);

        if (start) {
            columns.add(new Rectangle(getWidth() + width + columns.size() * 300, getHeight() - height - 120, width, height));
            columns.add(new Rectangle(getWidth() + width + (columns.size() - 1) * 300, 0, width, getHeight() - height - space));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, getHeight() - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, getHeight() - height - space));
        }
    }

    // Handle game updates (movement, collisions)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (started) {
            yMotion += 1; // Gravity
            bird.y += yMotion;

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= 5; // Pipe movement

                if (column.x + column.width < 0) {
                    columns.remove(column);
                    if (columns.get(i).y == 0) { // Only add new pair when top pipe goes off-screen
                        addColumns(false);
                    }
                }

                if (column.intersects(bird)) {
                    gameOver = true;
                }
            }

            // Ground collision
            if (bird.y > getHeight() - 120 || bird.y < 0) {
                gameOver = true;
            }

            // Check if passed through pipes for score
            for (Rectangle column : columns) {
                if (column.y == 0 && bird.x > column.x && bird.x < column.x + column.width && !gameOver) {
                    score++;
                }
            }
        }
        repaint(); // Request a repaint
    }

    // Handle key presses (flapping)
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                // Reset game
                bird = new Rectangle(100, getHeight() / 2 - 10, 20, 20);
                columns.clear();
                yMotion = 0;
                score = 0;
                gameOver = false;
                addColumns(true);
                addColumns(true);
                addColumns(true);
                addColumns(true);
            }
            if (!started) {
                started = true;
            } else if (!gameOver) {
                yMotion = -10; // Flap upwards
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    // Drawing method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background, bird, pipes, score, game over message
        g.setColor(Color.cyan);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.orange);
        g.fillRect(0, getHeight() - 120, getWidth(), 120);

        g.setColor(Color.green);
        g.fillRect(0, getHeight() - 120, getWidth(), 20);

        g.setColor(Color.red);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            g.setColor(Color.green.darker());
            g.fillRect(column.x, column.y, column.width, column.height);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 80));

        if (!started) {
            g.drawString("Click to start!", 75, getHeight() / 2 - 50);
        }

        if (gameOver) {
            g.drawString("Game Over!", 100, getHeight() / 2 - 50);
        }

        if (!gameOver && started) {
            g.drawString(String.valueOf(score), getWidth() / 2 - 25, 100);
        }
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}