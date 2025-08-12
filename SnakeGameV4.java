import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGameV4 extends JPanel implements ActionListener, KeyListener {
    private final int size = 20;
    private final int width = 400, height = 400;
    private LinkedList<Point> snake = new LinkedList<>();
    private Point food;
    private int dx = size, dy = 0;
    private Timer timer;
    private Random rand = new Random();
    private boolean gameOver = false;
    private JFrame frame;

    public SnakeGameV4() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        snake.add(new Point(100, 100));
        snake.add(new Point(80, 100));
        snake.add(new Point(60, 100));

        spawnFood();

        timer = new Timer(150, this);
        timer.start();
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        updateTitle();
    }

    private void spawnFood() {
        int fx, fy;
        do {
            fx = rand.nextInt(width / size) * size;
            fy = rand.nextInt(height / size) * size;
            food = new Point(fx, fy);
        } while (snake.contains(food));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            timer.stop();
            return;
        }

        Point head = snake.getFirst();
        Point newHead = new Point(head.x + dx, head.y + dy);

        // Wrap edges
        if (newHead.x < 0) newHead.x = width - size;
        if (newHead.x >= width) newHead.x = 0;
        if (newHead.y < 0) newHead.y = height - size;
        if (newHead.y >= height) newHead.y = 0;

        // Check self collision
        if (snake.contains(newHead)) {
            gameOver = true;
            repaint();
            updateTitle();
            return;
        }

        snake.addFirst(newHead);

        if (newHead.equals(food)) {
            spawnFood();
        } else {
            snake.removeLast();
        }

        updateTitle();
        repaint();
    }

    private void updateTitle() {
        if (frame != null) {
            if (gameOver)
                frame.setTitle("Game Over! Score: " + (snake.size() - 3));
            else
                frame.setTitle("Snake Game V4 - Score: " + (snake.size() - 3));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillOval(food.x, food.y, size, size);

        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x, p.y, size, size);
        }

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over!", 100, height / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (gameOver) return;

        if (key == KeyEvent.VK_LEFT && dx == 0) {
            dx = -size; dy = 0;
        } else if (key == KeyEvent.VK_RIGHT && dx == 0) {
            dx = size; dy = 0;
        } else if (key == KeyEvent.VK_UP && dy == 0) {
            dx = 0; dy = -size;
        } else if (key == KeyEvent.VK_DOWN && dy == 0) {
            dx = 0; dy = size;
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SnakeGameV4 game = new SnakeGameV4();
        game.setFrame(frame);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}