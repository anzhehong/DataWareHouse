package com.Warehouse.controller;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.entity.MovieStaff;
import com.Warehouse.entity.MovieStyle;
import com.Warehouse.entity.Staff;
import com.Warehouse.service.AllMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fowafolo on 15/12/31.
 */

@Controller
public class InsertMovieInfo {

    @Autowired
    AllMovieService allMovieService;

    private static File movieInfoPath = new File("/Users/fowafolo/Desktop/warehouse/CleanedData/outFile5.txt");

    private static ArrayList<AllMovie> tableMovieArray = new ArrayList();
    private static ArrayList<Staff> tableMovieStaffArray = new ArrayList();
    private static ArrayList<MovieStyle> tableMovieStyleArray = new ArrayList();
    private static ArrayList<MovieStaff> tableMovieStaffRelatioinArray = new ArrayList();

    static int staffCount = 0;

    public static void main(String[] args) throws SQLException,Exception {




        if (movieInfoPath.isFile() && movieInfoPath.exists())
        {
            //TODO: read
            System.out.println("Successfully access file: " + movieInfoPath);

            InputStreamReader reader = new InputStreamReader(new FileInputStream(movieInfoPath));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tempLine = null;
            String[] tempList = null;

            while ((tempLine = bufferedReader.readLine()) != null)
            {
                int MovieId;
                String ProductId;
                String MovieName;
                String MovieStyle;
                String Starring;
                String Actors;
                String Directors;
                String Format;
                String Year;
                String Month;
                String Day;

                tempList = tempLine.split("\t");
                MovieId = Integer.parseInt(tempList[0]);
                ProductId = tempList[1];
                MovieName = tempList[2];
                MovieStyle = tempList[3];
                Starring = tempList[4];
                Actors = tempList[5];
                Directors = tempList[6];
                Format = tempList[7];
                Year = tempList[8];
                Month = tempList[9];
                Day = tempList[10];

                // temp Movie info
//               System.out.println(Year + " " +Day + " " +Month);
//               System.out.println("size:" + tableMovieArray.size());
//               System.out.println(ProductId);
                AllMovie tempAllMovie = new AllMovie();
                tempAllMovie.setMovieId(MovieId);
                tempAllMovie.setProductId(ProductId);
                tempAllMovie.setMovieName(MovieName);
                tempAllMovie.setStyle(MovieStyle);
                tempAllMovie.setMovieVersion(Format);
                if (Year.equals(null) || Year.equals("null")) {
                    tempAllMovie.setYear("1900");
                }else {
                    String temp = Year.substring(0,4);
                    tempAllMovie.setYear(temp);
                }
                if (Month.equals(null) || Month.equals("null")) {
                    tempAllMovie.setMonth("01");
                }else {
                    tempAllMovie.setMonth(Month);
                }
                if (Day.equals(null) || Day.equals("null")) {
                    tempAllMovie.setDay(Day);
                }else {
                    tempAllMovie.setDay(Day);
                }
                tableMovieArray.add(tempAllMovie);

                // temp Staff info
                // TODO: 把staff id 设置为自增？



                //starings
                ArrayList<Staff> staringList = new ArrayList<Staff>();
                if (!(Starring.equals(null)||Starring.equals("null")))
                {
                    String[] tempStarings = Starring.split(",");
                    for (int i = 0; i < tempStarings.length; i++)
                    {
                        // index = -1代表这个人不存在，不为-1返回已经存在的staffid
                        int flag = isStaffExistInArray(tempStarings[i],staringList);
                        if (flag == -1)
                        {
                            Staff tempStaff = new Staff();
                            MovieStaff tempMovieStaff = new MovieStaff();
                            int tempId = getId();
                            tempStaff.setStaffId(tempId);
                            tempStaff.setStaffJob(2);
                            tempStaff.setStaffName(tempStarings[i]);
                            tableMovieStaffArray.add(tempStaff);
                            staringList.add(tempStaff);

                            tempMovieStaff.setMovieId(MovieId);
                            tempMovieStaff.setStaffId(tempId);
                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
                        }else { //已经存在，关联id
                            MovieStaff tempMovieStaff = new MovieStaff();
                            tempMovieStaff.setMovieId(MovieId);
                            tempMovieStaff.setStaffId(flag);
                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
                        }
                    }
                    staffCount++;
//                    System.out.println("staffCount: "+staffCount);
                }



                // Actors
                ArrayList<Staff> actorsList = new ArrayList<Staff>();
                if (!(Actors.equals(null)||Actors.equals("null")))
                {
                    String[] tempActors = Actors.split(",");
                    for (int i = 0; i < tempActors.length; i++)
                    {
                        // index = -1代表这个人不存在，不为-1返回已经存在的staffid
                        int flag = isStaffExistInArray(tempActors[i],actorsList);
                        if (flag == -1)
                        {
                            Staff tempStaff = new Staff();
                            MovieStaff tempMovieStaff = new MovieStaff();
                            int tempId = getId();
                            tempStaff.setStaffId(tempId);
                            tempStaff.setStaffJob(1);
                            tempStaff.setStaffName(tempActors[i]);
                            tableMovieStaffArray.add(tempStaff);
                            actorsList.add(tempStaff);

                            tempMovieStaff.setMovieId(MovieId);
                            tempMovieStaff.setStaffId(tempId);
                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
                        }else { //已经存在，关联id
                            MovieStaff tempMovieStaff = new MovieStaff();
                            tempMovieStaff.setMovieId(MovieId);
                            tempMovieStaff.setStaffId(flag);
                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
                        }
                    }
                    staffCount++;
                    System.out.println("staffCount: "+staffCount);
                }

                // Directors
                ArrayList<Staff> directorList = new ArrayList<Staff>();
                if (!(Directors.equals(null)||Directors.equals("null")))
                {
                    String[] tempDirectors = Actors.split(",");
                    for (int i = 0; i < tempDirectors.length; i++)
                    {
                        // index = -1代表这个人不存在，不为-1返回已经存在的staffid
                        int flag = isStaffExistInArray(tempDirectors[i],directorList);
                        if (flag == -1)
                        {
                            Staff tempStaff = new Staff();
                            MovieStaff tempMovieStaff = new MovieStaff();
                            int tempId = getId();
                            tempStaff.setStaffId(tempId);
                            tempStaff.setStaffJob(0);
                            tempStaff.setStaffName(tempDirectors[i]);
                            tableMovieStaffArray.add(tempStaff);
                            directorList.add(tempStaff);

                            tempMovieStaff.setMovieId(MovieId);
                            tempMovieStaff.setStaffId(tempId);
                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
                        }else { //已经存在，关联id
                            MovieStaff tempMovieStaff = new MovieStaff();
                            tempMovieStaff.setMovieId(MovieId);
                            tempMovieStaff.setStaffId(flag);
                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
                        }
                    }
                    staffCount++;
                    System.out.println("staffCount: "+staffCount);
                }

                // movie styles

                if (!(MovieStyle.equals(null) || MovieStyle.equals("null")))
                {

                    String [] tempStyles = MovieStyle.split(",");
                    for (int i = 0; i < tempStyles.length; i ++)
                    {
                        MovieStyle tempStyle = new MovieStyle();
                        tempStyle.setMovieId(MovieId);
                        tempStyle.setMovieStyle(tempStyles[i]);
                        tableMovieStyleArray.add(tempStyle);

                    }
                }else {
                    MovieStyle tempStyle = new MovieStyle();
                    tempStyle.setMovieId(MovieId);
                    tempStyle.setMovieStyle("null");
                    tableMovieStyleArray.add(tempStyle);
                }

            }

        }
       else {
           System.out.println("Cannot find this file!");
       }


        //test before insertion

//        System.out.println("size1:" + tableMovieArray.size());
//        System.out.println("size2:" + tableMovieStaffArray.size());
//        System.out.println("size3:" + tableMovieStaffRelatioinArray.size());
//        System.out.println("size4:" + tableMovieStyleArray.size());

        // test style
//        for (int i = 0 ; i<tableMovieStyleArray.size(); i ++) {
//            System.out.println(tableMovieStyleArray.get(i).getMovieId() + "\t" +tableMovieStyleArray.get(i).getMovieStyle());
//        }

        // test movie info
//        for (int i = 0 ; i<tableMovieArray.size(); i ++) {
//            System.out.println(tableMovieArray.get(i).getMovieId()+"\t"+tableMovieArray.get(i).getMovieName()+"\t"+tableMovieArray.get(i).getMovieVersion());
//        }

        // test staff info
//        for (int i = 0 ; i<tableMovieStaffArray.size(); i ++) {
//            System.out.println(tableMovieStaffArray.get(i).getStaffId() + "\t" + tableMovieStaffArray.get(i).getStaffName());
//        }

        // test staff relation
//        for (int i = 0 ; i<tableMovieStaffRelatioinArray.size(); i ++) {
//            System.out.println(tableMovieStaffRelatioinArray.get(i).getMovieId() + "\t" + tableMovieStaffRelatioinArray.get(i).getStaffId());
//            if (tableMovieStaffRelatioinArray.get(i).getMovieId()==1410338)
//            {
//                for (int j = 0; j < tableMovieStaffArray.size(); j ++)
//                {
//                    if (tableMovieStaffArray.get(j).getStaffId() == tableMovieStaffRelatioinArray.get(i).getStaffId())
//                    {
//                        System.out.println("哈哈："+ tableMovieStaffArray.get(j).getStaffId() + "\tStaffName:" +tableMovieStaffArray.get(j).getStaffName()+"\tjob:" + tableMovieStaffArray.get(j).getStaffJob());
//                    }
//                }
//
//            }
//        }


//        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//        String DB_URL = "jdbc:mysql://localhost/WarehouseFinal";
//        String USER_NAME = "root";
//        String USER_PASSWORD = "root";
//
//        Connection conn = null;
//        CallableStatement cstmt = null;
//
//
//
//        try {
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);
//            if (conn != null)
//                System.out.println("success");
//
//            //insert MovieInfo
//            for (int i = 0; i< tableMovieArray.size(); i++)
//            {
//                String talbeIndex = String.valueOf((i%3+1));
//                String tableName = "WarehouseFinal.Movie_"+talbeIndex;
//
//                AllMovie allMovie = tableMovieArray.get(i);
//                int movieId = allMovie.getMovieId();
//                String productId = allMovie.getProductId();
//                String movieName = allMovie.getMovieName();
//                String movieVersion = allMovie.getMovieVersion();
//                String style = allMovie.getMovieStyle();
//                String year = allMovie.getYear();
//                String month = allMovie.getMonth();
//                String day = allMovie.getDay();
////                String sql = "INSERT INTO "+tableName+" MovieId=" + String.valueOf(movieId)+",ProductId=" + productId+ ",MovieName="
////                        +movieName +",MovieVersion=" +movieVersion + ",Style=" +style +",Year="+  year +",Month=" +month + ",Day=" + day +";";
////                String sql = "INSERT INTO "+tableName + "('MovieId','ProductId','MovieName','MovieVersion', ' +  ;
////                System.out.println(sql);
//
//                HAllMovie hAllMovie = new HAllMovie();
//                hAllMovie.setMovieId(movieId);
//                hAllMovie.setMovieName(movieName);
//                hAllMovie.setYear(year);
//                hAllMovie.setMonth(month);
//                hAllMovie.setDay(day);
//                hAllMovie.setMovieVersion(movieVersion);
//                hAllMovie.setStyle(style);
//
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }



        ApplicationContext beanFactory;
        beanFactory = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
        InsertMovieInfo insertMovieInfo = (InsertMovieInfo)beanFactory.getBean("main");
