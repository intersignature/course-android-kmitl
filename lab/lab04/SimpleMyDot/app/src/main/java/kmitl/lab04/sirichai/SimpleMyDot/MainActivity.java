package kmitl.lab04.sirichai.SimpleMyDot;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import kmitl.lab04.sirichai.SimpleMyDot.model.Colors;
import kmitl.lab04.sirichai.SimpleMyDot.model.Dot;
import kmitl.lab04.sirichai.SimpleMyDot.model.DotParcelable;
import kmitl.lab04.sirichai.SimpleMyDot.model.Dots;
import kmitl.lab04.sirichai.SimpleMyDot.model.Screenshot;
import kmitl.lab04.sirichai.SimpleMyDot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener {

    private DotView dotView;
    private Dot dot;
    private Dots dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            dotView = (DotView) findViewById(R.id.dotview);
            dots = new Dots();
            dots.setListener((Dots.OnDotsChangeListener) this);
            dotView.setOnDotViewPressListener(this);
        }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int radius = random.nextInt(90)+30;
        Dot newDot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(newDot);
    }



    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    public void onShareFb(View view) {
        View main;
        main = findViewById(R.id.main);
        Bitmap b = Screenshot.takescreenshotOfRottView(main);
        saveBitmap(b);
        File imagePath = new File(this.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(this, "kmitl.lab04.sirichai.SimpleMyDot.fileprovider", newFile);
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }


    }
    private void saveBitmap(Bitmap bitmap) {
        // save bitmap to cache directory
        try {
            File cachePath = new File(this.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }
    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if(dotPosition == -1) {
            Dot newDot = new Dot(x, y, 30, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            alertDialog(dotPosition);
        }
    }

    public void alertDialog(final int dotPosition) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("What you want to do?");
        alertDialogBuilder
                .setMessage("")
                .setCancelable(true)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dots.removeBy(dotPosition);
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final DotParcelable dotParcelable = new DotParcelable(dotPosition, dots.getAllDot().get(dotPosition).getColor(), dots.getAllDot().get(dotPosition).getRadius());
                        Intent editActIntent = new Intent(MainActivity.this, EditActivity.class);
                        editActIntent.putExtra("dotParcelable", dotParcelable);
                        startActivityForResult(editActIntent, 1);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            DotParcelable dotParcelable = data.getParcelableExtra("reDotParcelable");
            if (resultCode == Activity.RESULT_OK) {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setColor(dotParcelable.getColor());
            } else {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setRadius(dotParcelable.getRadius());
            }
        }
    }
}



