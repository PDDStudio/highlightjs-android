package com.pddstudio.highlightjs.demo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pddstudio.highlightjs.demo.R;
import com.pddstudio.highlightjs.demo.utils.FileObject;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 13.06.16. For more Details and Licensing
 * have a look at the README.md
 */

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder> {

    private List<FileObject> itemData;
    private final OnItemSelectedListener onItemSelectedListener;

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public FilesAdapter(List<FileObject> data, OnItemSelectedListener onItemSelectedListener) {
        this.itemData = data;
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void addItem(FileObject fileObject) {
        this.itemData.add(fileObject);
        notifyItemInserted(itemData.size());
    }

    public FileObject getItem(int pos) {
        return itemData.get(pos);
    }

    public void clear() {
        itemData.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_files, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fileName.setText(itemData.get(position).getFileName());
        holder.filePath.setText(itemData.get(position).getAbsoluteFilePath());
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fileName;
        TextView filePath;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            fileName = (TextView) itemView.findViewById(R.id.file_title);
            filePath = (TextView) itemView.findViewById(R.id.file_content);
        }

        @Override
        public void onClick(View v) {
           if(onItemSelectedListener != null) onItemSelectedListener.onItemSelected(getAdapterPosition());
        }

    }
}
