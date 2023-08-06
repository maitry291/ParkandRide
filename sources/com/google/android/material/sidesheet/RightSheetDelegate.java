package com.google.android.material.sidesheet;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

final class RightSheetDelegate extends SheetDelegate {
    final SideSheetBehavior<? extends View> sheetBehavior;

    RightSheetDelegate(SideSheetBehavior<? extends View> sheetBehavior2) {
        this.sheetBehavior = sheetBehavior2;
    }

    /* access modifiers changed from: package-private */
    public int getSheetEdge() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getHiddenOffset() {
        return this.sheetBehavior.getParentWidth();
    }

    /* access modifiers changed from: package-private */
    public int getExpandedOffset() {
        return Math.max(0, getHiddenOffset() - this.sheetBehavior.getChildWidth());
    }

    private boolean isReleasedCloseToOriginEdge(View releasedChild) {
        return releasedChild.getLeft() > (getHiddenOffset() - getExpandedOffset()) / 2;
    }

    /* access modifiers changed from: package-private */
    public int calculateTargetStateOnViewReleased(View releasedChild, float xVelocity, float yVelocity) {
        if (xVelocity < 0.0f) {
            return 3;
        }
        if (shouldHide(releasedChild, xVelocity)) {
            if (isSwipeSignificant(xVelocity, yVelocity) || isReleasedCloseToOriginEdge(releasedChild)) {
                return 5;
            }
            return 3;
        } else if (xVelocity != 0.0f && SheetUtils.isSwipeMostlyHorizontal(xVelocity, yVelocity)) {
            return 5;
        } else {
            int currentLeft = releasedChild.getLeft();
            if (Math.abs(currentLeft - getExpandedOffset()) < Math.abs(currentLeft - getHiddenOffset())) {
                return 3;
            }
            return 5;
        }
    }

    private boolean isSwipeSignificant(float xVelocity, float yVelocity) {
        return SheetUtils.isSwipeMostlyHorizontal(xVelocity, yVelocity) && yVelocity > ((float) this.sheetBehavior.getSignificantVelocityThreshold());
    }

    /* access modifiers changed from: package-private */
    public <V extends View> void setTargetStateOnNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed, int type) {
        int currentLeft = child.getLeft();
        int newLeft = currentLeft - dx;
        if (dx < 0) {
            if (newLeft > getExpandedOffset()) {
                consumed[1] = currentLeft - getExpandedOffset();
                ViewCompat.offsetLeftAndRight(child, -consumed[1]);
                this.sheetBehavior.setStateInternal(3);
            } else if (this.sheetBehavior.isDraggable()) {
                consumed[1] = dx;
                ViewCompat.offsetLeftAndRight(child, -dx);
                this.sheetBehavior.setStateInternal(1);
            }
        } else if (dx > 0 && !target.canScrollHorizontally(-1)) {
            if (newLeft > getHiddenOffset()) {
                consumed[1] = currentLeft - getHiddenOffset();
                ViewCompat.offsetLeftAndRight(child, consumed[1]);
                this.sheetBehavior.setStateInternal(5);
            } else if (this.sheetBehavior.isDraggable()) {
                consumed[1] = dx;
                ViewCompat.offsetLeftAndRight(child, dx);
                this.sheetBehavior.setStateInternal(1);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public <V extends View> int calculateTargetStateOnStopNestedScroll(V child) {
        if (this.sheetBehavior.getLastNestedScrollDx() > 0) {
            return 3;
        }
        SideSheetBehavior<? extends View> sideSheetBehavior = this.sheetBehavior;
        if (sideSheetBehavior.shouldHide(child, sideSheetBehavior.getXVelocity()) || this.sheetBehavior.getLastNestedScrollDx() != 0) {
            return 5;
        }
        int currentLeft = child.getLeft();
        if (Math.abs(currentLeft - getExpandedOffset()) < Math.abs(currentLeft - getHiddenOffset())) {
            return 3;
        }
        return 5;
    }

    /* access modifiers changed from: package-private */
    public <V extends View> boolean hasReachedExpandedOffset(V child) {
        return child.getLeft() == getExpandedOffset();
    }

    /* access modifiers changed from: package-private */
    public boolean shouldHide(View child, float velocity) {
        return Math.abs(((float) child.getRight()) + (this.sheetBehavior.getHideFriction() * velocity)) > this.sheetBehavior.getHideThreshold();
    }

    /* access modifiers changed from: package-private */
    public boolean isSettling(View child, int state, boolean isReleasingView) {
        int left = this.sheetBehavior.getOutwardEdgeOffsetForState(state);
        ViewDragHelper viewDragHelper = this.sheetBehavior.getViewDragHelper();
        return viewDragHelper != null && (!isReleasingView ? viewDragHelper.smoothSlideViewTo(child, left, child.getTop()) : viewDragHelper.settleCapturedViewAt(left, child.getTop()));
    }

    /* access modifiers changed from: package-private */
    public <V extends View> int getOutwardEdge(V child) {
        return child.getLeft();
    }
}
