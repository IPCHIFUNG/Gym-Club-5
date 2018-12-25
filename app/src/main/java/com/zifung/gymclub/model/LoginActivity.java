package com.zifung.gymclub.model;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zifung.gymclub.R;
import com.zifung.gymclub.api.Service;
import com.zifung.gymclub.api.ServiceFactory;
import com.zifung.gymclub.user.PostUser;
import com.zifung.gymclub.user.UserBean;
import com.zifung.gymclub.user.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    private UserInfo user_info;

    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_info = (UserInfo) getApplication();
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
    }

    public void login_btn_clicked(View view) {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        user_info.setUsername(username);
        user_info.setNickname(password);

        Intent intent = new Intent(  LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        LoginActivity.this.finish();

        /*
        try {
            Service service = ServiceFactory.getService(Service.class);
            Call<UserBean> call = service.login(new PostUser(username, "", password));
            call.enqueue(new Callback<UserBean>() {
                @Override
                public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                    final UserBean body = response.body();
                    switch (body.getStatus()) {
                        case 200:
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            user_info.setUsername(body.getUsername());
                            user_info.setNickname(body.getNickname());
                            Intent intent = new Intent(  LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            LoginActivity.this.finish();
                            break;
                        case 404:
                            Toast.makeText(LoginActivity.this, "用户不存在，请重新输入", Toast.LENGTH_SHORT).show();
                            break;
                        case 401:
                            Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "发生未知状态: " + body.getStatus(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<UserBean> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }*/
    }

    public void sign_up_btn_clicked(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

}
