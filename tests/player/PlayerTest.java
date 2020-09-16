package player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import score.Score;
import score.ScoreEnums;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {

        player = new Player(new Score());
    }

    @Test
    void checkIfIsSpareScoreToLittle() {
        player.getScore().getCurrentFrame().setFirstThrow(ScoreEnums.THREE);
        assertFalse(player.checkIfIsSpare(ScoreEnums.ONE));
    }

    @Test
    void checkIfIsSpare() {
        player.getScore().getCurrentFrame().setPoints(ScoreEnums.THREE.getValue());
        assertTrue(player.checkIfIsSpare(ScoreEnums.SEVEN));
    }

    @Test
    void convertIntToScoreEnum() {
        assertEquals(ScoreEnums.SIX, player.convertIntToScoreEnum(6));
    }

    @Test
    void convertIntToScoreEnumStrike() {
        assertEquals(ScoreEnums.STRIKE, player.convertIntToScoreEnum(10));
    }


    @Test
    void bowl() {
    }

    @Test
    void handleFirstThrow() {
        player.handleFirstThrow(ScoreEnums.ONE, player.getScore().getCurrentFrame());
        assertEquals(ScoreEnums.ONE, player.getScore().getCurrentFrame().getFirstThrow());
    }

    @Test
    void handleRegularSecondThrow() {
        player.handleRegularSecondThrow(ScoreEnums.ONE, player.getScore().getCurrentFrame());
        assertEquals(ScoreEnums.ONE, player.getScore().getCurrentFrame().getSecondThrow());
    }

    @Test
    void handleSpare() {
        player.handleSpare(player.getScore().getCurrentFrame());
        assertEquals(ScoreEnums.SPARE, player.getScore().getCurrentFrame().getSecondThrow());
    }
}