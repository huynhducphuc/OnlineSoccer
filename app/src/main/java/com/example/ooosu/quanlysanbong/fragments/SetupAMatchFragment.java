package com.example.ooosu.quanlysanbong.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.R;
import com.example.ooosu.quanlysanbong.activities.MainActivity;
import com.example.ooosu.quanlysanbong.model.bean.Field;
import com.example.ooosu.quanlysanbong.model.bean.Match;
import com.example.ooosu.quanlysanbong.service.FieldService;
import com.example.ooosu.quanlysanbong.service.MatchService;
import com.example.ooosu.quanlysanbong.utils.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 12/31/15.
 */
public class SetupAMatchFragment extends Fragment{

    View myView;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private TextView txtstartTimeCreate,txtendTimeCreate,tvErrorDateTime;
    private Spinner spinner;
    private EditText txtMaximumPlayer,txtPrice;
    private Button btnSelectStart,btnSelectEnd,btnCreateMatch;
    private List<Field> fieldList = null;
    private String idFieldString = "Field Name";
    private int idField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.setup_a_match_layout, container, false);
        spinner = (Spinner) myView.findViewById(R.id.spinnerFieldIdCreate);
        txtMaximumPlayer = (EditText) myView.findViewById(R.id.txtMaximumPlayerCreate);
        txtPrice = (EditText) myView.findViewById(R.id.txtPriceCreate);
        tvErrorDateTime = (TextView) myView.findViewById(R.id.tvErrorDateTime);
        txtstartTimeCreate= (TextView)myView.findViewById(R.id.txtstartTimeCreate);
        txtendTimeCreate = (TextView)myView.findViewById(R.id.txtendTimeCreate);
        btnSelectStart = (Button) myView.findViewById(R.id.btnSelectStart);
        btnSelectEnd = (Button) myView.findViewById(R.id.btnSelectEnd);
        btnCreateMatch = (Button) myView.findViewById(R.id.btnCreateMatch);
        // Spinner Drop down elements
        fieldList = new FieldService(getActivity().getApplicationContext()).getAllFields();
        List<String> list = new ArrayList<String>();
        if(fieldList!=null){
            for(Field field : fieldList){
                list.add(field.getName());
            }
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>((MainActivity)getActivity(), android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                idFieldString = parent.getItemAtPosition(position).toString();
                if (fieldList != null) {
                    for (Field field : fieldList) {
                        if (idFieldString.equals(field.getName()))
                            idField = field.getId();
                    }
                }
                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSelectStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog((MainActivity) getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                txtstartTimeCreate.setText(txtstartTimeCreate.getText().toString() + " " + hourOfDay + ":" + minute + ":00");
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

                DatePickerDialog datePickerDialog = new DatePickerDialog((MainActivity) getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtstartTimeCreate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        btnSelectEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog((MainActivity) getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                txtendTimeCreate.setText(txtendTimeCreate.getText().toString() + " " + hourOfDay + ":" + minute + ":00");
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

                DatePickerDialog datePickerDialog = new DatePickerDialog((MainActivity) getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtendTimeCreate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnCreateMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    int id_user = ((MainActivity)getActivity()).user_id;
                    Timestamp current = DateUtils.convertToTimestamp(new Date());
                    Random random = new Random();
                    int verificationCode = random.nextInt(999999 - 000000 + 1) + 000000;
                    new MatchService(getActivity().getApplicationContext()).addMatch(new Match(idField,id_user,1,Integer.parseInt(txtMaximumPlayer.getText().toString()),Integer.parseInt(txtPrice.getText().toString()), DateUtils.convertToTimestamp(txtstartTimeCreate.getText().toString(),DateUtils.FOR_SCREEN),DateUtils.convertToTimestamp(txtendTimeCreate.getText().toString(),DateUtils.FOR_SCREEN),false,String.valueOf(verificationCode),current,null,null));
                    Toast.makeText(getActivity().getApplicationContext(), "Tạo trận đấu thành công", Toast.LENGTH_LONG).show();
                    ((MainActivity) getActivity()).chooise = R.id.nav_mymatches_layout;
                    ((MainActivity)getActivity()).fragmentManager.beginTransaction().replace(R.id.content_frame, new MyMatchesFragment()).commit();
                    ((MainActivity)getActivity()).actionBar.setTitle("My matches");
                    ((MainActivity)getActivity()).menu.findItem(R.id.action_search).setVisible(true);
                }
            }
        });
        return myView;
    }
    public boolean validate(){
        boolean valid = true;
        String maxPlayer = txtMaximumPlayer.getText().toString();
        String price = txtPrice.getText().toString();
        String startTime = txtstartTimeCreate.getText().toString();
        String endTime = txtendTimeCreate.getText().toString();
        if (maxPlayer.isEmpty()) {
            txtMaximumPlayer.setError("Enter somthing here!");
            valid = false;
        }else{
            txtMaximumPlayer.setError(null);
        }
        if (price.isEmpty()) {
            txtPrice.setError("Enter somthing here!");
            valid = false;
        }else{
            txtPrice.setError(null);
        }
        if(startTime.length()<=10||endTime.length()<=10){
            tvErrorDateTime.setText("Select both date and time");
            valid = false;
        }else if(DateUtils.convertToTimestamp(startTime,DateUtils.FOR_SCREEN).after(DateUtils.convertToTimestamp(endTime,DateUtils.FOR_SCREEN))
                ||DateUtils.convertToTimestamp(startTime,DateUtils.FOR_SCREEN).before(DateUtils.convertToTimestamp(new Date()))) {
                tvErrorDateTime.setText("Datetime incorrect");
                valid = false;
            }else {
                tvErrorDateTime.setText("");
            }
        return valid;
    }
}
