package br.com.thiago.note.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import br.com.thiago.note.R;
import br.com.thiago.note.dao.StudentDAO;
import br.com.thiago.note.model.Student;
import br.com.thiago.note.ui.activity.actions.ConfigureAdapter;
import br.com.thiago.note.ui.activity.actions.DialogBuilderConfirmExclusionStudent;
import br.com.thiago.note.ui.adapter.ListAdapterStudent;

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
    protected void onResume() {
        super.onResume();
        adapter.actualizeStudents(dao);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_list_students_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_list_students_menu_remove) {
            createDialogConfirmExclusionStudent(item);
        }

        return super.onContextItemSelected(item);
    }

    private void createDialogConfirmExclusionStudent(final MenuItem item) {
        DialogBuilderConfirmExclusionStudent.dialogBuilder(this, item, adapter, dao);
    }

    private void configureFabNewStudent() {
        FloatingActionButton buttonNewStudent = findViewById(R.id.activity_list_students_fab_new_student);
        buttonNewStudent.setOnClickListener(view -> openFormModePutStudent());
    }

    private void configureList() {
        ListView listStudents = findViewById(R.id.activity_list_students_listview);
        adapter = ConfigureAdapter.configureAdapter(listStudents, this);
        configureListenerClickItem(listStudents);
        registerForContextMenu(listStudents);
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
}