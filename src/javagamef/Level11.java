
package javagamef;

//import static demomvingobject.DemoMvingObject.circle1;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level11 extends Application {

    private static final int KEYBOARD_MOVEMENT_DELTA = 5;
    private static final Duration TRANSLATE_DURATION = Duration.seconds(0.25);
    public static Circle circle10;
    public static Circle circle1;
    Timer time;
    Text text;
    static Integer score = 0;
     private int mainTime = 0;
    private long prevTime = System.currentTimeMillis();
    private long timePassed = 0;
    private long currentTime;
    private Text t;
    private Font myFont;
    public Timeline loop;
    public double deltaX, deltaY, deltaX1, deltaY1;

    public Group group;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        deltaX =3;
        deltaY =2;
        deltaX1 = 5;
        deltaY1 = 6;
        
        AnchorPane pane = new AnchorPane();
        Circle circle2 = new Circle(25, Color.BLUEVIOLET);
        text = new Text();
        text.setFont(Font.font(20));
        text.setTranslateX(500);
        text.setTranslateY(25);
        group = new Group(createInstructions(), circle2, text);
        initStuff();
        final TranslateTransition transition = createTranslateTransition(circle2);

        final Scene scene = new Scene(group, 800, 600);
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

        loop = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

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
                //Bounds bounds = 
                final boolean atRightBorder = circle10.getLayoutX() >= (800 - circle10.getRadius());
                final boolean atLeftBorder = circle10.getLayoutX() <= (0 + circle10.getRadius());
                final boolean atBottomBorder = circle10.getLayoutY() >= (600 - circle10.getRadius());
                final boolean atTopBorder = circle10.getLayoutY() <= (0 + circle10.getRadius());
                ///
                final boolean atRightBorder1 = circle1.getLayoutX() >= (800 - circle1.getRadius());
                final boolean atLeftBorder1 = circle1.getLayoutX() <= (0 + circle1.getRadius());
                final boolean atBottomBorder1 = circle1.getLayoutY() >= (600 - circle1.getRadius());
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
                  theTimer();
                  testCollision(circle1, circle2, circle10);
            }

        }));
        //chkCollision(circle1, circle2);
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
       // checkCollision(pane, circle1, circle2);

        ////
        /*
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                text.setText(score.toString());
                //chkCollision(circle1, circle2);
                checkCollision( pane,circle1, circle2);
            }
            
        })); */
        //timer.play();
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

    void checkCollision( AnchorPane pane,final Circle rect1,final Circle rect2) {
//        Point2D rect1Center= rect1.localToScene(rect1.getCenterX(),rect1.getCenterY());
//        Point2D rect2Center= rect2.localToScene(rect2.getCenterX(),rect2.getCenterY());
//        double dx=rect1Center.getX()-rect2Center.getX();
//        double dy=rect1Center.getY()-rect2Center.getY();
//        double distance = Math.sqrt(dx * dx + dy*dy);
//        double minDist = rect1.getRadius()+rect2.getRadius();
//        if(distance < minDist){
//            System.out.println("hiiiiiii");
//        }
        
        /*
        rect2.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> arg0, Bounds oldValue, Bounds newValue) {
                if (rect1.getBoundsInParent().intersects(newValue) ) {
                    score++;
                    text.setText("Score: "+ score.toString());
                    System.out.println("Score: " + score);
                }
            }
        });*/
    }

//    void chkCollision(Circle rct1, Circle rct2) {
//        ReadOnlyObjectProperty<Bounds> bSm = rct1.boundsInParentProperty();
//        if (rct2.getBoundsInParent().intersects(bSm.getValue())) {
//            //score++;
//            System.out.println("Collide1 ============= Collide1\n" + score);
//        }
//    }
    
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
    
    // X,Y = red, X1,Y1 = blue
    //r1 = blue, r2 = purple, r3 = red
    void testCollision(Circle r1, Circle r2, Circle r3){
       if(r1.getBoundsInParent().intersects(r2.getBoundsInParent())){
           deltaX1 *= -1;
           deltaY1 *= -1;
           //loop. *= -1;
           score+=5;
           text.setText(score.toString());
       }
       if(r3.getBoundsInParent().intersects(r2.getBoundsInParent())){
           deltaX *= -1;
           deltaY *= -1;
           try{
               text.setX(-250);
               text.setY(250);
                text.setFont(Font.font(48));
               text.setText("Game Over!");
               //Thread.sleep(3000);
               loop.stop();
               
           }catch(Exception e){
               
           }
           
       }
    }
}
