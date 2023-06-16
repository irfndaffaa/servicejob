package com.muf.jobmaster.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muf.jobmaster.model.JobModel;
import com.muf.jobmaster.repository.JobRepo;
import com.muf.jobmaster.service.JobMasterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl implements JobMasterService {

    private final JobRepo jobRepository;
    private final Environment env;

    public JobServiceImpl (JobRepo jobRepository, Environment env){
        this.jobRepository = jobRepository;
        this.env = env;
    }

    @Override
    public JobModel insertJob(JobModel jobModel) throws Exception {
        
        JobModel dataJobModel = new JobModel();
        String logid = "[553F2C26-7232-307A-E044-00144F67D22C]";

        try{

            dataJobModel.setEmplJobCode(jobModel.getEmplJobCode());
            dataJobModel.setEmplJobDesc(jobModel.getEmplJobDesc());
            dataJobModel.setEmplLogId(logid);
            dataJobModel.setEmplDeleted(jobModel.getEmplDeleted());
            dataJobModel.setEmplJobStatus(jobModel.getEmplJobStatus());
            dataJobModel.setEmplComId(jobModel.getEmplComId());
            dataJobModel.setEmplJobNotes(jobModel.getEmplJobNotes());
            dataJobModel.setEmplFlagPool(jobModel.getEmplFlagPool());

            jobRepository.save(dataJobModel);

        } catch(Exception e){
            log.error(e.getMessage(), e);
        }

        return dataJobModel;

    }

    @Override
    public List<JobModel> getAllDataJob() {
        return jobRepository.findAllOrderByEmplJobCodeAsc();
    }

    // @Override
    // public JobModel updateJob(JobModel jobModel) {

        

    //     return null;
    // }

    
}
