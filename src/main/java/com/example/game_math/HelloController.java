package com.example.game_math;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Pane centerPane;
    @FXML
    public Pane pane1;
    @FXML
    public AnchorPane ap1;
    @FXML
    private TextField inp1;
    @FXML
    private Text pl1;
    @FXML
    private Text pl2;
    @FXML
    private Button cnp;
    @FXML
    private Button cnp1;
    @FXML
    private Button play;


    @FXML
    private Rectangle rLeft;
    @FXML
    private Rectangle rRight;

    @FXML
    private Line lineL1;
    @FXML
    private Line lineL2;
    @FXML
    private Line lineR1;
    @FXML
    private Line lineR2;
    @FXML
    private Line lineLmid;
    @FXML
    private Line lineRmid;

    @FXML
    private Circle ball;
    @FXML
    private Button beginBtn;
    @FXML
    private Button beginBtn1;
    @FXML
    private Button beginBtn2;
    @FXML
    private Line topLine;
    @FXML
    private Line botLine;

    @FXML
    private Line leftBoundary;
    @FXML
    private Line rightBoundary;

    @FXML
    private Text pl1_name;
    @FXML
    private Text pl2_name;
    @FXML
    private Text pl1_score;
    @FXML
    private Text pl2_score;

    @FXML
    private Text win_text;
    @FXML
    private Text winner;
    @FXML
    private Text warn;
    @FXML
    private Text warn1;

    private Text[] pls;
    private int count;
    private static Player[] players = new Player[2];
    private Stage stage;
    private Parent root;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    static boolean moving_right = true;
    private boolean left;
    @FXML
    private AnchorPane anchorPane;

    private BooleanProperty isWPressed = new SimpleBooleanProperty();
    private BooleanProperty isSPressed = new SimpleBooleanProperty();
    private BooleanProperty isUPPressed = new SimpleBooleanProperty();
    private BooleanProperty isDownPressed = new SimpleBooleanProperty();
    private BooleanBinding KeyPressed = isWPressed.or(isSPressed).or(isUPPressed).or(isDownPressed);
    private boolean isNull = true;
    public void initialize() { pls = new Text[]{pl1, pl2}; }

    @FXML
    public void setPlayerName(ActionEvent event) {
        warn.setVisible(false);
        warn1.setVisible(false);

        String name = inp1.getText();

        if (name.equals("")) {
            warn.setVisible(true);
            return;
        }

        else if (name.equals(pls[0].getText()) && !isNull) {
            warn1.setVisible(true);
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (i == count) {
                pls[i].setText(name);
                players[i] = new Player(name);
                count++;
                break;
            }
        }

        isNull = false;
    }

    @FXML
    public void switchToPlay(ActionEvent event) throws IOException {
        if (pl1 == null || pl2 == null || players[0] == null || players[1] == null) {
            warn.setVisible(true);
        }
        else {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("play.fxml"));
            root = fxmlLoader.load();
            HelloController controller = fxmlLoader.getController();
            Rectangle rectangle = controller.rLeft;
            Rectangle rectangle2 = controller.rRight;
            Line lineL1 = controller.lineL1;
            Line lineL2 = controller.lineL2;
            Line lineR1 = controller.lineR1;
            Line lineR2 = controller.lineR2;
            Line lineLmid = controller.lineLmid;
            Line lineRmid = controller.lineRmid;
            AnchorPane anchorPane1 = controller.anchorPane;
            Pane centerPane1 = controller.centerPane;
            Button btn1 = controller.beginBtn1;
            Button btn2 = controller.beginBtn2;
            Button btn = controller.beginBtn;

            btn.setVisible(true);
            btn1.setVisible(false);
            btn2.setVisible(false);

            anchorPane1.layoutXProperty().bind(
                    centerPane1.widthProperty().subtract(anchorPane1.widthProperty()).divide(2)
            );

            anchorPane1.layoutYProperty().bind(
                    centerPane1.heightProperty().subtract(anchorPane1.heightProperty()).divide(2)
            );

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            rectangle2.setVisible(false);
            rectangle.setVisible(false);
            lineL1.setVisible(false);
            lineL2.setVisible(false);
            lineR1.setVisible(false);
            lineR2.setVisible(false);
            lineLmid.setVisible(false);
            lineRmid.setVisible(false);

            anchorPane1.setMaxSize(523, 490);

            controller.pl1_name.setText(controller.players[0].getName());
            controller.pl2_name.setText(controller.players[1].getName());

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case UP:
                            moveUp(rectangle2);
                            moveUp(lineR1);
                            moveUp(lineR2);
                            moveUp(lineRmid);
                            break;
                        case DOWN:
                            moveDown(rectangle2);
                            moveDown(lineR1);
                            moveDown(lineR2);
                            moveDown(lineRmid);
                            break;
                        case W:
                            moveUp(rectangle);
                            moveUp(lineL1);
                            moveUp(lineL2);
                            moveUp(lineLmid);
                            break;
                        case S:
                            moveDown(rectangle);
                            moveDown(lineL1);
                            moveDown(lineL2);
                            moveDown(lineLmid);
                            break;
                        default:
                            break;
                    }
                }
            });


            stage.setScene(scene);
            stage.show();
        }

    }

    public void moveUp(Rectangle rectangle) {
        rectangle.setLayoutY(rectangle.getLayoutY() - 30);
    }

    public void moveDown(Rectangle rectangle) {
        rectangle.setY(rectangle.getY() + 30);
    }

    public void moveUp(Line line) {
        line.setStartY(line.getStartY() - 30);
        line.setEndY(line.getEndY() - 30);
    }

    public void moveDown(Line line) {
        line.setStartY(line.getStartY() + 30);
        line.setEndY(line.getEndY() + 30);
    }

    @FXML
    public void runTimeline(ActionEvent event) {
        int score11 = Integer.parseInt(pl1_score.getText());
        int score22 = Integer.parseInt(pl2_score.getText());

        boolean[] movingRight = {true};
        boolean[] movingLeft = {false};
        boolean[] movingLeftUp = {false};
        boolean[] movingLeftDown = {false};
        boolean[] movingRightUp = {false};
        boolean[] movingRightDown = {false};

        if (left) {
            movingRight[0] = false;
            movingLeft[0] = true;
        }

        beginBtn.setVisible(false);
        rLeft.setVisible(true);
        rRight.setVisible(true);
        lineL1.setVisible(true);
        lineL2.setVisible(true);
        lineR1.setVisible(true);
        lineR2.setVisible(true);
        lineLmid.setVisible(true);
        lineRmid.setVisible(true);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(3), e -> {
            if (movingRight[0]) {
                ball.setCenterX(ball.getCenterX() + 1);
            }
            else {
                if (movingLeft[0]) {
                    ball.setCenterX(ball.getCenterX() - 1);
                }
                else if (movingLeftUp[0]) {
                    ball.setCenterX(ball.getCenterX() - 1);
                    ball.setCenterY(ball.getCenterY() - 1);
                }
                else if (movingLeftDown[0]) {
                    ball.setCenterX(ball.getCenterX() - 1);
                    ball.setCenterY(ball.getCenterY() + 1);
                }
                else if (movingRightUp[0]) {
                    ball.setCenterX(ball.getCenterX() + 1);
                    ball.setCenterY(ball.getCenterY() - 1);
                }
                else if (movingRightDown[0]) {
                    ball.setCenterX(ball.getCenterX() + 1);
                    ball.setCenterY(ball.getCenterY() + 1);
                }
            }

            if (lineRmid.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                movingLeft[0] = true;
                movingRight[0] = false;
            }
            else if (lineLmid.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                movingRight[0] = true;
                movingLeft[0] = false;
            }
            else if (lineR1.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                movingLeftUp[0] = true;
                movingRight[0] = false;
                movingLeft[0] = false;
            }
            else if (lineR2.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                movingLeftDown[0] = true;
                movingRight[0] = false;
                movingLeftUp[0] = false;
                movingLeft[0] = false;
            }
            else if (lineL1.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                movingRightUp[0] = true;
                movingLeft[0] = false;
                movingRight[0] = false;
                movingLeftUp[0] = false;
                movingLeftDown[0] = false;
            }
            else if (lineL2.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                movingRightDown[0] = true;
                movingRightUp[0] = false;
                movingLeft[0] = false;
                movingRight[0] = false;
                movingLeftUp[0] = false;
                movingLeftDown[0] = false;
            }

            if (topLine.getBoundsInParent().intersects(ball.getBoundsInParent()) ||
                    botLine.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                if (movingLeftDown[0]) {
                    movingLeftUp[0] = true;
                    movingLeftDown[0] = false;
                }
                else if (movingLeftUp[0]) {
                    movingLeftDown[0] = true;
                    movingLeftUp[0] = false;
                }
                else if (movingRightUp[0]) {
                    movingRightDown[0] = true;
                    movingRightUp[0] = false;
                }
                else if (movingRightDown[0]) {
                    movingRightUp[0] = true;
                    movingRightDown[0] = false;
                }
            }

            if (rLeft.getBoundsInParent().intersects(topLine.getBoundsInParent())) {
                rLeft.setY(rLeft.getY() + 30);
                lineL1.setStartY(lineL1.getStartY() + 30);
                lineL1.setEndY(lineL1.getEndY() + 30);

                lineL2.setStartY(lineL2.getStartY() + 30);
                lineL2.setEndY(lineL2.getEndY() + 30);

                lineLmid.setStartY(lineLmid.getStartY() + 30);
                lineLmid.setEndY(lineLmid.getEndY() + 30);

            }
            else if (rLeft.getBoundsInParent().intersects(botLine.getBoundsInParent())) {
                rLeft.setY(rLeft.getY() - 30);
                lineL1.setStartY(lineL1.getStartY() - 30);
                lineL1.setEndY(lineL1.getEndY() - 30);

                lineL2.setStartY(lineL2.getStartY() - 30);
                lineL2.setEndY(lineL2.getEndY() - 30);

                lineLmid.setStartY(lineLmid.getStartY() - 30);
                lineLmid.setEndY(lineLmid.getEndY() - 30);
            }

            if (rRight.getBoundsInParent().intersects(topLine.getBoundsInParent())) {
                rRight.setY(rRight.getY() + 30);
                lineR1.setStartY(lineR1.getStartY() + 30);
                lineR1.setEndY(lineR1.getEndY() + 30);

                lineR2.setStartY(lineR2.getStartY() + 30);
                lineR2.setEndY(lineR2.getEndY() + 30);

                lineRmid.setStartY(lineRmid.getStartY() + 30);
                lineRmid.setEndY(lineRmid.getEndY() + 30);
            }
            else if (rRight.getBoundsInParent().intersects(botLine.getBoundsInParent())) {
                rRight.setY(rRight.getY() - 30);
                lineR1.setStartY(lineR1.getStartY() - 30);
                lineR1.setEndY(lineR1.getEndY() - 30);

                lineR2.setStartY(lineR2.getStartY() - 30);
                lineR2.setEndY(lineR2.getEndY() - 30);

                lineRmid.setStartY(lineRmid.getStartY() - 30);
                lineRmid.setEndY(lineRmid.getEndY() - 30);
            }

            if (leftBoundary.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                int score1 = Integer.parseInt(pl2_score.getText()) + 1;
                pl2_score.setText("" + score1);
                timeline.stop();

                lineLmid.setStartY(52.507110595703125);
                lineLmid.setEndY(79.5);
                lineL1.setStartY(2.8000030517578125);
                lineL1.setEndY(17.800003051757812);
                lineL2.setStartY(0.9999847412109375);
                lineL2.setEndY(17.800003051757812);

                lineRmid.setStartY(56.19999694824219);
                lineRmid.setEndY(79.20001220703125);
                lineR1.setStartY(0.9999847412109375);
                lineR1.setEndY(17.800003051757812);
                lineR2.setStartY(0);
                lineR2.setEndY(17.800003051757812);

                rLeft.relocate(47, 170);
                rRight.relocate(544, 170);
                ball.setCenterY(0);
                ball.setCenterX(450);
                left = true;

                rLeft.setVisible(false);
                rRight.setVisible(false);
                lineL1.setVisible(false);
                lineL2.setVisible(false);
                lineR1.setVisible(false);
                lineR2.setVisible(false);
                lineLmid.setVisible(false);
                lineRmid.setVisible(false);

                if (score1 < 11) {
                    beginBtn.setVisible(true);
                }
                else {
                    beginBtn1.setVisible(true);
                    beginBtn2.setVisible(true);

                    winner.setText(pl2_name.getText());
                    winner.setVisible(true);
                    win_text.setVisible(true);
                }
            }
            else if (rightBoundary.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                int score2 = Integer.parseInt(pl1_score.getText()) + 1;
                pl1_score.setText("" + score2);
                timeline.stop();


                lineLmid.setStartY(52.507110595703125);
                lineLmid.setEndY(79.5);
                lineL1.setStartY(2.8000030517578125);
                lineL1.setEndY(17.800003051757812);
                lineL2.setStartY(0.9999847412109375);
                lineL2.setEndY(17.800003051757812);

                lineRmid.setStartY(56.19999694824219);
                lineRmid.setEndY(79.20001220703125);
                lineR1.setStartY(0.9999847412109375);
                lineR1.setEndY(17.800003051757812);
                lineR2.setStartY(0);
                lineR2.setEndY(17.800003051757812);

                rLeft.relocate(47, 170);
                rRight.relocate(544, 171);
                ball.setCenterY(0);
                ball.setCenterX(0);
                left = false;

                rLeft.setVisible(false);
                rRight.setVisible(false);
                lineL1.setVisible(false);
                lineL2.setVisible(false);
                lineR1.setVisible(false);
                lineR2.setVisible(false);
                lineLmid.setVisible(false);
                lineRmid.setVisible(false);

                if (score2 < 11) {
                    beginBtn.setVisible(true);
                }
                else {
                    beginBtn1.setVisible(true);
                    beginBtn2.setVisible(true);
                }
            }

        });
        timeline.getKeyFrames().add(keyFrame1);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void restart() {
        pl1_score.setText("0");
        pl2_score.setText("0");

        beginBtn.setVisible(true);
        beginBtn1.setVisible(false);
        beginBtn2.setVisible(false);

        win_text.setVisible(false);
        winner.setVisible(false);
    }

    public void restartWithDiffPlayers(ActionEvent actionEvent) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        root = fxmlLoader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        if (win_text != null && winner != null) {
            win_text.setVisible(false);
            winner.setVisible(false);
        }


        HelloController controller = fxmlLoader.getController();
        AnchorPane ap1 = controller.ap1;
        Pane pane1 = controller.pane1;

        ap1.layoutXProperty().bind(
                pane1.widthProperty().subtract(ap1.widthProperty()).divide(2)
        );
        ap1.layoutYProperty().bind(
                pane1.heightProperty().subtract(ap1.heightProperty()).divide(2)
        );
    }
    private Button btn;
    private Button btn1;
    public void mouseEnteredEffect(MouseEvent mouseEvent) {
        Button[] buttons_hello = {cnp, play, cnp1};

        for (Button button : buttons_hello) {
            if (button == null){

            }
            else if (button.isHover()) {
                button.setLayoutY(button.getLayoutY() - 1);
                button.setTextFill(Paint.valueOf("#FFFFFF"));
                btn = button;
            }
        }

        Button[] buttons_play = {beginBtn, beginBtn1, beginBtn2};

        for (Button button : buttons_play) {
            if (button == null){

            }
            else if (button.isHover()) {
                button.setScaleX(1.1);
                button.setScaleY(1.1);
                btn1 = button;
            }
        }
    }

    public void mouseExitedEffect(MouseEvent mouseEvent) {
        if (btn != null) {
        btn.setLayoutY(btn.getLayoutY() + 1);
        btn.setTextFill(Paint.valueOf("#09a419"));
        }

        if (btn1 != null) {
            btn1.setScaleX(1);
            btn1.setScaleY(1);
        }
    }

}