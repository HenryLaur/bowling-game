package frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import score.ScoreEnums;

import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    private FinalFrame frame;
    @BeforeEach
    void setUp() {
        this.frame = new FinalFrame();
    }

    @Test
    void isCurrentFrameCompletedFalse() {
        assertFalse(frame.isCurrentFrameCompleted());
    }
    @Test
    void isCurrentFrameCompletedTwoThrows() {
        frame.setFirstThrow(ScoreEnums.ONE);
        frame.setSecondThrow(ScoreEnums.TWO);
        assertFalse(frame.isCurrentFrameCompleted());
    }
    @Test
    void isCurrentFrameCompletedTrueStrike() {
        frame.setFirstThrow(ScoreEnums.STRIKE);
        assertTrue(frame.isCurrentFrameCompleted());
    }

    @Test
    void isCurrentFrameCompletedTrueTwoThrows() {
        frame.setFirstThrow(ScoreEnums.ONE);
        frame.setSecondThrow(ScoreEnums.TWO);
        frame.setThirdThrow(ScoreEnums.THREE);
        assertTrue(frame.isCurrentFrameCompleted());
    }

}