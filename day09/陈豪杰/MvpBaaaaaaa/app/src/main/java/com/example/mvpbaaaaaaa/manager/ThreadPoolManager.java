package com.example.mvpbaaaaaaa.manager;


import com.example.mvpbaaaaaaa.base.BaseThreadPool;
import com.example.mvpbaaaaaaa.thread.CustomThreadPool;
import com.example.mvpbaaaaaaa.thread.SchduleThreadPool;
import com.example.mvpbaaaaaaa.thread.SingleThreadPool;

public class ThreadPoolManager {
    public static final int CUSTOM_THREADPOOL = 0;
    public static final int SINGLE_THREADPOOL = 1;
    public static final int SCHDULE_THREADPOOL = 2;

    public static BaseThreadPool getThreadPool(int type) {
        switch (type) {
            case CUSTOM_THREADPOOL:
                return CustomThreadPool.getThreadPool();
            case SINGLE_THREADPOOL:
                return SingleThreadPool.getSingleThreaPool();
            case SCHDULE_THREADPOOL:
                return SchduleThreadPool.getmSchduleThreadPool();
        }
        return null;
    }

}