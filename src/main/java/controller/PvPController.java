package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PvPController implements Initializable, ControllerScreen
{
    private ScreenController screenController;
    private boolean isFirstPlayer;
    private int count;
    private Alert a = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void setScreenParent(ScreenController parent)
    {
        screenController=parent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.count=0;
        this.isFirstPlayer=true;
    }
    //UI
    @FXML Button b1;
    @FXML Button b2;
    @FXML Button b3;
    @FXML Button b4;
    @FXML Button b5;
    @FXML Button b6;
    @FXML Button b7;
    @FXML Button b8;
    @FXML Button b9;
    @FXML
    Label label;
    @FXML
    GridPane gameBoard;
//UI ENDS HERE


    private boolean find3InARow(){
        if (equals3(b1,b2,b3)) {
            highlightWinningCombo(b1, b2, b3);
            return true;
        }
        //Row 2
        if (equals3(b3,b4,b5)) {
            highlightWinningCombo(b4, b5, b6);
            return true;
        }
        //Row 3
        if (equals3(b7,b8,b9)) {
            highlightWinningCombo(b7, b8, b9);
            return true;
        }
        //Column 1
        if (equals3(b1, b4, b7)) {
            highlightWinningCombo(b1, b4, b7);
            return true;
        }
        //Column 2
        if (equals3(b2,b5,b8)) {
            highlightWinningCombo(b2, b5, b8);
            return true;
        }
        //Column 3
        if (equals3(b3, b6, b9)) {
            highlightWinningCombo(b3, b6, b9);
            return true;
        }
        //Diagonal 1
        if (equals3(b1, b5, b9)) {
            highlightWinningCombo(b1, b5, b9);
            return true;
        }
        //Diagonal 2
        if (equals3(b3, b5, b7)) {
            highlightWinningCombo(b3, b5, b7);
            return true;
        }
        return false;
    }

    private void highlightWinningCombo(Button first, Button second, Button third){
        first.getStyleClass().add("winning-button");
        second.getStyleClass().add("winning-button");
        third.getStyleClass().add("winning-button");
        if(first.getText()=="O")
        {
            a.setContentText("Player 2 wins \n Want to Play Again");
        }
        else
        {
            a.setContentText("Player 1 wins \n Want to Play Again");
        }
        a.setTitle("Winner");
        ButtonType One = new ButtonType("Play Again");
        ButtonType cancel = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
        a.getButtonTypes().setAll(One, cancel);
        Optional<ButtonType> result = a.showAndWait();
        if(result.get()==One)
        {
            refresh(new ActionEvent());
        }
        else
        {
            System.exit(1);
        }

    }


    public void buttonClickHandler(javafx.event.ActionEvent evt)
    {
        Button clickedButton = (Button) evt.getTarget();
        String buttonLabel = clickedButton.getText();

        if ("".equals(buttonLabel) && isFirstPlayer){
            clickedButton.setText("X");
            clickedButton.setDisable(true);
            isFirstPlayer = false;
            label.setText("Player Two's Turn");

        } else if("".equals(buttonLabel) && !isFirstPlayer){
            clickedButton.setText("O");
            clickedButton.setDisable(true);
            isFirstPlayer = true;
            label.setText("Player One's Turn");
        }
        check();

    }
    private void check()
    {
        if(!find3InARow() && count>=9)
        {
            a.setContentText("That was A Tie");
            a.setTitle("Draw");
            a.setHeaderText("Draw");

            ButtonType One = new ButtonType("Re Match");
            ButtonType cancel = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
            a.getButtonTypes().setAll(One, cancel);
            Optional<ButtonType> result = a.showAndWait();
            if(result.isPresent() && result.get()==One)
            {
                refresh(new ActionEvent());
            }
            else
            {
                System.exit(1);
            }
        }
    }

    private boolean equals3(Button a, Button b, Button c) {

        return !a.getText().equals("") && a.getText().equals(b.getText()) && b.getText().equals(c.getText()) ;
    }

    public void gotoMain(ActionEvent actionEvent)
    {
        screenController.setScreen("main");
    }

    public void refresh(ActionEvent actionEvent) {
        screenController.unLoadScreen("pvp");
        screenController.loadScreen("pvp","/view/PvP.fxml");
        screenController.setScreen("pvp");
    }
}
