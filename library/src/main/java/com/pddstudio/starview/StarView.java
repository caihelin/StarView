package com.pddstudio.starview;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.pddstudio.starview.opengl.GLTextureView;
import com.pddstudio.starview.opengl.ParticleSystemRenderer;

/**
 * An animated OpenGL based {@linkplain android.view.TextureView} which can be used to display a space-like overlay in your layout.
 */
public class StarView extends GLTextureView {

    private ActivityManager activityManager;
    private ConfigurationInfo configurationInfo;
    private boolean supportsEs2;

    public StarView(Context context) {
        super(context);

        if (!isInEditMode()) {

            activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            configurationInfo = activityManager.getDeviceConfigurationInfo();
            supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

            if (supportsEs2) {
                // Request an OpenGL ES 2.0 compatible context.

                // Set the renderer to our demo renderer, defined below.
                ParticleSystemRenderer mRenderer = new ParticleSystemRenderer(this);

                setEGLContextClientVersion(2);
                setEGLConfigChooser(8, 8, 8, 8, 0, 0);
                setRenderer(mRenderer);
                setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
            } else {
                if (!isInEditMode()) throw new UnsupportedOperationException();
            }

        }

    }

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {

            activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            configurationInfo = activityManager.getDeviceConfigurationInfo();
            supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

            if (supportsEs2) {
                // Request an OpenGL ES 2.0 compatible context.

                // Set the renderer to our demo renderer, defined below.
                ParticleSystemRenderer mRenderer = new ParticleSystemRenderer(this);

                setEGLContextClientVersion(2);
                setEGLConfigChooser(8, 8, 8, 8, 0, 0);
                setRenderer(mRenderer);
                setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
            } else {
                if (!isInEditMode()) throw new UnsupportedOperationException();
            }

        }

    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (isInEditMode()) {
            return;
        }

        if (visibility == View.VISIBLE) {
            //Resume the starView
            onResume();
        } else {
            //Pause the starView
            onPause();
        }
    }
}
