package com.muf.jobmaster.service;

import java.util.List;

import com.muf.jobmaster.dto.JobDTO;
import com.muf.jobmaster.model.JobModel;

public interface JobMasterService {

    public JobModel insertJob(JobDTO jobModelDto) throws Exception;
    public JobModel updateJob(JobDTO jobModelDto);
    public List<JobModel> getAllDataJob();
    public JobModel deleteJob(JobDTO jobModelDto);
    // public JobModel insertUpdateJob(JobModel jobModel) throws Exception;
    
}
