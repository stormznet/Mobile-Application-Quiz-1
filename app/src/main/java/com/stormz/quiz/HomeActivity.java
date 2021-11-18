package com.stormz.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.stormz.quiz.adaptor.PhotoAdaptor;
import com.stormz.quiz.database.ApplicationDatabase;
import com.stormz.quiz.instance.PhotoInstance;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {


    //Import:
    ListView listView;
    ArrayList<PhotoInstance> photoList = new ArrayList<PhotoInstance>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Item select
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = null;
        switch (item.getItemId()){
            case R.id.manageuserMenu:
                intent = new Intent(HomeActivity.this, ManagePhotoActivity.class);

                finish();
                break;
            case R.id.logoutMenu:
                intent = new Intent(HomeActivity.this, LoginActivity.class);

                finish();
                break;

      }
      if (intent != null){
          startActivity(intent);
      }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Import:
        listView = findViewById(R.id.listItem);

        ArrayList<PhotoInstance> photoList = ApplicationDatabase.getInstance(HomeActivity.this).getPhotos();

        PhotoAdaptor photoAdaptor = new PhotoAdaptor(this, R.layout.photo_list, photoList);
        listView.setAdapter(photoAdaptor);

    }
}