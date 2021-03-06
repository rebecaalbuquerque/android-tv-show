package com.albuquerque.movietvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.movietvshow.R
import com.albuquerque.movietvshow.core.adapter.BaseAdapter
import com.albuquerque.movietvshow.core.view.holder.BaseViewHolder
import com.albuquerque.movietvshow.modules.shows.enum.TypeImage
import com.albuquerque.movietvshow.modules.shows.model.Picture
import com.albuquerque.movietvshow.modules.shows.view.holder.ImageViewHolder

class ImageAdapter(val type: TypeImage): BaseAdapter<Picture, BaseViewHolder<Picture>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Picture> {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Picture>, position: Int) {
        super.onBindViewHolder(holder, position)

        (holder as ImageViewHolder).bind(items[position], type)
    }

}