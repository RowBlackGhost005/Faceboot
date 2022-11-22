/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.masa.authentication;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


/**
 *
 * @author Andrea
 */
public class Google implements IAuthenticationMethod{
    
    private final String API_KEY = "471315530256-rtbikn5l1q809qfcipj0i8q6virr4s0i.apps.googleusercontent.com";

    private final String API_SECRET = "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";

    // Port in the "Callback URL". 
    private final int PORT = 8080;

    //Domain name in the "Callback URL". 
    private final String DOMAIN = "localhost";

    /**
     * Directory to store user credentials.
     */
    private static final File DATA_STORE_DIR
            = new File(System.getProperty("user.home"), ".store/ouath_sample");

    /**
     * Global instance of the {@link DataStoreFactory}. The best practice is to
     * make it a single globally shared instance across your application.
     */
    private FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * OAuth 2 scope.
     */
    private final String SCOPE = "profile";

    /**
     * Global instance of the HTTP transport.
     */
    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Global instance of the JSON factory.
     */
    private final JsonFactory JSON_FACTORY = new GsonFactory();

    private static final String TOKEN_SERVER_URL = " https://accounts.google.com/o/oauth2/token";
    private static final String AUTHORIZATION_SERVER_URL
            = "https://accounts.google.com/o/oauth2/auth";

    @Override
    public Profile Login() throws Exception{
    
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            final Credential credential = authorize();

            if (credential.getAccessToken() == null) {
                System.out.println("Don't authorize");
            } else {

                HttpRequestFactory requestFactory
                        = HTTP_TRANSPORT.createRequestFactory(
                                new HttpRequestInitializer() {
                            @Override
                            public void initialize(HttpRequest request) throws IOException {
                                credential.initialize(request);
                                request.setParser(new JsonObjectParser(JSON_FACTORY));
                            }
                        });
                GenericUrl url = new GenericUrl("https://www.googleapis.com/oauth2/v1/userinfo?alt=json");
                HttpRequest request = requestFactory.buildGetRequest(url);

                String userInfo = request.execute().parseAsString();
                Profile profile = new Gson().fromJson(userInfo, Profile.class);
                return profile;
                //Success!
            }
          

        return null;
    }

    @Override
    public Credential authorize() throws Exception{
         
    // set up authorization code flow
    AuthorizationCodeFlow flow =
        new AuthorizationCodeFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GenericUrl(TOKEN_SERVER_URL),
                new ClientParametersAuthentication(
                    API_KEY, API_SECRET),
                API_KEY,
                AUTHORIZATION_SERVER_URL)
            .setScopes(Arrays.asList(SCOPE,"email"))
            .setDataStoreFactory(DATA_STORE_FACTORY)
            .build();
    // authorize
    LocalServerReceiver receiver =
        new LocalServerReceiver.Builder()
            .setHost(DOMAIN)
            .setPort(PORT)
            .build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

    }
    
}
