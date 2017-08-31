package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.provider.MediaStore;
import android.view.View;
import android.content.Intent;

import com.example.s3529589.mad_a1.Model.CustomArrayAdapter;

public class PickDisplayPictureController implements View.OnClickListener {
    private CustomArrayAdapter caa;
    private int id;

    public PickDisplayPictureController(CustomArrayAdapter caa, int id) {
        this.caa = caa;
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        caa.getActivity().startActivityForResult(it, 212);
    }
}
