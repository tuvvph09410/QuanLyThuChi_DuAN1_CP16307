package com.dung.quanlythuchi.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dung.quanlythuchi.DAO.UserDAO;
import com.dung.quanlythuchi.DTO.User;
import com.dung.quanlythuchi.R;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    ArrayList<User> dsUser;
    UserDAO userDAO;

    public UserAdapter(ArrayList<User> dsUser, UserDAO userDAO) {
        this.dsUser = dsUser;
        this.userDAO = userDAO;
    }

    @Override
    public int getCount() {
        return dsUser.size();
    }

    @Override
    public Object getItem(int i) {

        User objUser = dsUser.get(i);
        return objUser;
    }

    @Override
    public long getItemId(int i) {
        User objUser = dsUser.get(i);
        return objUser.getIdUser();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemView;
        if (view == null) {
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_list_user, null);
        } else {
            itemView = view;
        }

        final User userDTO = dsUser.get(i);
        final int _index = i;


        TextView txtSTT = itemView.findViewById(R.id.txt_stt);
        TextView txtName = itemView.findViewById(R.id.txt_name_list);
        TextView txtUserName = itemView.findViewById(R.id.txt_user_list);
        ImageView imgDel = itemView.findViewById(R.id.img_xoa);
        ImageView imgEdi = itemView.findViewById(R.id.img_edit);

        txtSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setIcon(R.drawable.ic_baseline_person_24);
                builder.setTitle("Thông Tin User");
                builder.setMessage("Thông tin chi tiết: " + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName()
                        + "\n" + "Email: " + userDTO.getEmail() + "\n" + "Phone: " + userDTO.getSoDT() + "\n" + "Date: " + userDTO.getDate()
                );
                builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setIcon(R.drawable.ic_baseline_person_24);
                builder.setTitle("Thông Tin User");
                builder.setMessage("Thông tin chi tiết: " + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName()
                        + "\n" + "Email: " + userDTO.getEmail() + "\n" + "Phone: " + userDTO.getSoDT() + "\n" + "Date: " + userDTO.getDate()
                );
                builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        txtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setIcon(R.drawable.ic_baseline_person_24);
                builder.setTitle("Thông Tin User");
                builder.setMessage("Thông tin chi tiết: " + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName()
                        + "\n" + "Email: " + userDTO.getEmail() + "\n" + "Phone: " + userDTO.getSoDT() + "\n" + "Date: " + userDTO.getDate()
                );
                builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        imgEdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit(userDTO, _index, viewGroup.getContext());
            }
        });

//        TextView txtUserNameHeader = itemView.findViewById(R.id.txt_username_header);
//        TextView txtEmailHeader = itemView.findViewById(R.id.txt_email_header);

//        txtUserNameHeader.setText(userDTO.getUserName());
//        txtEmailHeader.setText(userDTO.getEmail());

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Xóa User");
                builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
                builder.setMessage("Bạn có muốn xóa không?" + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName());
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = userDAO.deleteRow(userDTO);
                        if (res > 0) {
                            dsUser.remove(_index);
                            notifyDataSetChanged();
                            Toast.makeText(viewGroup.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(viewGroup.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        txtSTT.setText(userDTO.getIdUser() + "");
        txtName.setText(userDTO.getName());
        txtUserName.setText(userDTO.getUserName());

        return itemView;
    }

    public void showDialogRegister(Context context) {
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.register_activity);
        EditText edName = dialog.findViewById(R.id.ed_add_register_name);
        EditText edUserName = dialog.findViewById(R.id.ed_add_register_user);
        EditText edPass = dialog.findViewById(R.id.ed_add_register_pass);
        EditText edEmail = dialog.findViewById(R.id.ed_add_register_sdt);
        EditText edSDT = dialog.findViewById(R.id.ed_add_register_sdt);
        EditText edDate = dialog.findViewById(R.id.ed_add_register_date);
        Button btnAdd = dialog.findViewById(R.id.btn_save_add_register);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User objUser = new User();
                objUser.setName(edName.getText().toString());
                objUser.setUserName(edUserName.getText().toString());
                objUser.setPassWord(edPass.getText().toString());
                objUser.setEmail(edEmail.getText().toString());
                objUser.setSoDT(edSDT.getText().toString());
                objUser.setDate(edDate.getText().toString());

                long res = userDAO.insertRow(objUser);
                if (res > 0) {
                    dsUser.clear();
                    dsUser.addAll(userDAO.selectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void showDialogEdit(User objUser, int index, Context context) {
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.edit_user_activity);
        EditText edName = dialog.findViewById(R.id.ed_edit_register_name);
        EditText edUserName = dialog.findViewById(R.id.ed_edit_register_user);
        EditText edPass = dialog.findViewById(R.id.ed_edit_register_pass);
        EditText edEmail = dialog.findViewById(R.id.ed_edit_register_email);
        EditText edPhone = dialog.findViewById(R.id.ed_eđit_register_sdt);
        EditText edDate = dialog.findViewById(R.id.ed_edit_register_date);
        Button btnAdd = dialog.findViewById(R.id.btn_save_edit_user);
        edName.setText(objUser.getName());
        edUserName.setText(objUser.getUserName());
        edPass.setText(objUser.getPassWord());
        edEmail.setText(objUser.getEmail());
        edPhone.setText(objUser.getSoDT());
        edDate.setText(objUser.getDate());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objUser.setName(edName.getText().toString());
                objUser.setUserName(edUserName.getText().toString());
                objUser.setPassWord(edPass.getText().toString());
                objUser.setEmail(edEmail.getText().toString());
                objUser.setSoDT(edPhone.getText().toString());
                objUser.setDate(edDate.getText().toString());
                int res = userDAO.updateRow(objUser);
                if (res > 0) {
                    dsUser.set(index, objUser);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}
