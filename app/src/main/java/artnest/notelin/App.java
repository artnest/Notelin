package artnest.notelin;

import android.app.Application;
import android.content.Context;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        if (sInstance == null) {
            sInstance = new App();
        }
        return sInstance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }
}
