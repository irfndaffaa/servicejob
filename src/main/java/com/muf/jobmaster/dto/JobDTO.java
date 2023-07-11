package com.muf.jobmaster.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {

    public String emplJobCode;
    public String emplJobDesc;
    public String emplLogId;
    public Boolean emplDeleted;
    public String emplJobStatus;
    public String emplComId;
    public String emplJobNotes;
    public Long emplFlagPool;
    public String insertBy;
    public String updateBy;
    public String msg;
    public String logId;
    public Date logDate;
    public String userId;
    public String memo;
    public String ipAddr;
    public String branchCode;
    
}
