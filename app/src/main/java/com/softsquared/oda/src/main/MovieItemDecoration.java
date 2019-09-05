package com.softsquared.oda.src.main;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieItemDecoration extends RecyclerView.ItemDecoration {

    private int size16;
    private int size1;

    public MovieItemDecoration(Context context) {

        size16 = dpToPx(context, 16);
        size1 = dpToPx(context, 1);
    }

    // dp -> pixel 단위로 변경
    private int dpToPx(Context context, int dp) {

        return (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }


    @Override
    public void getItemOffsets
            (@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();

        //상하 설정
        if (position == 0 || position == 1 || position == 2) {
            // 첫번 째 줄 아이템
            outRect.top = size16;
            outRect.bottom = size16;
        } else {
            outRect.bottom = size16;
        }

        // spanIndex = 0 -> 왼쪽
        // spanIndex = 1 -> 오른쪽
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanSize = lp.getSpanSize();
        int spanIndex = lp.getSpanIndex();
        int totalSpanSize = 0;
        if (gridLayoutManager != null) {
            totalSpanSize = gridLayoutManager.getSpanCount();
        }
        if (spanIndex == 0) {//left
        } else if (spanSize + spanIndex == totalSpanSize) {//right
        } else if ((spanIndex > 0) && ((spanSize + spanIndex) < totalSpanSize)) {//가운데
            outRect.left = size1;
            outRect.right = size1 ;

        }


    }

}
