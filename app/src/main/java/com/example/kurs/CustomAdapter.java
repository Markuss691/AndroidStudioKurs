package com.example.kurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Task> {
    private LayoutInflater inflater;

    public CustomAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_task, parent, false);
            holder = new ViewHolder();
            holder.taskHeaderTextView = convertView.findViewById(R.id.taskHeaderTextView);
            holder.taskDescriptionTextView = convertView.findViewById(R.id.taskDescriptionTextView);
            holder.isCompletedCheckBox = convertView.findViewById(R.id.isCompletedCheckBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Task task = getItem(position);
        if (task != null) {
            holder.taskHeaderTextView.setText(task.getTaskHeader());
            holder.taskDescriptionTextView.setText(task.getTaskText());
            holder.isCompletedCheckBox.setChecked(task.getIsCompleted());

            // Изменение фона в зависимости от выполнения задачи
            if (task.getIsCompleted()) {
                convertView.setBackgroundResource(R.drawable.item_background);
            } else {
                convertView.setBackgroundResource(R.drawable.default_task_background);
            }
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView taskHeaderTextView;
        TextView taskDescriptionTextView;
        CheckBox isCompletedCheckBox;
    }
}