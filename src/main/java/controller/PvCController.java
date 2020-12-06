package controller;

import Algorithm.MinMax;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

public class PvCController implements Initializable, ControllerScreen {

    private  ScreenController screenController;
    private MinMax minMax;


    private int[][] board;
    @Override
    public void setScreenParent(ScreenController parent)
    {
        screenController=parent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.board = new int[3][3];
        minMax=new MinMax();
        computerTurn();
    }

    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    Button b5;
    @FXML
    Button b6;
    @FXML
    Button b7;
    @FXML
    Button b8;
    @FXML
    Button b9;

    @FXML
    GridPane gameBoard;

    //Keeps Count of the blanks already filled
    private int count=1;

    //Alert
    private final Alert a=new Alert(Alert.AlertType.CONFIRMATION);

    /*This Function Checks the winner Button and helps in hightling the winner pattern*/
    private boolean find3InARow() {
        //Row 1
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

    /*Sticks the CSS to the winner button and also raise alert*/
    private void highlightWinningCombo(Button first, Button second, Button third) {
        first.getStyleClass().add("winning-button");
        second.getStyleClass().add("winning-button");
        third.getStyleClass().add("winning-button");
        if(first.getText()=="O")
        {
            a.setContentText("You win \n Want to Play Again");
        }
        else
        {
            a.setContentText("I win \n Want to Play Again");
        }
        a.setTitle("Winner");
        a.setHeaderText("Hurrah");

        ButtonType One = new ButtonType("Play Again");
        ButtonType cancel = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
        a.getButtonTypes().setAll(One, cancel);
        Optional<ButtonType> result = a.showAndWait();
        if(result.isPresent() && result.get()==One)
        {
            refresh();
        }
        else
        {
            System.exit(1);
        }
    }




    //Handles the Button click event
    public void buttonClickHandler(javafx.event.ActionEvent evt) {

        Button clickedButton = (Button) evt.getTarget();
        //This is User Play
        String buttonLabel = clickedButton.getId();
        clickedButton.setDisable(true);
        settoInternalBoard(buttonLabel);
        clickedButton.setText("O");

        //After User Computer Plays
        computerTurn();
        count+=2;
        checkTieOrWin();

    }
    //Checks Wining Or Tie Condtion and Raise and alert
    private void checkTieOrWin()
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
                refresh();
            }
            else
            {
                System.exit(1);
            }
        }

    }
    //This Method handles how Computer make decision
    private void computerTurn()
    {
        int[] decision=minMax.computerTurn(board);
        board[decision[0]][decision[1]]=-1;
        setToExternalBoard(decision);
    }


    //Utility Function to check text inside button
    private boolean equals3(Button a, Button b, Button c) {

        return  !a.getText().equals("") && a.getText().equals(b.getText()) && b.getText().equals(c.getText()) ;
    }


    //Sets user choice to intern board
    private void settoInternalBoard(String buttonLabel) {
        if (buttonLabel.equalsIgnoreCase("b1")) {
            //System.out.println("b1");
            this.board[0][0] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b2")) {
            //System.out.println("b2");
            this.board[0][1] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b3")) {
            //System.out.println("b3");
            this.board[0][2] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b4")) {
            //System.out.println("b4");
            this.board[1][0] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b5")) {
            //System.out.println("b5");
            this.board[1][1] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b6")) {
            //System.out.println("b6");
            this.board[1][2] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b7")) {
            //System.out.println("b7");
            this.board[2][0] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b8")) {
            //System.out.println("b8");
            this.board[2][1] = 1;
        }
        if (buttonLabel.equalsIgnoreCase("b9")) {
            //System.out.println("b9");
            this.board[2][2] = 1;
        }
    }
    //Function handles the decision made by computer to view.
    private void setToExternalBoard(int[] arr) {
        if (arr[0] == 0 && arr[1] == 0) {
            b1.setText("X");
            b1.setDisable(true);
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 0 && arr[1] == 1) {
            b2.setText("X");
            b2.setDisable(true);
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 0 && arr[1] == 2) {
            b3.setText("X");
            b3.setDisable(true);
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 1 && arr[1] == 0) {
            b4.setText("X");
            b4.setDisable(true);
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 1 && arr[1] == 1) {
            b5.setText("X");
            b5.setDisable(true);
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 1 && arr[1] == 2) {
            b6.setText("X");
            b6.setDisable(true);
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 2 && arr[1] == 0) {
            b7.setText("X");
            b6.setDisable(true);
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 2 && arr[1] == 1) {
            b8.setText("X");
            //System.out.println(Arrays.toString(arr));
        }
        if (arr[0] == 2 && arr[1] == 2) {
            b9.setText("X");
            //System.out.println(Arrays.toString(arr));
        }

    }
    //Function for going back to main menu
    public void gotoMain()
    {
        screenController.setScreen("main");
    }
    //Function for refresing the screen
    public void refresh() {
        screenController.unLoadScreen("pvc");
        screenController.loadScreen("pvc","/view/PvC.fxml");
        screenController.setScreen("pvc");
    }
}

