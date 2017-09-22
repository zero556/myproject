package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.adapter.PasswordShowAdapter;
import com.passwordkeep.zeromq.passwordkeep.activites.adapter.PasswordTypeAdapter;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordTypeModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.UserModel;

import java.util.LinkedList;
import java.util.List;

public class ShowPasswordKeepActivity extends TitleActivity {

    private List<PasswordKeepModel> pData=null;
    private Context pContext;
    private PasswordShowAdapter passwordShowAdapter=null;
    private ListView plistView;
    private SingletonModel singletonModel;
    private String type =null;
    private int iconIndex=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_password_keep);
        singletonModel= SingletonModel.getInstance();
        setTitle("");
        showBackwardView(R.string.fa_chevron_circle_left,true);
        Button mBackwardButton = (Button) findViewById(R.id.button_backward);
        mBackwardButton.setTextSize(25);
        mBackwardButton.setBackground(null);

        showForwardView(R.string.fa_plus_square,true);
        Button mForwardButton = (Button) findViewById(R.id.button_forward);
        mForwardButton.setTextSize(25);

        Bundle bundle = this.getIntent().getExtras();


        if(bundle!=null)
        {
            type = bundle.getString("title");
            iconIndex=bundle.getInt("iconIndex");
        }

        if(type!=null) {

            UserModel userModel=singletonModel.getUserModel();
            pData = new LinkedList<PasswordKeepModel>();
            for(PasswordKeepModel p : userModel.PasswordKeepList) {
                if(p.getPasswordType().getTitle().equals(type))
                {
                    pData.add(p);
                }
            }


            pContext = ShowPasswordKeepActivity.this;
            plistView = (ListView) findViewById(R.id.listViewShowPassword);



//            PasswordKeepModel passwordKeepModel = new PasswordKeepModel("ss", "ss", "ss", "ss", new PasswordTypeModel("WebSite", R.string.fa_chrome, 0));
//            PasswordKeepModel passwordKeepModel1 = new PasswordKeepModel("ss", "ss", "ss", "ss", new PasswordTypeModel("WebSite", R.string.fa_chrome, 0));
//            pData.add(passwordKeepModel);
//            pData.add(passwordKeepModel1);

            passwordShowAdapter = new PasswordShowAdapter((LinkedList<PasswordKeepModel>) pData, pContext);
            plistView.setAdapter(passwordShowAdapter);
        }
    }
}
