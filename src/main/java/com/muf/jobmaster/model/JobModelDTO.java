package com.muf.jobmaster.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobModelDTO {

    public String empl_job_code;
    public String empl_job_desc;
    public String empl_log_id;
    public Long empl_deleted;
    public String empl_job_status;
    public String empl_com_id;
    public String empl_job_notes;
    public Long empl_flag_pool;
    public String msg;
    
}
