package com.example.noteappusinroom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DialogAddTask extends DialogFragment {
    CallBack callBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callBack = (CallBack) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.addtask_dg,null,false);
        TextInputLayout textInputLayout = view.findViewById(R.id.add_task_input_layout);
        TextInputEditText textInputEditText = view.findViewById(R.id.et_add_task);
        MaterialButton add_task_btn = view.findViewById(R.id.btn_add_task);
        add_task_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textInputEditText.length()>0){
                    TaskModel taskModel = new TaskModel();
                    taskModel.setTaskTitle(textInputEditText.getText().toString());
                    taskModel.setCompleted(false);
                    callBack.addTask(taskModel);
                    dismiss();
                }else {
                    textInputLayout.setError("title most not be empty");
                }
            }
        });
        builder.setView(view);
        return builder.create();
    }
    public interface CallBack{
        void addTask(TaskModel taskModel);
    }
}
