package mihaic.com.example.house_tasks_admin.network;

import mihaic.com.example.house_tasks_admin.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockInterceptor implements Interceptor {

    public Response intercept(Interceptor.Chain chain) {
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

            return new Response.Builder().code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"),
                            responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .request(chain.request())
                    .build();
        } else {
            //just to be on safe side.
            throw new IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode");
        }
    }

}