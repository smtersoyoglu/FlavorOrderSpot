package com.sametersoyoglu.flavororderspot.ui.cartDelete

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.ui.adapter.CartAdapter
import com.sametersoyoglu.flavororderspot.ui.viewmodel.CartViewModel

class SwipeToDelete (private val adapter: CartAdapter, private val viewModel: CartViewModel) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        if (position < adapter.itemCount) { //Eğer pozisyon, listenizin boyutundan küçükse, öğeyi güvenle silebilirsiniz.
            val cartItem = adapter.getCartItem(position)
            viewModel.deleteFoodFromCart(cartItem.cart_food_id,cartItem.username)
            adapter.removeItem(position)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val background = ColorDrawable()
        val deleteIcon = ContextCompat.getDrawable(adapter.mContext, R.drawable.swipedelete)
        val iconMargin = (itemView.height - deleteIcon!!.intrinsicHeight) / 2

        if (dX > 0) {
            // Swiping to the right
        } else if (dX < 0) {
            // Swiping to the left
            background.color = Color.RED
            background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
            deleteIcon.setBounds(itemView.right - iconMargin - deleteIcon.intrinsicWidth, itemView.top + iconMargin, itemView.right - iconMargin, itemView.bottom - iconMargin)
            background.draw(c)
            deleteIcon.draw(c)
        } else {
            // No swipe
            background.setBounds(0, 0, 0, 0)
        }
    }

}