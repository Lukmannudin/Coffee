package com.oleg.coffee.helper

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Crafted by Lukman on 04/04/2021.
 **/
class RecyclerViewVerticalItemDecoration(
    private val context: Context, private val spacing: Float
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val metrics = context.resources.displayMetrics
        val mPadding =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacing, metrics).toInt()

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0
        } else {
            outRect.top = mPadding
        }
    }
}