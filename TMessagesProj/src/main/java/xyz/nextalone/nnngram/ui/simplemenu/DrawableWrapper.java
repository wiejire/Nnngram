/*
 * Copyright (C) 2019-2023 qwq233 <qwq233@qwq2333.top>
 * https://github.com/qwq233/Nullgram
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this software.
 *  If not, see
 * <https://www.gnu.org/licenses/>
 */

package xyz.nextalone.nnngram.ui.simplemenu;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.DrawableCompat;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class DrawableWrapper extends Drawable implements Drawable.Callback {

    private Drawable mDrawable;

    public DrawableWrapper(Drawable drawable) {
        setWrappedDrawable(drawable);
    }

    @Override
    public void getOutline(@NonNull Outline outline) {
        mDrawable.getOutline(outline);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mDrawable.draw(canvas);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mDrawable.setBounds(bounds);
    }

    @Override
    public int getChangingConfigurations() {
        return mDrawable.getChangingConfigurations();
    }

    @Override
    public void setChangingConfigurations(int configs) {
        mDrawable.setChangingConfigurations(configs);
    }

    @Override
    public void setDither(boolean dither) {
        mDrawable.setDither(dither);
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        mDrawable.setFilterBitmap(filter);
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mDrawable.setColorFilter(cf);
    }

    @Override
    public boolean isStateful() {
        return mDrawable.isStateful();
    }

    @Override
    public boolean setState(@NonNull final int[] stateSet) {
        return mDrawable.setState(stateSet);
    }

    @NonNull
    @Override
    public int[] getState() {
        return mDrawable.getState();
    }

    @Override
    public void jumpToCurrentState() {
        mDrawable.jumpToCurrentState();
    }

    @NonNull
    @Override
    public Drawable getCurrent() {
        return mDrawable.getCurrent();
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        return super.setVisible(visible, restart) || mDrawable.setVisible(visible, restart);
    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public Region getTransparentRegion() {
        return mDrawable.getTransparentRegion();
    }

    @Override
    public int getIntrinsicWidth() {
        return mDrawable.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mDrawable.getIntrinsicHeight();
    }

    @Override
    public int getMinimumWidth() {
        return mDrawable.getMinimumWidth();
    }

    @Override
    public int getMinimumHeight() {
        return mDrawable.getMinimumHeight();
    }

    @Override
    public boolean getPadding(@NonNull Rect padding) {
        return mDrawable.getPadding(padding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        invalidateSelf();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        scheduleSelf(what, when);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        unscheduleSelf(what);
    }

    @Override
    protected boolean onLevelChange(int level) {
        return mDrawable.setLevel(level);
    }

    @Override
    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(mDrawable);
    }

    @Override
    public void setAutoMirrored(boolean mirrored) {
        DrawableCompat.setAutoMirrored(mDrawable, mirrored);
    }

    @Override
    public void setTint(int tint) {
        DrawableCompat.setTint(mDrawable, tint);
    }

    @Override
    public void setTintList(ColorStateList tint) {
        DrawableCompat.setTintList(mDrawable, tint);
    }

    @Override
    public void setTintMode(PorterDuff.Mode tintMode) {
        DrawableCompat.setTintMode(mDrawable, tintMode);
    }

    @Override
    public void setHotspot(float x, float y) {
        DrawableCompat.setHotspot(mDrawable, x, y);
    }

    @Override
    public void setHotspotBounds(int left, int top, int right, int bottom) {
        DrawableCompat.setHotspotBounds(mDrawable, left, top, right, bottom);
    }

    public Drawable getWrappedDrawable() {
        return mDrawable;
    }

    public void setWrappedDrawable(Drawable drawable) {
        if (mDrawable != null) {
            mDrawable.setCallback(null);
        }

        mDrawable = drawable;

        if (drawable != null) {
            drawable.setCallback(this);
        }
    }
}
