package com.test.geniuschallenge.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.test.geniuschallenge.R;
import com.test.geniuschallenge.models.User;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    private Context context;
    private List<User> users;

    public UserListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup parent){
        if(view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.single_user_layout,parent,false);
        }

        TextView personName = view.findViewById(R.id.nameTextView);
        ImageView avatarImage =  view.findViewById(R.id.user_avatar);
        User theUser = users.get(pos);
        personName.setText(getPersonName(theUser));

        if(theUser.getAvatar() != null && theUser.getAvatar().length()>0)
        {
            Picasso.get().load(theUser.getAvatar()).placeholder(R.drawable.genius_placeholder).into(avatarImage);
        }else {
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.genius_placeholder).into(avatarImage);
        }

        return view;
    }

    private String getPersonName(User user) {
        if (user == null)
            return "";

        return user.getFirstName() + " " + user.getLastName();
    }
}
