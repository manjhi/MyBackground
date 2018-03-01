package manjinder.mybackground;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView imageView;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String i = intent.getStringExtra("result");
                Bitmap bitmap = intent.getParcelableExtra("bitmap");
                Log.d(TAG, "onReceive: " + bitmap);
                imageView.setImageBitmap(bitmap);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
    }

    public void changeImage(View v) {
        Intent i = new Intent(MainActivity.this, DownloadService.class);
        i.putExtra("url", "https://placeimg.com/250/250/any");
        startService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("manjinder.mybackground"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
