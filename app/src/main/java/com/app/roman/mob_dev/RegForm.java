package com.app.roman.mob_dev;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegForm extends AppCompatActivity implements View.OnClickListener {

    EditText firstName, lastName, email;
    EditText phone, password, confirmPassword;
    Button submitButton;
    SharedPreferences sharedPreferences;
    String emailText, phoneText, passwordText, confPassText;
    String firstNameText, lastNameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_form);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.passwordConfirm);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        emailText = email.getText().toString();
        passwordText = password.getText().toString();
        firstNameText = firstName.getText().toString();
        lastNameText = lastName.getText().toString();
        passwordText = password.getText().toString();
        confPassText = confirmPassword.getText().toString();
        phoneText = phone.getText().toString();


        if (!Validations.isValidPassword(passwordText) || !Validations.isValidPassword(confPassText) || !passwordText.equals(confPassText) ||
                !Validations.isValidPhoneNumber(phoneText) || !Validations.isValidEmail(emailText)) {
            if (!Validations.isValidFirstName(firstNameText)) {
                firstName.setError("Bad First name");
            }
            if (!Validations.isValidLastName(lastNameText)) {
                lastName.setError("Bad Last Name");
            }
            if (!Validations.isValidEmail(emailText)) {
                email.setError("Invalid email");
            }
            if (!Validations.isValidPhoneNumber(phoneText)) {
                phone.setError("Bad phone number");
            }
            if (!Validations.isValidPassword(passwordText)) {
                password.setError("invalid password");
            }
            if (!Validations.isValidPassword(confPassText)) {
                confirmPassword.setError("Do not match");
            }
            if (!passwordText.equals(confPassText)) {
                confirmPassword.setError("Do not match");
            }
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}

