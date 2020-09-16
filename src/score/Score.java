package score;

import frame.FinalFrame;
import frame.Frame;
import score.specialpoints.SpareSpecialPoints;
import score.specialpoints.SpecialPoints;
import score.specialpoints.StrikeSpecialPoints;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private final List<Frame> frames = new ArrayList<>();
    private final SpecialPoints spareSpecialPoints = new SpareSpecialPoints();
    private final SpecialPoints strikeSpecialPoints = new StrikeSpecialPoints();
    public final static int NEXT_FRAME = 1;
    public final static int FRAME_AFTER_NEXT = 2;
    public final static int MAXIMUM_NUMBER_OF_FRAMES = 10;
    public Score() {
        frames.add(new Frame());
    }

    public Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    public void addFrame() {
        if(frames.size() < MAXIMUM_NUMBER_OF_FRAMES - 1) {
            frames.add(new Frame());
        } else {
            frames.add(new FinalFrame());
        }
    }

    public void addFrameWithSpare(Frame frameWithSpare) {
        spareSpecialPoints.addFrameWithSpecialPoints(frameWithSpare);
    }
    public void addFrameWithStrike(Frame frameWithStrike) {
        strikeSpecialPoints.addFrameWithSpecialPoints(frameWithStrike);
    }

    public void checkIfPossibleToAddScoreToFramesWithSpares() {
        spareSpecialPoints.checkIfPossibleToAddSpecialPoints(frames);
    }
    public void checkIfPossibleToAddScoreToFramesWithStrikes() {
        strikeSpecialPoints.checkIfPossibleToAddSpecialPoints(frames);
    }

    public void displayScoreToConsole() {
        System.out.print("| ");
        for (Frame frame : frames) {
            if (frame instanceof FinalFrame && ((FinalFrame) frame).getThirdThrow() != null) {
                System.out.print(frame.getFirstThrow() + " : " + frame.getSecondThrow() + " : " + ((FinalFrame) frame).getThirdThrow() + " (" + frame.getPoints() + ")");
                System.out.print(" | ");
            } else if(frame.getFirstThrow() != null && frame.getSecondThrow() != null) {
                    System.out.print(frame.getFirstThrow() + " : " + frame.getSecondThrow() + " (" + frame.getPoints() + ")");
                    System.out.print(" | ");

            } else if (frame.getFirstThrow() != null) {
                System.out.print(frame.getFirstThrow() + " (" + frame.getPoints() + ")");
                System.out.print(" | ");
            }
        }
        System.out.print(" Total: " + getTotalScore());
    }

    public int getTotalScore() {
        return frames.stream().mapToInt(Frame::getPoints).sum();
    }

    public boolean gameOver() {
        return frames.size() > MAXIMUM_NUMBER_OF_FRAMES;
    }
}
