package com.sbiitju.smart_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class SearchResultAdapter(
    val onClickItem: (String) -> Unit
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    val dataSet = ArrayList<String>()

    fun replaceData(data: List<String>?) {
        dataSet.clear()
        if (data != null) {
            dataSet.addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_search,
            parent,
            false
        )
        return SearchResultViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val datum = dataSet[position]
        holder.layout.setOnClickListener {
            onClickItem(datum)
        }
        holder.tvTitle.text = datum
    }

    class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: ConstraintLayout = view.findViewById(R.id.layoutItemSearch)
        val tvTitle: AppCompatTextView = view.findViewById(R.id.tvTitleItemSearch)
    }

}
