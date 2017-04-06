
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagamef;

/**
 *
 * @author jamal
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;

public class Level1 extends Application {

    private static final int KEYBOARD_MOVEMENT_DELTA = 5;
    private static final Duration TRANSLATE_DURATION = Duration.seconds(0.25);
    public static Circle circle10;
    public static Circle circle1;

    private int mainTime = 0;
    private long prevTime = System.currentTimeMillis();
    private long timePassed = 0;
    private long currentTime;
    private Text t;
    private Font myFont;

    public Group group;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Circle circle2 = createCircle();
        group = new Group(createInstructions(), circle2);
        initStuff();             //for initializations
        final TranslateTransition transition = createTranslateTransition(circle2);

        final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);
        moveCircleOnKeyPress(scene, circle2);
        moveCircleOnMousePress(scene, circle2, transition);

        stage.setScene(scene);
        stage.show();
        /////
        circle10 = new Circle(15, Color.RED);
        circle10.relocate(100, 100);
////
        circle1 = new Circle(15, Color.BLUE);
        circle1.relocate(200, 200);
///
        group.getChildren().addAll(circle10);
        //
        group.getChildren().addAll(circle1);
        //

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

            double deltaX = 3;
            double deltaY = 3;
            //
            double deltaX1 = 2;
            double deltaY1 = 3;
//

            @Override
            public void handle(final ActionEvent t) {
                circle10.setLayoutX(circle10.getLayoutX() + deltaX);
                circle10.setLayoutY(circle10.getLayoutY() + deltaY);
                ///
                circle1.setLayoutX(circle1.getLayoutX() + deltaX1);
                circle1.setLayoutY(circle1.getLayoutY() + deltaY1);
                ///

                final Bounds bounds = group.getBoundsInLocal();
                final boolean atRightBorder = circle10.getLayoutX() >= (600 - circle10.getRadius());
                final boolean atLeftBorder = circle10.getLayoutX() <= (0 + circle10.getRadius());
                final boolean atBottomBorder = circle10.getLayoutY() >= (400 - circle10.getRadius());
                final boolean atTopBorder = circle10.getLayoutY() <= (0 + circle10.getRadius());
                ///
                final boolean atRightBorder1 = circle1.getLayoutX() >= (600 - circle1.getRadius());
                final boolean atLeftBorder1 = circle1.getLayoutX() <= (0 + circle1.getRadius());
                final boolean atBottomBorder1 = circle1.getLayoutY() >= (400 - circle1.getRadius());
                final boolean atTopBorder1 = circle1.getLayoutY() <= (0 + circle1.getRadius());
                //

                if (atRightBorder || atLeftBorder) {
                    deltaX *= -1;
                }
                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                }
                ///
                if (atRightBorder1 || atLeftBorder1) {
                    deltaX1 *= -1;
                }
                if (atBottomBorder1 || atTopBorder1) {
                    deltaY1 *= -1;
                }
                //

                //testing method
                theTimer();
            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
        ////

    }

    private Label createInstructions() {
        Label instructions = new Label( //      "Use the arrow keys to move the circle in small increments\n" +
                //      "Click the mouse to move the circle to a given location\n" +
                //      "Ctrl + Click the mouse to slowly translate the circle to a given location"      
                );
        instructions.setTextFill(Color.FORESTGREEN);
        return instructions;
    }

    private Circle createCircle() {
        final Circle circle3 = new Circle(200, 150, 50, Color.BLUEVIOLET);
        circle3.setOpacity(0.7);
        return circle3;
    }

    private TranslateTransition createTranslateTransition(final Circle circle) {
        final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
                circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
                circle.setTranslateX(0);
                circle.setTranslateY(0);
            }
        });
        return transition;
    }

    private void moveCircleOnKeyPress(Scene scene, final Circle circle) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA);
                        break;
                    case RIGHT:
                        circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA);
                        break;
                    case DOWN:
                        circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA);
                        break;
                    case LEFT:
                        circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA);
                        break;
                }
            }
        });
    }

    private void moveCircleOnMousePress(Scene scene, final Circle circle, final TranslateTransition transition) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!event.isControlDown()) {
                    circle.setCenterX(event.getSceneX());
                    circle.setCenterY(event.getSceneY());
                } else {
                    transition.setToX(event.getSceneX() - circle.getCenterX());
                    transition.setToY(event.getSceneY() - circle.getCenterY());
                    transition.playFromStart();
                }
            }
        });
    }

    //handles the time
    private void theTimer() {

        currentTime = System.currentTimeMillis();
        timePassed += currentTime - prevTime;
        if (timePassed >= 1000) {
            timePassed = 0;
            mainTime++;
            setTimeText(mainTime);
            System.out.println(mainTime + " seconds Passed!");
        }
        prevTime = currentTime;
    }

    void initStuff(){
         t = new Text();
         group.getChildren().add(t);
    }
    
    void setTimeText(int someTime) {
        t.setFont(Font.font("Verdana", 30));
        t.setFill(Color.CADETBLUE);
        t.setX(500f);
        t.setY(50f);
        if(someTime > -1){
            t.setText(Integer.toString(someTime));
        }else{
            t.setText("");
        }
        
    }
}