import controller.ScreenController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.fail;

public class PvC extends ApplicationTest {

    public void start(Stage stage) throws Exception {
        ScreenController mainScreen = new ScreenController();
        mainScreen.loadScreen(TicTacToe.mainScreenID, TicTacToe.mainScreenFile);
        mainScreen.loadScreen(TicTacToe.PvCScreenID, TicTacToe.PvCScreenFile);


        mainScreen.setScreen(TicTacToe.PvCScreenID);

        Group root = new Group();
        root.getChildren().addAll(mainScreen);
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void refreshButtonPvc() throws InterruptedException {

        clickOn("#refresh");
        System.out.println("Refresh Button Player Vs Computer Test Passed");

        //Label label=(Label) GuiTest.find("#label");
    }
    @Test
    public void mainButtonPvc() throws InterruptedException {

        clickOn("#mainMenu");
        System.out.println("Back To Main Menu Button Test Passed");
        //Label label=(Label) GuiTest.find("#label");
    }
    @Test
    public void player()
    {

        clickOn("#b7");
        System.out.println("Click Button  Testing Passed");
    }
    @Test
    public void computerInput()
    {
        String x=lookup("#b1").queryButton().getText();
        if(x!="X"){
            fail("Computer Not Putting output");
        }
        System.out.println("Computer Player Working Test passed");
    }

}

