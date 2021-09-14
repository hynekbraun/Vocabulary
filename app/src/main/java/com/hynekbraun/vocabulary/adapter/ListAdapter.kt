package com.hynekbraun.vocabulary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hynekbraun.vocabulary.R
import com.hynekbraun.vocabulary.activities.MainActivity
import com.hynekbraun.vocabulary.model.WordListEntity
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var wordList = emptyList<WordListEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = wordList[position]
        holder.itemView.tv_rv_word.text = currentItem.word
        holder.itemView.tv_rv_translation.text = currentItem.translation
        holder.itemView.tv_rv_notes.text = currentItem.notes

        holder.itemView.iv_rv_delete.setOnClickListener {
            if (context is MainActivity){
                context.deleteWord(currentItem)
            }
        }

        holder.itemView.iv_rv_update.setOnClickListener {
            if (context is MainActivity) {
                context.updateWord(currentItem)



            }
        }
    }

    fun setData(word: List<WordListEntity>) {
        this.wordList = word
        notifyDataSetChanged()
    }

}