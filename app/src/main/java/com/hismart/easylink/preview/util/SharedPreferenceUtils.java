package com.hismart.easylink.preview.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;


/**
 * @author qinwendong.
 * @date 2018/5/30
 * description SharedPreferenceUtils SharedPreference工具类
 */
public class SharedPreferenceUtils {
    private static final String TAG = "SharedPreferenceUtils";
    private static final String PREFERENCES = "preferences";
    public static final String INIT_FLAG = "init";

    //私人模式 只有自己与相同user ID可以打开
    public static final int MODE_PRIVATE = Context.MODE_PRIVATE;

    //需要init，可切换的SP
    private static SharedPreferences mSharePrefs;

    /**
     * 打开/切换mSharePrefs
     * MODE_PRIVATE
     *
     * @param name Desired preferences file.
     */
    public static void init(String name) {
        mSharePrefs = RefWatcherUtils.getContext().getSharedPreferences(PREFERENCES + name, MODE_PRIVATE);
    }

    /**
     * 打开/切换mSharePrefs
     *
     * @param name Desired preferences file.
     * @param Mode 打开模式
     * MODE_PRIVATE 私人模式-只有自己与相同user ID可以打开
     * MODE_PUBLIC 公开模式-可以被其他人打开
     */
    public static void init(String name, int Mode) {
        mSharePrefs = RefWatcherUtils.getContext().getSharedPreferences(PREFERENCES + name, Mode);
    }

    /**
     * 存放数据到mSharePrefs
     * @param key key
     * @param object value
     */
    public static void put(String key, Object object) {
        if (mSharePrefs != null) {
            saveData(mSharePrefs, key, object);
        } else {
            LogUtils.e(TAG, "Please init AppSP first!");
        }
    }

    /**
     * 从mSharePrefs取出SharedPreferences基本类型数据
     * @param key key
     * @param defValue defValue
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, T defValue) {
        if (mSharePrefs != null) {
            return (T) getData(mSharePrefs, key, defValue);
        } else {
            LogUtils.e(TAG, "Please init AppSP first!");
            return defValue;
        }
    }

    /**
     * 从mSharePrefs取出数据
     * @param key key
     * @param clazz class
     */
    public static <T> T get(String key, Class<T> clazz) {
        if (mSharePrefs != null) {
            return SharedPreferenceUtils.getObject(mSharePrefs, key, clazz);
        } else {
            LogUtils.e(TAG, "Please init AppSP first!");
            return null;
        }
    }

    /**
     * 从mSharePrefs移除
     * @param key key
     */
    public static void remove(String key) {
        if (mSharePrefs != null) {
            removeData(mSharePrefs, key);
        } else {
            LogUtils.e(TAG, "Please init AppSP first!");
        }
    }

    /**
     * 清空mSharePrefs
     */
    public static void clear() {
        if (mSharePrefs != null) {
            clearAll(mSharePrefs);
        } else {
            LogUtils.e(TAG, "Please init AppSP first!");
        }
    }

    /**
     * mSharePrefs是否包含Key
     */
    public static void contains(String key) {
        if (mSharePrefs != null) {
            contains(mSharePrefs, key);
        } else {
            LogUtils.e(TAG, "Please init AppSP first!");
        }
    }

    /**
     * mSharePrefs获取所有键值对
     */
    public static Map<String, ?> getAll() {
        if (mSharePrefs != null) {
            return getAll(mSharePrefs);
        } else {
            LogUtils.e(TAG, "Please init AppSP first!");
            return null;
        }
    }

    /**
     * 保存数据到文件
     *
     * @param sharedPreferences Sp
     * @param key key
     * @param object value
     */
    private static void saveData(SharedPreferences sharedPreferences, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        } else {
            SharedPreferenceUtils.setObject(editor, key, object);
        }

        editor.apply();
    }

    /**
     * 从文件中读取数据
     *
     * @param sharedPreferences Sp
     * @param key key
     * @param defValue 默认值
     * @return Object
     */
    private static Object getData(SharedPreferences sharedPreferences, String key, Object defValue) {
        String type = defValue.getClass().getSimpleName();

        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        } else {
            return defValue;
        }
    }

    /**
     * 删除sharedPreferences的key项
     * @param sharedPreferences sp
     * @param key key
     */
    private static void removeData(SharedPreferences sharedPreferences, String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     *
     */
    private static void clearAll(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return 是否已经存在
     */
    private static boolean contains(SharedPreferences sharedPreferences, String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    private static Map<String, ?> getAll(SharedPreferences sharedPreferences) {
        return sharedPreferences.getAll();
    }


    /**
     * 针对复杂类型存储<对象>到SP中
     *
     * @param editor 已经获取到的SP的editor
     * @param key    value对应的key
     * @param object value对象
     */
    private static void setObject(SharedPreferences.Editor editor, String key, Object object) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取sp中的对象
     *
     * @param sp    SharedPreferences
     * @param key   key
     * @param clazz 类
     * @return Object
     */
    @SuppressWarnings("unchecked")
    private static <T> T getObject(SharedPreferences sp, String key, Class<T> clazz) {
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                return (T) ois.readObject();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    bais.close();
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
