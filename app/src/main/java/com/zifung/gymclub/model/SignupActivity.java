package com.zifung.gymclub.model;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends Activity {

    private EditText et_username;
    private EditText et_nickname;
    private EditText et_password;
    private EditText et_password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
    }

    private void initViews() {
        et_username = findViewById(R.id.et_signup_username);
        et_nickname = findViewById(R.id.et_nickname);
        et_password = findViewById(R.id.et_signup_password);
        et_password_confirmation = findViewById(R.id.et_password_confirmation);
    }

    public void signup_btn_clicked(View view) {
        String password = et_password.getText().toString();
        if (!password.equals(et_password_confirmation.getText().toString())) {
            Toast.makeText(SignupActivity.this, "密码不匹配", Toast.LENGTH_SHORT).show();
            return;
        }
        String username = et_username.getText().toString();
        String nickname = et_nickname.getText().toString();
        try {
            Service service = ServiceFactory.getService(Service.class);
            Call<UserBean> call = service.register(new PostUser(username, nickname, password));
            call.enqueue(new Callback<UserBean>() {
                @Override
                public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                    final UserBean body = response.body();
                    switch (body.getStatus()) {
                        case 200:
                            Toast.makeText(SignupActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            SignupActivity.this.finish();
                            break;
                        case 400:
                            Toast.makeText(SignupActivity.this, "用户已存在，请重新输入", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(SignupActivity.this, "发生未知状态", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<UserBean> call, Throwable t) {
                    Toast.makeText(SignupActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void return_btn_clicked(View view) {
        this.finish();
    }


}
