package com.example.testtask.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val verticalSpacing: Int,
    private val horizontalSpacing: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (position != RecyclerView.NO_POSITION) {
            if (position == 0) {
                outRect.right = horizontalSpacing / 2
                outRect.bottom = verticalSpacing / 2
            } else if (position == itemCount - 1) {
                outRect.left = horizontalSpacing / 2
                outRect.top = verticalSpacing / 2
            } else {
                outRect.left = horizontalSpacing / 2
                outRect.right = horizontalSpacing / 2
                outRect.top = verticalSpacing / 2
                outRect.bottom = verticalSpacing / 2
            }
        }
    }
}
