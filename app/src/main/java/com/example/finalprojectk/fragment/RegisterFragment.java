package com.example.finalprojectk.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.finalprojectk.R;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterFragment extends Fragment {
    EditText regUsername, regEmail, regPhone, regPassword;
    Button btnRegister;
    String email, username, phone, password;
    TextInputLayout emailLayout, usernameLayout, phoneLayout, passwordLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        regEmail = view.findViewById(R.id.inputEmail);
        regUsername = view.findViewById(R.id.inputUsername);
        regPhone = view.findViewById(R.id.inputPhone);
        regPassword = view.findViewById(R.id.inputPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        emailLayout = view.findViewById(R.id.etEmail);
        usernameLayout = view.findViewById(R.id.etUsername);
        phoneLayout = view.findViewById(R.id.etPhone);
        passwordLayout = view.findViewById(R.id.etPassword);
        email = regEmail.getText().toString();
        username = regUsername.getText().toString();
        phone = regPhone.getText().toString();
        password = regPassword.getText().toString();
        btnRegister.setOnClickListener(v -> {
            checkInput(email, username, phone, password);
        });



    }

    boolean checkInput(String email, String username, String phone, String password){
        boolean temp = true;
        if(email.length()==0){
            temp = false;
            emailLayout.setError("Field cannot be empty");
        }
        if(username.length()==0){
            temp = false;
            usernameLayout.setError("Field cannot be empty!");
        }
        if(phone.length()==0){
            temp = false;
            phoneLayout.setError("Field cannot be empty!");
        }
        if(password.length()==0){
            temp = false;
            passwordLayout.setError("Field cannot be empty!");
        }
        return temp;
    }

}