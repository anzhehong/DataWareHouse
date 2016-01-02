package com.Warehouse.DAO;

import com.Warehouse.entity.Staff;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class StaffDAOImp extends GeneralDAOImp<Staff> implements StaffDAO {
    public StaffDAOImp()
    {
        super(Staff.class);
    }
}
