package mihaic.com.example.house_tasks_admin.ui.login;

import androidx.annotation.Nullable;

import mihaic.com.example.house_tasks_admin.data.Token;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private Token success;
    @Nullable
    private Exception error;

    LoginResult(@Nullable Exception error) {
        this.error = error;
    }

    LoginResult(@Nullable Token success) {
        this.success = success;
    }

    @Nullable
    Token getSuccess() {
        return success;
    }

    @Nullable
    Throwable getError() {
        return error;
    }
}
