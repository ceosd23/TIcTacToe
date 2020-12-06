import controller.ScreenController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class TicTacToeTest extends ApplicationTest {

    public void start(Stage stage)throws Exception
    {
        ScreenController mainScreen=new ScreenController();
        mainScreen.loadScreen(TicTacToe.mainScreenID,TicTacToe.mainScreenFile);
        mainScreen.loadScreen(TicTacToe.PvCScreenID,TicTacToe.PvCScreenFile);
        mainScreen.loadScreen(TicTacToe.PvPScreenID, TicTacToe.PvPScreenFile);

        mainScreen.setScreen(TicTacToe.mainScreenID);

        Group root=new Group();
        root.getChildren().addAll(mainScreen);
        Scene scene=new Scene(root,400,400);
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
    public void testPlayerVPlayer() throws InterruptedException {

        clickOn("#pvpButton");
        System.out.println("Player vs Player Option Button Test Passed");
    }
    @Test
    public void testPlayerVComputer()
    {

        clickOn("#pvcButton");
        System.out.println("Player vs Computer Option Button Test Passed");
    }

}