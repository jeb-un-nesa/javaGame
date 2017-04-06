
package javagamef;

/**
 *
 * @author jamal
 */
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.scene.shape.Circle;
import static javafx.application.Application.launch;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class Level3 extends Application {

    private AnimationTimer timer;

    private Pane root;

    private List<Node> balls = new ArrayList<>();
    private Node frog;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(800, 600);

        frog = initFrog();

        root.getChildren().add(frog);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private Node initFrog() {
        Circle rect = new Circle(19, Color.BLUE);
        rect.setTranslateY(600 - 39);

        return rect;
    }

    private Node spawnCar() {
        Circle rect = new Circle(20, Color.DEEPPINK);
        rect.setTranslateY((int)(Math.random() * 14) * 40);

        root.getChildren().add(rect);
        return rect;
    }

    private void onUpdate() {
        for (Node car : balls)
            car.setTranslateX(car.getTranslateX() + Math.random() * 10);

        if (Math.random() < 0.075) {
            balls.add(spawnCar());
        }

        checkState();
    }

    private void checkState() {
        for (Node car : balls) {
            if (car.getBoundsInParent().intersects(frog.getBoundsInParent())) {
                frog.setTranslateX(0);
                frog.setTranslateY(600 - 39);
                return;
            }
        }

        if (frog.getTranslateY() <= 0) {
            timer.stop();
            String win = "YOU WIN";

            HBox hBox = new HBox();
            hBox.setTranslateX(300);
            hBox.setTranslateY(250);
            root.getChildren().add(hBox);

            for (int i = 0; i < win.toCharArray().length; i++) {
                char letter = win.charAt(i);

                Text text = new Text(String.valueOf(letter));
                text.setFont(Font.font(48));
                text.setOpacity(0);

                hBox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));

        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    frog.setTranslateY(frog.getTranslateY() - 40);
                    break;
                case S:
                    frog.setTranslateY(frog.getTranslateY() + 40);
                    break;
                case A:
                    frog.setTranslateX(frog.getTranslateX() - 40);
                    break;
                case D:
                    frog.setTranslateX(frog.getTranslateX() + 40);
                    break;
                default:
                    break;
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
