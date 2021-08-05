package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class add_news_admin extends AppCompatActivity {
//admin add news
    TextView title,description;
    Button addNewsBtn,uploadImageBtn;
    DatabaseReference reference;
    AlertDialog.Builder sucessAlert;
    String url;
    List<News> newsList;
    Uri imageUri;
    static final int IMAGE_REQUEST =2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news_admin);

        //props

        title = findViewById(R.id.titleNewAdd);
        description = findViewById(R.id.descriptionNewAdd);
        addNewsBtn = findViewById(R.id.addNewsBtn);
        uploadImageBtn = findViewById(R.id.uploadImageBtn);
        sucessAlert = new AlertDialog.Builder(this);
        newsList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("news");

        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }


        });

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Profile.class));
            }
        });

        addNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!title.getText().toString().isEmpty() && !description.getText().toString().isEmpty()){
                uploadImage();
                }
                else{
                    Toast.makeText(getApplicationContext(),"something empty",Toast.LENGTH_SHORT);
                }
            }
        });


    }

    private void openImage() {
//        Intent  intent = new Intent();
//        intent.setType("image/");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,IMAGE_REQUEST);
        Intent og = new Intent();
        og.setType("image/*");
        og.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(og,"Pick a picture"),IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            imageUri = data.getData();
            Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT);
           // uploadImage();
        }
    }

    private String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private void uploadImage() {
   // final ProgressDialog pd = new ProgressDialog(getApplicationContext());
   // pd.setMessage("Uploading");
   // pd.show();
    if(imageUri != null){
        Toast.makeText(getApplicationContext(),"Uploading",Toast.LENGTH_LONG);
        StorageReference fileRef = FirebaseStorage.getInstance().getReference().child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
        fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot data : snapshot.getChildren()) {
                                        News newNews = data.getValue(News.class);
                                        newsList.add(newNews);
                                    }

                                    News news = new News(description.getText().toString(),title.getText().toString(),url);
                                    newsList.add(news);
                                    reference.setValue(newsList).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            sucessAlert.setTitle("sucessfully added news")
                                                    .setMessage("ADDED")
                                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            startActivity(new Intent(getApplicationContext(), Profile.class));
                                                        }
                                                    }).create().show();
                                        }
                                    });


                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                       // Log.d("DownloadURL",url);

                       // pd.dismiss();

                    }
                });
            }
        });
    }
    }



}