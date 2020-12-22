package com.example.mymvp.manager;

public class DBManager {
    private volatile static DBManager manager;
    private DBManager(){

    }
    public static  synchronized DBManager getManager(){
        if(manager==null){
            synchronized (DBManager.class){
                if(manager==null){
                    manager = new DBManager();
                }
            }
        }
        return manager;
    }
}
