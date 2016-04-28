package com.example.ooosu.quanlysanbong.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.service.UserService;
import com.example.ooosu.quanlysanbong.utils.SessionManager;

/**
 * Created by oOosu on 4/26/2016.
 */
public class ChangePasswordFragment extends Fragment {
    View myView;
    private EditText txtCurrentPassword,txtNewPassword,txtConfirmPassword;
    private Button btnSaveChangePassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.change_password_layout,container,false);
        txtCurrentPassword = (EditText)myView.findViewById(R.id.txtCurrentPassword);
        txtNewPassword = (EditText)myView.findViewById(R.id.txtNewPassword);
        txtConfirmPassword = (EditText)myView.findViewById(R.id.txtConfirmPassword);
        btnSaveChangePassword = (Button) myView.findViewById(R.id.btnSaveChangePassword);

        btnSaveChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    UserService userService = new UserService(getActivity().getApplicationContext());
                    User user = userService.getUser(SessionManager.getSessionManager(getActivity().getApplicationContext()).getUser().getId());
                    if(!user.getPassword().equals(txtCurrentPassword.getText().toString())){
                        txtCurrentPassword.setError("Current password is incorrect !");
                    }else {
                        user.setPassword(txtNewPassword.getText().toString());
                        userService.updateUser(user);
                        Toast.makeText(getActivity().getApplicationContext(),"Updated successfully !",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return myView;
    }
    public boolean validate(){
        boolean valid = true;
        String currentPassword = txtCurrentPassword.getText().toString();
        String password = txtNewPassword.getText().toString();
        String passwordconfirm = txtConfirmPassword.getText().toString();

        if (currentPassword.isEmpty() || currentPassword.length() < 4 || currentPassword.length() > 16) {
            txtCurrentPassword.setError("Between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            txtCurrentPassword.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 16) {
            txtNewPassword.setError("Between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            txtNewPassword.setError(null);
        }

        if (passwordconfirm.isEmpty() || passwordconfirm.length() < 4 || password.length() > 16) {
            txtConfirmPassword.setError("Between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            txtConfirmPassword.setError(null);
        }

        if(!passwordconfirm.equals(password))
        {
            txtConfirmPassword.setError("Confirm password does not match !");
            valid = false;
        }else  txtConfirmPassword.setError(null);
        return valid;
    }
}
