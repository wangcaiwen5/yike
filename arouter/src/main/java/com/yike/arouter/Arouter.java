package com.yike.arouter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import dalvik.system.DexFile;

import java.util.*;

/**
 * Author：wangcaiwen
 * Date：2019-07-16
 * Description：中间人 代理
 */
public class Arouter {

    private static Arouter arouter= new Arouter();
    private Context context;
    private static final String TAG = "Arouter";
    public void init(Application context){
        this.context = context;
       List<String> classNmaes = getClassNmae("com.yike.util");
        for (String classNmae : classNmaes) {
            Log.e(TAG, "init: "+classNmae );
            try {
                Class<?> aClass = Class.forName(classNmae);
                //判断这个类是否是IRouter的实现类
                //如果是IArouter的实现类
                if (IArouter.class.isAssignableFrom(aClass)){
                    IArouter iArouter = (IArouter) aClass.newInstance();
                    iArouter.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    /**
     *  装载所有的activity的类对象的容器
     */
    private Map<String,Class<? extends Activity>>  activityList;

    public Arouter() {
        activityList = new HashMap<>();
    }

    public static Arouter getInstance(){
        return arouter;
    }

    /**
     * 将activity的类对象,添加进map
     * @param path
     * @param clazz
     */
    public void putActivity(String path,Class<? extends Activity> clazz){
        if (path!=null&&clazz!=null){
            activityList.put(path, clazz);
        }
    }


    public void jumpActivity(String key, Bundle bundle){
        Class<? extends Activity> aClass = activityList.get(key);
        if (aClass==null){
            return;
        }
        Intent intent = new Intent().setClass(context, aClass);
        if (bundle!=null){
            intent.putExtra("bundle", bundle);
        }
        /**
         * 这里这行必须加一下
         * 网上查询了一下说明如下  参考链接:
         * 1.在Activity上下文之外启动Activity需要给Intent设置FLAG_ACTIVITY_NEW_TASK标志，不然会报异常。
         * 2.加了该标志，如果在同一个应用中进行Activity跳转，不会创建新的Task，只有在不同的应用中跳转才会创建新的Task
         */
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    private List<String> getClassNmae(String packeagename) {
        //创建一个class对象集合
        List<String> clazzs = new ArrayList<>();
        String path=null;
        try {
            //通过包管理器  获取到应用信息类然后获取到apk的完整路径
            String sourceDir = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            //根据apk的完整路径获取编译后的dex文件
            DexFile dexFile = new DexFile(sourceDir);
            //获得编译后的dex文件中的左右class
            Enumeration<String> entries = dexFile.entries();
            //然后进行遍历
            while (entries.hasMoreElements()){
                //通过遍历所有的class 的包名
                String name = entries.nextElement();
                //判断累的包名是否符合
                if (name.contains(packeagename)){
                    //符合添加到集合中
                    clazzs.add(name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazzs;
    }
}
