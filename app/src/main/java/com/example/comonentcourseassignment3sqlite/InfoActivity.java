package com.example.comonentcourseassignment3sqlite;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView nameText = findViewById(R.id.nameText);
        TextView emailText = findViewById(R.id.emailText);
        TextView phoneText = findViewById(R.id.phoneText);
        TextView titleText = findViewById(R.id.titleText);
        TextView deptText = findViewById(R.id.departmentText);

        DB_Sqlite employeeDataBase = new DB_Sqlite(this);
        ArrayList<String> empInfo =  employeeDataBase.getEmployeeData(getIntent().getExtras().getString("empId"));

        nameText.setText("Name : " + empInfo.get(1));
        emailText.setText("Email : " + empInfo.get(2));
        phoneText.setText("Phone : " + empInfo.get(3));
        titleText.setText("Title : " + empInfo.get(4));
        deptText.setText("Department : " + empInfo.get(5));
    }

}
