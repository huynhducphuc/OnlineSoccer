package com.example.ooosu.quanlysanbong.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.activities.ForgotPasswordActivity;
import com.example.ooosu.quanlysanbong.activities.LoginActivity;
import com.example.ooosu.quanlysanbong.activities.MainActivity;
import com.example.ooosu.quanlysanbong.dbhelper.DatabaseHelper;
import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.service.UserService;

public class LoginFragment extends Fragment {

    private EditText txtUsername, txtPassword;
    private Button btnLogin;
    private TextView tvCreateAccount,tvForgotPassword;
    private Context context = null;
    public LoginFragment(){
        setRetainInstance(true);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment_layout,container,false);
        final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        txtUsername = (EditText) view.findViewById(R.id.txtUsername);
        txtPassword = (EditText) view.findViewById(R.id.txtPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        tvCreateAccount = (TextView) view.findViewById(R.id.tvCreateAccount);
        tvForgotPassword = (TextView) view.findViewById(R.id.tvForgotPassword);
        for(User user :new UserService(getContext()).getAllUsers()){
            Log.d("user: ",""+user.toString());
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validate()){
//                    String storedPassword = databaseHelper.getSinlgeEntry(txtUsername.getText().toString());
//                    String password = txtPassword.getText().toString();
//                    if (password.equals(storedPassword))
                        onLoginSuccess();
//                    else onLoginFailed();
                //}
            }
        });
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).changeViewPagePosition(1);
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }





    public boolean validate() {
        boolean valid = true;

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();


        if (username.isEmpty() || username.length() < 4 || username.length() > 16) {
            txtUsername.setError("Between 4 and 16 alphanumeric characters!");
            valid = false;
        } else {
            txtUsername.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 16) {
             txtPassword.setError("Between 4 and 16 alphanumeric characters!");
            valid = false;
        } else {
            txtPassword.setError(null);
        }

        return valid;
    }
    public void onLoginFailed() {
        Toast.makeText(getContext(), "Đăng nhập thấ bại", Toast.LENGTH_LONG).show();

        btnLogin.setEnabled(true);
    }
    public void onLoginSuccess() {
        Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
        Bundle bundle = new Bundle();
        int user_id = 1;
        bundle.putInt("user_id",user_id);
        Intent intent = new Intent(getContext(),MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();
    }

}
