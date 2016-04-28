package com.example.ooosu.quanlysanbong.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.activities.MainActivity;
import com.example.ooosu.quanlysanbong.model.bean.District;
import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.service.DistrictService;
import com.example.ooosu.quanlysanbong.service.UserService;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 12/31/15.
 */
public class ProfileInformationFragment extends Fragment{

    View myView;
    private int districtBefore;
    private Button btnUpdateProfile;
    private EditText txt_profile_username,txt_profile_phonenumber,txt_profile_lastLogin,txt_profile_usertype,txt_profile_verifiedmember,txt_profile_joindate,txt_profile_email;
    private Spinner spinner_profile_district;
    private List<District> districtList = null;
    private String idDistrictString;
    private int idDistrict;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.profile_information_layout, container, false);
        spinner_profile_district = (Spinner) myView.findViewById(R.id.spinner_profile_district);
        txt_profile_username = (EditText) myView.findViewById(R.id.txt_profile_username);
        txt_profile_phonenumber = (EditText) myView.findViewById(R.id.txt_profile_phonenumber);
        txt_profile_lastLogin = (EditText) myView.findViewById(R.id.txt_profile_lastLogin);
        txt_profile_usertype = (EditText) myView.findViewById(R.id.txt_profile_usertype);
        txt_profile_verifiedmember = (EditText) myView.findViewById(R.id.txt_profile_verifiedmember);
        txt_profile_joindate = (EditText) myView.findViewById(R.id.txt_profile_joindate);
        txt_profile_email = (EditText) myView.findViewById(R.id.txt_profile_email);
        btnUpdateProfile = (Button) myView.findViewById(R.id.btnUpdateProfile);

        UserService userService = new UserService(getActivity().getApplicationContext());
        final User user = userService.getUser(((MainActivity) getActivity()).user_id);

        txt_profile_username.setText(user.getUsername());
        txt_profile_phonenumber.setText(user.getPhoneNumber());
        txt_profile_lastLogin.setText(DateUtils.formatDatetime(user.getLastLogin(), DateUtils.FOR_SCREEN));
        String user_type = "";
        if(user.getUserType()==0) user_type = "Admin";
        else user_type = "User";
        txt_profile_usertype.setText(user_type);
        txt_profile_verifiedmember.setText(user.getVerificationCode());
        txt_profile_joindate.setText(DateUtils.formatDatetime(user.getCreatedDate(),DateUtils.FOR_SCREEN));
        txt_profile_email.setText(user.getEmail());

        districtList = new DistrictService(getActivity().getApplicationContext()).getAllDistricts();
        List<String> list = new ArrayList<String>();
        //lay chuoi district cho spinner, chuoi district tu ma~ district user
        if(districtList!=null){
            for(int i=0;i<districtList.size();i++){
                list.add(districtList.get(i).getName());
                if(user.getDistrictId()==districtList.get(i).getId())
                    districtBefore = i;
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>((MainActivity)getActivity(),android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_profile_district.setAdapter(dataAdapter);
        spinner_profile_district.setSelection(districtBefore);
        spinner_profile_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idDistrictString = parent.getItemAtPosition(position).toString();
                if (districtList != null) {
                    for (District district : districtList) {
                        if (idDistrictString.equals(district.getName()))
                            idDistrict = district.getId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    user.setDistrictId(idDistrict);
                    user.setUsername(txt_profile_username.getText().toString());
                    user.setPhoneNumber(txt_profile_phonenumber.getText().toString());
                    user.setEmail(txt_profile_email.getText().toString());
                    user.setUpdatedDate(DateUtils.convertToTimestamp(new Date()));
                    new UserService(getActivity().getApplicationContext()).updateUser(user);
                    Toast.makeText(getActivity().getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    ((MainActivity)getActivity()).fragmentManager.beginTransaction().replace(R.id.content_frame, new MatchesListFragment()).commit();
                }
            }
        });

        return myView;
    }
    public boolean validate(){
        boolean valid = true;
        String username = txt_profile_username.getText().toString();
        String phone = txt_profile_phonenumber.getText().toString();
        String email = txt_profile_email.getText().toString();

        if (username.isEmpty() || username.length() < 4 || username.length() > 16) {
            txt_profile_username.setError("Between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            txt_profile_username.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_profile_email.setError("Enter a valid email address");
            valid = false;
        } else {
            txt_profile_email.setError(null);
        }
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            txt_profile_phonenumber.setError("Enter a valid phone number");
            valid = false;
        }else{
            txt_profile_phonenumber.setError(null);
        }
        return valid;
    }
}
