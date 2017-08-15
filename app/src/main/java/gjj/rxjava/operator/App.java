package gjj.rxjava.operator;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * 作者：l on 2017/8/15 18:16
 * 邮箱：gujj512@163.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
