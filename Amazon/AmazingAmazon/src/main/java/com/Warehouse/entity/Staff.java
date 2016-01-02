package com.Warehouse.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fowafolo on 15/12/31.
 */

@Entity
@Table
public class Staff {

    @Id
    private int StaffId;
    private String StaffName;

    // 0表示导演;1表示主演;2表示演员
    private int StaffJob;

    public int getStaffId() {
        return StaffId;
    }

    public void setStaffId(int staffId) {
        StaffId = staffId;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public int getStaffJob() {
        return StaffJob;
    }

    public void setStaffJob(int staffJob) {
        StaffJob = staffJob;
    }
}
