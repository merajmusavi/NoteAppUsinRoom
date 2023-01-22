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

import kotlinx.coroutines.scheduling.Task;

public class DialogEditTask extends DialogFragment {
    CallBack callBack;
    TaskModel taskModel;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callBack = (CallBack) context;
        taskModel = getArguments().getParcelable("task");
        if (taskModel==null){
            dismiss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_task_dg,null,false);
        TextInputLayout textInputLayout = view.findViewById(R.id.update_task_input_layout);
        TextInputEditText textInputEditText = view.findViewById(R.id.update_input);
        textInputEditText.setText(taskModel.getTaskTitle());

        MaterialButton add_task_btn = view.findViewById(R.id.btn_update);
        add_task_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textInputEditText.length()>0){
                    taskModel.setTaskTitle(textInputEditText.getText().toString());
                    callBack.EditTask(taskModel);
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
        void EditTask(TaskModel taskModel);
    }
}
