package com.obiomaofoamalu.albums.albumlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.obiomaofoamalu.albums.R
import kotlinx.android.synthetic.main.itemview_album.view.*

/**
 * The [AlbumListAdapter] class is responsible for displaying each album title in the list view.
 */
class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    private val albumList = mutableListOf<AlbumViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListAdapter.ViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.itemview_album, parent, false)
            .also { return ViewHolder(it) }
    }

    override fun onBindViewHolder(viewHolder: AlbumListAdapter.ViewHolder, position: Int) {
        viewHolder.bindView(albumList[position])
    }

    override fun getItemCount(): Int = albumList.size

    fun updateData(albumList: List<AlbumViewModel>) {
        this.albumList.run {
            this.clear()
            this.addAll(albumList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(viewModel: AlbumViewModel) {
            itemView.albumTitleView.text = viewModel.title
        }
    }
}
