package com.elka.foody.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearItemDecorator(private val space: Int, private val type: Int) : RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect, view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    with(outRect) {
      if (type == LinearLayoutManager.VERTICAL) {
        if (parent.getChildAdapterPosition(view) == 0) top = space
        bottom = space
      } else {
        if (parent.getChildAdapterPosition(view) != 0) left = space
      }
    }
  }
}