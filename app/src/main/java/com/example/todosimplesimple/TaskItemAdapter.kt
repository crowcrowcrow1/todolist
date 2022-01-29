package com.example.todosimplesimple

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter(val listoftaks: List<String>,
                      val longClickListener : OnlongClickListener ):
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    interface OnlongClickListener {
        fun onItemLongclicked(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: TaskItemAdapter.ViewHolder, position: Int) {


        val item = listoftaks.get(position)

        holder.textView.text=item
    }

    override fun getItemCount(): Int {
        return listoftaks.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init{
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                longClickListener.onItemLongclicked(adapterPosition)
                true

            }

        }
    }
}
