package com.example.ooosu.quanlysanbong.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ooosu.quanlysanbong.R;

/**
 * Created by oOosu on 4/27/2016.
 */
public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText editEmailConfirm;
    private Button btnConfirmByEmail;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4CAF50")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        editEmailConfirm = (EditText) findViewById(R.id.editEmailConfirm);
        btnConfirmByEmail = (Button) findViewById(R.id.btnConfirmByEmail);

        btnConfirmByEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
//                    new AlertDialog.Builder(getApplicationContext())
//                        .setTitle("Message")
//                        .setMessage("We have send code for you to "+editEmailConfirm.getText().toString())
//                        .show();
                    AlertDialog alertDialog = new AlertDialog.Builder(ForgotPasswordActivity.this).create();
                    alertDialog.setTitle("Message");
                    alertDialog.setMessage("We have sent code for you to " + editEmailConfirm.getText().toString());
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }

    public boolean validate() {
        boolean valid = true;
        String email = editEmailConfirm.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmailConfirm.setError("Enter a valid email address");
            valid = false;
        } else {
            editEmailConfirm.setError(null);
        }
        return valid;
    }
}
