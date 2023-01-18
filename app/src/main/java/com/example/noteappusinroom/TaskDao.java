package com.example.noteappusinroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    long addTask(TaskModel taskModel);
    @Delete
    int DeleteTask(TaskModel taskModel);
    @Update
    int updateTask(TaskModel taskModel);
    @Query("SELECT * FROM tbl_tasks")
    List<TaskModel> getAll();
    @Query("SELECT * FROM tbl_tasks WHERE title LIKE '%' || :search || '%' ")
    List<TaskModel> search(String search);
    @Query("DELETE FROM tbl_tasks ")
    void deleteALl();

}
