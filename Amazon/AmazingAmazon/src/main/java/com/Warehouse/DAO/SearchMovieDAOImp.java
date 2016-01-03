package com.Warehouse.DAO;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.entity.MovieStaff;
import com.Warehouse.entity.MovieStyle;
import com.Warehouse.entity.Staff;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ÉÏÎç1:15
 */

@Repository
public class SearchMovieDAOImp extends GeneralDAOImp<AllMovie> implements SearchMovieDAO {

    public SearchMovieDAOImp()
    {
        super(AllMovie.class);
    }

    @Autowired
    private MovieStyleDAO movieStyleDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private MovieStaffDAO movieStaffDAO;


    @Override
    public ArrayList<String> getAllMovieStyles() {
        ArrayList<MovieStyle> styles = (ArrayList<MovieStyle>) movieStyleDAO.queryAll();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i< styles.size(); i++)
        {
            arrayList.add(styles.get(i).getMovieStyle());
        }
        ArrayList<String> strings = new ArrayList<String>();
        for (int j = 0; j<arrayList.size(); j++)
        {
            if (!strings.contains(arrayList.get(j)))
                strings.add(arrayList.get(j));
        }

        return strings;
    }

    public ArrayList<AllMovie> findByMutiple(String name, String style, String starring, String actor, String director, String version, String year, String month, String day) {
        String hql = "from AllMovie where MovieName like :name and MovieVersion like :version and Style like:style and Year like:y and Month like:m and Day like:d and Starring like :starring and Actor like :actor and Director like :director";

        String hql2 = "from AllMovie where MovieName like ? and MovieVersion like ? and Style like ? and Year like ? and Month like ? and Day like ?";
//        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
//        query.setString("name","%"+name+"%");
//        query.setString("version","%"+version+"%");
//        query.setString("style","%"+style+"%");
//        query.setString("y","%"+year+"%");
//        query.setString("m","%"+month+"%");
//        query.setString("d","%"+day+"%");
//        query.setString("director","%"+director+"%");
//        query.setString("starring","%"+starring+"%");
//        query.setString("actor","%"+actor+"%");

        Query query2 = super.sessionFactory.getCurrentSession().createQuery(hql2);
        query2.setString(0,"%"+name+"%");
        query2.setString(1,"%"+version+"%");
        query2.setString(2,"%"+style+"%");
        query2.setString(3,"%"+year+"%");
        query2.setString(4,"%"+month+"%");
        query2.setString(5,"%"+day+"%");
//        query2.setString(,"%"++"%");
//        query2.setString(,"%"++"%");
//        query2.setString(,"%"++"%");
        return (ArrayList<AllMovie>) query2.list();
    }

    public List findByMovieName(String name) {

        String hql = "from AllMovie where MovieName = :m";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",name);

        return query.list();
    }

    public ArrayList<AllMovie> findByMovieStyle(String style) {
        String hql1 = "from MovieStyle where MovieStyle = :m";
        Query query1 = super.sessionFactory.getCurrentSession().createQuery(hql1);
        query1.setString("m",style);
        ArrayList<MovieStyle> styles = (ArrayList<MovieStyle>) query1.list();

        ArrayList<AllMovie> allMovieArrayList = new ArrayList<AllMovie>();
        for (int i = 0; i < styles.size(); i++)
        {
            AllMovie temp = queryByIntId(styles.get(i).getMovieId());
            allMovieArrayList.add(temp);
        }
        return allMovieArrayList;
    }

    public ArrayList<AllMovie> findByStarring(String starring) {
        String hql1 = "from Staff where StaffName = :m and StaffJob = :n";
        Query query1 = super.sessionFactory.getCurrentSession().createQuery(hql1);
        query1.setString("m",starring);
        query1.setInteger("n",1);
        ArrayList<Staff> staffs = (ArrayList<Staff>) query1.list();
        ArrayList<Integer> staffIds = new ArrayList<Integer>();

        for (int i =0 ; i < staffs.size(); i ++)
        {
            staffIds.add(staffs.get(i).getStaffId());
        }

        ArrayList<MovieStaff> movieStaffs = new ArrayList<MovieStaff>();
        for (int i = 0; i< staffIds.size(); i++)
        {
            System.out.println("aaaa");
            System.out.println(staffIds.get(i).intValue());
            int tempId = staffIds.get(i).intValue();
            MovieStaff tempStaff = movieStaffDAO.getMovieStaffArrayListByStaffId(tempId).get(0);
            movieStaffs.add(tempStaff);
        }

        ArrayList<AllMovie> allMovieArrayList = new ArrayList<AllMovie>();
        for (int i = 0; i< movieStaffs.size(); i++)
        {
            AllMovie allMovie = queryByIntId(movieStaffs.get(i).getMovieId());
            System.out.println(allMovie.getYear());
            allMovieArrayList.add(allMovie);
        }
        return allMovieArrayList;
    }

    public ArrayList<AllMovie> findByActor(String actor) {
        String hql1 = "from Staff where StaffName = :m and StaffJob = :n";
        Query query1 = super.sessionFactory.getCurrentSession().createQuery(hql1);
        query1.setString("m",actor);
        query1.setInteger("n",2);
        ArrayList<Staff> staffs = (ArrayList<Staff>) query1.list();
        ArrayList<Integer> staffIds = new ArrayList<Integer>();

        for (int i =0 ; i < staffs.size(); i ++)
        {
            staffIds.add(staffs.get(i).getStaffId());
        }

        ArrayList<MovieStaff> movieStaffs = new ArrayList<MovieStaff>();
        for (int i = 0; i< staffIds.size(); i++)
        {
            int tempId = staffIds.get(i).intValue();
            MovieStaff tempStaff = movieStaffDAO.getMovieStaffArrayListByStaffId(tempId).get(0);
            movieStaffs.add(tempStaff);
        }

        ArrayList<AllMovie> allMovieArrayList = new ArrayList<AllMovie>();
        for (int i = 0; i< movieStaffs.size(); i++)
        {
            AllMovie allMovie = queryByIntId(movieStaffs.get(i).getMovieId());
            allMovieArrayList.add(allMovie);
        }
        return allMovieArrayList;
    }

    public ArrayList<AllMovie> findByDirector(String director) {
        String hql1 = "from Staff where StaffName = :m and StaffJob = :n";
        Query query1 = super.sessionFactory.getCurrentSession().createQuery(hql1);
        query1.setString("m",director);
        query1.setInteger("n",0);
        ArrayList<Staff> staffs = (ArrayList<Staff>) query1.list();
        ArrayList<Integer> staffIds = new ArrayList<Integer>();

        for (int i =0 ; i < staffs.size(); i ++)
        {
            staffIds.add(staffs.get(i).getStaffId());
        }

        ArrayList<MovieStaff> movieStaffs = new ArrayList<MovieStaff>();
        for (int i = 0; i< staffIds.size(); i++)
        {
            int tempId = staffIds.get(i).intValue();
            MovieStaff tempStaff = movieStaffDAO.getMovieStaffArrayListByStaffId(tempId).get(0);
            movieStaffs.add(tempStaff);
        }

        ArrayList<AllMovie> allMovieArrayList = new ArrayList<AllMovie>();
        for (int i = 0; i< movieStaffs.size(); i++)
        {
            AllMovie allMovie = queryByIntId(movieStaffs.get(i).getMovieId());
            allMovieArrayList.add(allMovie);
        }
        return allMovieArrayList;
    }

    public ArrayList<AllMovie> findByVersion(String version) {
        String hql = "from AllMovie where MovieVersion like :m";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",version+"%");
        return (ArrayList<AllMovie>) query.list();
    }

    public ArrayList<AllMovie> findByTime(String year, String month, String day) {
        String hql = "from AllMovie where Year = :m and Month = :n and Day = :o";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",year);
        query.setString("n",month);
        query.setString("o",day);
        return (ArrayList<AllMovie>) query.list();
    }

    public ArrayList<AllMovie> findByYear(String year) {
        String hql = "from AllMovie where Year = :m";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",year);
        return (ArrayList<AllMovie>) query.list();
    }

    @Override
    public SessionFactory getSessionFactory() {

        return super.sessionFactory;
    }
}
