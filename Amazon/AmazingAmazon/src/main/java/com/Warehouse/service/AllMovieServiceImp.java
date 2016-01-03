package com.Warehouse.service;

import com.Warehouse.DAO.*;
import com.Warehouse.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fowafolo on 16/1/1.
 */

@Service
@Transactional
public class AllMovieServiceImp implements AllMovieService {

    @Autowired
    private AllMovieDAO allMovieDAO;
    @Autowired
    private Movie_1DAO movie_1DAO;
    @Autowired
    private Movie_2DAO movie_2DAO;
    @Autowired
    private Movie_3DAO movie_3DAO;

    @Autowired
    private MovieStyleDAO movieStyleDAO;

    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private MovieStaffDAO movieStaffDAO;

    public void insertWithIndex(AllMovie movie,int index)
    {
        switch (index)
        {
            case 1:
                Movie_1 movie_1 = new Movie_1();
                movie_1.setMovieId(movie.getMovieId());
                movie_1.setMovieVersion(movie.getMovieVersion());
                movie_1.setYear(movie.getYear());
                movie_1.setMonth(movie.getMonth());
                movie_1.setDay(movie.getDay());
                movie_1.setStyle(movie.getStyle());
                movie_1.setProductId(movie.getProductId());
                movie_1.setMovieName(movie.getMovieName());
                movie_1DAO.insert(movie_1);
                break;
            case 2:
                Movie_2 movie_2 = new Movie_2();
                movie_2.setMovieId(movie.getMovieId());
                movie_2.setMovieVersion(movie.getMovieVersion());
                movie_2.setYear(movie.getYear());
                movie_2.setMonth(movie.getMonth());
                movie_2.setDay(movie.getDay());
                movie_2.setStyle(movie.getStyle());
                movie_2.setProductId(movie.getProductId());
                movie_2.setMovieName(movie.getMovieName());
                movie_2DAO.insert(movie_2);
                break;
            case 3:
                Movie_3 movie_3 = new Movie_3();
                movie_3.setMovieId(movie.getMovieId());
                movie_3.setMovieVersion(movie.getMovieVersion());
                movie_3.setYear(movie.getYear());
                movie_3.setMonth(movie.getMonth());
                movie_3.setDay(movie.getDay());
                movie_3.setStyle(movie.getStyle());
                movie_3.setProductId(movie.getProductId());
                movie_3.setMovieName(movie.getMovieName());
                movie_3DAO.insert(movie_3);
                break;
            default:
                break;
        }
    }

    public void insertMovieInfo(ArrayList<AllMovie> tableMovieArray) {
//        allMovieDAO.insert(allMovie);
//        System.out.println("insertSuccess");

        for (int i = 0; i< tableMovieArray.size(); i++)
        {
//            String talbeIndex = String.valueOf((i%3+1));
            AllMovie allMovie = tableMovieArray.get(i);
            int movieId = allMovie.getMovieId();
            String productId = allMovie.getProductId();
            String movieName = null;
            try {
                movieName = recover(allMovie.getMovieName());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            String movieVersion = null;
            try {
                movieVersion = recover(allMovie.getMovieVersion());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            String style = null;
            try {
                style = recover(allMovie.getStyle());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            String year = allMovie.getYear();
            String month = allMovie.getMonth();
            String day = allMovie.getDay();

            AllMovie hAllMovie = new AllMovie();
            hAllMovie.setMovieId(movieId);
            hAllMovie.setMovieName(movieName);
            hAllMovie.setProductId(productId);
            hAllMovie.setYear(year);
            hAllMovie.setMonth(month);
            hAllMovie.setDay(day);
            hAllMovie.setMovieVersion(movieVersion);
            hAllMovie.setStyle(style);

            switch (i%3)
            {
                case 0:
                    insertWithIndex(hAllMovie,1);
                    break;
                case 1:
                    insertWithIndex(hAllMovie,2);
                    break;
                case 2:
                    insertWithIndex(hAllMovie,3);
                    break;
                default:
                    break;
            }

//            System.out.println("我分开插Movie");
        }
    }

    public void insertMovieStyle(ArrayList<MovieStyle> arrayList) {
        for (int i = 0; i< arrayList.size(); i++)
        {
//            System.out.println(arrayList.get(i).getMovieId());
            MovieStyle movieStyle = arrayList.get(i);
            movieStyle.setId(getId());
            try {
                movieStyle.setMovieStyle(recover(movieStyle.getMovieStyle()));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            movieStyleDAO.insert(movieStyle);
//            System.out.println("我插Style");
        }
//        System.out.println("ll");
    }

    public void insertStaff(ArrayList<Staff> arrayList) {
        for (int i = 0; i< arrayList.size(); i++)
        {
            Staff staff = arrayList.get(i);
            String name = staff.getStaffName();
            try {
                name = recover(name);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            staff.setStaffName(name);
//            System.out.println(name);
            staffDAO.insert(staff);
//            System.out.println("我插Staff");
//            System.out.println(staff.getStaffName());
        }
    }

    public void insertMovieStaff(ArrayList<MovieStaff> arrayList) {
        for (int i = 0; i< arrayList.size(); i++)
        {
//            System.out.println(arrayList.get(i).getMovieId());
            MovieStaff movieStaff = arrayList.get(i);
            movieStaff.setId(getId());

            movieStaffDAO.insert(movieStaff);
//            System.out.println("我插movie staff");
        }
    }


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

    public static String recover(String str) throws Throwable {
        return new String(str.getBytes(), "UTF-8");
    }
}
