import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Pong extends Frame implements KeyListener, Runnable {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_RADIUS = 10;
    private static final int BALL_SPEED = 5;

    private int player1Score = 0;
    private int player2Score = 0;

    private Rectangle leftPaddle;
    private Rectangle rightPaddle;
    private Rectangle ball;

    private double dx;
    private double dy;

    private boolean gameStarted = false;

    public Pong() {
        // Set up game objects
        leftPaddle = new Rectangle(0, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle = new Rectangle(WIDTH - PADDLE_WIDTH, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new Rectangle(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);
        resetBall();

        // Set up game window
        setTitle("Pong");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        addKeyListener(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Exit the application when the window is closed
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public void paint(Graphics g) {
        // Set color to black
        g.setColor(Color.BLACK);

        // Draw background
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Set color to white
        g.setColor(Color.WHITE);

        // Draw paddles
        g.fillRect(leftPaddle.x, leftPaddle.y, leftPaddle.width, leftPaddle.height);
        g.fillRect(rightPaddle.x, rightPaddle.y, rightPaddle.width, rightPaddle.height);

        // Draw ball
        g.fillOval(ball.x, ball.y, ball.width, ball.height);

        // Set font to "bold arial"
        g.setFont(new Font("Arial", Font.BOLD, 32));

        // Draw scoreboard
        g.drawString(player1Score + " - " + player2Score, WIDTH / 2 - 50, 50);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && leftPaddle.y > 0) {
            leftPaddle.y -= 20;
        } else if (e.getKeyCode() == KeyEvent.VK_S && leftPaddle.y + PADDLE_HEIGHT < HEIGHT) {
            leftPaddle.y += 20;
        } else if (e.getKeyCode() == KeyEvent.VK_UP && rightPaddle.y > 0) {
            rightPaddle.y -= 20;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && rightPaddle.y + PADDLE_HEIGHT < HEIGHT) {
            rightPaddle.y += 20;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameStarted) {
            gameStarted = true;
            Thread t = new Thread(this);
            t.start();
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    //This method is used to calculate the angle of the ball
    public void ballAngle() {
        Random random = new Random();
        int angle = random.nextInt(30) + 20;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
        if (random.nextBoolean()) {
            dy = -dy;
        }
    }

    // this method is used to calculate the X-direction of the ball when it collides with the paddles
    public void ballDirectionX() {
        // When the ball collides with the left paddle, the ball's x direction is reversed and the ball's angle is randomized.
        if (ball.intersects(leftPaddle)) {
            ballAngle();
            dx = Math.abs(dx);
        // When the ball collides with the right paddle, the ball's x direction is reversed and the ball's angle is randomized.
        } else if (ball.intersects(rightPaddle)) {
            ballAngle();
            dx = -Math.abs(dx);
        }
    }


    // This method makes sure the paddles can't go out of bounds
    public void containPaddle(Rectangle paddle) {
            if (paddle.y <0) {
                paddle.y =0;
            }
            else if (paddle.y + PADDLE_HEIGHT > HEIGHT) {
                paddle.y = HEIGHT - PADDLE_HEIGHT;
            }
        }

    //this method runs the game
    public void run() {
        while (gameStarted) {
            moveBall();
            ballDirectionX();

            containPaddle(leftPaddle);
            containPaddle(rightPaddle);

            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    //this method contains the movement of the ball
    private void containBall() {
        if (ball.y < 0 || ball.y + BALL_RADIUS * 2 > HEIGHT) {
            dy = -dy;
        }
    }

    //this methods keeps the score
    private void keepScore() {
        if (ball.x < 0) {
            player2Score++;
            resetBall();
        } else if (ball.x + BALL_RADIUS * 2 > WIDTH) {
            player1Score++;
            resetBall();
        }
    }

    private void moveBall() {
        ball.x += dx * BALL_SPEED;
        ball.y += dy * BALL_SPEED;

        keepScore();
        containBall();
    }

    private void resetBall() {
        Random random = new Random();
        int angle = random.nextInt(30) + 20;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
        if (random.nextBoolean()) {
            dy = -dy;
            dx = -dx;
        }
        
        ball.x = WIDTH / 2 - BALL_RADIUS;
        ball.y = HEIGHT / 2 - BALL_RADIUS;
        leftPaddle.y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        rightPaddle.y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        gameStarted = false;
    }

    public static void main(String[] args) {
        Pong pong = new Pong();
    }
}