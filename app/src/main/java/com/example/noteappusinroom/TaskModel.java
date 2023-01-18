package com.example.noteappusinroom;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskModel implements Parcelable {
    private int id;
    private String TaskTitle;
    private boolean isCompleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.TaskTitle);
        dest.writeByte(this.isCompleted ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.TaskTitle = source.readString();
        this.isCompleted = source.readByte() != 0;
    }

    public TaskModel() {
    }

    protected TaskModel(Parcel in) {
        this.id = in.readInt();
        this.TaskTitle = in.readString();
        this.isCompleted = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TaskModel> CREATOR = new Parcelable.Creator<TaskModel>() {
        @Override
        public TaskModel createFromParcel(Parcel source) {
            return new TaskModel(source);
        }

        @Override
        public TaskModel[] newArray(int size) {
            return new TaskModel[size];
        }
    };
}
