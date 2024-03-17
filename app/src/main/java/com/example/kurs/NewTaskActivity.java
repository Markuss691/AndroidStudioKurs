package com.example.kurs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.kurs.utils.DBHelper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {
    private EditText editTextStartDate, editTextEndDate, editTextReminderDate, editTextHeader,editTextTaskText;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);
        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextReminderDate = findViewById(R.id.editTextReminderDate);


        editTextHeader = findViewById(R.id.editTextHeading);
        editTextTaskText = findViewById(R.id.editTextDescription);
        //buttonSaveTask = findViewById(R.id.buttonSaveTask);

        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(editTextStartDate);
            }
        });

        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(editTextEndDate);
            }
        });

        editTextReminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(editTextReminderDate);
            }
        });

        findViewById(R.id.buttonSaveTask).setOnClickListener(view -> {
// Создаем новый экземпляр класса Task

//            LocalDate startDate = LocalDate.parse(editTextStartDate.getText().toString());
//            LocalDate endDate = LocalDate.parse(editTextEndDate.getText().toString());
//            LocalDate reminderDate = LocalDate.parse(editTextReminderDate.getText().toString());

            Task task = new Task();

            // Создание объекта LocalDate для текущей даты
//            LocalDate currentDate = LocalDate.now();

            // Устанавливаем значения полей класса Task из полей ввода
//            task.setTaskHeader(editTextHeader.getText().toString());
//            task.setTaskText(editTextTaskText.getText().toString());
//            task.setStartDate(editTextStartDate.getText().toString());
//            task.setEndDate(editTextEndDate.getText().toString());
//            task.setReminderDate(editTextReminderDate.getText().toString());

            DBHelper dbHelper = new DBHelper(this);

            dbHelper.addTask(new Task(editTextHeader.getText().toString(), editTextTaskText.getText().toString(),editTextStartDate.getText().toString(),editTextEndDate.getText().toString(),editTextReminderDate.getText().toString(),false));


//            task.setStartDate(editTextStartDate.getText().toString());
//            task.setEndDate(editTextEndDate.getText().toString());
//            task.setReminderDate(editTextReminderDate.getText().toString());

            Intent intent= new Intent();
            intent.putExtra("task",(Serializable) task);
//            intent.putExtra("Header",editTextHeader.getText().toString());
            //Log.i("777", Integer.toString(count));

            setResult(200,intent);
            finish();// в основном окне так делать не нужно. Много ресурсов на создание его.
            //также не нужно сразу закрывать окно, если из него запуускается какое-то ещё окно третьего уровня
            // при финише автоматом переходим в окно, которое вызвало закрываемое окно
        });

        findViewById(R.id.buttonCancel).setOnClickListener(view -> {

            Intent intent= new Intent();
            //intent.putExtra("task", task);
//            intent.putExtra("Header",editTextHeader.getText().toString());
            //Log.i("777", Integer.toString(count));

            setResult(100,intent);
            finish();
        });
    }

    private void showDateTimePicker(final EditText editText) {
        DateTimePickerDialog.showDateTimePicker(this, editText);
    }

}