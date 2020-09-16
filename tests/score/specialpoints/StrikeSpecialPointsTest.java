package score.specialpoints;

import frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import score.ScoreEnums;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrikeSpecialPointsTest {

    private StrikeSpecialPoints strikeSpecialPoints;
    @BeforeEach
    void setUp() {
        strikeSpecialPoints = new StrikeSpecialPoints();
    }



    @Test
    void canAddSpecialPointsWithEnoughFrames() {
        List<Frame> framesList = new ArrayList<>();
        Frame frame = new Frame();
        frame.setFirstThrow(ScoreEnums.STRIKE);
        frame.setPoints(10);
        framesList.add(frame);
        Frame frame2 = new Frame();
        frame2.setFirstThrow(ScoreEnums.SEVEN);
        frame2.setSecondThrow(ScoreEnums.TWO);
        frame2.setPoints(9);
        framesList.add(frame2);
        assertTrue(strikeSpecialPoints.canAddSpecialPoints(framesList, frame));
    }

    @Test
    void canAddSpecialPointsWithoutEnoughFrames() {
        List<Frame> framesList = new ArrayList<>();
        Frame frame = new Frame();
        frame.setFirstThrow(ScoreEnums.STRIKE);
        frame.setPoints(10);
        framesList.add(frame);
        framesList.add(new Frame());
        assertFalse(strikeSpecialPoints.canAddSpecialPoints(framesList, frame));
    }


    @Test
    void addSpecialPoints() {
        List<Frame> framesList = new ArrayList<>();
        Frame frame = new Frame();
        frame.setFirstThrow(ScoreEnums.STRIKE);
        frame.setPoints(10);
        framesList.add(frame);
        Frame frame2 = new Frame();
        frame2.setFirstThrow(ScoreEnums.SEVEN);
        frame2.setSecondThrow(ScoreEnums.TWO);
        frame2.setPoints(9);
        framesList.add(frame2);
        strikeSpecialPoints.addSpecialPoints(framesList, frame);
        assertEquals(19, frame.getPoints());
    }
}