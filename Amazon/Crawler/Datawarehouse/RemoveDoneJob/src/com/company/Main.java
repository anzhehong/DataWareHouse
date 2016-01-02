package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {


    public static File movieUrlFile = new File("/Users/Harold_LIU/Desktop/Amazon/MovieUrlB2.txt");
    public static File filterFile = new File("/Users/Harold_LIU/Desktop/Amazon/ComparedMoiveId.txt");

    public static File movieUrlFileNew = new File("/Users/Harold_LIU/Desktop/Amazon/MovieUrlC2.txt");
    private static ArrayList urlArray = new ArrayList();

    public static void main(String[] args) throws  Exception{
	// write your code here
        if (movieUrlFile.isFile() && movieUrlFile.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(movieUrlFile));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTXT = null;

            while ((lineTXT = bufferedReader.readLine()) != null)
            {
                String url =  lineTXT.toString();
                urlArray.add(url);
            }
            System.out.println("COUNT:" + urlArray.size());
            read.close();
        } else {
            System.out.println("找不到文件");
        }

        if (filterFile.isFile() && filterFile.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(filterFile));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTXT = null;

            while ((lineTXT = bufferedReader.readLine()) != null)
            {
                String url = lineTXT.toString();

                if (urlArray.contains(url)) {
                    urlArray.remove(url);
                }

            }


        } else {
            System.out.println("找不到文件");
        }


        BufferedWriter bufferWritter = null;
        bufferWritter = new BufferedWriter(new FileWriter(movieUrlFileNew, true));
        for (int i = 0 ;i<urlArray.size();i++) {
            bufferWritter.write(urlArray.get(i) + "\n");
        }


    }
}
