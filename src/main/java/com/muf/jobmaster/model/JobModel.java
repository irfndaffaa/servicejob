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
    public String empl_job_code;
    public String empl_job_desc;
    public String empl_log_id;
    public Long empl_deleted;
    public String empl_job_status;
    public String empl_com_id;
    public String empl_job_notes;
    public Long empl_flag_pool;
    
}
