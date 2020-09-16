package frame;

import score.ScoreEnums;

public class FinalFrame extends Frame {
    private ScoreEnums thirdThrow;

    @Override
    public boolean isCurrentFrameCompleted() {
        return getFirstThrow() == ScoreEnums.STRIKE || getFirstThrow() != null && getFirstThrow() == ScoreEnums.SPARE ||  getFirstThrow() != null && getFirstThrow() != null && thirdThrow != null;
    }

    public void setThirdThrow(ScoreEnums thirdThrow) {
        this.thirdThrow = thirdThrow;
    }
    public ScoreEnums getThirdThrow() {
        return thirdThrow;
    }

}
