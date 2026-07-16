package de.rovyzen.scoreboard.animation;

import java.util.List;

public class AnimationManager {

    private int index = 0;

    public String next(List<String> frames) {
        if (frames == null || frames.isEmpty()) {
            return "RovyzenScoreboard";
        }

        String frame = frames.get(index);
        index++;

        if (index >= frames.size()) {
            index = 0;
        }

        return frame;
    }
}
