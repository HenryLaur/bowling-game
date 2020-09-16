package score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {

        score = new Score();
    }

    @Test
    void getTotalScore() {
        score.getCurrentFrame().setPoints(10);
        score.addFrame();
        score.getCurrentFrame().setPoints(2);
        score.addFrame();
        score.getCurrentFrame().setPoints(5);
        assertEquals(17, score.getTotalScore());
    }

    @Test
    void gameOverTrue() {
        for (int i = 0; i < 10; i++) {
            score.addFrame();
        }
        assertTrue(score.gameOver());
    }

    @Test
    void gameOverNotEnoughFrames() {
        for (int i = 0; i < 5; i++) {
            score.addFrame();
        }
        assertFalse(score.gameOver());
    }
}