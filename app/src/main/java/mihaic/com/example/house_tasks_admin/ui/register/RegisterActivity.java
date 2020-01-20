package mihaic.com.example.house_tasks_admin.ui.register;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.Result;
import mihaic.com.example.house_tasks_admin.data.TokenPersister;
import mihaic.com.example.house_tasks_admin.data.UserRepository;
import mihaic.com.example.house_tasks_admin.databinding.ActivityRegisterBinding;
import mihaic.com.example.house_tasks_admin.services.DaggerAdminModuleComponent;
import mihaic.com.example.house_tasks_admin.services.UserRequest;

public class RegisterActivity extends AppCompatActivity {

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userRepository = UserRepository.getInstance(DaggerAdminModuleComponent.create().adminService(), TokenPersister.getInstance(getApplicationContext()));

        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.submit.setOnClickListener(v -> {

            UserRequest request = new UserRequest();
            request.setEmail(binding.email.getText().toString());
            request.setFirstname(binding.firstname.getText().toString());
            request.setLastname(binding.lastname.getText().toString());
            request.setPassword(binding.password.getText().toString());
            request.setUsername(binding.username.getText().toString());

            userRepository.register(request, result -> {
                String text;
                if (result instanceof Result.Success) {
                    text = ((User) ((Result.Success) result).getData()).getId().toString();
                } else {
                    text = ((Result.Error) result).getError().getLocalizedMessage();
                }
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            });

        });
    }
}


