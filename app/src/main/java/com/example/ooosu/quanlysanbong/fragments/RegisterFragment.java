package com.example.ooosu.quanlysanbong.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.model.bean.District;
import com.example.ooosu.quanlysanbong.activities.LoginActivity;
import com.example.ooosu.quanlysanbong.service.DistrictService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oOosu on 4/13/2016.
 */
public class RegisterFragment extends Fragment {
    private TextView txtUsername,txtPassword,txtEmail,txtPhonenumber;
    private Button btnRegister;
    private Spinner spinnerAddress;
    private ActionBar actionBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment_layout,container,false);
        //final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        txtUsername = (TextView) view.findViewById(R.id.txtUser);
        txtPassword = (TextView) view.findViewById(R.id.txtPass);
        txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        txtPhonenumber = (TextView) view.findViewById(R.id.txtPhone);
        spinnerAddress = (Spinner) view.findViewById(R.id.spinnerAddress);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);
//
//        DistrictService districtService = new DistrictService(getContext());
//        List<District> districtList = districtService.getAllDistricts();
//        List<String> list = new ArrayList<String>();
//        if(districtList!=null){
//            for(District district : districtList){
//
//            }
//        }
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
//                    User user = new User(txtUsername.getText().toString(), txtPassword.getText().toString(),
//                            txtEmail.getText().toString(), txtPhonenumber.getText().toString(), txtAddress.getText().toString());
//                    databaseHelper.addUser(user);
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

        if (username.isEmpty() || username.length() < 4 || username.length() > 16) {
            txtUsername.setError("Between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            txtUsername.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 16) {
            txtPassword.setError("Between 4 and 16 alphanumeric characters");
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

        return valid;
    }
}
