package comqq.example.asus_pc.kuaidiapi;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by asus-pc on 2016/12/29.
 */
//线程池
public class ThreadUtils {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private  static Executor sExecutor = Executors.newSingleThreadExecutor();

    public static void runOnBackgroundThread(Runnable runnable) {
        sExecutor.execute(runnable);
    }

    public static void runOnMainThread(Runnable runnable) {
        mHandler.post(runnable);
    }

}
