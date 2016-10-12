package com.yaokun.giftspirit.fileutils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 姚 坤 on 2016/9/20.
 */
public class FileUtils {


    public static <E> E ReadObjfromFile(Context context, String filename, Class<E> clazz) {
        E ee = null;
        try {

            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ee = (E) ois.readObject();
            fis.close();
            ois.close();
            return ee;
        } catch (Exception e) {
            e.printStackTrace();
            return ee;
        }
    }

    public static <E> ArrayList<E> ReadObjsfromFile(Context context, String filename, Class<E> clazz) {
        ArrayList<E> tt = new ArrayList<>();
        try {
            File f1 = context.getFilesDir();
            File f = new File(f1, filename);
            if (f.exists()) {


                FileInputStream fis = context.openFileInput(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object ee = null;
                while ((ee = ois.readObject()) != null) {
                    tt.add((E) ee);
                }
                fis.close();
                ois.close();
                return tt;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return tt;
        }
        return null;
    }

    public static <T> boolean SaveObjstoFile(Context context, String filename, List<T> objects) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (T t : objects) {
                oos.writeObject(t);
            }
            oos.flush();
            oos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean SaveObjtoFile(Context context, String filename, Object objects) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objects);
            oos.flush();
            oos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void SaveJsonToFile(Context context, String filename, String json) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String ReadJsonFromFile(Context context, String filename) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        StringBuffer sb = new StringBuffer();
        String temp;
        try {
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void SaveStringListToFile(Context context, String filename, List<String> strs) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            for (int i = 0; i < strs.size(); i++) {
                if (1 != 0) {
                    printWriter.write("\n" + strs.get(i));
                } else {
                    printWriter.write(strs.get(i));
                }
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<String> ReadStringListFromFile(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            List<String> slist = new ArrayList<>();
            String temp = null;

            while ((temp = bufferedReader.readLine()) != null) {
                slist.add(temp);
            }
            return slist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static String SaveBitmap(Context context, String filename, Bitmap bmp) {
        String path = null;
        try {
            path = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    bmp, filename, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        Log.e("====", "====" + path);
        return path;
    }

    public static String SaveToDiak(Context context, String filename, Bitmap bmp) {
        File root = new File(Environment.getExternalStorageDirectory(), "cache_00k");
        String path = null;
        if (!root.exists()) {
            root.mkdirs();
        }
        File file = new File(root, filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        path = file.getAbsolutePath();
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        return path;
    }
}
