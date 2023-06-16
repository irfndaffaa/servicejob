package com.muf.jobmaster.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MST_EMPL_JOB_DEV",schema = "MUFPARAM")
@Getter
@Setter
public class JobModel {

    @Id
    public String emplJobCode;
    public String emplJobDesc;
    public String emplLogId;
    public Long emplDeleted;
    public String emplJobStatus;
    public String emplComId;
    public String emplJobNotes;
    public Long emplFlagPool;
    
}