//        insertMovieInfo//TODO:
        insertMovieInfo.insertToTable();
        System.out.println("success");




    }


    public void insertToTable()
    {
        //movie info
        allMovieService.insertMovieInfo(tableMovieArray);

        //movie style info
        allMovieService.insertMovieStyle(tableMovieStyleArray);

        //staff
        allMovieService.insertStaff(tableMovieStaffArray);

        //movie staff
        allMovieService.insertMovieStaff(tableMovieStaffRelatioinArray);
    }





    public static String filterNumber(String number)
    {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(number);
        String all = matcher.replaceAll("");
        return all;
    }

    // function to generate random primary integer key
    private static final AtomicInteger integer = new AtomicInteger(0);
    public static int getId() {
        long time = System.currentTimeMillis();
        StringBuilder str = new StringBuilder(20);
        str.append(time);
        int intValue = integer.getAndIncrement();
        if (integer.get() >= 10000) {
            integer.set(0);
        }
        if (intValue < 10) {
            str.append("000");
        } else if (intValue < 100) {
            str.append("00");
        } else if (intValue < 1000) {
            str.append("0");
        }
        str.append(intValue);
//        System.out.println("long:" + Long.parseLong(str.toString()));

        int temp = (int) Long.parseLong(str.toString());
        if (temp < 0)
            temp = -temp;

        return temp;
    }


    private static int isStaffExistInArray(String staffName,ArrayList<Staff> thisJobStaff)
    {
        // index = -1代表这个人不存在，不为-1返回已经存在的staffid
        int index = -1;
        for (int i = 0; i< thisJobStaff.size(); i ++)
        {
            if (thisJobStaff.get(i).getStaffName().equals(staffName))
            {
                index = thisJobStaff.get(i).getStaffId();
                return index;
            }
        }

        return index;
    }

}


