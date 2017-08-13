package com.example.dns.project_3.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dns.project_3.R;
import com.example.dns.project_3.presenter.ProfilePresenter;
import com.example.dns.project_3.presenter.ProfilePresenterInit;
import com.example.dns.project_3.ui.view.ProfileView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE = 2;
    private Uri mCurrentPhotoPath = null;

    @BindView(R.id.profile_image) CircleImageView image;
    @BindView(R.id.sex_spiner) Spinner sex_spinner;
    @BindView(R.id.dept_spiner) Spinner dept_spinner;
    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initial();

        presenter.create();

        image.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.change_photo))
                    .setItems(R.array.foto_value, (dialog, which) -> {
                        switch (which){

                            case 0:
                                dispatchTakePictureIntent();
                                break;

                            case 1:
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, ""),PICK_IMAGE);
                                break;

                            case 2:
                                image.setImageDrawable(getResources().getDrawable(R.drawable.sml));
                                break;

                            default:
                                Toast.makeText(this,"Неудачно",Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.create();
            builder.show();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null && requestCode == PICK_IMAGE)
        {
            Uri selectedImageUri = data.getData();
            Glide.with(this).load(selectedImageUri).into(image);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Glide.with(this).load(mCurrentPhotoPath).into(image);
        }
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName,".jpg",storageDir);

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                mCurrentPhotoPath = photoURI;
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.setOptionsItem(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_button,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initial() {
        ButterKnife.bind(this);
        presenter = new ProfilePresenterInit(this) ;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setSexSpinner() {
        String[] arraySex = getResources().getStringArray(R.array.sex_value);
        ArrayAdapter<String> adapterSex = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arraySex);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex_spinner.setAdapter(adapterSex);
    }

    @Override
    public void setDeptSpinner() {
        String[] arrayDept = getResources().getStringArray(R.array.dept_value);
        ArrayAdapter<String> adapterSex = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayDept);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept_spinner.setAdapter(adapterSex);
    }

    @Override
    public void createLenta() {
        Intent intent = new Intent(this,LentaActivity.class);
        startActivity(intent);
    }

    @Override
    public void finished() {
        finish();
    }

}