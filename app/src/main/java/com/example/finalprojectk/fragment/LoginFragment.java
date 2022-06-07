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
    String email, password;
    TextInputLayout emailLayout, passwordLayout;
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
        initView(view);

        btnLogin.setOnClickListener(v -> {
            resetError();
            initVar();
            Boolean check = checkError(email, password);
        });

    }

    private void initVar() {
        email = logEmail.getText().toString();
        password = logPassword.getText().toString();
    }

    private void initView(@NonNull View view) {
        logEmail = view.findViewById(R.id.inputEmailLog);
        logPassword = view.findViewById(R.id.inputPasswordLog);
        btnLogin = view.findViewById(R.id.btnLogin);
        emailLayout = view.findViewById(R.id.etEmailLog);
        passwordLayout = view.findViewById(R.id.etPasswordLog);
    }

    private void resetError(){
        emailLayout.setError(null);
        passwordLayout.setError(null);
    }

    private Boolean checkError(String email, String password){
        boolean temp = true;
        if(!email.endsWith(".com")){
            temp = false;
            emailLayout.setError("Email must ends with '.com'");
        }
        if(email.length()==0){
            emailLayout.setError("Field cannot be empty!");
            temp = false;
        }
        if(password.length()==0){
            temp = false;
            passwordLayout.setError("Field cannot be empty!");
        }
        return temp;
    }
}