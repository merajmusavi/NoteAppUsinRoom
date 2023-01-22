package com.example.noteappusinroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogAddTask.CallBack,RecyclerTasksAdaptor.CallBack,DialogEditTask.CallBack {
    private TaskDao taskDao;
    RecyclerTasksAdaptor tasksAdaptor = new RecyclerTasksAdaptor(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskDao = DatabaseClass.getDatabaseClass(this).getTaskDao();

        RecyclerView recyclerView = findViewById(R.id.rec_task);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(tasksAdaptor);
        List<TaskModel> taskModelList = taskDao.getAll();
        tasksAdaptor.addAll(taskModelList);
        View btn_add_task = findViewById(R.id.add_item_fab);
        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddTask dialogAddTask = new DialogAddTask();
                dialogAddTask.show(getSupportFragmentManager(), null);

            }
        });
    }

    @Override
    public void addTask(TaskModel taskModel) {
        long add = taskDao.addTask(taskModel);
        if (add != -1) {
            taskModel.setId((int) add);
            tasksAdaptor.addItem(taskModel);
        }
    }

    @Override
    public void onDeleteClicked(TaskModel taskModel) {
        int result = taskDao.DeleteTask(taskModel);
        if (result >0) {
            tasksAdaptor.deleteItem(taskModel);

        }
    }

    @Override
    public void OnUpdateClicked(TaskModel taskModel) {
     DialogEditTask dialogEditTask = new DialogEditTask();
     Bundle bundle = new Bundle();
     bundle.putParcelable("task",taskModel);
     dialogEditTask.setArguments(bundle);
     dialogEditTask.show(getSupportFragmentManager(),null);
    }


    @Override
    public void EditTask(TaskModel taskModel) {
        int result = taskDao.updateTask(taskModel);
        if (result>0){
tasksAdaptor.updateItem(taskModel);
        }
    }
}