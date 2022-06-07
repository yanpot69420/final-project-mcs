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


public class LoginFragment extends Fragment {
    EditText logEmail, logPassword;
    Button btnLogin;
    TextInputLayout logEmailLayout, logPasswordLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logEmail = view.findViewById(R.id.inputEmailLog);
        logPassword = view.findViewById(R.id.inputPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        logEmailLayout = view.findViewById(R.id.etEmailLog);
        logPasswordLayout = view.findViewById(R.id.etPasswordLog);
        String email = logEmail.getText().toString();
        String password = logEmail.getText().toString();
        btnLogin.setOnClickListener(v -> {
            checkInput(email, password);
        });

    }

    Boolean checkInput(String name, String password){
        boolean temp = true;
        if(name.length()==0){
            temp = false;
            logEmailLayout.setError("Field cannot be empty!");
        }
        if(password.length()==0){
            temp = false;
            logPasswordLayout.setError("Field cannot be empty!");
        }
        return temp;
    }
}