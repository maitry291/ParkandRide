package com.airbnb.lottie;

public enum RenderMode {
    AUTOMATIC,
    HARDWARE,
    SOFTWARE;

    /* renamed from: com.airbnb.lottie.RenderMode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$RenderMode = null;

        static {
            int[] iArr = new int[RenderMode.values().length];
            $SwitchMap$com$airbnb$lottie$RenderMode = iArr;
            try {
                iArr[RenderMode.HARDWARE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$RenderMode[RenderMode.SOFTWARE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$RenderMode[RenderMode.AUTOMATIC.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public boolean useSoftwareRendering(int sdkInt, boolean hasDashPattern, int numMasksAndMattes) {
        switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$RenderMode[ordinal()]) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                if ((!hasDashPattern || sdkInt >= 28) && numMasksAndMattes <= 4 && sdkInt > 25) {
                    return false;
                }
                return true;
        }
    }
}
