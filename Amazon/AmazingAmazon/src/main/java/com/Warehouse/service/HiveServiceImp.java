package com.Warehouse.service;

import com.Warehouse.DAO.SearchMovieDAO;
import org.hibernate.hql.spi.FilterTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.*;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: 下午10:56
 */

@Service
@Transactional
public class HiveServiceImp implements HiveService {

    @Autowired
    private SearchMovieDAO searchMovieDAO;

    private static final String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static final String connection = "jdbc:hive2://10.0.1.29:10000/DataWarehouseL";
    private static final String userName = "bruno";
    private static final String password ="";
    private  static  int count = 0;


    @Override
    public void transHqlToSql(String hql) {
        SessionFactoryImpl sfi = (SessionFactoryImpl) searchMovieDAO.getSessionFactory();
        QueryTranslatorFactory qtf = sfi.getSettings().getQueryTranslatorFactory();
        FilterTranslator qt = qtf.createFilterTranslator(hql, hql, null, sfi);
        try {
            String sql = hqlToSql(hql,sfi);
            System.out.println("sql: "+sql);
        } catch (Exception e) {
            e.printStackTrace();
        }



//        qt.compile(null, false);
//        System.out.println(" hql==>sql:  "+qt.getSQLString());
        hiveQuery(qt.getSQLString());
    }


    protected static String hqlToSql(String hql,
                              org.hibernate.SessionFactory sessionFactory) throws Exception {
        org.hibernate.hql.internal.ast.QueryTranslatorImpl queryTranslator = new org.hibernate.hql.internal.ast.QueryTranslatorImpl(
                hql, hql, java.util.Collections.EMPTY_MAP,
                (org.hibernate.engine.spi.SessionFactoryImplementor) sessionFactory);

        queryTranslator.compile(java.util.Collections.EMPTY_MAP, false);


        hiveQuery(queryTranslator.getSQLString());
        return queryTranslator.getSQLString();
    }

    public  static  void  hiveQuery(String sql)
    {
        try
        {
            Class.forName(driverName);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        try
        {
            Connection con = DriverManager.getConnection(connection, userName, password);
            java.sql.Statement statement = con.createStatement();
            long startTime = System.currentTimeMillis();
            ResultSet res = statement.executeQuery(sql);
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;
            ResultSetMetaData resultSetMetaData = res.getMetaData();
            // loop get data example:

            while (res.next()) {
                System.out.println(res.getString(1));
            }
            release(con,statement,res);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public  static  void release(Connection connection, Statement statement, ResultSet resultSet)
    {
        if (resultSet !=null)
        {
            try{
                resultSet.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally {
                resultSet = null;
            }

        }
        if (connection !=null)
        {
            try{
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally {
                connection = null;
            }

        }
        if (statement !=null)
        {
            try{
                statement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally {
                statement = null;
            }

        }

    }
}
