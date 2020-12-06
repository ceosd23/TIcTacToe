import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class TicTacToe extends Application
{
    public static String mainScreenID="main";
    public static String mainScreenFile="/view/Main.fxml";
    public static String PvPScreenID="pvp";
    public static String PvPScreenFile="/view/PvP.fxml";
    public static String PvCScreenID="pvc";
    public static String PvCScreenFile="/view/PvC.fxml";
    @Override
    public void start(Stage stage)throws Exception
    {
        ScreenController mainScreen=new ScreenController();
        mainScreen.loadScreen(TicTacToe.mainScreenID,TicTacToe.mainScreenFile);
        mainScreen.loadScreen(TicTacToe.PvCScreenID,TicTacToe.PvCScreenFile);
        mainScreen.loadScreen(TicTacToe.PvPScreenID,PvPScreenFile);

        mainScreen.setScreen(TicTacToe.mainScreenID);
        Group root=new Group();
        root.getChildren().addAll(mainScreen);
        Scene scene=new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
