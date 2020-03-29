package mihaic.com.example.house_tasks_admin.network.users.dto;

import mihaic.com.example.house_tasks_admin.databinding.ActivityRegisterBinding;

public class UserRequest {
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public UserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserRequest fromActivityRegisterBinding(ActivityRegisterBinding binding){
        UserRequest request = new UserRequest();
        request.setEmail(binding.email.getText().toString());
        request.setFirstName(binding.firstname.getText().toString());
        request.setLastName(binding.lastname.getText().toString());
        request.setPassword(binding.password.getText().toString());
        request.setUsername(binding.username.getText().toString());
        return request;
    }
}
