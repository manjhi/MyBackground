package manjinder.mybackground;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

/**
 * Created by Manjinder_Singh on 06-Sep-17.
 */
public class DownloadService extends IntentService {


    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String dataUrl = intent.getStringExtra("url");
        try {

            URL url = new URL(dataUrl);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Intent intent1 = new Intent("manjinder.mybackground");
            intent1.putExtra("result",1);
            intent1.putExtra("bitmap", bitmap);
            sendBroadcast(intent1);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
