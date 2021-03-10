package com.teambhawanasukant.todoapp.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.teambhawanasukant.todoapp.AddNewTask;
import com.teambhawanasukant.todoapp.MainActivity;
import com.teambhawanasukant.todoapp.Model.ToDoModel;
import com.teambhawanasukant.todoapp.R;
import com.teambhawanasukant.todoapp.Utlis.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private MainActivity activity;

    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, MainActivity activity){
        this.activity=activity;
        this.db=db;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout,parent,false);
        return new ViewHolder(itemView);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task=view.findViewById(R.id.todoCheckbox);
        }
    }

    public void editItem(int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle= new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("task",item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(),AddNewTask.TAG);
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        db.openDatabase();
        ToDoModel item=todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(),1);
                }else{
                    db.updateStatus(item.getId(),0);
                }
            }
        });
    }

    private boolean toBoolean(int n){ //helper function to convert int to bool
        return n!=0;
    }

    public int getItemCount(){
        return todoList.size();
    }

    public void setTasks(List<ToDoModel>todoList){
        this.todoList=todoList;
        notifyDataSetChanged();
    }

}
