package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.StudentDAO;
import br.com.alura.agenda.model.Student;

import static br.com.alura.agenda.ui.activity.ConstantsActivities.KEY_STUDENT;

public class ListStudentActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de alunos";
    private final StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        setTitle(TITLE_APPBAR);
        configureFabNewStudent();
        configureList();
        dao.save(new Student("Alex", "1122223333", "alex@alura.com.br"));
        dao.save(new Student("Fran", "1122223333", "fran@gmail.com"));
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
            Student studentEscolhido = adapter.getItem(menuInfo.position);
            remove(studentEscolhido);
        }

        return super.onContextItemSelected(item);
    }

    private void configureFabNewStudent() {
        FloatingActionButton buttonNewStudent = findViewById(R.id.activity_list_students_fab_new_student);
        buttonNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormModePutStudent();
            }
        });
    }

    private void openFormModePutStudent() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizeStudents();
    }

    private void actualizeStudents() {
        adapter.clear();
        adapter.addAll(dao.getStudents());
    }

    private void configureList() {
        ListView listaDeAlunos = findViewById(R.id.activity_list_students_listview);
        configuraAdapter(listaDeAlunos);
        configureListenerClickItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void remove(Student student) {
        dao.remove(student);
        adapter.remove(student);
    }

    private void configureListenerClickItem(ListView listStudents) {
        listStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Student studentEscolhido = (Student) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(studentEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Student student) {
        Intent vaiParaFormularioActivity = new Intent(ListStudentActivity.this, FormStudentActivity.class);
        vaiParaFormularioActivity.putExtra(KEY_STUDENT, student);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }
}
