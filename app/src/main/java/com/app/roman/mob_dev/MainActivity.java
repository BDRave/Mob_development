package com.app.roman.mob_dev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText etName;
    TextView textWithName;
    Button clearText;
    Button sayHello;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World :)");

        etName = findViewById(R.id.etName);
        textWithName = findViewById(R.id.textWithName);
        clearText = findViewById(R.id.clearText);
        sayHello = findViewById(R.id.sayHello);

        sayHello.setOnClickListener(this);
        clearText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clearText:
                etName.getText().clear();
                break;
            case R.id.sayHello:
                textWithName.setText("Hello, " + etName.getText().toString());
                etName.getText().clear();
        }
    }
}
