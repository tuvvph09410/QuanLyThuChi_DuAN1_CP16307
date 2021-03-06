package com.dung.quanlythuchi.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dung.quanlythuchi.DAO.UserDAO;
import com.dung.quanlythuchi.DTO.User;
import com.dung.quanlythuchi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        txtSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setIcon(R.drawable.ic_baseline_person_24);
                builder.setTitle("Th??ng Tin User");
                builder.setMessage("Th??ng tin chi ti???t: " + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName()
                        + "\n" + "Email: " + userDTO.getEmail() + "\n" + "Phone: " + userDTO.getSoDT() + "\n" + "Date: " + simpleDateFormat.format(userDTO.getDate())
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
                builder.setTitle("Th??ng Tin User");
                builder.setMessage("Th??ng tin chi ti???t: " + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName()
                        + "\n" + "Email: " + userDTO.getEmail() + "\n" + "Phone: " + userDTO.getSoDT() + "\n" + "Date: " + simpleDateFormat.format(userDTO.getDate())
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
                builder.setTitle("Th??ng Tin User");
                builder.setMessage("Th??ng tin chi ti???t: " + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName()
                        + "\n" + "Email: " + userDTO.getEmail() + "\n" + "Phone: " + userDTO.getSoDT() + "\n" + "Date: " + simpleDateFormat.format(userDTO.getDate())
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
                builder.setTitle("X??a User");
                builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
                builder.setMessage("B???n c?? mu???n x??a kh??ng?" + "\n" + "Name: " + userDTO.getName() + "\n" + "UserName: " + userDTO.getUserName());
                builder.setPositiveButton("X??a", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = userDAO.deleteRow(userDTO);
                        if (res > 0) {
                            dsUser.remove(_index);
                            notifyDataSetChanged();
                            Toast.makeText(viewGroup.getContext(), "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(viewGroup.getContext(), "X??a th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("H???y", new DialogInterface.OnClickListener() {
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
        EditText edEmail = dialog.findViewById(R.id.ed_add_register_email);
        EditText edSDT = dialog.findViewById(R.id.ed_add_register_sdt);
        EditText edDate = dialog.findViewById(R.id.ed_add_register_date);
        Button btnAdd = dialog.findViewById(R.id.btn_save_add_register);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                edDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (edName.getText().length() > 0 && edUserName.getText().length() > 0 && edPass.getText().length() > 0 && edSDT.getText().length() > 0 && edEmail.getText().length() > 0) {
                    if (edDate.getText().length() != 0) {
                        if (edEmail.getText().toString().trim().matches(emailPattern)) {
                            try {
                                int sdt = Integer.parseInt(edSDT.getText().toString());
                                edSDT.setTextColor(Color.WHITE);
                                User objUser = new User();
                                objUser.setName(edName.getText().toString());
                                objUser.setUserName(edUserName.getText().toString());
                                objUser.setPassWord(edPass.getText().toString());
                                objUser.setEmail(edEmail.getText().toString());
                                objUser.setSoDT(sdt);
                                try {
                                    objUser.setDate(simpleDateFormat.parse(edDate.getText().toString()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                long res = userDAO.insertRow(objUser);
                                if (res > 0) {
                                    dsUser.clear();
                                    dsUser.addAll(userDAO.selectAll());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "????ng K?? Th??nh C??ng", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(context, "????ng K?? Th???t B???i", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                edSDT.setTextColor(Color.RED);
                            }
                        } else {
                            Toast.makeText(context, "Sai ?????nh d???ng, Vui l??ng nh???p l???i gmail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Vui l??ng ch???n ng??y sinh", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Vui l??ng nh???p ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
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
        EditText edPhone = dialog.findViewById(R.id.ed_e??it_register_sdt);
        EditText edDate = dialog.findViewById(R.id.ed_edit_register_date);
        Button btnAdd = dialog.findViewById(R.id.btn_save_edit_user);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, month);
                edDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        edName.setText(objUser.getName());
        edUserName.setText(objUser.getUserName());
        edPass.setText(objUser.getPassWord());
        edEmail.setText(objUser.getEmail());
        edPhone.setText(String.valueOf(objUser.getSoDT()));
        edDate.setText(simpleDateFormat.format(objUser.getDate()));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (edName.getText().length() > 0 && edUserName.getText().length() > 0 && edPass.getText().length() > 0 && edPhone.getText().length() > 0 && edEmail.getText().length() > 0) {
                    if (edDate.getText().length() != 0) {
                        if (edEmail.getText().toString().trim().matches(emailPattern)) {
                            try {
                                int phone = Integer.parseInt(edPhone.getText().toString());
                                edPhone.setTextColor(Color.WHITE);
                                objUser.setName(edName.getText().toString());
                                objUser.setUserName(edUserName.getText().toString());
                                objUser.setPassWord(edPass.getText().toString());
                                objUser.setEmail(edEmail.getText().toString());
                                objUser.setSoDT(phone);
                                try {
                                    objUser.setDate(simpleDateFormat.parse(edDate.getText().toString()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                int res = userDAO.updateRow(objUser);
                                if (res > 0) {
                                    dsUser.set(index, objUser);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(context, "S???a th???t b???i", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                edPhone.setTextColor(Color.RED);
                            }
                        } else {
                            Toast.makeText(context, "Sai ?????nh d???ng, Vui l??ng nh???p l???i gmail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Vui l??ng ch???n ng??y sinh", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Vui l??ng nh???p ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

}
