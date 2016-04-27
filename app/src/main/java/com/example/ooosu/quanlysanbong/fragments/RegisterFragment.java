package com.example.ooosu.quanlysanbong.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.model.bean.District;
import com.example.ooosu.quanlysanbong.activities.LoginActivity;
import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.service.DistrictService;
import com.example.ooosu.quanlysanbong.service.UserService;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by oOosu on 4/13/2016.
 */
public class RegisterFragment extends Fragment {
    private TextView txtUsername,txtPassword,txtEmail,txtPhonenumber;
    private Button btnRegister;
    private Spinner spinnerAddress;
    private int idDistrict;
    private String idDistrictString;
    private List<District> districtList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment_layout,container,false);

        txtUsername = (TextView) view.findViewById(R.id.txtUser);
        txtPassword = (TextView) view.findViewById(R.id.txtPass);
        txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        txtPhonenumber = (TextView) view.findViewById(R.id.txtPhone);
        spinnerAddress = (Spinner) view.findViewById(R.id.spinnerAddress);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        DistrictService districtService = new DistrictService(getContext());
//        CityService cityService = new CityService(getContext());
//        cityService.addCity(new City("Da Nang"));
//        cityService.addCity(new City("Quang Nam"));
//        cityService.addCity(new City("Quang Ngai"));
//        cityService.addCity(new City("Binh Dinh"));
//        districtService.addDistrict(new District("Ngu Hanh Son", 1));
//        districtService.addDistrict(new District("Son Tra",1));
//        districtService.addDistrict(new District("Hoa Vang",1));
//        districtService.addDistrict(new District("Thanh Khe",1));
//        districtService.addDistrict(new District("Cam Le",1));
//        districtService.addDistrict(new District("Hai Chau",1));
//        districtService.addDistrict(new District("Lien Chieu",1));
        districtList = districtService.getAllDistricts();
        List<String> list = new ArrayList<String>();
        if(districtList!=null){
            for(District district : districtList){
                list.add(district.getName());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddress.setAdapter(dataAdapter);

        spinnerAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idDistrictString = parent.getItemAtPosition(position).toString();
                if(districtList!=null){
                    for(District district : districtList){
                        if(idDistrictString.equals(district.getName()))
                            idDistrict = district.getId();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Chua chon gi ca", Toast.LENGTH_LONG).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Timestamp current = DateUtils.convertToTimestamp(new Date());
                    Random random = new Random();
                    int verificationCode = random.nextInt(999999 - 000000 + 1) + 000000;
                    User user = new User(txtUsername.getText().toString(), txtPassword.getText().toString(),
                            txtEmail.getText().toString(), txtPhonenumber.getText().toString(), 1, idDistrict, 1,current,false,String.valueOf(verificationCode),current,null,null);
                    new UserService(getContext()).addUser(user);

                    ((LoginActivity) getActivity()).changeViewPagePosition(0);
                    Toast.makeText(getContext(),"Đăng ký thành công",Toast.LENGTH_LONG);
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
