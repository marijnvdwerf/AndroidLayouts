package nl.marijnvdwerf.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class FixedAspectRatioFrameLayout extends FrameLayout {

    public static final int STRETCH_HORIZONTAL = 0;
    public static final int STRETCH_VERTICAL = 1;
    private float mAspectRatio = 1;
    private int mStretchMode = STRETCH_VERTICAL;

    public FixedAspectRatioFrameLayout(Context context) {
        super(context);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAspect(context, attrs);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeAspect(context, attrs);
    }

    private void initializeAspect(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioFrameLayout);
        mAspectRatio = attributes.getFloat(R.styleable.FixedAspectRatioFrameLayout_aspectRatio, 1f);
        mStretchMode = attributes.getInteger(R.styleable.FixedAspectRatioFrameLayout_stretchMode, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (mStretchMode == STRETCH_VERTICAL) {
            height = (int) ((double) width / mAspectRatio);
            if (height < getSuggestedMinimumHeight()) {
                height = getSuggestedMinimumHeight();
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        } else {
            width = (int) ((double) height * mAspectRatio);
            if (width < getSuggestedMinimumWidth()) {
                width = getSuggestedMinimumWidth();
            }
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
