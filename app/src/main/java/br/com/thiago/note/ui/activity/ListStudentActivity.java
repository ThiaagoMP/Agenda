package br.com.thiago.note.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.thiago.note.R;
import br.com.thiago.note.dao.StudentDAO;
import br.com.thiago.note.model.Student;
import br.com.thiago.note.ui.activity.adapter.ListAdapterStudent;

import static br.com.thiago.note.ui.activity.ConstantsActivities.KEY_STUDENT;

public class ListStudentActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de alunos";
    private final StudentDAO dao = new StudentDAO();
    private ListAdapterStudent adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        setTitle(TITLE_APPBAR);
        configureFabNewStudent();
        configureList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_list_students_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_list_students_menu_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student studentFill = adapter.getItem(menuInfo.position);
            remove(studentFill);
        }

        return super.onContextItemSelected(item);
    }

    private void configureFabNewStudent() {
        FloatingActionButton buttonNewStudent = findViewById(R.id.activity_list_students_fab_new_student);
        buttonNewStudent.setOnClickListener(view -> openFormModePutStudent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizeStudents();
    }

    private void actualizeStudents() {
        adapter.actualize(dao);
    }

    private void configureList() {
        ListView listStudents = findViewById(R.id.activity_list_students_listview);
        configureAdapter(listStudents);
        configureListenerClickItem(listStudents);
        registerForContextMenu(listStudents);
    }

    private void remove(Student student) {
        dao.remove(student);
        adapter.remove(student);
    }

    private void configureListenerClickItem(ListView listStudents) {
        listStudents.setOnItemClickListener((adapterView, view, position, id) -> {
            Student student = (Student) adapterView.getItemAtPosition(position);
            openFormModeEditStudent(student);
        });
    }

    private void openFormModePutStudent() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    private void openFormModeEditStudent(Student student) {
        Intent formActivityIntent = new Intent(ListStudentActivity.this, FormStudentActivity.class);
        formActivityIntent.putExtra(KEY_STUDENT, student);
        startActivity(formActivityIntent);
    }

    private void configureAdapter(ListView listView) {
        adapter = new ListAdapterStudent(this);
        listView.setAdapter(adapter);
    }
}
