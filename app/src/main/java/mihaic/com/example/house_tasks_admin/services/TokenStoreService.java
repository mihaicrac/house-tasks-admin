package mihaic.com.example.house_tasks_admin.services;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import mihaic.com.example.house_tasks_admin.data.Token;

@Singleton
public class TokenStoreService {
    private SharedPreferences sharedPref;
    private Token token;
    private String userId;

    @Inject
    public TokenStoreService(Context context) {
        sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public void save(Token token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("access_token", token.getAccessToken());
        editor.putString("refresh_token", token.getRefreshToken());
        editor.apply();
    }

    public void save(String userId) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user_id", userId);
        editor.apply();
    }

    public void saveFcmToken(String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("fcm_token", token);
        editor.apply();
    }

    public Token getToken() {
        if (token == null) {
            this.token = getTokenFromFileSystem();
        }
        return this.token;
    }

    public String getUserId() {
        if (userId == null) {
            this.userId = sharedPref.getString("user_id", null);
        }
        return this.userId;
    }

    public String getFcmToken() {
        return sharedPref.getString("fcm_token", null);
    }

    public void removeToken() {
        token = null;
        userId = null;
        save(new Token());
        save("");
    }

    private Token getTokenFromFileSystem() {
        Token token = null;
        String accessToken = sharedPref.getString("access_token", null);
        String refreshToken = sharedPref.getString("refresh_token", null);

        if (accessToken != null && refreshToken != null) {
            token = new Token(accessToken, refreshToken);
        }

        return token;
    }

}
