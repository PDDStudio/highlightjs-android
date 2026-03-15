package com.pddstudio.highlightjs.demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pddstudio.highlightjs.demo.R
import com.pddstudio.highlightjs.demo.utils.FileObject

/**
 * This Class was created by Patrick J
 * on 13.06.16. For more Details and Licensing
 * have a look at the README.md
 */

class FilesAdapter(
    private val itemData: MutableList<FileObject>,
    private val onItemSelectedListener: OnItemSelectedListener
) : RecyclerView.Adapter<FilesAdapter.ViewHolder>() {

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    fun addItem(fileObject: FileObject) {
        itemData.add(fileObject)
        notifyItemInserted(itemData.size)
    }

    fun getItem(pos: Int): FileObject = itemData[pos]

    fun clear() {
        itemData.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_files, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fileName.text = itemData[position].getFileName()
        holder.filePath.text = itemData[position].getAbsoluteFilePath()
    }

    override fun getItemCount(): Int = itemData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val fileName: TextView = itemView.findViewById(R.id.file_title)
        val filePath: TextView = itemView.findViewById(R.id.file_content)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            onItemSelectedListener.onItemSelected(adapterPosition)
        }
    }
}
