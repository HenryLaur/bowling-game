package frame;

import score.ScoreEnums;

public class Frame {
    private int points;
    private ScoreEnums firstThrow;
    private ScoreEnums secondThrow;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ScoreEnums getFirstThrow() {
        return firstThrow;
    }

    public void setFirstThrow(ScoreEnums firstThrow) {
        this.firstThrow = firstThrow;
    }

    public ScoreEnums getSecondThrow() {
        return secondThrow;
    }

    public void setSecondThrow(ScoreEnums secondThrow) {
        this.secondThrow = secondThrow;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public boolean isCurrentFrameCompleted() {
        return this.firstThrow == ScoreEnums.STRIKE || this.firstThrow != null && this.secondThrow != null;
    }

}
