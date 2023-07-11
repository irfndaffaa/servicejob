package com.muf.jobmaster.service;

import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;

import com.muf.jobmaster.dto.JobDTO;
import com.muf.jobmaster.model.JobModel;

public interface JobMasterService {

    public JobModel insertJob(JobDTO jobModelDto) throws Exception;
    public JobModel updateJob(JobDTO jobModelDto)throws Exception;
    public List<JobModel> getAllDataJob();
    public JobModel activationJob(JobDTO jobModelDto);
    public JobModel getJobByCode(JobDTO jobModelDTO);
    // public JobModel insertUpdateJob(JobModel jobModel) throws Exception;
    
}
