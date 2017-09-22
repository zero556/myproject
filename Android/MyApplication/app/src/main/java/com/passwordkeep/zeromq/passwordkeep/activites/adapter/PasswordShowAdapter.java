package com.passwordkeep.zeromq.passwordkeep.activites.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordTypeModel;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by VMLDEV6 on 22/09/2017.
 */

public class PasswordShowAdapter extends BaseAdapter {
    private LinkedList<PasswordKeepModel> pData;
    private Context pContext;
    // private String  pageType;

    public PasswordShowAdapter(LinkedList<PasswordKeepModel> pData, Context pContext)
    {
        this.pContext=pContext;
        this.pData=pData;
        //this.pageType=pageType;
    }

    public int getCount()
    {
        return pData.size();
    }

    public Objects getItem(int position)
    {
        return null;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View converView, ViewGroup parent)
    {

        Typeface font = Typeface.createFromAsset(pContext.getAssets(), "fontawesome-webfont.ttf");

        converView= LayoutInflater.from(pContext).inflate(R.layout.list_show_item,parent,false);

        TextView textType=(TextView)converView.findViewById(R.id.textViewType);
        TextView textTitle=(TextView)converView.findViewById(R.id.textViewTitle);
        TextView textAccount=(TextView)converView.findViewById(R.id.textViewAccount);
        TextView textPassword=(TextView)converView.findViewById(R.id.textViewPassword);

        textType.setText(pData.get(position).getPasswordType().getIcon());
        textTitle.setText(pData.get(position).getTitle());
        textAccount.setText(pData.get(position).getAccount());
        textPassword.setText("******");


        textType.setTypeface(font);
        return converView;

    }
}
