package com.example.vinay.resumebuilder.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.NavigationActivity;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.PersonalInfo;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


public class PersonalInfoFragment extends Fragment {


    DatabaseHandler dbHandler;
    Context context;
    EditText name, phone, altPhone, email, altEmail, house, street, address, country, city, pincode, pan, passport, dob;
    RadioButton gender, maritalStatus, maleRB, femaleRB, singleRB, marriedRB;
    RadioGroup genderGroup, maritalGroup;
    Button savePI, updatePI;
    ImageView pic;
    private static final int CAMERA_PIC_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        context = getContext();
        dbHandler = new DatabaseHandler(context);
        rootView = inflater.inflate(R.layout.fragment_tab_personal_info, container, false);

        savePI = (Button) rootView.findViewById(R.id.saveBtn);
        updatePI = (Button) rootView.findViewById(R.id.updateBtn);

        name = (EditText) rootView.findViewById(R.id.fullNameET);
        phone = (EditText) rootView.findViewById(R.id.phoneNumberET);
        altPhone = (EditText) rootView.findViewById(R.id.apnET);
        email = (EditText) rootView.findViewById(R.id.emailET);
        altEmail = (EditText) rootView.findViewById(R.id.alternateEmailET);
        house = (EditText) rootView.findViewById(R.id.houseET);
        street = (EditText) rootView.findViewById(R.id.streetET);
        address = (EditText) rootView.findViewById(R.id.addressEt);
        country = (EditText) rootView.findViewById(R.id.countryET);
        city = (EditText) rootView.findViewById(R.id.cityET);
        pincode = (EditText) rootView.findViewById(R.id.pincodeET);
        pan = (EditText) rootView.findViewById(R.id.panEt);
        passport = (EditText) rootView.findViewById(R.id.passportEt);
        dob = (EditText) rootView.findViewById(R.id.dobEt);
        maleRB = (RadioButton) rootView.findViewById(R.id.maleRB);
        femaleRB = (RadioButton) rootView.findViewById(R.id.femaleRB);
        singleRB = (RadioButton) rootView.findViewById(R.id.singleRB);
        marriedRB = (RadioButton) rootView.findViewById(R.id.marriedRB);


        genderGroup = (RadioGroup) rootView.findViewById(R.id.genderRG);

        maritalGroup = (RadioGroup) rootView.findViewById(R.id.maritalRG);


        pic = (ImageView) rootView.findViewById(R.id.picIV);

        int cid = NavigationActivity.cid;
        PersonalInfo personalInfo = dbHandler.getSinglePI(cid);
        //Toast.makeText(context, "hello"+personalInfo.getpGender()+personalInfo.getpMStatus(), Toast.LENGTH_LONG).show();
        if (personalInfo != null) {
//            //do  nothing
            //Toast.makeText(context, "Hi"+personalInfo.getpFullname(), Toast.LENGTH_SHORT).show();

            name.setText(personalInfo.getpFullname());
            phone.setText(personalInfo.getpPhoneNumber());
            altPhone.setText(personalInfo.getpAltPhoneNumber());
            email.setText(personalInfo.getpEmail());
            altEmail.setText(personalInfo.getpAltEmail());
            house.setText(personalInfo.getpHouse());
            street.setText(personalInfo.getpStreet());
            address.setText(personalInfo.getpAddress());
            country.setText(personalInfo.getpCountry());
            city.setText(personalInfo.getpCity());
            pincode.setText(personalInfo.getpPincode());
            dob.setText(personalInfo.getpDob());
            pan.setText(personalInfo.getpPan());
            passport.setText(personalInfo.getpPassport());

            if (personalInfo.getpImage() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(personalInfo.getpImage(), 0, personalInfo.getpImage().length);
                pic.setImageBitmap(bitmap);
            }
            if (personalInfo.getpGender().equals("Male")) {
                maleRB.setChecked(true);
            } else {
                femaleRB.setChecked(true);
            }

            if (personalInfo.getpMStatus().equals("Single")) {
                singleRB.setChecked(true);
            } else {
                marriedRB.setChecked(true);
            }
            //Toast.makeText(context, ""+personalInfo.getpGender()+personalInfo.getpMStatus(), Toast.LENGTH_SHORT).show();

            savePI.setVisibility(View.GONE);
            updatePI.setVisibility(View.VISIBLE);
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showCameraChooseDialog();

                }
            });
            updatePI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PersonalInfo personalInfo = modelPI();
                    long n = dbHandler.updatePersonalInfo(personalInfo);
                    //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                    if (n > 0) {
                        Toast.makeText(context, "PI updated", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "Problem in updating", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        } else {

            savePI.setVisibility(View.VISIBLE);
            updatePI.setVisibility(View.GONE);
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showCameraChooseDialog();

                }
            });

            savePI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PersonalInfo personalInfo = modelPI();
                    long n = dbHandler.addPersonalInfo(personalInfo);
                    //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                    if (n > 0) {
                        Toast.makeText(context, "PI saved", Toast.LENGTH_SHORT).show();
                        savePI.setVisibility(View.GONE);
                        updatePI.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(context, "Problem in adding", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
        return rootView;
    }

    public PersonalInfo modelPI() {
        int selectedId = genderGroup.getCheckedRadioButtonId();
        gender = (RadioButton) rootView.findViewById(selectedId);

        int selectedId1 = maritalGroup.getCheckedRadioButtonId();
        maritalStatus = (RadioButton) rootView.findViewById(selectedId1);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setpFullname(name.getText().toString());
        personalInfo.setpPhoneNumber(phone.getText().toString());
        personalInfo.setpAltPhoneNumber(altPhone.getText().toString());
        personalInfo.setpEmail(email.getText().toString());
        personalInfo.setpAltEmail(altEmail.getText().toString());
        personalInfo.setpHouse(house.getText().toString());
        personalInfo.setpStreet(street.getText().toString());
        personalInfo.setpAddress(address.getText().toString());
        personalInfo.setpCountry(country.getText().toString());
        personalInfo.setpCity(city.getText().toString());
        personalInfo.setpPincode(pincode.getText().toString());
        personalInfo.setpPan(pan.getText().toString());
        personalInfo.setpPassport(passport.getText().toString());
        personalInfo.setpDob(dob.getText().toString());
        personalInfo.setpGender(gender.getText().toString());
        personalInfo.setpMStatus(maritalStatus.getText().toString());
        personalInfo.setCid(NavigationActivity.cid);

        //convert image to byte[]
        Bitmap bitmap = ((BitmapDrawable) pic.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageInByte = baos.toByteArray();

        personalInfo.setpImage(imageInByte);
        return personalInfo;
    }


    public void showCameraChooseDialog() {
        final Button cam, gall;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.camera_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Resume Builder");
        dialogBuilder.setMessage("Choose one");


        final AlertDialog b = dialogBuilder.create();
        b.show();

        cam = (Button) dialogView.findViewById(R.id.openCamera);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });

        gall = (Button) dialogView.findViewById(R.id.openGallery);
        gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case 0: // Do your stuff here...
                Bitmap image = (Bitmap) data.getExtras().get("data");
                pic.setImageBitmap(image);
                break;
            case 1: // Do your other stuff here...

                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    pic.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                }
                break;
        }

    }
}

