package com.elka.foody.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(
  private val spaceVrt: Int,
  private val spaceHrz: Int,
  private val spanCount: Int = 1,
  private val orientation: Int = GridLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
  ) {
    with(outRect) {
      if (orientation == GridLayoutManager.VERTICAL) {
        if (parent.getChildAdapterPosition(view) < spanCount) {
          top = spaceVrt
        }
        if (parent.getChildAdapterPosition(view) % spanCount == 0) {
          left = spaceHrz
        }
      } else {
        if (parent.getChildAdapterPosition(view) < spanCount) {
          left = spaceHrz
        }
        if (parent.getChildAdapterPosition(view) % spanCount == 0) {
          top = spaceVrt
        }
      }

      right = spaceHrz
      bottom = spaceVrt
    }
  }
}