package com.dung.quanlythuchi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dung.quanlythuchi.DAO.UserDAO;
import com.dung.quanlythuchi.fragment.ChiFragment;
import com.dung.quanlythuchi.fragment.ThongKeFragment;
import com.dung.quanlythuchi.fragment.ThuFragment;
import com.google.android.material.navigation.NavigationView;


public class NavQuanLyThuChi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private UserDAO userDAO;
    private static final int FRAGMENT_THU = 0;
    private static final int FRAGMENT_CHI = 1;
    private static final int FRAGMENT_THONG_KE = 2;
    // private static final int FRAGMENT_TAI_KHOAN = 3;

    private int mCurrentFragment = FRAGMENT_THU;//biến để check fragment hiện tại
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_quan_ly_thu_chi);
        userDAO = new UserDAO(NavQuanLyThuChi.this);
        userDAO.openDB();
// thêm toolbar và bắt sự kiện khi ấn vào nút 3 gạch
        drawerLayout = findViewById(R.id.drawer_layout);
        Intent intent = getIntent();
        Bundle userName = intent.getExtras();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.tv_username_header);
        TextView email = header.findViewById(R.id.tv_email_header);
        if (intent != null) {
            String nameText = (String) userName.get("userName");
            userDAO.getEmail(nameText).getEmail();

            name.setText(nameText);
            email.setText(userDAO.getEmail(nameText).getEmail());
        }


        replaceFragment(new ThuFragment());
        //xét fragment chi đang là fragment đc chọn
        navigationView.getMenu().findItem(R.id.nav_thu).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_thu) {
            if (mCurrentFragment != FRAGMENT_THU) {
                replaceFragment(new ThuFragment());
                mCurrentFragment = FRAGMENT_THU;
            }
        } else if (id == R.id.nav_chi) {
            if (mCurrentFragment != FRAGMENT_CHI) {
                replaceFragment(new ChiFragment());
                mCurrentFragment = FRAGMENT_CHI;
            }
        } else if (id == R.id.nav_thongke) {
            if (mCurrentFragment != FRAGMENT_THONG_KE) {
                replaceFragment(new ThongKeFragment());
                mCurrentFragment = FRAGMENT_THONG_KE;
            }
//        } else if (id == R.id.nav_taikhoan) {
//                if (mCurrentFragment != FRAGMENT_TAI_KHOAN) {
//                    replaceFragment(new TaiKhoanFragment());
//                    mCurrentFragment = FRAGMENT_TAI_KHOAN;
//                }
        } else if (id == R.id.nav_thoat) {
            Intent intent = new Intent(NavQuanLyThuChi.this, MainActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);//dong drawer lai
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {//nếu drawer đang mở
            drawerLayout.closeDrawer(GravityCompat.START);//thì ấn vào nút trở về của máy thì sẽ tắt drawer
        } else {//nếu drawer đang tắt thì ấn vào nút thoát của máy đt sẽ thoát ứng dụng
            super.onBackPressed();
        }
    }

    //viết logic xử lý replace fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);//có 2 tham số: 1 là content mk muốn replace vào, 2 là fragment mk muốn replace
        fragmentTransaction.commit();

    }


}