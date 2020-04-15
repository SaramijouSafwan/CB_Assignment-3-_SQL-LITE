package com.example.comonentcourseassignment3sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myList;
    ArrayAdapter<String> listAdapter;
    public DB_Sqlite employeeDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        employeeDB = new DB_Sqlite(getApplicationContext());
        employeeDB.addEmployee();

        myList = (ListView) findViewById(R.id.empList);
        listAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        myList.setAdapter(listAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, InfoActivity.class);
                String empName = ((TextView)view).getText().toString();
                i.putExtra("empId", employeeDB.getEmployeeId(empName));
                startActivity(i);
                return;
            }
        });

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listAdapter.clear();
                EditText searchText = (EditText) findViewById(R.id.searctText);
                String empName = searchText.getText().toString();
                ArrayList<String> employee = employeeDB.getEmployee(empName);
                for(int i = 0; i < employee.size(); i++){
                    listAdapter.add(employee.get(i));
                }
                searchText.getText().clear();
            }
        });
    }

}
