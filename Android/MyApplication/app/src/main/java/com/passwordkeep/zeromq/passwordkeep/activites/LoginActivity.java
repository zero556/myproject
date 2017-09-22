package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SaveObjectUtils;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.UserModel;

import java.util.LinkedList;

public class LoginActivity extends Activity {

    private EditText pass1,pass2,pass3,pass4;
    private SingletonModel singletonModel;
    SaveObjectUtils utils;
    private static final String key=LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        utils=new SaveObjectUtils(this,key);
        singletonModel=SingletonModel.getInstance();


        UserModel userModel=utils.getObject("zero",UserModel.class);
        if(userModel==null) {
            userModel=new UserModel();
            userModel.PasswordKeepList=new LinkedList<PasswordKeepModel>();
        }
        singletonModel.setUser(userModel);



        pass1 = (EditText) findViewById(R.id.editText1);
        pass2 = (EditText) findViewById(R.id.editText2);
        pass3 = (EditText) findViewById(R.id.editText3);
        pass4 = (EditText) findViewById(R.id.editText4);

        pass1.addTextChangedListener(textWatcher);
        pass2.addTextChangedListener(textWatcher);
        pass3.addTextChangedListener(textWatcher);
        pass4.addTextChangedListener(textWatcher);



    }

    private TextWatcher textWatcher = new TextWatcher() {

        // @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        // @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            Log.w("Log", "----" + s);

        }

        // @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == 1) {
                if (pass1.isFocused()) {
                    pass1.clearFocus();
                    pass2.requestFocus();
                } else if (pass2.isFocused()) {
                    pass2.clearFocus();
                    pass3.requestFocus();
                } else if (pass3.isFocused()) {
                    pass3.clearFocus();
                    pass4.requestFocus();
                } else if (pass4.isFocused()) {
                    pass4.clearFocus();

                    int inputPassword=Integer.parseInt(pass1.getText().toString()+pass2.getText().toString()+pass3.getText().toString()+pass4.getText().toString());

                    if(inputPassword==singletonModel.getUserModel().loginPassword)
                    {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(pass4.getWindowToken(), 0);

                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        pass1.setText("");
                        pass2.setText("");
                        pass3.setText("");
                        pass4.setText("");
                    }

                }
            }
        }
    };

}
