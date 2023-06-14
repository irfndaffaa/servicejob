package com.muf.jobmaster.service;

import java.util.List;

import com.muf.jobmaster.model.JobModel;
import com.muf.jobmaster.model.JobModelDTO;

public interface JobMasterService {

    public JobModel insertJob(JobModel jobModel) throws Exception;
    public JobModel updateJob(JobModel jobModel);
    public JobModel deleteJob(JobModel jobModel);
    public List<JobModel> getAllDataJob();
    public JobModel insertUpdateJob(JobModel jobModel) throws Exception;
    
}
