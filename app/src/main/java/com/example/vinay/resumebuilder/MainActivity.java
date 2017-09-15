package com.example.vinay.resumebuilder;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.bt_go)
    Button btGo;
    @Bind(R.id.guest_btn)
    Button btGuest;
    @Bind(R.id.cv)
    CardView cv;
    @Bind(R.id.fp)
    TextView fp;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    ProgressDialog progressDialog;

    EditText editEmail, editPassword;
    Button btnLogin;
    String URL= "http://www.fratelloinnotech.com/resumebuilder/index.php";

    JSONParser jsonParser=new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        editEmail=(EditText) findViewById(R.id.et_email);
        editPassword=(EditText) findViewById(R.id.et_password);

        btnLogin=(Button)findViewById(R.id.bt_go);
        btGuest=(Button)findViewById(R.id.guest_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editEmail.setError("enter a valid email address");
                } else {
                    editEmail.setError(null);
                }

                if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                    editPassword.setError("between 4 and 10 alphanumeric characters");
                }
                    else
                {
                    editPassword.setError(null);

                    AttemptRegister attemptLogin = new AttemptRegister();
                    attemptLogin.execute(editEmail.getText().toString(), editPassword.getText().toString());
                }
//                Intent intent= new Intent(getApplicationContext(),AddProfilesActivity.class);
//                startActivity(intent);
            }
        });
        btGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddProfilesActivity.class);
                startActivity(intent);
            }
        });
    }



    private class AttemptRegister extends AsyncTask<String, String, JSONObject> {

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String email = args[0];
            String password = args[1];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("email",email));

                params.add(new BasicNameValuePair("password", password));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            progressDialog.dismiss();


            try {
                if (result != null) {
                    editPassword.setText("");
                    editEmail.setText("");
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    if(result.getInt("success")==1)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                            Explode explode = new Explode();
                            explode.setDuration(500);

                            getWindow().setExitTransition(explode);
                            getWindow().setEnterTransition(explode);
                            Intent i2 = new Intent(getApplicationContext(), AddProfilesActivity.class);
                            startActivity(i2);
                        }
                        else {
                                Intent intent = new Intent(getApplicationContext(),AddProfilesActivity.class);
                                startActivity(intent);
                                }

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }



    @OnClick({R.id.bt_go, R.id.fab,R.id.fp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
//            case R.id.guest_btn:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//                    Explode explode = new Explode();
//                    explode.setDuration(500);
//
//                    getWindow().setExitTransition(explode);
//                    getWindow().setEnterTransition(explode);
//                    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//                    Intent i2 = new Intent(this, AddProfilesActivity.class);
//                    startActivity(i2, oc2.toBundle());
//                }
//                else {
//                    Intent intent = new Intent(getApplicationContext(),AddProfilesActivity.class);
//                    startActivity(intent);
//                }
//                break;
            case R.id.fp:

                startActivity(new Intent(this, ForgetPassword.class));
                break;
        }
    }
}
