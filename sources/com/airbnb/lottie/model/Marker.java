package com.airbnb.lottie.model;

public class Marker {
    private static final String CARRIAGE_RETURN = "\r";
    public final float durationFrames;
    private final String name;
    public final float startFrame;

    public Marker(String name2, float startFrame2, float durationFrames2) {
        this.name = name2;
        this.durationFrames = durationFrames2;
        this.startFrame = startFrame2;
    }

    public String getName() {
        return this.name;
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public float getDurationFrames() {
        return this.durationFrames;
    }

    public boolean matchesName(String name2) {
        if (this.name.equalsIgnoreCase(name2)) {
            return true;
        }
        if (this.name.endsWith(CARRIAGE_RETURN)) {
            String str = this.name;
            if (str.substring(0, str.length() - 1).equalsIgnoreCase(name2)) {
                return true;
            }
        }
        return false;
    }
}
