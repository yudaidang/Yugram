package lab.yu.yugram.Utils;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Yu on 11/22/2017.
 */

public class FileSearch {

    /**
     * Search a directory and return a list of all **directories** contained inside
     * @param directory
     * @return
     */
    public static ArrayList<String> getDirectoryPaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listFile = file.listFiles();
        for(int i = 0; i < listFile.length; i++){
            if(listFile[i].isDirectory()){
                pathArray.add(listFile[i].getAbsolutePath());
            }
        }
        return pathArray;
    }

    /**
     * Search a directory and return a list of all **files** contained inside
     * @param directory
     * @return
     */
    public static ArrayList<String> getFilePaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listFile = file.listFiles();
        Log.d("BABABABABABA", "cos toonf tai" + listFile.length);
        for(int i = 0; i < listFile.length; i++){
            if(listFile[i].isFile()){
                pathArray.add(listFile[i].getAbsolutePath());
            }
        }
        return pathArray;
    }
}
