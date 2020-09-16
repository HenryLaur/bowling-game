package score.specialpoints;

import frame.Frame;
import score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpareSpecialPoints implements SpecialPoints {
    private final List<Frame> framesWithSpare = new ArrayList<>();

    @Override
    public void addFrameWithSpecialPoints(Frame frame) {
        framesWithSpare.add(frame);
    }

    @Override
    public boolean canAddSpecialPoints(List<Frame> frames, Frame frameWithSpecialPoints) {
        return frames.get(frames.indexOf(frameWithSpecialPoints) + Score.NEXT_FRAME).getFirstThrow() != null;
    }


    @Override
    public void addSpecialPoints(List<Frame> frames, Frame frameWithSpecialPoints) {
        frameWithSpecialPoints.addPoints(frames.get(frames.indexOf(frameWithSpecialPoints) + Score.NEXT_FRAME).getFirstThrow().getValue());

    }

    @Override
    public void checkIfPossibleToAddSpecialPoints(List<Frame> frames) {
        framesWithSpare.stream().filter(frame -> canAddSpecialPoints(frames, frame)).forEach(frame -> addSpecialPoints(frames, frame));
        framesWithSpare.removeAll(framesWithSpare.stream().filter(frame -> canAddSpecialPoints(frames, frame)).collect(Collectors.toList()));
    }
}
