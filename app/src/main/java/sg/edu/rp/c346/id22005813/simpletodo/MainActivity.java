package sg.edu.rp.c346.id22005813.simpletodo;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd;
    Button btnClear;
    Button btnDel;
    ListView lvToDo;
    Spinner addRemoveSelect;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDel = findViewById(R.id.buttonDelete);
        lvToDo = findViewById(R.id.listToDo);
        addRemoveSelect = findViewById(R.id.spinner);

        alToDo = new ArrayList<>();

        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);

        lvToDo.setAdapter(aaToDo);

        addRemoveSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnClear.setEnabled(true);
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        etElement.setHint("Enter new task");
                        break;
                    case 1:
                        btnClear.setEnabled(true);
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        etElement.setHint("Enter index of task to remove");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etElement.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Enter a task", Toast.LENGTH_SHORT).show();
                } else {
                    alToDo.add(etElement.getText().toString());
                    aaToDo.notifyDataSetChanged();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etElement.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "There is no task", Toast.LENGTH_SHORT).show();
                } else {
                    alToDo.clear();
                    aaToDo.notifyDataSetChanged();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alToDo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                } else {
                    String input = etElement.getText().toString();
                    if (input.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter an index number", Toast.LENGTH_SHORT).show();
                    } else {
                        int position = Integer.parseInt(input);
                        if (position >= 1 && position <= alToDo.size()) {
                            alToDo.remove(position - 1);
                            aaToDo.notifyDataSetChanged();
                            etElement.setText("");
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
