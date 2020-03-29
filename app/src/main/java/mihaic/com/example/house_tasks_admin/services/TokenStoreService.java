package mihaic.com.example.house_tasks_admin.services;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import mihaic.com.example.house_tasks_admin.data.Token;

@Singleton
public class TokenStoreService {
    private SharedPreferences sharedPref;

    @Inject
    public TokenStoreService(Context context) {
        sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public void save(Token token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("access_token", token.getAccessToken());
        editor.putString("refresh_token", token.getRefreshToken());
        editor.commit();
    }

    public Token getToken() {
        Token token = null;
        String accessToken = sharedPref.getString("access_token", null);
        String refresh_token = sharedPref.getString("refresh_token", null);

        if (accessToken != null && refresh_token != null) {
            token = new Token();
            token.setAccessToken(accessToken);
            token.setAccessToken(refresh_token);
        }

        return token;
    }

}
