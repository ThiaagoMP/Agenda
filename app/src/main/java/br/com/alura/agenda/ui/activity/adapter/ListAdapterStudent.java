package br.com.alura.agenda.ui.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Student;

public class ListAdapterStudent extends BaseAdapter {

    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public ListAdapterStudent(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View viewInflate = LayoutInflater
                .from(context)
                .inflate(R.layout.item_student, viewGroup, false);
        final TextView viewName = viewInflate.findViewById(R.id.item_student_name);
        final TextView viewPhone = viewInflate.findViewById(R.id.item_student_phone);

        Student student = getItem(position);
        viewName.setText(student.getName());
        viewPhone.setText(student.getPhone());

        return viewInflate;
    }

    public void clear() {
        students.clear();
    }

    public void addAll(List<Student> students) {
        this.students.addAll(students);
    }

    public void remove(Student student) {
        students.remove(student);
    }
}
