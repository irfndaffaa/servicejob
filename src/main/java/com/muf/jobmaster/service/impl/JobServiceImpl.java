package com.muf.jobmaster.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muf.jobmaster.dto.JobDTO;
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
    public JobModel insertJob(JobDTO jobModelDto) throws Exception {
        
        JobModel dataJobModel = new JobModel();
        String logid = "insert by: "+jobModelDto.getInsertBy();

        try{

            dataJobModel.setEmplJobCode(jobModelDto.getEmplJobCode());
            dataJobModel.setEmplJobDesc(jobModelDto.getEmplJobDesc());
            dataJobModel.setEmplLogId(logid);
            dataJobModel.setEmplDeleted(jobModelDto.getEmplDeleted());
            dataJobModel.setEmplJobStatus(jobModelDto.getEmplJobStatus());
            dataJobModel.setEmplComId(jobModelDto.getEmplComId());
            dataJobModel.setEmplJobNotes(jobModelDto.getEmplJobNotes());
            dataJobModel.setEmplFlagPool(jobModelDto.getEmplFlagPool());

            jobRepository.save(dataJobModel);

        } catch(Exception e){
            log.error(e.getMessage(), e);
        }

        return dataJobModel;

    }

    @Override
    public List<JobModel> getAllDataJob() {
        return jobRepository.findAll();
    }

    @Override
    public JobModel updateJob(JobDTO jobModelDto) {

        JobModel dataJobModel = jobRepository.findByEmplJobCodeAndEmplDeletedIsFalse(jobModelDto.getEmplJobCode());
        String logid = "Update By: " + jobModelDto.getUpdateBy();

        try{
            dataJobModel.setEmplJobDesc(jobModelDto.getEmplJobDesc());
            dataJobModel.setEmplLogId(logid);
            dataJobModel.setEmplJobStatus(jobModelDto.getEmplJobStatus());
            dataJobModel.setEmplComId(jobModelDto.getEmplComId());
            dataJobModel.setEmplJobNotes(jobModelDto.getEmplJobNotes());
            dataJobModel.setEmplFlagPool(jobModelDto.getEmplFlagPool());

            jobRepository.save(dataJobModel);

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }

        return dataJobModel;
    }

    @Override
    public JobModel deleteJob(JobDTO jobModelDto) {

        JobModel dataJobModel = jobRepository.findByEmplJobCodeAndEmplDeletedIsFalse(jobModelDto.getEmplJobCode());

        try{
            dataJobModel.setEmplDeleted(jobModelDto.getEmplDeleted());
            jobRepository.save(dataJobModel);
        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        
        return dataJobModel;
    }

    
}
