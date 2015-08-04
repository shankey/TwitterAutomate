package com.automate.twitterautomate;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.w3c.dom.Text;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ExHisnogNbJJbUIawTdtIByN9";
    private static final String TWITTER_SECRET = "NVo0aoGz1sPtt4gbeVmpELJd9TlXf9eU7GK3XbUoSZAjWQaId8";

    private TwitterLoginButton loginButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        loginButton = (TwitterLoginButton)findViewById(R.id.twitter_login_button);
        textView = (TextView)findViewById(R.id.status);
        loginButton.setCallback(new LoginHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


    private class LoginHandler extends Callback<TwitterSession> {


        @Override
        public void success(Result<TwitterSession> twitterSessionResult) {
            textView.setText(twitterSessionResult.data.getUserName()
                    + " "
                    + twitterSessionResult.data.getAuthToken()
                    );
        }

        @Override
        public void failure(TwitterException e) {

        }

    }



}
