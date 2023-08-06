package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.MergePathsContent;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.Logger;

public class MergePaths implements ContentModel {
    private final boolean hidden;
    private final MergePathsMode mode;
    private final String name;

    public enum MergePathsMode {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static MergePathsMode forId(int id) {
            switch (id) {
                case 1:
                    return MERGE;
                case 2:
                    return ADD;
                case 3:
                    return SUBTRACT;
                case 4:
                    return INTERSECT;
                case 5:
                    return EXCLUDE_INTERSECTIONS;
                default:
                    return MERGE;
            }
        }
    }

    public MergePaths(String name2, MergePathsMode mode2, boolean hidden2) {
        this.name = name2;
        this.mode = mode2;
        this.hidden = hidden2;
    }

    public String getName() {
        return this.name;
    }

    public MergePathsMode getMode() {
        return this.mode;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public Content toContent(LottieDrawable drawable, BaseLayer layer) {
        if (drawable.enableMergePathsForKitKatAndAbove()) {
            return new MergePathsContent(this);
        }
        Logger.warning("Animation contains merge paths but they are disabled.");
        return null;
    }

    public String toString() {
        return "MergePaths{mode=" + this.mode + '}';
    }
}
