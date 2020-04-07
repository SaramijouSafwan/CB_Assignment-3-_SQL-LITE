package com.example.comonentcourseassignment3sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DB_Sqlite extends SQLiteOpenHelper {
    static private final String DB_Name = "EmployeeDB";

    public DB_Sqlite(Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table department(deptId integer primary key autoincrement, name text)");
        // email text not null, phone text not null," +
        //                "title text not null, deptId integer, FOREIGN KEY (deptId) REFERENCES department (deptId)
        db.execSQL("create table employee(empId integer primary key autoincrement, name text not null, email text, phone text," +
                " title text, deptId integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("Drop table if exists department");
        db.execSQL("Drop table if exists employee");
        onCreate(db);
    }

    public ArrayList getEmployee(String empName) {
        ArrayList employee = new ArrayList();
        SQLiteDatabase DataBase = this.getReadableDatabase();
        Cursor cursor = DataBase.rawQuery("select * from employee where name like ? ", new String[]{empName+"%"});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String Name = cursor.getString(1);
            String Email = cursor.getString(2);
            String Phone = cursor.getString(3);
            String Title = cursor.getString(4);
            String Dept = cursor.getString(5);
            Cursor deptCursor = DataBase.rawQuery("select name from department where deptId == ?", new String[]{Dept});
            deptCursor.moveToFirst();
            employee.add("[" + id + "] " + " " + Name + " | " + Email + " | " + Phone + " | " + Title + " | " + deptCursor.getString(0));
            cursor.moveToNext();
        }
        return employee;
    }

    public void addEmployee() {
        ContentValues Dept1 = new ContentValues();
        Dept1.put("name", "HR");

        ContentValues Dept2 = new ContentValues();
        Dept2.put("name", "DESIGN");

        ContentValues Emp1 = new ContentValues();
        Emp1.put("name", "Safwan");
        Emp1.put("email", "gmail.com");
        Emp1.put("phone", "01123");
        Emp1.put("title", "Manger");
        Emp1.put("deptId", 1);

        ContentValues Emp2 = new ContentValues();
        Emp2.put("name", "Hassan");
        Emp2.put("email", "hotmail.com");
        Emp2.put("phone", "01234");
        Emp2.put("title", "Employee");
        Emp2.put("deptId", 2);

        ContentValues Emp3 = new ContentValues();
        Emp3.put("name", "Safwan Mhd");
        Emp3.put("email", "hotmail.com");
        Emp3.put("phone", "01234");
        Emp3.put("title", "Employee");
        Emp3.put("deptId", 1);

        SQLiteDatabase DataBase = this.getWritableDatabase();
        DataBase.insert("department", null, Dept1);
        DataBase.insert("department", null, Dept2);
        DataBase.insert("employee", null, Emp1);
        DataBase.insert("employee", null, Emp2);
        DataBase.insert("employee", null, Emp3);
    }
}




/*
        ContentValues Dep1 = new ContentValues();
        ContentValues Dep2 = new ContentValues();
        Dep1.put("name", "HR");
        Dep2.put("name", "IT");
        employeeDB = getWritableDatabase();
        employeeDB.insert("department", null, Dep1);
        employeeDB.insert("department", null, Dep2);

        ContentValues Emp1 = new ContentValues();
        ContentValues Emp2 = new ContentValues();

        Emp1.put("name", "Safwan");
        Emp1.put("email", "safwan@gmail.com");
        Emp1.put("title", "Manger");
        Emp1.put("phone", "123");
        Emp1.put("deptId", 1);

        Emp2.put("name", "Hassan");
        Emp2.put("email", "hassan@gmail.com");
        Emp2.put("title", "Support");
        Emp2.put("phone", "123");
        Emp2.put("deptId", 2);

        employeeDB.insert("employee", null, Emp1);
        employeeDB.insert("employee", null, Emp2);
*/