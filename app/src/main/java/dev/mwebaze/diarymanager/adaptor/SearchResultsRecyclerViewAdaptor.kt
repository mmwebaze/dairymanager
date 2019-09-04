package dev.mwebaze.diarymanager.adaptor

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import dev.mwebaze.diarymanager.R

class SearchResultsRecyclerViewAdaptor(val context: Context, val cowTag: ArrayList<Int>, val cowName: ArrayList<String>, val milkAmount: ArrayList<Double>)
    : RecyclerView.Adapter<SearchResultsRecyclerViewAdaptor.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.cowTag.text = ""+cowTag.get(position)
        holder.cowName.text = ""+cowName.get(position)
        holder.milkAmount.text = ""+milkAmount.get(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)

        return ViewHolder(view)
    }
    override fun getItemCount(): Int {

        return cowTag.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cowTag: TextView = itemView.findViewById(R.id.cow_tag)
        var cowName: TextView = itemView.findViewById(R.id.cow_name)
        var milkAmount: TextView = itemView.findViewById(R.id.milk_amount)
    }
}