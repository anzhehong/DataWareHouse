package com.Warehouse.DAO;

import com.Warehouse.entity.Styles;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: 下午6:36
 */

@Repository
public class StylesDAOImp extends GeneralDAOImp<Styles> implements StylesDAO {

    public StylesDAOImp()
    {
        super(Styles.class);
    }
}
