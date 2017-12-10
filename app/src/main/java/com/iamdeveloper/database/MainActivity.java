package com.iamdeveloper.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import Model.UserModel;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<UserModel> adapter;
    private List<UserModel> readUser;
    private ListView listView;
    private Button butt1,butt2;
    public static int array[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butt1 = (Button) findViewById(R.id.save);
        butt2 = (Button) findViewById(R.id.dontsave);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,InsertActivity.class);
                startActivity(a);
            }
        });
        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,InsertActivity2.class);
                startActivity(a);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUser();
    }

    private void getUser() {
        readUser = new ArrayList<UserModel>();
        DatabaseHelper helper = new DatabaseHelper(this);
        readUser = helper.readUser();

        if (readUser.size() != 0) {
            listView = (ListView) findViewById(R.id.list);
            adapter = new ArrayAdapter<UserModel>(this, android.R.layout.simple_list_item_1, android.R.id.text1, readUser);
            listView.setAdapter(adapter);
            Log.i("Adapter OnCreate", readUser.size() + "");
            registerForContextMenu(listView);// การสร้างตัว menu (กดค้าง)
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {// สร้าง adapter view ใช้ในการอ้างถึงตัว adapter
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo  info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        Log.i("INFO",info.position+"");
        menu.setHeaderTitle("แก้ไข");
        menu.add("update");
        menu.add("delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo  info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Log.i("INFO",position+"");
        if(item.getTitle().equals("update")){
            String id = readUser.get(position).getId();
            Log.i("UPDATE ID",id +"");
            Intent i = new Intent(MainActivity.this, InsertActivity.class);
            i.putExtra("position",id);
            startActivity(i);
        }else if (item.getTitle().equals("delete")){
            String id = readUser.get(position).getId();
            Log.i("DELETE ID",id);
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.deleteUser(id);
            readUser = helper.readUser();
            Log.i("DELETE", readUser.size() + "");
            adapter.clear();
            adapter.addAll(readUser);
            adapter.notifyDataSetChanged();
        }

        return true;

    }
    public void setMo(int m){
        array[0] = array[0] + m;
    }
}
