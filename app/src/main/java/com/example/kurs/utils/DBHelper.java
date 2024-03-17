package com.example.kurs.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kurs.Task;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Tasks_DB2";
    private static final String TABLE_TASKS = "Tasks";

    private static final String COLUMN_TASKHEADER = "taskHeader";
    private static final String COLUMN_TASKTEXT = "taskText";
    private static final String COLUMN_STARTDATE = "startDate";
    private static final String COLUMN_ENDTDATE = "endDate";
    private static final String COLUMN_REMINDERTDATE = "reminderDate";
    private static final String COLUMN_ISCOMPLETED = "isCompleted";
    private static final String COLUMN_ID = "id";
    private static final String CREATE_TABLE_TASKS_SCRIPT = "CREATE TABLE IF Not exists Tasks(" +
            "Id integer primary key autoincrement, " +
            "taskHeader text not null," +
            "taskText text not null,"+
            "startDate text not null,"+
            "endDate text not null," +
            "reminderDate text," +
            "isCompleted INTEGER DEFAULT 0)";

    //    private String taskHeader;
//    private String taskText;
//    private String startDate;
//    private String endDate;
//    private String reminderDate;

    public DBHelper(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_TASKS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //при смене версии написать тут дроп тэйбл и скрипт обновленной таблицы
        //db.execSQL(CREATE_TABLE_TASKS_SCRIPT);
    }

    public void addTask(Task task){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_TASKHEADER, task.getTaskHeader() );
            values.put(COLUMN_TASKTEXT, task.getTaskText() );
            values.put(COLUMN_STARTDATE, task.getStartDate() );
            values.put(COLUMN_ENDTDATE, task.getEndDate() );
            values.put(COLUMN_REMINDERTDATE, task.getReminderDate() );
            //values.put(COLUMN_ISCOMPLETED, task.getIsCompleted() );
            values.put(COLUMN_ISCOMPLETED, task.getIsCompleted() ? 1 : 0);//булево в инт

            db.insert(TABLE_TASKS, null, values);
            db.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            //печать лога ошибки
        }

    }

    //public int updateTask(Task task){
    public int updateTask(Task task, Context context) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_TASKHEADER, task.getTaskHeader() );
            values.put(COLUMN_TASKTEXT, task.getTaskText() );
            values.put(COLUMN_STARTDATE, task.getStartDate() );
            values.put(COLUMN_ENDTDATE, task.getEndDate() );
            values.put(COLUMN_REMINDERTDATE, task.getReminderDate() );
           // values.put(COLUMN_ISCOMPLETED, task.getIsCompleted() );
            values.put(COLUMN_ISCOMPLETED, task.getIsCompleted() ? 1 : 0);//булево в инт

            boolean isChecked = task.getIsCompleted();
//// Преобразовать значение в строку
//            String checkBoxState = isChecked ? "Флажок установлен" : "Флажок снят";
//// Создать и показать Toast с состоянием флажка
//            Toast.makeText(context, checkBoxState, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, COLUMN_ISCOMPLETED, Toast.LENGTH_SHORT).show();

            int result = db.update(TABLE_TASKS, values, COLUMN_ID + " =? ", new String[]{String.valueOf(task.getId())} );
            //третий параметр это поле, по которому обновляем
            //четвертый параметр это массив в который нужно запихать значение поиска. Это значение встанет вместо вопроса в третьем параметре
            //result вернет количество измененных строк
            db.close();
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
            //печать лога ошибки
            return -1;
        }

    }

    public int deleteTask(Task task){
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            int result = db.delete(TABLE_TASKS, COLUMN_ID + " =? ", new String[]{String.valueOf(task.getId())} );
            //2 параметр это поле, по которому обновляем
            //3 параметр это массив в который нужно запихать значение поиска. Это значение встанет вместо вопроса в третьем параметре
            //result вернет количество удаленных строк
            db.close();
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
            //печать лога ошибки
            return -1;
        }
    }

    public Task readTask(int id){
        try{
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_TASKS, new String[]{COLUMN_ID,COLUMN_TASKHEADER,COLUMN_TASKTEXT,COLUMN_STARTDATE,COLUMN_ENDTDATE,COLUMN_REMINDERTDATE,COLUMN_ISCOMPLETED},
                    COLUMN_ID + " =? ", new String[]{String.valueOf(id)}, null,null,null,null );
           if (cursor != null){
               cursor.moveToFirst();
           }
           //public Task(int id, String taskHeader, String taskText, String startDate, String endDate, String reminderDate, boolean isCompleted)
           Task task = new Task(cursor.getInt(0),
                   cursor.getString(1),
                   cursor.getString(2),
                   cursor.getString(3),
                   cursor.getString(4),
                   cursor.getString(5),
                   cursor.getInt(6) == 1 // Преобразуем целое значение в boolean
           );
            db.close();
            return task;
        }
        catch (Exception ex){
            ex.printStackTrace();
            //печать лога ошибки
            return null;
        }
    }

    public List<Task> ListTasks(){
        List<Task> ListTasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try{
            db = this.getReadableDatabase();

            cursor = db.rawQuery("SELECT * FROM " + TABLE_TASKS, null);
            if (cursor != null){
                cursor.moveToFirst();
                do{
                    Task task = new Task(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getInt(6) == 1 // Преобразуем целое значение в boolean
                    );
                    ListTasks.add(task);

                }while (cursor.moveToNext());
            }

            return ListTasks;
        }
        catch (Exception ex){
            ex.printStackTrace();
            //печать лога ошибки
            return null;
        }finally {
            if (cursor != null) {
                cursor.close(); // Закрываем курсор
            }
            db.close();
        }

    }

}
