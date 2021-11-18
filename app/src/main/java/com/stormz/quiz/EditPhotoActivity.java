package com.stormz.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stormz.quiz.adaptor.PhotoAdaptor;
import com.stormz.quiz.database.ApplicationDatabase;
import com.stormz.quiz.instance.PhotoInstance;
import com.stormz.quiz.instance.UserInstance;

import java.util.ArrayList;

public class EditPhotoActivity extends AppCompatActivity {

    //Import
    EditText text;
    ImageView image;
    Button btnUpdate;

    //Data Type intent get:
    String nameIntent;
    Integer imageIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);

        //ListView:

        text = findViewById(R.id.textEdit);
        image = findViewById(R.id.imageEdit);
        btnUpdate = findViewById(R.id.btnUpdateEdit);

        //Get Intent:
        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        nameIntent = bun.getString("nameIntent");
        imageIntent = bun.getInt("imageIntent");

        //Set image and text
        image.setImageResource(imageIntent);
        text.setText(nameIntent);

        //Button update on click
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String originalPhotoName = bun.getString("nameIntent");
                String modifiedPhotoName = text.getText().toString();

                PhotoInstance photo = ApplicationDatabase.getInstance(EditPhotoActivity.this).findPhoto(originalPhotoName);
                photo.setNameStr(modifiedPhotoName);
                Toast selected = Toast.makeText(EditPhotoActivity.this, "Data has been updated!", Toast.LENGTH_SHORT);
                selected.show();
                Intent home = new Intent(EditPhotoActivity.this, ManagePhotoActivity.class);

                startActivity(home);

            }
        });
    }


    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    //Item list
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = null;
        switch (item.getItemId()) {

            case R.id.home3Menu:
                intent = new Intent(EditPhotoActivity.this, HomeActivity.class);

                finish();
                break;
            case R.id.manageuser3Menu:
                intent = new Intent(EditPhotoActivity.this, ManagePhotoActivity.class);

                finish();
                break;
            case R.id.logout3Menu:
                intent = new Intent(EditPhotoActivity.this, LoginActivity.class);

                finish();
                break;

        }
        if (intent != null) {
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}