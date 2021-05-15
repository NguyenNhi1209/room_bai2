package com.example.android_lab6_b;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CusAdapter cusAdapter;
    List<Customer> customers ;
    ArrayList<Customer> arrayList;

    EditText diachi;
    Button save;
    Button cancel;
    CustomerDao customerDao;
    AppDatabase db;
    Customer all ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        recyclerView = findViewById(R.id.list);
        customers = new ArrayList<Customer>();
        diachi = (EditText)findViewById(R.id.diachi1);

//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Test3").allowMainThreadQueries().build();
//        db = Room.databaseBuilder(this, AppDatabase.class,"test3").allowMainThreadQueries().build();
        db = AppDatabase.getInstance(this);
        customerDao = db.customerDao();


//        Customer c1 = new Customer();
//        Customer c2 = new Customer();
//        c1.diaChi = "Phu Yen";
//        c2.diaChi = "Tuy an";
//
//        customerDao.insertCustomer(c1);
//        customerDao.insertCustomer(c2);

        customers = customerDao.getAll();



        arrayList = (ArrayList<Customer>)customerDao.getAll();


        cusAdapter = new CusAdapter(arrayList,this);
        recyclerView.setAdapter(cusAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = diachi.getText().toString();
                if(place.equals("")) {
                    Toast.makeText(MainActivity.this, "Nhap dia diem", Toast.LENGTH_SHORT).show();
                }else{
                    Customer c = new Customer();
                    c.diaChi = place;
                    customerDao.insertCustomer(c);
                    arrayList.clear();
                    customers = customerDao.getAll();
                    arrayList.addAll(customers);
                    cusAdapter.notifyDataSetChanged();
//                    cusAdapter.notifyItemInserted(customers.size());
                    diachi.setText("");
                }


            }
        });
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diachi.setText("");
            }
        });






    }
    public void loadData(){
        customers = customerDao.getAll();



//       arrayList = (ArrayList<Customer>)customerDao.getAll();


        cusAdapter = new CusAdapter(customers,this);
        recyclerView.setAdapter(cusAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}