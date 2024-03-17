package com.example.kurs;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Parcelable, Serializable {
    private String taskHeader;
    private String taskText;
    private String startDate;
    private String endDate;
    private String reminderDate;
    private int id;
    private boolean isCompleted;

    // Конструктор

    // Parcelable конструктор
    private Task(Parcel in) {
        taskHeader = in.readString();
        taskText = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        reminderDate = in.readString();
        id = in.readInt();
        isCompleted = in.readByte() != 0; // Читаем байт и преобразуем его в boolean
    }

    // Генератор Parcelable
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskHeader);
        dest.writeString(taskText);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(reminderDate);
        dest.writeInt(id);
        dest.writeByte((byte) (isCompleted ? 1 : 0)); // Записываем байт, представляющий boolean
    }
    public Task() {
    }

    public Task(int id, String taskHeader, String taskText, String startDate, String endDate, String reminderDate, boolean isCompleted) {
        this.taskHeader = taskHeader;
        this.taskText = taskText;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reminderDate = reminderDate;
        this.isCompleted = isCompleted;
        this.id = id;
    }

    public Task(String taskHeader, String taskText, String startDate, String endDate, String reminderDate, boolean isCompleted) {
        this.taskHeader = taskHeader;
        this.taskText = taskText;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reminderDate = reminderDate;
        this.isCompleted = isCompleted;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return taskHeader + ", " + taskText + ", " + startDate + ", " + endDate + ", " + reminderDate + ", " +  id;
        //return super.toString();
    }

    // Геттеры и сеттеры для всех полей

    public int getId() {
        return id;
    }
    public String getTaskHeader() {
        return taskHeader;
    }

    public void setTaskHeader(String taskHeader) {
        this.taskHeader = taskHeader;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }
    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
