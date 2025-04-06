package anjali.learning.taskmanagementapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button Add,Update;
EditText createTask;
ListView taskListView;
int selectedTaskIndex=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        createTask=findViewById(R.id.task);
        Add=findViewById(R.id.Add);
        Update=findViewById(R.id.update);
        taskListView=findViewById(R.id.taskListView);

        ArrayList<String> taskList=new ArrayList<>();
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,taskList);
        taskListView.setAdapter(adapter);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String task = createTask.getText().toString().trim();
                if(!task.isEmpty()){
                    taskList.add(task);
                    adapter.notifyDataSetChanged();
                    createTask.setText("");
                    Toast.makeText(MainActivity.this,"Task added successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Please enter valid task",Toast.LENGTH_SHORT).show();
                }
            }
        });
        taskListView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            new AlertDialog.Builder(this).setTitle("Delete Task").setMessage("Do you want to delete this task?").setPositiveButton("Yes", (dialogInterface, position) -> {
                taskList.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();

            }).setNegativeButton("NO", null).show();
            return true;
        });

        taskListView.setOnItemClickListener((adapterView, view, i, l) -> {
            createTask.setText(taskList.get(i));
            selectedTaskIndex = i;
        });

        Update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(android.view.View view) {
                String updatedtask=createTask.getText().toString().trim();
                if(selectedTaskIndex!=-1&&!updatedtask.isEmpty()){
                    taskList.set(selectedTaskIndex,updatedtask);
                    adapter.notifyDataSetChanged();
                    createTask.setText("");
                    selectedTaskIndex=-1;
                    Toast.makeText(MainActivity.this,"Task updated",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Please enter task than update",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}