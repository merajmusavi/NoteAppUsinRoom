package com.example.noteappusinroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.scheduling.Task;

public class RecyclerTasksAdaptor extends RecyclerView.Adapter<RecyclerTasksAdaptor.ViewHolder> {
 List<TaskModel> taskModelList = new ArrayList<>();
 private CallBack callBack;
 public RecyclerTasksAdaptor(CallBack callBack){
     this.callBack = callBack;
 }

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
   public void updateItem(TaskModel taskModel){
       for (int i = 0; i < taskModelList.size(); i++) {
           if (taskModelList.get(i).getId()==taskModel.getId()){
               taskModelList.set(i,taskModel);
               notifyItemChanged(i);
           }
       }
   }
   public void deleteItem(TaskModel taskModel){
       for (int i = 0; i < taskModelList.size(); i++) {
           if (taskModelList.get(i).getId()==taskModel.getId()){
               taskModelList.remove(i);
               notifyItemRemoved(i);
           }
       }
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
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             callBack.onDeleteClicked(taskModel);
            }
        });
  edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          callBack.OnUpdateClicked(taskModel);
      }
  });
 }

    }
    public interface CallBack{
        void onDeleteClicked(TaskModel taskModel);
        void OnUpdateClicked(TaskModel taskModel);

    }

}
