package com.example.finalprojectk.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.finalprojectk.HomeActivity;
import com.example.finalprojectk.R;
import com.example.finalprojectk.database.Database;
import com.example.finalprojectk.object.Users;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;


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
            if(check){
                Integer checkUser = checkUserAccount(email, password);
                if(checkUser == -1){
                    Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Database.userLog = Database.getUserData(getActivity()).get(checkUser);
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                }
            }
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
            temp = false;
            emailLayout.setError("Field cannot be empty!");
        }
        if(password.length()==0){
            temp = false;
            passwordLayout.setError("Field cannot be empty!");
        }
        return temp;
    }

    private Integer checkUserAccount(String email, String password){
        ArrayList<Users> userList = Database.getUserData(getActivity());
        for (int i=0;i<userList.size();i++) {
            if(userList.get(i).getUserEmail().equals(email)){
                if(userList.get(i).getUserPassword().equals(password)){
                    return i;
                }
                else {
                    passwordLayout.setError("Password is incorrect!");
                    return -1;
                }
            }
        }
        emailLayout.setError("Your email is not registered!");
        return -1;
    }

}