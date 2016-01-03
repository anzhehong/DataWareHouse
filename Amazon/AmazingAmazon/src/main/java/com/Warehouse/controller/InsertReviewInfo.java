package com.Warehouse.controller;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.entity.MovieStaff;
import com.Warehouse.entity.MovieStyle;
import com.Warehouse.entity.Staff;
import com.Warehouse.service.AllMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: 下午10:31
 */

@Controller
public class InsertReviewInfo {

    @Autowired
    AllMovieService allMovieService;

    private static File movieInfoPath = new File("/Users/fowafolo/Desktop/warehouse/CleanedData/outFile5.txt");

    private static ArrayList<AllMovie> tableMovieArray = new ArrayList();
    private static ArrayList<Staff> tableMovieStaffArray = new ArrayList();
    private static ArrayList<MovieStyle> tableMovieStyleArray = new ArrayList();
    private static ArrayList<MovieStaff> tableMovieStaffRelatioinArray = new ArrayList();

    static int staffCount = 0;

    public static void main(String[] args) throws SQLException,Exception {

//        if (movieInfoPath.isFile() && movieInfoPath.exists())
//        {
//            //TODO: read
//            System.out.println("Successfully access file: " + movieInfoPath);
//
//            InputStreamReader reader = new InputStreamReader(new FileInputStream(movieInfoPath));
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String tempLine = null;
//            String[] tempList = null;
//
//            while ((tempLine = bufferedReader.readLine()) != null)
//            {
//                int MovieId;
//                String ProductId;
//                String MovieName;
//                String MovieStyle;
//                String Starring;
//                String Actors;
//                String Directors;
//                String Format;
//                String Year;
//                String Month;
//                String Day;
//
//                tempList = tempLine.split("\t");
//                MovieId = Integer.parseInt(tempList[0]);
//                ProductId = tempList[1];
//                MovieName = tempList[2];
//                MovieStyle = tempList[3];
//                Starring = tempList[4];
//                Actors = tempList[5];
//                Directors = tempList[6];
//                Format = tempList[7];
//                Year = tempList[8];
//                Month = tempList[9];
//                Day = tempList[10];
//
//                // temp Movie info
////               System.out.println(Year + " " +Day + " " +Month);
////               System.out.println("size:" + tableMovieArray.size());
////               System.out.println(ProductId);
//                AllMovie tempAllMovie = new AllMovie();
//                tempAllMovie.setMovieId(MovieId);
//                tempAllMovie.setProductId(ProductId);
//                tempAllMovie.setMovieName(MovieName);
//                tempAllMovie.setStyle(MovieStyle);
//                tempAllMovie.setMovieVersion(Format);
//                if (Year.equals(null) || Year.equals("null")) {
//                    tempAllMovie.setYear("1900");
//                }else {
//                    String temp = Year.substring(0,4);
//                    tempAllMovie.setYear(temp);
//                }
//                if (Month.equals(null) || Month.equals("null")) {
//                    tempAllMovie.setMonth("01");
//                }else {
//                    tempAllMovie.setMonth(Month);
//                }
//                if (Day.equals(null) || Day.equals("null")) {
//                    tempAllMovie.setDay(Day);
//                }else {
//                    tempAllMovie.setDay(Day);
//                }
//                tableMovieArray.add(tempAllMovie);
//
//                // temp Staff info
//                // TODO: 把staff id 设置为自增？
//
//
//
//                //starings
//                ArrayList<Staff> staringList = new ArrayList<Staff>();
//                if (!(Starring.equals(null)||Starring.equals("null")))
//                {
//                    String[] tempStarings = Starring.split(",");
//                    for (int i = 0; i < tempStarings.length; i++)
//                    {
//                        // index = -1代表这个人不存在，不为-1返回已经存在的staffid
//                        int flag = isStaffExistInArray(tempStarings[i],staringList);
//                        if (flag == -1)
//                        {
//                            Staff tempStaff = new Staff();
//                            MovieStaff tempMovieStaff = new MovieStaff();
//                            int tempId = getId();
//                            tempStaff.setStaffId(tempId);
//                            tempStaff.setStaffJob(2);
//                            tempStaff.setStaffName(tempStarings[i]);
//                            tableMovieStaffArray.add(tempStaff);
//                            staringList.add(tempStaff);
//
//                            tempMovieStaff.setMovieId(MovieId);
//                            tempMovieStaff.setStaffId(tempId);
//                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
//                        }else { //已经存在，关联id
//                            MovieStaff tempMovieStaff = new MovieStaff();
//                            tempMovieStaff.setMovieId(MovieId);
//                            tempMovieStaff.setStaffId(flag);
//                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
//                        }
//                    }
//                    staffCount++;
////                    System.out.println("staffCount: "+staffCount);
//                }
//
//
//
//                // Actors
//                ArrayList<Staff> actorsList = new ArrayList<Staff>();
//                if (!(Actors.equals(null)||Actors.equals("null")))
//                {
//                    String[] tempActors = Actors.split(",");
//                    for (int i = 0; i < tempActors.length; i++)
//                    {
//                        // index = -1代表这个人不存在，不为-1返回已经存在的staffid
//                        int flag = isStaffExistInArray(tempActors[i],actorsList);
//                        if (flag == -1)
//                        {
//                            Staff tempStaff = new Staff();
//                            MovieStaff tempMovieStaff = new MovieStaff();
//                            int tempId = getId();
//                            tempStaff.setStaffId(tempId);
//                            tempStaff.setStaffJob(1);
//                            tempStaff.setStaffName(tempActors[i]);
//                            tableMovieStaffArray.add(tempStaff);
//                            actorsList.add(tempStaff);
//
//                            tempMovieStaff.setMovieId(MovieId);
//                            tempMovieStaff.setStaffId(tempId);
//                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
//                        }else { //已经存在，关联id
//                            MovieStaff tempMovieStaff = new MovieStaff();
//                            tempMovieStaff.setMovieId(MovieId);
//                            tempMovieStaff.setStaffId(flag);
//                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
//                        }
//                    }
//                    staffCount++;
//                    System.out.println("staffCount: "+staffCount);
//                }
//
//                // Directors
//                ArrayList<Staff> directorList = new ArrayList<Staff>();
//                if (!(Directors.equals(null)||Directors.equals("null")))
//                {
//                    String[] tempDirectors = Actors.split(",");
//                    for (int i = 0; i < tempDirectors.length; i++)
//                    {
//                        // index = -1代表这个人不存在，不为-1返回已经存在的staffid
//                        int flag = isStaffExistInArray(tempDirectors[i],directorList);
//                        if (flag == -1)
//                        {
//                            Staff tempStaff = new Staff();
//                            MovieStaff tempMovieStaff = new MovieStaff();
//                            int tempId = getId();
//                            tempStaff.setStaffId(tempId);
//                            tempStaff.setStaffJob(0);
//                            tempStaff.setStaffName(tempDirectors[i]);
//                            tableMovieStaffArray.add(tempStaff);
//                            directorList.add(tempStaff);
//
//                            tempMovieStaff.setMovieId(MovieId);
//                            tempMovieStaff.setStaffId(tempId);
//                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
//                        }else { //已经存在，关联id
//                            MovieStaff tempMovieStaff = new MovieStaff();
//                            tempMovieStaff.setMovieId(MovieId);
//                            tempMovieStaff.setStaffId(flag);
//                            tableMovieStaffRelatioinArray.add(tempMovieStaff);
//                        }
//                    }
//                    staffCount++;
//                    System.out.println("staffCount: "+staffCount);
//                }
//
//                // movie styles
//
//                if (!(MovieStyle.equals(null) || MovieStyle.equals("null")))
//                {
//
//                    String [] tempStyles = MovieStyle.split(",");
//                    for (int i = 0; i < tempStyles.length; i ++)
//                    {
//                        MovieStyle tempStyle = new MovieStyle();
//                        tempStyle.setMovieId(MovieId);
//                        tempStyle.setMovieStyle(tempStyles[i]);
//                        tableMovieStyleArray.add(tempStyle);
//
//                    }
//                }else {
//                    MovieStyle tempStyle = new MovieStyle();
//                    tempStyle.setMovieId(MovieId);
//                    tempStyle.setMovieStyle("null");
//                    tableMovieStyleArray.add(tempStyle);
//                }
//
//            }
//
//        }
//        else {
//            System.out.println("Cannot find this file!");
//        }
//
//
//        ApplicationContext beanFactory;
//        beanFactory = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
//        InsertMovieInfo insertMovieInfo = (InsertMovieInfo)beanFactory.getBean("main");
////        insertMovieInfo//TODO:
//        insertMovieInfo.insertToTable();
//        System.out.println("success");





    }


    public void insertToTable()
    {
        //movie info
//        allMovieService.insertMovieInfo(tableMovieArray);

        //movie style info
//        allMovieService.insertMovieStyle(tableMovieStyleArray);

        //staff
//        allMovieService.insertStaff(tableMovieStaffArray);

        //movie staff
//        allMovieService.insertMovieStaff(tableMovieStaffRelatioinArray);
    }





}