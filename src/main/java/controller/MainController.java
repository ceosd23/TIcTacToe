package controller;


import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements ControllerScreen, Initializable
{
    ScreenController screenController;
    @Override
    public void setScreenParent(ScreenController parent) {
        screenController=parent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void gotoPvPScreen(javafx.event.ActionEvent actionEvent)
    {
        screenController.setScreen("pvp");
    }

    public void gotoPvCScreen(javafx.event.ActionEvent actionEvent)
    {
        screenController.setScreen("pvc");
    }
}