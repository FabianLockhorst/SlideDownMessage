package com.fabianlockhorst.sdm;

import android.content.Context;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author fabian
 * @since 9/23/17
 */
public class SlideDownMessage extends RelativeLayout {

    private static final String TAG = SlideDownMessage.class.getSimpleName();

    public static final Integer LENGTH_INDEFINITELY = 0;
    public static final Integer LENGTH_SHORT = 2000;
    public static final Integer LENGTH_LONG = 3500;

    LayoutInflater mLayoutInflater;
    ViewGroup mTarget;
    String mMessageText;
    TextView mMessageTextView;
    View mToolbarMessageView;

    boolean mIsShown, mIsAdded;
    Integer mOffset = 0;
    Integer mLength = LENGTH_LONG;

    //make methods

    /**
     * @param target  target view
     * @param message message to be displayed
     * @param context context
     * @param length  length in milliseconds to display message
     * @return instance of ToolbarMessage
     */
    public static SlideDownMessage make(@NonNull View target, @NonNull String message, @NonNull Context context, Integer length) {
        SlideDownMessage mToolbarMessage = new SlideDownMessage(context);
        mToolbarMessage.setTarget((ViewGroup) target);
        mToolbarMessage.setMessage(message);
        mToolbarMessage.setLength(length);
        mToolbarMessage.inflate();
        return mToolbarMessage;
    }

    /**
     * @param target  target view
     * @param message message to be displayed
     * @param context context
     * @return instance of ToolbarMessage
     */
    public static SlideDownMessage make(@NonNull View target, @NonNull String message, @NonNull Context context) {
        SlideDownMessage mToolbarMessage = new SlideDownMessage(context);
        mToolbarMessage.setTarget((ViewGroup) target);
        mToolbarMessage.setMessage(message);
        mToolbarMessage.inflate();
        return mToolbarMessage;
    }

    /**
     * @param offset  view to measure to go under
     * @param target  target view
     * @param message message to be displayed
     * @param context context
     * @param length  length in milliseconds to display message
     * @return instance of ToolbarMessage
     */
    public static SlideDownMessage make(@NonNull View target, @NonNull String message, @NonNull Context context, Integer length, int offset) {
        SlideDownMessage mToolbarMessage = new SlideDownMessage(context);
        mToolbarMessage.setTarget((ViewGroup) target);
        mToolbarMessage.setMessage(message);
        mToolbarMessage.setOffset(offset);
        mToolbarMessage.setLength(length);
        mToolbarMessage.inflate();
        return mToolbarMessage;
    }

    /**
     * @param offset  view to measure to go under
     * @param target  target view
     * @param message message to be displayed
     * @param context context
     * @return instance of ToolbarMessage
     */
    public static SlideDownMessage make(@NonNull View target, @NonNull String message, @NonNull Context context, int offset) {
        SlideDownMessage mToolbarMessage = new SlideDownMessage(context);
        mToolbarMessage.setTarget((ViewGroup) target);
        mToolbarMessage.setMessage(message);
        mToolbarMessage.setOffset(offset);
        mToolbarMessage.setLength(LENGTH_LONG);
        mToolbarMessage.inflate();
        return mToolbarMessage;
    }

    /**
     * @param target     target view
     * @param message    message to be displayed
     * @param context    context
     * @param length     length in milliseconds to display message
     * @param hasToolbar will calculate default actionBar height to use as offset
     * @return instance of ToolbarMessage
     */
    public static SlideDownMessage make(@NonNull View target, @NonNull String message, @NonNull Context context, Integer length, boolean hasToolbar) {
        SlideDownMessage mToolbarMessage = new SlideDownMessage(context);
        mToolbarMessage.setTarget((ViewGroup) target);
        mToolbarMessage.setMessage(message);
        if (hasToolbar) {
            mToolbarMessage.setOffset(getActionBarSize(context));
        }
        mToolbarMessage.setLength(length);
        mToolbarMessage.inflate();
        return mToolbarMessage;
    }

    /**
     * @param target     target view
     * @param message    message to be displayed
     * @param context    context
     * @param hasToolbar will calculate default actionBar height to use as offset
     * @return instance of ToolbarMessage
     */
    public static SlideDownMessage make(@NonNull View target, @NonNull String message, @NonNull Context context, boolean hasToolbar) {
        SlideDownMessage mToolbarMessage = new SlideDownMessage(context);
        mToolbarMessage.setTarget((ViewGroup) target);
        mToolbarMessage.setMessage(message);
        if (hasToolbar) {
            mToolbarMessage.setOffset(getActionBarSize(context));
        }
        mToolbarMessage.setLength(LENGTH_LONG);
        mToolbarMessage.inflate();
        return mToolbarMessage;
    }

    private static int getActionBarSize(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    //object methods

    private SlideDownMessage(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);

    }

    private SlideDownMessage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutInflater = LayoutInflater.from(context);

    }

    private SlideDownMessage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLayoutInflater = LayoutInflater.from(context);
    }

    private void setMessage(String mMessageText) {
        this.mMessageText = mMessageText;
    }

    private void setTarget(ViewGroup mTarget) {
        this.mTarget = mTarget;
    }

    private void setOffset(Integer actionBarHeight) {
        this.mOffset = actionBarHeight;
    }

    private void setLength(Integer length) {
        this.mLength = length;
    }

    private void inflate() {
        if (mTarget != null) {
            mToolbarMessageView = mLayoutInflater.inflate(R.layout.slide_down_message, mTarget, false);
            mMessageTextView = mToolbarMessageView.findViewById(R.id.toolbar_message_text);
            if (mMessageTextView != null) {
                mMessageTextView.setText(mMessageText);
            }
        }
    }

    /**
     * @return View which is of type RelativeLayout
     */
    public View getView() {
        return mToolbarMessageView;
    }


    /**
     * Display SlideDownMessage with animation.
     * if a Length is set other then <code>LENGTH_INDEFINITELY</code>
     * or default length is used SlideDownMessage will automatically hide itself
     */
    public void show() {
        if (!mIsShown) {
            if (!mIsAdded) {
                mTarget.addView(mToolbarMessageView);
                mIsAdded = true;
            }
            mToolbarMessageView.post(new Runnable() {
                @Override
                public void run() {
                    TranslateAnimation animate = new TranslateAnimation(0, 0, -100, mOffset);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    animate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            mToolbarMessageView.setVisibility(VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (mLength != -1 && mLength != LENGTH_INDEFINITELY) {
                                new CountDownTimer(mLength, 1000) {
                                    @Override
                                    public void onTick(long l) {
                                        //do nothing
                                    }

                                    @Override
                                    public void onFinish() {
                                        hide();
                                    }
                                }.start();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mToolbarMessageView.startAnimation(animate);
                    mIsShown = true;
                }
            });
        }
    }

    /**
     * Hide SlideDownMessage with animation.
     */
    public void hide() {
        if (mIsShown) {
            mToolbarMessageView.post(new Runnable() {
                @Override
                public void run() {
                    TranslateAnimation animate = new TranslateAnimation(0, 0, mOffset, -100);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    animate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mIsShown = false;
                            mToolbarMessageView.setVisibility(INVISIBLE);
                        }


                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mToolbarMessageView.startAnimation(animate);
                }
            });
        }
    }

    /**
     * @return boolean if view is currently shown.
     */
    public boolean isShown() {
        return mIsShown;
    }
}
