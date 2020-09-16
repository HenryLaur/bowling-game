package score.specialpoints;

import frame.Frame;
import score.ScoreEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static score.Score.FRAME_AFTER_NEXT;
import static score.Score.NEXT_FRAME;

public class StrikeSpecialPoints implements SpecialPoints {
    private final List<Frame> framesWithStrike = new ArrayList<>();

    @Override
    public void addFrameWithSpecialPoints(Frame frame) {
        framesWithStrike.add(frame);
    }

    @Override
    public boolean canAddSpecialPoints(List<Frame> frames, Frame frameWithSpecialPoints) {
        return (frames.get(frames.indexOf(frameWithSpecialPoints) + NEXT_FRAME).getFirstThrow() != null && frames.get(frames.indexOf(frameWithSpecialPoints) + NEXT_FRAME).getSecondThrow() != null)
                || (frames.get(frames.indexOf(frameWithSpecialPoints) + NEXT_FRAME).getFirstThrow() == ScoreEnums.STRIKE && frames.get(frames.indexOf(frameWithSpecialPoints) + FRAME_AFTER_NEXT).getFirstThrow() != null);
    }

    @Override
    public void addSpecialPoints(List<Frame> frames, Frame frameWithSpecialPoints) {
        int points = frames.get(frames.indexOf(frameWithSpecialPoints) + NEXT_FRAME).getFirstThrow().getValue();
        if (frames.get(frames.indexOf(frameWithSpecialPoints) + NEXT_FRAME).getSecondThrow() == ScoreEnums.SPARE) {
            points = ScoreEnums.SPARE.getValue();
        } else if (frames.get(frames.indexOf(frameWithSpecialPoints) + NEXT_FRAME).getSecondThrow() != null) {
            points += frames.get(frames.indexOf(frameWithSpecialPoints) + NEXT_FRAME).getSecondThrow().getValue();
        } else {
            points += frames.get(frames.indexOf(frameWithSpecialPoints) + FRAME_AFTER_NEXT).getFirstThrow().getValue();
        }
        frameWithSpecialPoints.addPoints(points);
    }

    @Override
    public void checkIfPossibleToAddSpecialPoints(List<Frame> frames) {
        framesWithStrike.stream().filter(frame -> canAddSpecialPoints(frames, frame)).forEach(frame -> addSpecialPoints(frames, frame));
        framesWithStrike.removeAll(framesWithStrike.stream().filter(frame -> canAddSpecialPoints(frames, frame)).collect(Collectors.toList()));
    }
}
