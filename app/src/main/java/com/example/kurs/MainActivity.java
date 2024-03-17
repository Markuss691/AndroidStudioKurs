package com.example.kurs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kurs.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String Header = "";
    private DBHelper dbHelper;
    //private ArrayAdapter<String> adapter;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //*/н тест БД
//        DBHelper dbHelper2 = new DBHelper(this);
//
////        dbHelper.addTask(new Task("Header", "Text","DataNach","DataKon","DataReminder"));
////        dbHelper.addTask(new Task("Header1", "Text1","DataNach1","DataKon1","DataReminder1"));
////        dbHelper.addTask(new Task("Header2", "Text2","DataNach2","DataKon2","DataReminder2"));
//
//        List<Task> listTasks = dbHelper2.ListTasks();
//        for (int i = 0; i < listTasks.size(); i++) {
//            if(listTasks.get(i).getIsCompleted()){
//                Toast.makeText(this, "True",Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "False",Toast.LENGTH_SHORT).show();
//            }
//            Toast.makeText(this, listTasks.get(i).getTaskHeader(),Toast.LENGTH_SHORT).show();
//        }
//        //*/к тест БД
        ListView listView = findViewById(R.id.listView);
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //adapter = new CustomAdapter(this, new ArrayList<>());
        adapter = new CustomAdapter(getApplicationContext(), new ArrayList<Task>());
        listView.setAdapter(adapter);

        dbHelper = new DBHelper(this);
        displayTasks();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Получить выбранную задачу из адаптера
                Task selectedTask = (Task) parent.getItemAtPosition(position);

                // Здесь можно передать выбранную задачу в новую активность и открыть её
                // Например:
                Intent intent = new Intent(MainActivity.this, TaskDetailsActivity.class);
                intent.putExtra("selectedTask",(Parcelable) selectedTask);
                //поскольку putExtra в классе Task может быть  Parcelable или  Serializable надо прямо указать.
                //в шапке указано: public class Task implements Parcelable, Serializable
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

//        Button btnAddTask = findViewById(R.id.btnAddTask);
        //Button btnTaskList = findViewById(R.id.btnTaskList);
        Button btnCalendar = findViewById(R.id.btnCalendar);


        findViewById(R.id.btnAddTask).setOnClickListener(view -> {
            Intent intent= new Intent(this, NewTaskActivity.class);//Передает данные из одного активити в другое
            startActivityForResult(intent, 1);//отправляем вместе с интентом реквест код 1, этот код может означать вызов с этой кнопки
        });

//        btnTaskList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "TaskList", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT).show();
            }
        });

    }
//    private void displayTasks() {
//        List<Task> listTasks = dbHelper.ListTasks();
//        adapter.clear();
//        adapter.addAll(listTasks);
//        adapter.notifyDataSetChanged();
//    }
    private void displayTasks() {
        List<Task> listTasks = dbHelper.ListTasks();
        adapter.clear();
        for (int i = 0; i < listTasks.size(); i++) {
            adapter.add(listTasks.get(i));
//         //   adapter.add(listTasks.get(i).getTaskHeader());
//         //   Toast.makeText(this, listTasks.get(i).getTaskHeader(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    //onActivityResult эта процедура срабатывает при переходе на это окно из другого. в нем получаем то, что было отправлено методом setResult(200,intent);
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//для проверки реквестКода, который отправили через startActivityForResult
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode определяется в этом окне, resultCode во втором окне
        if(requestCode==1 && resultCode == 200 && data !=null){//вместо requestCode==1 лучше использовать requestCode == SecondRequestCode
//            Header = data.getStringExtra("Header");
//            Toast.makeText(this, Header, Toast.LENGTH_LONG).show();
            if (data != null && data.hasExtra("task")) {

              }

        }
        displayTasks();
    }

    public void onClickbtnStartShedule(View view) {
    }
}