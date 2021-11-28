package com.dung.quanlythuchi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dung.quanlythuchi.DAO.UserDAO;
import com.dung.quanlythuchi.DTO.User;
import com.dung.quanlythuchi.adapter.UserAdapter;
import com.dung.quanlythuchi.fragment.ThongKeFragment;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    ListView lvUser;
    UserAdapter userAdapter;
    UserDAO userDAO;
    EditText edUserName, edPass;
//    String mUser = "";
//    String mPass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        edUserName = findViewById(R.id.ed_add_user_login);
        edPass = findViewById(R.id.ed_add_pass_login);
        lvUser = findViewById(R.id.lv_user);
        userDAO = new UserDAO(MainActivity.this);
        userDAO.openDB();
        userAdapter = new UserAdapter(userDAO.selectAll(), userDAO);
        lvUser.setAdapter(userAdapter);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUserName.getText().toString();
                String passWord = edPass.getText().toString();
                boolean res = userDAO.kiemTraLogin(userName, passWord);
                if (res) {
                    Intent intent = new Intent(MainActivity.this, NavQuanLyThuChi.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
                // sendDataFragment();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAdapter.showDialogRegister(MainActivity.this);
            }
        });
    }

//    public void sendDataFragment() {
//        String sendUser = edUserName.getText().toString().trim();
//        String sendPass = edPass.getText().toString().trim();
//
//        mUser = sendUser;
//        mPass = sendPass;
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.content_frame, new ThongKeFragment());
//        fragmentTransaction.commit();
//    }


//    public String getmUser() {
//        return mUser;
//    }
//
//    public String getmPass() {
//        return mPass;
//    }
}