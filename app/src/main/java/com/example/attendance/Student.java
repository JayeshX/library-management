package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.poi.sl.usermodel.TextBox;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {
    private Spinner spinner1;
    private Button b;
    private CheckBox cb;
    private EditText gr;
    private String department,year,roll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        spinner1 = findViewById(R.id.spinner);
        cb = findViewById(R.id.checkBox2);
        b = findViewById(R.id.button7);
        gr = findViewById(R.id.editTextTextPersonName);
        // Spinner click listener


        // Spinner Drop down elements
        List<String> department = new ArrayList<String>();
        department.add("DS");
        department.add("Item 2");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, department);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("attendance",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String DEPT = String.valueOf(spinner1.getSelectedItem());
                String GR = String.valueOf(gr.getText());
                Toast.makeText(Student.this,DEPT,Toast.LENGTH_SHORT).show();
                editor.putString("department",DEPT);
                editor.putString("login","student");
                editor.putString("gr",GR);
                editor.apply();
                startActivity(new Intent(Student.this, qrgene.class));
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("attendance",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("b","true");
                    editor.apply();
                    Toast.makeText(Student.this,"Checked",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("attendance",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("b","false");
                    editor.apply();
                    Toast.makeText(Student.this,"UnChecked",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}