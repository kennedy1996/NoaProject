package com.noaproject.ui.recyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.noaproject.R
import com.noaproject.ui.viewModel.NoaViewModel

private const val ARTICLES = "ARTICLES"
private const val TYPE_ARTICLES = 1
private const val TYPE_PLAYLISTS = 2

class ListNoaAdapter(
    private val context: Context,
    private val viewModel: NoaViewModel,
    private val table: String
) : RecyclerView.Adapter<ListNoaAdapter.ViewHolder>() {

    private val list = viewModel.getNoaData().value!!.filter { it.name == table }[0].items

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articles_company_name: TextView? =
            itemView.findViewById(R.id.item_articles_company_name)
        val articles_title: TextView? = itemView.findViewById(R.id.item_articles_title)
        val articles_image_article: ImageView? =
            itemView.findViewById(R.id.item_articles_image_article)
        val articles_image_company: ImageView? =
            itemView.findViewById(R.id.item_articles_image_articles_company)

        val playlists_title: TextView? = itemView.findViewById(R.id.item_playlists_title)
        val playlists_image: ImageView? = itemView.findViewById(R.id.item_playlists_image_article)


    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].type == ARTICLES) TYPE_ARTICLES
        else TYPE_PLAYLISTS

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var view = LayoutInflater.from(context)
            .inflate(R.layout.item_playlists, parent, false)
        if (viewType == TYPE_ARTICLES) {
            view = LayoutInflater.from(context)
                .inflate(R.layout.item_articles, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            if (list[position].type == ARTICLES) {
                holder.articles_title!!.text = list[position].title
                holder.articles_company_name!!.text = list[position].publishers[0].publisherName
                loadingImage(holder.articles_image_article!!, list[position].imageItem!!)
                loadingImage(
                    holder.articles_image_company!!,
                    list[position].publishers[0].publisherImage!!
                )
            } else {
                holder.playlists_title!!.text = list[position].title
                loadingImage(holder.playlists_image!!, list[position].imageItem!!)
            }

        } catch (e: Exception) {
            Log.e("ListNoaAdapter", e.message.toString())
        }
    }

    override fun getItemCount(): Int {
        return list?.count() ?: 0
    }

    private fun loadingImage(
        image: ImageView,
        link: String
    ) {
        val imageLoader = image.context.imageLoader
        val request = ImageRequest.Builder(context)
            .data(link)
            .target(image)
            .build()
        imageLoader.enqueue(request)
    }

}
