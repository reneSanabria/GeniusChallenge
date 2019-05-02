package com.test.geniuschallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.geniuschallenge.models.User;
import com.test.geniuschallenge.remote.APIHelper;
import com.test.geniuschallenge.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity  extends AppCompatActivity {

    UserService userService;
    EditText firstName;
    EditText lastName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_layout);
        firstName = findViewById(R.id.firstName_value);
        lastName = findViewById(R.id.lastName_value);
        btnSave = findViewById(R.id.btnSave);
        userService = APIHelper.getUserService();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setFirstName(firstName.getText().toString());
                u.setLastName(lastName.getText().toString());
                addUser(u);
            }
        });

    }

    public void addUser(User u){
        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddUserActivity.this, "User created successfully with ID: " + response.body().getId(), Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
