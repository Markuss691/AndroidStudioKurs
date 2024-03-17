package com.example.kurs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kurs.utils.DBHelper;

import java.io.Serializable;

public class TaskDetailsActivity extends AppCompatActivity {
    Task selectedTask;
    private EditText editTextStartDate, editTextEndDate, editTextReminderDate, editTextHeader,editTextTaskText;
    private CheckBox checkBoxCompleted;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        // Получить выбранную задачу из интента
        selectedTask = getIntent().getParcelableExtra("selectedTask");

        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextReminderDate = findViewById(R.id.editTextReminderDate);

        editTextHeader = findViewById(R.id.editTextHeading);
        editTextTaskText = findViewById(R.id.editTextDescription);
        checkBoxCompleted = findViewById(R.id.checkBoxCompleted);

//        CheckBox checkBox = findViewById(R.id.isCompletedCheckBox);
//        checkBox.setEnabled(false); // Запрещаем реагирование на нажатия в списке задач
//
//        // Отобразить детали задачи, например, в TextView
//        TextView taskHeaderTextView = findViewById(R.id.editTextHeading);
//        TextView taskDescriptionTextView = findViewById(R.id.taskDescriptionTextView);
//
        editTextHeader.setText(selectedTask.getTaskHeader());
        editTextTaskText.setText(selectedTask.getTaskText());
        editTextStartDate.setText(selectedTask.getStartDate());
        editTextEndDate.setText(selectedTask.getEndDate());
        editTextReminderDate.setText(selectedTask.getReminderDate());
        checkBoxCompleted.setChecked(selectedTask.getIsCompleted());

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

            //Task task = new Task();

            // Создание объекта LocalDate для текущей даты
//            LocalDate currentDate = LocalDate.now();

            // Устанавливаем значения полей класса Task из полей ввода
//            task.setTaskHeader(editTextHeader.getText().toString());
//            task.setTaskText(editTextTaskText.getText().toString());
//            task.setStartDate(editTextStartDate.getText().toString());
//            task.setEndDate(editTextEndDate.getText().toString());
//            task.setReminderDate(editTextReminderDate.getText().toString());



            DBHelper dbHelper = new DBHelper(this);

            //dbHelper.addTask(new Task(editTextHeader.getText().toString(), editTextTaskText.getText().toString(),editTextStartDate.getText().toString(),editTextEndDate.getText().toString(),editTextReminderDate.getText().toString()));
            dbHelper.updateTask(new Task(selectedTask.getId(), editTextHeader.getText().toString(), editTextTaskText.getText().toString(),
                    editTextStartDate.getText().toString(),editTextEndDate.getText().toString(),editTextReminderDate.getText().toString(), checkBoxCompleted.isChecked()),this);


//            task.setStartDate(editTextStartDate.getText().toString());
//            task.setEndDate(editTextEndDate.getText().toString());
//            task.setReminderDate(editTextReminderDate.getText().toString());

            Intent intent= new Intent();
         //   intent.putExtra("task",(Serializable) task);
//            intent.putExtra("Header",editTextHeader.getText().toString());
            //Log.i("777", Integer.toString(count));

            setResult(200,intent);
            finish();// в основном окне так делать не нужно. Много ресурсов на создание его.
            //также не нужно сразу закрывать окно, если из него запуускается какое-то ещё окно третьего уровня
            // при финише автоматом переходим в окно, которое вызвало закрываемое окно
        });

        findViewById(R.id.buttonDeleteTask).setOnClickListener(view -> {

            DBHelper dbHelper = new DBHelper(this);

            //dbHelper.addTask(new Task(editTextHeader.getText().toString(), editTextTaskText.getText().toString(),editTextStartDate.getText().toString(),editTextEndDate.getText().toString(),editTextReminderDate.getText().toString()));
            dbHelper.deleteTask(selectedTask);

            Intent intent= new Intent();
            intent.putExtra("task",(Serializable) selectedTask);
            setResult(200,intent);
            finish();
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