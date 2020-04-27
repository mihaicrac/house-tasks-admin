package mihaic.com.example.house_tasks_admin.network;

import java.io.IOException;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.BuildConfig;
import mihaic.com.example.house_tasks_admin.data.Token;
import mihaic.com.example.house_tasks_admin.services.TokenStoreService;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MockInterceptor implements Interceptor {

    private TokenStoreService tokenStoreService;

    @Inject
    public MockInterceptor(TokenStoreService tokenStoreService) {
        this.tokenStoreService = tokenStoreService;
    }


    public Response intercept(Interceptor.Chain chain) throws IOException {
        if (BuildConfig.DEBUG) {
            String uri = chain.request().url().uri().toString();
            String responseString = "{}";
            if (uri.endsWith("oauth/token")) {
                responseString = "{" +
                        "  \"access_token\": \"acces_token\"," +
                        "  \"refresh_token\": \"refresh_token\"" +
                        "}";
            } else if (uri.endsWith("users")) {
                responseString = "{" +
                        "  \"id\": \"9f81a5a8-3e07-11ea-b77f-2e728ce88125\"," +
                        "  \"username\": \"username\"," +
                        "  \"firstname\": \"firstname\"," +
                        "  \"lastname\": \"lastname\"," +
                        "  \"email\": \"email\"" +
                        "}";
            }


//            return new Response.Builder().code(200)
//                    .protocol(Protocol.HTTP_2)
//                    .message(responseString)
//                    .body(ResponseBody.create(MediaType.parse("application/json"),
//                            responseString.getBytes()))
//                    .addHeader("content-type", "application/json")
//                    .request(chain.request())
//                    .build();
            Request.Builder builder = chain.request().newBuilder();
            if (!uri.endsWith(":8084/oauth/token") && !uri.endsWith(":8084/users")) {
                addBearerHeader(builder);
            }
            Request request = builder.build();
            return chain.proceed(request);
        } else {
            //just to be on safe side.
            throw new IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode");
        }
    }

    private Request.Builder addBearerHeader(Request.Builder builder) {
        Token token = tokenStoreService.getToken();
        if (token != null) {
            builder.addHeader("Authorization", "Bearer " + token.getAccessToken());
        }
        return builder;
    }

}