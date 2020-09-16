package player;

import frame.FinalFrame;
import frame.Frame;
import score.Score;
import score.ScoreEnums;

public class Player {

    private final Score score;
    private boolean isCurrentFrameCompleted = false;

    public Score getScore() {
        return score;
    }

    public Player(Score score) {
        this.score = score;
    }

    public boolean checkIfIsSpare(ScoreEnums currentThrow) {
        Frame currentFrame = score.getCurrentFrame();
        int points = currentFrame.getPoints();
        return points + currentThrow.getValue() >= 10;
    }

    public ScoreEnums convertIntToScoreEnum(int points) {
        Frame currentFrame = score.getCurrentFrame();
        if(currentFrame.getFirstThrow() == null && points == 10) {
            return ScoreEnums.STRIKE;
        } else if (currentFrame.getFirstThrow() != null && points == 10) {
            return ScoreEnums.SPARE;
        } else {
            return ScoreEnums.values()[points];
        }
    }

    private void addNextFrame() {
        score.addFrame();
    }

    public boolean isCurrentFrameCompleted() {
        return isCurrentFrameCompleted;
    }

    public void bowl(int points) {
        ScoreEnums currentThrow = convertIntToScoreEnum(points);
        Frame currentFrame = score.getCurrentFrame();
        isCurrentFrameCompleted = currentFrame.isCurrentFrameCompleted();
        if(currentFrame.getFirstThrow() == null) {
            handleFirstThrow(currentThrow, currentFrame);
        } else if(currentFrame instanceof FinalFrame && currentFrame.getSecondThrow() != null) {
            handleLastFrameLastThrow(currentThrow, currentFrame);
        } else if(checkIfIsSpare(currentThrow)) {
            handleSpare(currentFrame);

        } else{
            handleRegularSecondThrow(currentThrow, currentFrame);
        }
        if(currentFrame.isCurrentFrameCompleted()) {
            isCurrentFrameCompleted = true;
            addNextFrame();
        }
        score.checkIfPossibleToAddScoreToFramesWithSpares();
        score.checkIfPossibleToAddScoreToFramesWithStrikes();
    }

    public void handleFirstThrow(ScoreEnums currentThrow, Frame currentFrame) {
        currentFrame.setFirstThrow(currentThrow);
        currentFrame.addPoints(currentThrow.getValue());
        if(currentThrow == ScoreEnums.STRIKE) {
            score.addFrameWithStrike(currentFrame);
        }
    }

    public void handleLastFrameLastThrow(ScoreEnums currentThrow, Frame currentFrame) {
        if (checkIfIsSpare(currentThrow)) {
            ((FinalFrame) currentFrame).setThirdThrow(ScoreEnums.SPARE);
            currentFrame.setPoints(ScoreEnums.SPARE.getValue());
        } else {
            ((FinalFrame) currentFrame).setThirdThrow(currentThrow);
            currentFrame.addPoints(currentThrow.getValue());
        }
    }

    public void handleRegularSecondThrow(ScoreEnums currentThrow, Frame currentFrame) {
        currentFrame.setSecondThrow(currentThrow);
        currentFrame.addPoints(currentThrow.getValue());
    }

    public void handleSpare(Frame currentFrame) {
        currentFrame.setSecondThrow(ScoreEnums.SPARE);
        currentFrame.setPoints(ScoreEnums.SPARE.getValue());
        score.addFrameWithSpare(currentFrame);
    }
}
