package mihaic.com.example.house_tasks_admin.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.Result;
import mihaic.com.example.house_tasks_admin.databinding.ActivityLoginBinding;
import mihaic.com.example.house_tasks_admin.di.LoginComponent;
import mihaic.com.example.house_tasks_admin.ui.admin.AdminActivity;
import mihaic.com.example.house_tasks_admin.ui.register.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    private LoginComponent loginComponent;

    @Inject
    LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginComponent = ((MyApplication) getApplicationContext()).getAppComponent().loginComponent().create();
        loginComponent.inject(this);
        setContentView(R.layout.activity_login);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        if (loginViewModel.isLoggedId()) {
            startHomeActivity();
            finish();
        }

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            binding.loading.setVisibility(View.GONE);
            if (loginResult instanceof Result.Error) {
                showLoginFailed(((Result.Error) loginResult).getError().getLocalizedMessage());
            } else {
                startHomeActivity();
            }
            setResult(Activity.RESULT_OK);
            finish();
        });

        binding.password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(binding.username.getText().toString(), binding.password.getText().toString());
            }
            return false;
        });

        binding.login.setOnClickListener(v -> {
            binding.loading.setVisibility(View.VISIBLE);
            loginViewModel.login(binding.username.getText().toString(), binding.password.getText().toString());
        });

        binding.register.setOnClickListener(v -> startRegisterActivity());
    }

    private void startHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(homeIntent);
    }

    private void startRegisterActivity() {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(registerIntent);
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
