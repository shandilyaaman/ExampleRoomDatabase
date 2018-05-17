package demoroom.app.com.roomdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
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
        etFname = findViewById(R.id.et_fname);
        etLname = findViewById(R.id.et_lname);
        etAge = findViewById(R.id.et_age);
        recyclerView = findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        userDao.getAll().observe(this, (List<User> userList) -> {
            myAdapter = new MyAdapter(userList, MainActivity.this);
            recyclerView.setAdapter(myAdapter);
        });


        btnAdd.setOnClickListener((view) -> {
            User user = new User();
            user.setFirstName(etFname.getText().toString());
            user.setLastName(etLname.getText().toString());
            user.setAge(Integer.parseInt(etAge.getText().toString()));
            userDao.insertAll(user);
        });

    }
}
