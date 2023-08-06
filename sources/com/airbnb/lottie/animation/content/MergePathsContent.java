package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.os.Build;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;

public class MergePathsContent implements PathContent, GreedyContent {
    private final Path firstPath = new Path();
    private final MergePaths mergePaths;
    private final String name;
    private final Path path = new Path();
    private final List<PathContent> pathContents = new ArrayList();
    private final Path remainderPath = new Path();

    public MergePathsContent(MergePaths mergePaths2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.name = mergePaths2.getName();
            this.mergePaths = mergePaths2;
            return;
        }
        throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000a, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void absorbContent(java.util.ListIterator<com.airbnb.lottie.animation.content.Content> r4) {
        /*
            r3 = this;
        L_0x0000:
            boolean r0 = r4.hasPrevious()
            if (r0 == 0) goto L_0x000d
            java.lang.Object r0 = r4.previous()
            if (r0 == r3) goto L_0x000d
            goto L_0x0000
        L_0x000d:
            boolean r0 = r4.hasPrevious()
            if (r0 == 0) goto L_0x0029
            java.lang.Object r0 = r4.previous()
            com.airbnb.lottie.animation.content.Content r0 = (com.airbnb.lottie.animation.content.Content) r0
            boolean r1 = r0 instanceof com.airbnb.lottie.animation.content.PathContent
            if (r1 == 0) goto L_0x0028
            java.util.List<com.airbnb.lottie.animation.content.PathContent> r1 = r3.pathContents
            r2 = r0
            com.airbnb.lottie.animation.content.PathContent r2 = (com.airbnb.lottie.animation.content.PathContent) r2
            r1.add(r2)
            r4.remove()
        L_0x0028:
            goto L_0x000d
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.MergePathsContent.absorbContent(java.util.ListIterator):void");
    }

    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        for (int i = 0; i < this.pathContents.size(); i++) {
            this.pathContents.get(i).setContents(contentsBefore, contentsAfter);
        }
    }

    public Path getPath() {
        this.path.reset();
        if (this.mergePaths.isHidden()) {
            return this.path;
        }
        switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[this.mergePaths.getMode().ordinal()]) {
            case 1:
                addPaths();
                break;
            case 2:
                opFirstPathWithRest(Path.Op.UNION);
                break;
            case 3:
                opFirstPathWithRest(Path.Op.REVERSE_DIFFERENCE);
                break;
            case 4:
                opFirstPathWithRest(Path.Op.INTERSECT);
                break;
            case 5:
                opFirstPathWithRest(Path.Op.XOR);
                break;
        }
        return this.path;
    }

    /* renamed from: com.airbnb.lottie.animation.content.MergePathsContent$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode;

        static {
            int[] iArr = new int[MergePaths.MergePathsMode.values().length];
            $SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode = iArr;
            try {
                iArr[MergePaths.MergePathsMode.MERGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePaths.MergePathsMode.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePaths.MergePathsMode.SUBTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePaths.MergePathsMode.INTERSECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePaths.MergePathsMode.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public String getName() {
        return this.name;
    }

    private void addPaths() {
        for (int i = 0; i < this.pathContents.size(); i++) {
            this.path.addPath(this.pathContents.get(i).getPath());
        }
    }

    private void opFirstPathWithRest(Path.Op op) {
        this.remainderPath.reset();
        this.firstPath.reset();
        for (int i = this.pathContents.size() - 1; i >= 1; i--) {
            PathContent content = this.pathContents.get(i);
            if (content instanceof ContentGroup) {
                List<PathContent> pathList = ((ContentGroup) content).getPathList();
                for (int j = pathList.size() - 1; j >= 0; j--) {
                    Path path2 = pathList.get(j).getPath();
                    path2.transform(((ContentGroup) content).getTransformationMatrix());
                    this.remainderPath.addPath(path2);
                }
            } else {
                this.remainderPath.addPath(content.getPath());
            }
        }
        PathContent lastContent = this.pathContents.get(0);
        if (lastContent instanceof ContentGroup) {
            List<PathContent> pathList2 = ((ContentGroup) lastContent).getPathList();
            for (int j2 = 0; j2 < pathList2.size(); j2++) {
                Path path3 = pathList2.get(j2).getPath();
                path3.transform(((ContentGroup) lastContent).getTransformationMatrix());
                this.firstPath.addPath(path3);
            }
        } else {
            this.firstPath.set(lastContent.getPath());
        }
        this.path.op(this.firstPath, this.remainderPath, op);
    }
}
