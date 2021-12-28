//import org.junit.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import Model.Score;
//
//public class TestScore {
//    @Test public void testAddScore()
//    {
//        Score test = new Score();
//
//        test.addScore(3);
//        test.addScore(2);
//
//        assertEquals(5, test.getScore());
//    }
//
//    @Test public void testUpdateScore()
//    {
//        Score test = new Score();
//
//        test.updateScore(16);
//        test.updateScore(0);
//
//        assertEquals(0, test.getScore());
//    }
//
//    @Test public void testUpdateHighScore()
//    {
//        Score test = new Score();
//
//        test.addScore(16);
//        test.updateHighScore(); //HS = 16
//
//        test.addScore(1);
//        test.updateHighScore(); //HS = 17
//
//        test.updateScore(5);
//        test.updateHighScore(); //HS = 17
//
//        assertEquals(17, test.getHighScore());
//    }
//}
