package com.example.vinay.resumebuilder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.cv_add)
    CardView cvAdd;

    ProgressDialog progressDialog;
    EditText editEmail, editPassword, editName;
    Button btnNext;
    String URL= "http://www.fratelloinnotech.com/resumebuilder/index.php";


    JSONParser jsonParser=new JSONParser();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    animateRevealClose();
                } else {

                    finish();
                }
            }
        });

        editName=(EditText) findViewById(R.id.et_username);
        editEmail=(EditText) findViewById(R.id.et_email);
        editPassword=(EditText) findViewById(R.id.et_password);

        btnNext=(Button)findViewById(R.id.bt_go);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name= editName.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (name.isEmpty() || name.length() < 3) {
                    editName.setError("at least 3 characters");

                } else {
                    editName.setError(null);
                }

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editEmail.setError("enter a valid email address");

                } else {
                    editEmail.setError(null);
                }

                if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                    editPassword.setError("between 4 and 10 alphanumeric characters");

                } else {
                    editPassword.setError(null);

                    AttemptRegister attemptLogin= new AttemptRegister();
                    attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString(),editEmail.getText().toString());
                }



            }
        });
    }




    private class AttemptRegister extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();


        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String email = args[2];
            String password = args[1];
            String name= args[0];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

            try {
                if (result != null) {
                    editName.setText("");
                    editPassword.setText("");
                    editEmail.setText("");
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }





    private void ShowEnterAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
            getWindow().setSharedElementEnterTransition(transition);

            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    cvAdd.setVisibility(View.GONE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    animateRevealShow();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }


            });
        }
    }

    public void animateRevealShow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    cvAdd.setVisibility(View.VISIBLE);
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

    public void animateRevealClose() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 3, 0, cvAdd.getHeight(), fab.getWidth() / 2);
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    cvAdd.setVisibility(View.INVISIBLE);
                    super.onAnimationEnd(animation);
                    fab.setImageResource(R.drawable.plus);
                    RegisterActivity.super.onBackPressed();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

        @Override
        public void onBackPressed () {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                animateRevealClose();
            }
        }
    }



