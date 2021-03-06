package com.example.sep4androidapp.connection;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        Request request = chain.request();

        request = request.newBuilder().header("Authorization", Credentials.basic(userId, "1234")).build();
        return chain.proceed(request);
    }
}
