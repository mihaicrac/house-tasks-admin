package mihaic.com.example.house_tasks_admin.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.users.UserRepository;
import mihaic.com.example.house_tasks_admin.databinding.ActivityRegisterBinding;
import mihaic.com.example.house_tasks_admin.network.users.dto.UserRequest;
import mihaic.com.example.house_tasks_admin.ui.admin.AdminActivity;

public class RegisterActivity extends AppCompatActivity {

    @Inject
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MyApplication) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.submit.setOnClickListener(v -> {
            UserRequest request = UserRequest.fromActivityRegisterBinding(binding);
            userRepository.register(request, result -> {
                String text = result.getData().getAccessToken();
                startHomeActivity();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }, error -> {
                String text = error.getLocalizedMessage();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            });

        });
    }

    private void startHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(homeIntent);
    }
}


