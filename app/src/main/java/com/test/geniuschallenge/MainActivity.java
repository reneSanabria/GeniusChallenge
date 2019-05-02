package com.test.geniuschallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.test.geniuschallenge.adapters.UserListAdapter;
import com.test.geniuschallenge.models.User;
import com.test.geniuschallenge.models.UsersResponse;
import com.test.geniuschallenge.remote.APIHelper;
import com.test.geniuschallenge.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView userList;
    UserService userService;

    List<User> list = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = findViewById(R.id.user_list);
        userService = APIHelper.getUserService();
        getUsersList();

    }

    public void getUsersList(){
        final LinearLayout linlaHeaderProgress = findViewById(R.id.linlaHeaderProgress);
        linlaHeaderProgress.setVisibility(View.VISIBLE);

        Call<UsersResponse> call = userService.getUsersResponse();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if(response.isSuccessful()){
                    list = response.body().getData();
                    userList.setAdapter(new UserListAdapter(MainActivity.this, R.layout.activity_main, list));
                    linlaHeaderProgress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                linlaHeaderProgress.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Unable to retreat list of Users", Toast.LENGTH_LONG).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_user:
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsersList();
    }
}
