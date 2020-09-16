package frame;

import score.ScoreEnums;

public class FinalFrame extends Frame {

    private ScoreEnums thirdThrow;

    @Override
    public boolean isCurrentFrameCompleted() {
        return getFirstThrow() == ScoreEnums.STRIKE || getFirstThrow() != null && getSecondThrow() == ScoreEnums.SPARE || getFirstThrow() != null && getSecondThrow() != null && thirdThrow != null;
    }

    public ScoreEnums getThirdThrow() {
        return thirdThrow;
    }

    public void setThirdThrow(ScoreEnums thirdThrow) {
        this.thirdThrow = thirdThrow;
    }

}
