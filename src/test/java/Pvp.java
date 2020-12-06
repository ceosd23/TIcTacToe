import controller.ScreenController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static junit.framework.Assert.fail;

public class Pvp extends ApplicationTest {

    public void start(Stage stage) throws Exception {
        ScreenController mainScreen = new ScreenController();
        mainScreen.loadScreen(TicTacToe.mainScreenID, TicTacToe.mainScreenFile);
        mainScreen.loadScreen(TicTacToe.PvPScreenID, TicTacToe.PvPScreenFile);


        mainScreen.setScreen(TicTacToe.PvPScreenID);

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
    public void refreshButtonPvp() throws InterruptedException {

        clickOn("#refresh");
        System.out.println("Refresh Button Player Vs Player Test Passed");

        //Label label=(Label) GuiTest.find("#label");
    }
    @Test
    public void mainButtonPvp() throws InterruptedException {

        clickOn("#mainMenu");
        System.out.println("Refresh Button Player Vs Computer Test Passed");
        //Label label=(Label) GuiTest.find("#label");
    }
    @Test
    public void player()
    {
        clickOn("#b7");
        if(lookup("#label").<Label>query().getText()=="Player Two's Turn")
        {
            clickOn("#b8");
            if(lookup("#label").<Label>query().getText()!="Player One's Turn")
            {
                fail();
            }

        }else
            {
                fail();
            }
        System.out.println("Player vs Player Logic Test Passed");

    }


}

