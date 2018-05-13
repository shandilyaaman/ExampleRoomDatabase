package demoroom.app.com.roomdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd, btnShow;
    private EditText etFname, etLname, etAge;
    private AppDatabase appDatabase;
    private UserDao userDao;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.getAppDatabase(MainActivity.this);
        userDao = appDatabase.userDao();
        btnAdd = findViewById(R.id.btn_adduser);
//        btnShow = findViewById(R.id.btn_show);
        etFname = findViewById(R.id.et_fname);
        etLname = findViewById(R.id.et_lname);
        etAge = findViewById(R.id.et_age);
        recyclerView = findViewById(R.id.recyclerview);

        List<User> userList = new ArrayList<>();
        myAdapter = new MyAdapter(userList, MainActivity.this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        userList = userDao.getAll();
        myAdapter.refreshList(userList);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setFirstName(etFname.getText().toString());
                user.setLastName(etLname.getText().toString());
                user.setAge(Integer.parseInt(etAge.getText().toString()));

                userDao.insertAll(user);
            }
        });

        /*btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userList = userDao.getAll();
                for (User user : userList) {
                    Log.e("First Name : ", user.getFirstName());
                }
            }
        });*/

    }
}
