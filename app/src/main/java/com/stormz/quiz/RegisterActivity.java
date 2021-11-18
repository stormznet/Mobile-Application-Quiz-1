package com.stormz.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.stormz.quiz.database.ApplicationDatabase;
import com.stormz.quiz.instance.UserInstance;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RegisterActivity<UserDone> extends AppCompatActivity {

    EditText nameTxt, emailTxt, passTxt, addressTxt, phoneNumber;
    Button datePicker, btnRegister;
    RadioGroup genderRadio;
    RadioButton selectedRadioButton;
    CheckBox cbShow, agreeCheck;
    TextView loginNow;
    private int yearText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        datePickerset();

        //Import
        //EditText
        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        passTxt = findViewById(R.id.passTxt);
        addressTxt = findViewById(R.id.addressTxt);

        //Number
        phoneNumber = findViewById(R.id.phoneNumber);


        //DOB
        datePicker = findViewById(R.id.datePicker);

        //Radio
        genderRadio = findViewById(R.id.genderRadio);


        //Button
        loginNow = findViewById(R.id.loginNow);
        btnRegister = findViewById(R.id.btnRegister);
        datePicker = findViewById(R.id.datePicker);


        //CheckBox
        agreeCheck = findViewById(R.id.agreeCheck);
        cbShow = findViewById(R.id.showPass);


        //Show atau hide password
        cbShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbShow.isChecked()){

                    passTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {

                    passTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //Intent for login now:
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);

                finish();

            }
        });

        //Validasi button register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkDataEntered();
            }
        });

    }

    void datePickerset(){
        datePicker = findViewById(R.id.datePicker);
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datePicker.setText(dayOfMonth + "-" + month + "-" + year);

                yearText = year;
            }
        };

        //DatePicker select
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int date = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, listener, year, month, date);
                dialog.show();

            }
        });

    }


    //CheckAge 17 years old validation
    private boolean checkAge(int year) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Integer yearNow = Integer.parseInt(dateFormat.format(new Date()));

        return (yearNow - year) < 17;
    }

    //Empty text validation
    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    //Number validation
    private boolean isNumber(String phoneNumber) {
        try {
            BigInteger amount = BigInteger.valueOf(Long.parseLong(phoneNumber));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Email Validation
    private boolean isEmailD(EditText text){

        int aCounter = 0;
        boolean checkerB = false;
        for(int i = 0; i < text.length(); i++) {
            if(text.getText().toString().charAt(i) == '@') {
                aCounter++;
            } else if(text.getText().toString().charAt(i) == '.') {
                checkerB = true;
            }
        }
        int aIndex = text.getText().toString().indexOf('@');
        int dIndex = text.getText().toString().indexOf('.');
        return (aCounter != 1) || !checkerB || ((aIndex == dIndex + 1) || (aIndex == dIndex - 1));
    }

    //Validation when data entered
    private void checkDataEntered() {

        int isSelected = genderRadio.getCheckedRadioButtonId();
        boolean isChecked = true;
        //Name
        if (isEmpty(nameTxt)) {
            nameTxt.setError("Name must be filled!");
            isChecked = false;
        }

        if (nameTxt.length() < 4){
            nameTxt.setError("Name must be longer than 4 characters!");
            isChecked = false;
        }

        //Email
        if (isEmpty(emailTxt)) {
            emailTxt.setError("Email must be filled!");
            isChecked = false;
        }

        if (isEmailD(emailTxt)) {
            emailTxt.setError("Enter valid email!");
            isChecked = false;
        }

        //Password
        if (isEmpty(passTxt)) {
            passTxt.setError("Password must be filled!");
            isChecked = false;
        }
        if (passTxt.length() < 4) {
            passTxt.setError("Password must be longer than 4 characters!");
            isChecked = false;
        }

        //Gender
        if (isSelected == -1) {
            Toast selected = Toast.makeText(this, "Please select gender!", Toast.LENGTH_SHORT);
            selected.show();
            isChecked = false;
        }

        //Address
        if (isEmpty(addressTxt)) {
            addressTxt.setError("Address must be filled!");
            isChecked = false;
        }

        if (addressTxt.length() < 8) {
            addressTxt.setError("Address must longer than 8 characters!");
            isChecked = false;

        } else{
            if (!addressTxt.getText().toString().endsWith("street")) {
                addressTxt.setError("Address must ends with street!");
                isChecked = false;
            }
        }

        //Phone Number
        if (isEmpty(phoneNumber)) {
            phoneNumber.setError("Phone number must be filled!");
            isChecked = false;
        } else{
            String phone = phoneNumber.getText().toString();
            if (!isNumber(phone)){
                phoneNumber.setError("Phone number must be numeric!");
                isChecked = false;
            }
        }

        //DOB
        String date = datePicker.getText().toString();
        if (date.length() == 0){
            Toast agree = Toast.makeText(this, "You must fill DOB!", Toast.LENGTH_SHORT);
            agree.show();
            isChecked = false;
        }

        if (checkAge(yearText)){
            Toast agree = Toast.makeText(this, "You must older than 17 years old!", Toast.LENGTH_SHORT);
            agree.show();
            Log.d("DEBUG CHECKAGE:", String.valueOf(yearText));
            isChecked = false;
        }

        //Agree
        if (!agreeCheck.isChecked()){
            Toast agree = Toast.makeText(this, "You must agree the term and condition!", Toast.LENGTH_SHORT);
            agree.show();
            isChecked = false;
        }

        //Check Email
        if (isChecked){
            String nameArr = nameTxt.getText().toString();

            String emailArr = emailTxt.getText().toString();
            String passArr = passTxt.getText().toString();
            int selectedGroup = genderRadio.getCheckedRadioButtonId();
            selectedRadioButton = findViewById(selectedGroup);

            String genderArr = selectedRadioButton.getText().toString();
            String dobArr = datePicker.getText().toString();


            //Generate ID: US + 3 random number
            Random r = new Random();
            String id = "US" + (r.nextInt(900) + 100);

            //Save to database ArrayList:
            ApplicationDatabase.getInstance(RegisterActivity.this).getUsers().add(new UserInstance(id, nameArr, emailArr, passArr, genderArr, passArr, dobArr));
            Log.d("DEBUG!", ApplicationDatabase.getInstance(RegisterActivity.this).getUsers().get(0).getEmail());

            //Redirect to Login:
            Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(login);

        }

    }
}