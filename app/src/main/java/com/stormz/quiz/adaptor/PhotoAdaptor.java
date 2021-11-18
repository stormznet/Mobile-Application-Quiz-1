package com.stormz.quiz.adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.stormz.quiz.R;
import com.stormz.quiz.instance.PhotoInstance;
import java.util.ArrayList;

public class PhotoAdaptor extends ArrayAdapter<PhotoInstance> {
    private final Context context;
    private final int resource;


    public PhotoAdaptor(@NonNull Context context, int resource, @NonNull ArrayList<PhotoInstance> photos) {
        super(context, resource, photos);
        this.context = context;
        this.resource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Get getter and setter
        String name = getItem(position).getNameStr();
        Integer image = getItem(position).getPhotoInt();
        LayoutInflater inflateLay = LayoutInflater.from(context);

        convertView = inflateLay.inflate(resource, parent, false);
        TextView nameTxt = convertView.findViewById(R.id.text);
        ImageView imageV = convertView.findViewById(R.id.image);


        //Set preview text and image
        nameTxt.setText(name);
        imageV.setImageResource(image);

        return  convertView;

    }
}
