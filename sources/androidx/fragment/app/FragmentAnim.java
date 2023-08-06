package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R;

class FragmentAnim {
    private FragmentAnim() {
    }

    static AnimationOrAnimator loadAnimation(Context context, Fragment fragment, boolean enter, boolean isPop) {
        int transit = fragment.getNextTransition();
        int nextAnim = getNextAnim(fragment, enter, isPop);
        fragment.setAnimations(0, 0, 0, 0);
        if (!(fragment.mContainer == null || fragment.mContainer.getTag(R.id.visible_removing_fragment_view_tag) == null)) {
            fragment.mContainer.setTag(R.id.visible_removing_fragment_view_tag, (Object) null);
        }
        if (fragment.mContainer != null && fragment.mContainer.getLayoutTransition() != null) {
            return null;
        }
        Animation animation = fragment.onCreateAnimation(transit, enter, nextAnim);
        if (animation != null) {
            return new AnimationOrAnimator(animation);
        }
        Animator animator = fragment.onCreateAnimator(transit, enter, nextAnim);
        if (animator != null) {
            return new AnimationOrAnimator(animator);
        }
        if (nextAnim == 0 && transit != 0) {
            nextAnim = transitToAnimResourceId(context, transit, enter);
        }
        if (nextAnim != 0) {
            boolean isAnim = "anim".equals(context.getResources().getResourceTypeName(nextAnim));
            boolean successfulLoad = false;
            if (isAnim) {
                try {
                    Animation animation2 = AnimationUtils.loadAnimation(context, nextAnim);
                    if (animation2 != null) {
                        return new AnimationOrAnimator(animation2);
                    }
                    successfulLoad = true;
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException e2) {
                }
            }
            if (!successfulLoad) {
                try {
                    Animator animator2 = AnimatorInflater.loadAnimator(context, nextAnim);
                    if (animator2 != null) {
                        return new AnimationOrAnimator(animator2);
                    }
                } catch (RuntimeException e3) {
                    if (!isAnim) {
                        Animation animation3 = AnimationUtils.loadAnimation(context, nextAnim);
                        if (animation3 != null) {
                            return new AnimationOrAnimator(animation3);
                        }
                    } else {
                        throw e3;
                    }
                }
            }
        }
        return null;
    }

    private static int getNextAnim(Fragment fragment, boolean enter, boolean isPop) {
        if (isPop) {
            if (enter) {
                return fragment.getPopEnterAnim();
            }
            return fragment.getPopExitAnim();
        } else if (enter) {
            return fragment.getEnterAnim();
        } else {
            return fragment.getExitAnim();
        }
    }

    private static int transitToAnimResourceId(Context context, int transit, boolean enter) {
        int animAttr;
        int animAttr2;
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN /*4097*/:
                return enter ? R.animator.fragment_open_enter : R.animator.fragment_open_exit;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE /*4099*/:
                return enter ? R.animator.fragment_fade_enter : R.animator.fragment_fade_exit;
            case FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_OPEN /*4100*/:
                if (enter) {
                    animAttr = toActivityTransitResId(context, 16842936);
                } else {
                    animAttr = toActivityTransitResId(context, 16842937);
                }
                return animAttr;
            case 8194:
                return enter ? R.animator.fragment_close_enter : R.animator.fragment_close_exit;
            case FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_CLOSE /*8197*/:
                if (enter) {
                    animAttr2 = toActivityTransitResId(context, 16842938);
                } else {
                    animAttr2 = toActivityTransitResId(context, 16842939);
                }
                return animAttr2;
            default:
                return -1;
        }
    }

    private static int toActivityTransitResId(Context context, int attrInt) {
        TypedArray typedArray = context.obtainStyledAttributes(16973825, new int[]{attrInt});
        int resId = typedArray.getResourceId(0, -1);
        typedArray.recycle();
        return resId;
    }

    static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animation animation2) {
            this.animation = animation2;
            this.animator = null;
            if (animation2 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        AnimationOrAnimator(Animator animator2) {
            this.animation = null;
            this.animator = animator2;
            if (animator2 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        private boolean mAnimating = true;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimation(Animation animation, ViewGroup parent, View child) {
            super(false);
            this.mParent = parent;
            this.mChild = child;
            addAnimation(animation);
            parent.post(this);
        }

        public boolean getTransformation(long currentTime, Transformation t) {
            this.mAnimating = true;
            if (this.mEnded) {
                return true ^ this.mTransitionEnded;
            }
            if (!super.getTransformation(currentTime, t)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public boolean getTransformation(long currentTime, Transformation outTransformation, float scale) {
            this.mAnimating = true;
            if (this.mEnded) {
                return true ^ this.mTransitionEnded;
            }
            if (!super.getTransformation(currentTime, outTransformation, scale)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
                return;
            }
            this.mAnimating = false;
            this.mParent.post(this);
        }
    }
}
