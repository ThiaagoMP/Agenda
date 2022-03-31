package br.com.thiago.note.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.note.R;
import br.com.thiago.note.dao.StudentDAO;
import br.com.thiago.note.model.Student;

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
        View viewInflate = inflate(viewGroup);
        setViewText(position, viewInflate);

        return viewInflate;
    }

    private void setViewText(int position, View viewInflate) {
        final TextView viewName = viewInflate.findViewById(R.id.item_student_name);
        final TextView viewPhone = viewInflate.findViewById(R.id.item_student_phone);
        Student student = getItem(position);
        viewName.setText(student.getName());
        viewPhone.setText(student.getPhone());
    }

    private View inflate(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_student, viewGroup, false);
    }

    public void actualize(StudentDAO studentDAO) {
        this.students.clear();
        students.addAll(studentDAO.getStudents());
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }
}
