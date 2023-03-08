import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pong extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 80;
    private static final int PADDLE_SPEED = 10;

    private static final int BALL_RADIUS = 10;
    private static final int BALL_SPEED = 5;

    private static final int SCORE_SIZE = 36;

    private int player1Score = 0;
    private int player2Score = 0;

    private boolean gameStarted = false;

    private Rectangle paddle1;
    private Rectangle paddle2;
    private Rectangle ball;

    private double ballX = BALL_SPEED;
    private double ballY = BALL_SPEED;

    @Override
    public void start(Stage primaryStage) {

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        paddle1 = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT, Color.WHITE);
        paddle1.setTranslateX(0);
        paddle1.setTranslateY(HEIGHT / 2 - PADDLE_HEIGHT / 2);

        paddle2 = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT, Color.WHITE);
        paddle2.setTranslateX(WIDTH - PADDLE_WIDTH);
        paddle2.setTranslateY(HEIGHT / 2 - PADDLE_HEIGHT / 2);

        ball = new Rectangle(BALL_RADIUS * 2, BALL_RADIUS * 2, Color.WHITE);
        ball.setTranslateX(WIDTH / 2 - BALL_RADIUS);
        ball.setTranslateY(HEIGHT / 2 - BALL_RADIUS);

        gc.setFont(Font.font(SCORE_SIZE));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (gameStarted) {
                moveBall();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseClicked(event -> {
            if (!gameStarted) {
                gameStarted = true;
                timeline.play();
            }primaryStage.setTitle("Pong");
    primaryStage.setScene(scene);
    primaryStage.show();

    timeline.play();
}

private void moveBall() {
    ball.setTranslateX(ball.getTranslateX() + ballX);
    ball.setTranslateY(ball.getTranslateY() + ballY);

    checkPaddleCollision();
    checkBoundaryCollision();

    if (ball.getTranslateX() < 0) {
        player2Score++;
        resetBall();
    } else if (ball.getTranslateX() > WIDTH - BALL_RADIUS * 2) {
        player1Score++;
        resetBall();
    }
}

private void checkPaddleCollision() {
    Bounds bounds1 = paddle1.getBoundsInParent();
    Bounds bounds2 = paddle2.getBoundsInParent();
    Bounds ballBounds = ball.getBoundsInParent();

    if (ballBounds.intersects(bounds1) || ballBounds.intersects(bounds2)) {
        ballX *= -1;
    }
}

private void checkBoundaryCollision() {
    if (ball.getTranslateY() < 0 || ball.getTranslateY() > HEIGHT - BALL_RADIUS * 2) {
        ballY *= -1;
    }
}

private void resetBall() {
    ballX = BALL_SPEED;
    ballY = BALL_SPEED;
    ball.setTranslateX(WIDTH / 2 - BALL_RADIUS);
    ball.setTranslateY(HEIGHT / 2 - BALL_RADIUS);
    gameStarted = false;
}

public static void main(String[] args) {
    launch(args);
}
        });

        canvas.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.W && paddle1.getTranslateY() > 0) {
                paddle1.setTranslateY(paddle1.getTranslateY() - PADDLE_SPEED);
            } else if (event.getCode() == KeyCode.S && paddle1.getTranslateY() + PADDLE_HEIGHT < HEIGHT) {
                paddle1.setTranslateY(paddle1.getTranslateY() + PADDLE_SPEED);
            } else if (event.getCode() == KeyCode.UP && paddle2.getTranslateY() > 0) {
                paddle2.setTranslateY(paddle2.getTranslateY() - PADDLE_SPEED);
            } else if (event.getCode() == KeyCode.DOWN && paddle2.getTranslateY() + PADDLE_HEIGHT < HEIGHT) {
                paddle2.setTranslateY(paddle2.getTranslateY() + PADDLE_SPEED);
            }
        });

        StackPane root = new StackPane(canvas, paddle1, paddle2, ball);

        Scene scene = new Scene(root);
primaryStage.setTitle("Pong");
    primaryStage.setScene(scene);
    primaryStage.show();

    timeline.play();
}

private void moveBall() {
    ball.setTranslateX(ball.getTranslateX() + ballX);
    ball.setTranslateY(ball.getTranslateY() + ballY);

    checkPaddleCollision();
    checkBoundaryCollision();

    if (ball.getTranslateX() < 0) {
        player2Score++;
        resetBall();
    } else if (ball.getTranslateX() > WIDTH - BALL_RADIUS * 2) {
        player1Score++;
        resetBall();
    }
}

private void checkPaddleCollision() {
    Bounds bounds1 = paddle1.getBoundsInParent();
    Bounds bounds2 = paddle2.getBoundsInParent();
    Bounds ballBounds = ball.getBoundsInParent();

    if (ballBounds.intersects(bounds1) || ballBounds.intersects(bounds2)) {
        ballX *= -1;
    }
}

private void checkBoundaryCollision() {
    if (ball.getTranslateY() < 0 || ball.getTranslateY() > HEIGHT - BALL_RADIUS * 2) {
        ballY *= -1;
    }
}

private void resetBall() {
    ballX = BALL_SPEED;
    ballY = BALL_SPEED;
    ball.setTranslateX(WIDTH / 2 - BALL_RADIUS);
    ball.setTranslateY(HEIGHT / 2 - BALL_RADIUS);
    gameStarted = false;
}

public static void main(String[] args) {
    launch(args);
}
