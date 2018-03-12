package com.example.davidjusten.empower_nutrition_admin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddFoodItemActivity extends AppCompatActivity {
private static final String LOG_TAG = AddFoodItemActivity.class.getSimpleName();
    private ImageButton mFoodImageButton;
    private static final int GALLREQ = 1;
    private EditText mName, mDescription, mPrice;
    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        mName = findViewById(R.id.itemName);
        mDescription = findViewById(R.id.itemDescrip);
        mPrice = findViewById(R.id.itemPrice);
    }

    public void imageButtonClicked(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        galleryIntent.setType("Image/*");
        startActivityForResult(galleryIntent, GALLREQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLREQ && resultCode == RESULT_OK) {
            uri = data.getData();
            mFoodImageButton = findViewById(R.id.foodImageButton);
            mFoodImageButton.setImageURI(uri);
        }
    }

    public void addItemButtonClicked(View view) {
    }
}
