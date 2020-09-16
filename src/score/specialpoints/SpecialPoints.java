package score.specialpoints;

import frame.Frame;

import java.util.List;

public interface SpecialPoints {
    void addFrameWithSpecialPoints(Frame frame);

    boolean canAddSpecialPoints(List<Frame> frames, Frame frameWithSpecialPoints);

    void addSpecialPoints(List<Frame> frames, Frame frameWithSpecialPoints);

    void checkIfPossibleToAddSpecialPoints(List<Frame> frames);
}
