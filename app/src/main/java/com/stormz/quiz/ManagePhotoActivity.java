package com.stormz.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.stormz.quiz.adaptor.PhotoAdaptor;
import com.stormz.quiz.database.ApplicationDatabase;
import com.stormz.quiz.instance.PhotoInstance;

import java.util.ArrayList;

public class ManagePhotoActivity extends AppCompatActivity {

    //    Import:

    ListView listView;
    Button btnDelete, btnUpdate;

//    Menu
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_photo);

        //Import:

        //ListView:
        listView = findViewById(R.id.listItem);

        //Button:
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);


        //Add to database:
        ArrayList<PhotoInstance> photoList = ApplicationDatabase.getInstance(ManagePhotoActivity.this).getPhotos();
        //Set adaptor:
        PhotoAdaptor photoAdaptor = new PhotoAdaptor(this, R.layout.photo_list, photoList);
        listView.setAdapter(photoAdaptor);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), photoList.get(i).getNameStr(), Toast.LENGTH_LONG).show();
//            }
//        });


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                AlertDialog.Builder adb=new AlertDialog.Builder(ManagePhotoActivity.this);
//                adb.setTitle("Delete?");
//                adb.setMessage("Are you sure you want to delete " + position);
//                final int positionToRemove = position;
//                adb.setNegativeButton("Cancel", null);
//                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        photoList.remove(positionToRemove);
//                        photoAdaptor.notifyDataSetChanged();
//                    }});
//                adb.show();
//            }
//        });

        //Button delete -> click listener to listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position < 0){
                            Log.d("DEBUG","ERROR");
                        } else{
                            AlertDialog.Builder adb = new AlertDialog.Builder(ManagePhotoActivity.this);
                            adb.setTitle("Delete?");
                            adb.setMessage("Are you sure you want to delete " + position);
                            final int positionToRemove = position;
                            adb.setNegativeButton("Cancel", null);
                            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    photoList.remove(positionToRemove);
                                    photoAdaptor.notifyDataSetChanged();
                                }});
                            adb.show();
                        }

                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bun = new Bundle();
                        Intent intent = new Intent(ManagePhotoActivity.this, EditPhotoActivity.class);


                        String name = photoList.get(position).getNameStr();
                        Integer image = photoList.get(position).getPhotoInt();


                        bun.putString("nameIntent", name);
                        bun.putInt("imageIntent", image);
                        photoAdaptor.notifyDataSetChanged();

                        intent.putExtras(bun);

                        startActivity(intent);
                    }
                });
            }

        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

//    Item select


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = null;
        switch (item.getItemId()){
            case R.id.homeMenu:
                intent = new Intent(ManagePhotoActivity.this, HomeActivity.class);

                finish();
                break;
            case R.id.logout2Menu:
                intent = new Intent(ManagePhotoActivity.this, LoginActivity.class);

                finish();
                break;

        }
        if (intent != null){
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}