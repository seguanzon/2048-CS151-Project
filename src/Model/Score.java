package Model;

/**
 * @author Ashley Irawan and Calvin Quach
 * Class which holds data of the score and high score of the 2048 game.
 * Handles various operations relating to both the score and high score.
 */
public class Score {
    private int score;
    private static int highScore;

    /**
     * Default constructor for Score class.
     * Initializes score and highScore to 0.
     */
    public Score()
    {
        score = 0;
        highScore = 0;
    }

    /**
     * Method to increase score variable by specified amount. 
     * @param scoreValue parameter to increase score variable by.
     */
    public void addScore(int scoreValue)
    {
        score += scoreValue;
    }

    /**
     * Method to increase score variable by specified amount and handle the high Score.
     * If the score variable is greater than the previous highScore, then update the high score 
     * @param scoreValue parameter to increase score variable by.
     */
    public void updateScore(int scoreValue)
    {
        score += scoreValue;
        if (score > highScore) highScore = score;
    }


    /**
     * Method to return the score variable.
     * @return the score variable as an integer.
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Method to return the highScore variable.
     * @return the score variable as an integer.
     */
    public int getHighScore()
    {
        return highScore;
    }
}