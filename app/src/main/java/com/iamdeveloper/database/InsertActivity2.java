package com.iamdeveloper.database;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity2 extends AppCompatActivity {
    private EditText et_name,et_pass;
    private Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Bundle bundle = getIntent().getExtras();

        bindView();

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        if(bundle != null){
            final String position = bundle.getString("position");
            btn_insert.setText("update");
            btn_insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateData(position);
                }
            });

        }
    }

    private void bindView() {
        et_name = (EditText) findViewById(R.id.name);
        et_pass = (EditText) findViewById(R.id.pass);
        btn_insert = (Button) findViewById(R.id.btn_insert);
    }

    private void insertData(){
        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();
        if(!name.isEmpty()&&!pass.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(name,pass);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData(String id){
        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();
        if(!name.isEmpty()&&!pass.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.updateUser(id,name,pass);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

}
