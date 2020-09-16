package frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import score.ScoreEnums;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        this.frame = new Frame();
    }

    @Test
    void isCurrentFrameCompletedFalse() {
        assertFalse(frame.isCurrentFrameCompleted());
    }

    @Test
    void isCurrentFrameCompletedNotStrike() {
        frame.setFirstThrow(ScoreEnums.TWO);
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
        assertTrue(frame.isCurrentFrameCompleted());
    }
}