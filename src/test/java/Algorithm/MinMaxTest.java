package Algorithm;

import junit.framework.TestCase;
import org.junit.Test;


public class MinMaxTest extends TestCase {

    //This Test If MinMax is making first Choice
    public void testComputerTurn()
    {
        MinMax minMax=new MinMax();
        int[][] board={
                {0,0,0},
                {0,0,0},
                {0,0,0},
        };
        int[] ans=minMax.computerTurn(board);
        assertEquals(0,ans[0]);
        assertEquals(0,ans[1]);
        System.out.println("MinMAX First Choice Working");
    }
    //This test MinMax For second choice for a fixed user input
    @Test
    public void testsecondRun()
    {
        MinMax minMax=new MinMax();
        int[][] board={
                {1,-1,0},
                {0,0,0},
                {0,0,0},
        };
        int[] ans=minMax.computerTurn(board);
        assertEquals(0,ans[0]);
        assertEquals(2,ans[1]);
        System.out.println("MinMax Function Second Step Working");
    }
}