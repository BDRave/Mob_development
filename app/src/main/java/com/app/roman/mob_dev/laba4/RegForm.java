package com.app.roman.mob_dev.laba4;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.roman.mob_dev.R;

public class RegForm extends AppCompatActivity {

    private EditText firstName, lastName, email;
    private EditText phone, password, confirmPassword;
    private Button submitButton, nextLabButton;
    private String emailText, phoneText, passwordText, confPassText;
    private String firstNameText, lastNameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFields();
    }

    private void saveInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", firstName.getText().toString());
        editor.putString("surname", lastName.getText().toString());
        editor.putString("email", email.getText().toString());
        editor.putString("phone", phone.getText().toString());
        editor.apply();
    }


    public void checkFields() {
        if (!Validations.isValidPassword(passwordText)
                || !Validations.isValidPassword(confPassText)
                || !passwordText.equals(confPassText)
                || !Validations.isValidPhoneNumber(phoneText)
                || !Validations.isValidEmail(emailText)) {

            if (!Validations.isValidFirstName(firstNameText)) {
                firstName.setError(getText(R.string.badFirtsname));
            }
            if (!Validations.isValidLastName(lastNameText)) {
                lastName.setError(getText(R.string.badLastname));
            }
            if (!Validations.isValidEmail(emailText)) {
                email.setError(getText(R.string.badEmail));
            }
            if (!Validations.isValidPhoneNumber(phoneText)) {
                phone.setError(getText(R.string.badPhone));
            }
            if (!Validations.isValidPassword(passwordText)) {
                password.setError(getText(R.string.badPassword));
            }
            if (!Validations.isValidPassword(confPassText)) {
                confirmPassword.setError(getText(R.string.badPassword));
            }
            if (!passwordText.equals(confPassText)) {
                confirmPassword.setError(getText(R.string.badPasswordConfirmation));
            }
        }
        saveInfo();
    }

    public void initFields() {

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText = email.getText().toString();
                passwordText = password.getText().toString();
                firstNameText = firstName.getText().toString();
                lastNameText = lastName.getText().toString();
                passwordText = password.getText().toString();
                confPassText = confirmPassword.getText().toString();
                phoneText = phone.getText().toString();
                checkFields();
            }
        });

        nextLabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(RegForm.this, RegFormDataActivity.class);
                RegForm.this.startActivity(activityChangeIntent);
            }
        });
    }
}