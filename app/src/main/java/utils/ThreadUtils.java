package utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Leon on 2016/12/29.
 */

public class ThreadUtils {

    /**
     * 创建一个绑定主线程的Looper的handler
     * 处理message或者runnable都会在主线程执行
     */
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Executor sExecutor = Executors.newSingleThreadExecutor();

    /**
     * 在单线程的线程池中运行runnable
     * @param runnable
     */
    public static void runOnBackgroundThread(Runnable runnable) {
        sExecutor.execute(runnable);
    }

    public static void runOnMainThread(Runnable runnable) {
        mHandler.post(runnable);
    }
}
