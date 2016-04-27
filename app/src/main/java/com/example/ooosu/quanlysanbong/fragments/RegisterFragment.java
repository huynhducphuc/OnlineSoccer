package com.example.ooosu.quanlysanbong.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.activities.LoginActivity;
import com.example.ooosu.quanlysanbong.database.DatabaseHelper;

/**
 * Created by oOosu on 4/13/2016.
 */
public class RegisterFragment extends Fragment {
    private TextView txtUsername,txtPassword,txtEmail,txtPhonenumber,txtAddress;
    private Button btnRegister;
    private ActionBar actionBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment_layout,container,false);
        final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        txtUsername = (TextView) view.findViewById(R.id.txtUser);
        txtPassword = (TextView) view.findViewById(R.id.txtPass);
        txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        txtPhonenumber = (TextView) view.findViewById(R.id.txtPhone);
        txtAddress = (TextView) view.findViewById(R.id.txtAddress);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    User user = new User(txtUsername.getText().toString(), txtPassword.getText().toString(),
                            txtEmail.getText().toString(), txtPhonenumber.getText().toString(), txtAddress.getText().toString());
                    databaseHelper.addUser(user);
                    ((LoginActivity) getActivity()).changeViewPagePosition(0);
                }
            }
        });
        return view;
    }

    public boolean validate() {
        boolean valid = true;

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhonenumber.getText().toString();
        String address = txtAddress.getText().toString();

        if (username.isEmpty() || username.length() < 4 || username.length() > 16) {
            txtUsername.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            txtUsername.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 16) {
            txtPassword.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            txtPassword.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            txtEmail.setError(null);
        }
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            txtPhonenumber.setError("Enter a valid phone number");
            valid = false;
        }else{
            txtPhonenumber.setError(null);
        }

        if (address.isEmpty()) {
            txtAddress.setError("Enter your address!");
            valid = false;
        } else {
            txtAddress.setError(null);
        }

        return valid;
    }
}
