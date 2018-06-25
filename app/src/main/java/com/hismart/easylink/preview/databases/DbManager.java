package com.hismart.easylink.preview.databases;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

/**
 * @author qinwendong
 * @date 2018/6/20
 * descrption:
 */
public final class DbManager {

    private static class SingletonHolder {
        private static final DbManager INSTANCE = new DbManager();
    }

    private DbManager() {
        SQLiteDatabase db =  Connector.getDatabase();
    }

    public static DbManager getInsatance() {
        return SingletonHolder.INSTANCE;
    }

    public void save(LitePalSupport litepal,String ... strings){

    }

    public void saveThrows(LitePalSupport litepal){

    }

}
