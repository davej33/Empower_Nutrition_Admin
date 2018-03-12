package com.example.davidjusten.empower_nutrition_admin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddFoodItemActivity extends AppCompatActivity {
private static final String LOG_TAG = AddFoodItemActivity.class.getSimpleName();
    private ImageButton mFoodImageButton;
    private static final int GALLREQ = 1;
    private EditText mName, mDescription, mPrice;
    private Uri uri;
    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        mName = findViewById(R.id.itemName);
        mDescription = findViewById(R.id.itemDescrip);
        mPrice = findViewById(R.id.itemPrice);
        mStorageReference = FirebaseStorage.getInstance().getReference("Item");
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

        String name_text = mName.getText().toString().trim();
        String desc_text = mDescription.getText().toString().trim();
        String price_text = mPrice.getText().toString().trim();

        if(!TextUtils.isEmpty(name_text) && !TextUtils.isEmpty(desc_text) && !TextUtils.isEmpty(price_text)){
            Log.i(LOG_TAG,"check1 ----------------- ");
            StorageReference filepath = mStorageReference.child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Toast.makeText(AddFoodItemActivity.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
