package com.example.noteappusinroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTasksAdaptor extends RecyclerView.Adapter<RecyclerTasksAdaptor.ViewHolder> {
 List<TaskModel> taskModelList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_model,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
   holder.bind(taskModelList.get(position));
    }
   public void addItem(TaskModel taskModel){
        taskModelList.add(0,taskModel);
        notifyItemInserted(0);
   }
   public void addAll(List<TaskModel> list){
        this.taskModelList.addAll(list);
        notifyDataSetChanged();

   }
    @Override
    public int getItemCount() {
        return taskModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private ImageView edit,delete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkBox);
        edit = itemView.findViewById(R.id.edit);
        delete = itemView.findViewById(R.id.delete);


    }
 public void bind(TaskModel taskModel){
        checkBox.setChecked(taskModel.isCompleted());
        checkBox.setText(taskModel.getTaskTitle());
 }

    }
}
