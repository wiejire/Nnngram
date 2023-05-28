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

package xyz.nextalone.nnngram.ui

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.AndroidUtilities
import org.telegram.ui.ActionBar.AlertDialog
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.Components.ExtendedGridLayoutManager
import org.telegram.ui.Components.RecyclerListView
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter
import xyz.nextalone.nnngram.helpers.FolderIconHelper

object IconSelector {
    @JvmStatic
    fun show(fragment: BaseFragment, onIconSelectedListener: OnIconSelectedListener) {
        val context: Context = fragment.parentActivity
        val builder = AlertDialog.Builder(context)
        val gridAdapter = GridAdapter()
        val recyclerListView = RecyclerListView(context)
        recyclerListView.clipToPadding = false
        recyclerListView.setPadding(AndroidUtilities.dp(8f), 0, AndroidUtilities.dp(8f), 0)
        recyclerListView.layoutManager = ExtendedGridLayoutManager(recyclerListView.context, 6)
        recyclerListView.adapter = gridAdapter
        recyclerListView.setSelectorDrawableColor(0)
        recyclerListView.onItemClickListener = RecyclerListView.OnItemClickListener { view: View, position: Int ->
            onIconSelectedListener.onIconSelected(view.tag as String)
            builder.dismissRunnable.run()
        }
        builder.setView(recyclerListView)
        fragment.showDialog(builder.create())
    }

    private class GridAdapter : SelectionAdapter() {
        private val icons: Array<String> = FolderIconHelper.folderIcons.keys.toTypedArray()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: ImageView = object : ImageView(parent.context) {
                override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
                    val iconSize = MeasureSpec.makeMeasureSpec(parent.measuredWidth / 6, MeasureSpec.EXACTLY)
                    super.onMeasure(iconSize, iconSize)
                }
            }
            view.background =
                Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector), AndroidUtilities.dp(2f), AndroidUtilities.dp(2f))
            view.colorFilter = PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY)
            view.setPadding(AndroidUtilities.dp(10f), AndroidUtilities.dp(10f), AndroidUtilities.dp(10f), AndroidUtilities.dp(10f))
            return RecyclerListView.Holder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val imageView = holder.itemView as ImageView
            imageView.tag = icons[position]
            imageView.setImageResource(FolderIconHelper.getTabIcon(icons[position]))
        }

        override fun getItemCount(): Int {
            return icons.size
        }

        override fun isEnabled(holder: RecyclerView.ViewHolder): Boolean {
            return true
        }
    }

    interface OnIconSelectedListener {
        fun onIconSelected(emoticon: String?)
    }
}
