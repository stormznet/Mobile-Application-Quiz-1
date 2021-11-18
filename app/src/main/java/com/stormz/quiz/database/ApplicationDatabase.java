package com.stormz.quiz.database;

import android.content.Context;

import com.stormz.quiz.ManagePhotoActivity;
import com.stormz.quiz.R;
import com.stormz.quiz.instance.PhotoInstance;
import com.stormz.quiz.instance.UserInstance;

import java.util.ArrayList;

public class ApplicationDatabase {
    private Context context;
    private static ArrayList<UserInstance> users;
    private static ArrayList<PhotoInstance> photos;
    private static ApplicationDatabase instance;

    public ApplicationDatabase(Context context){
        this.context = context;
        this.users = new ArrayList<>();
        this.photos = new ArrayList<>();

        photos.add(new PhotoInstance("Hallstatt Lake Scener", R.drawable.hallstatt));
        photos.add(new PhotoInstance("Little Island in Seas", R.drawable.seas));
        photos.add(new PhotoInstance("Sunset Scenery", R.drawable.scenery));
    }

    public static synchronized ApplicationDatabase getInstance(Context context){
        if(instance == null){
            instance = new ApplicationDatabase(context);
        }

        return instance;

    }

    public ArrayList<UserInstance> getUsers()
    {
        return users;
    }

    public ArrayList<PhotoInstance> getPhotos()
    {
        return photos;
    }


    public UserInstance findUser(String email) {
        for (UserInstance userArrAdaptor : this.getUsers()){
            if (userArrAdaptor.getEmail().equals(email)){
                return userArrAdaptor;
            }
        }
        return null;
    }

    public PhotoInstance findPhoto(String photoName) {
        for (PhotoInstance photoInstance : this.getPhotos()){
            if (photoInstance.getNameStr().equals(photoName)){
                return photoInstance;
            }
        }
        return null;
    }
}
