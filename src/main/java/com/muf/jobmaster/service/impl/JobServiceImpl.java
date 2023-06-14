package com.muf.jobmaster.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muf.jobmaster.model.JobModel;
import com.muf.jobmaster.model.JobModelDTO;
// import com.muf.jobmaster.model.JobModelDTO;
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

            dataJobModel.setEmpl_job_code(jobModel.getEmpl_job_code());
            dataJobModel.setEmpl_job_desc(jobModel.getEmpl_job_desc());
            dataJobModel.setEmpl_log_id(logid);
            dataJobModel.setEmpl_deleted(jobModel.getEmpl_deleted());
            dataJobModel.setEmpl_job_status(jobModel.getEmpl_job_status());
            dataJobModel.setEmpl_com_id(jobModel.getEmpl_com_id());
            dataJobModel.setEmpl_job_notes(jobModel.getEmpl_job_notes());
            dataJobModel.setEmpl_flag_pool(jobModel.getEmpl_flag_pool());

            jobRepository.save(dataJobModel);

        } catch(Exception e){
            log.error(e.getMessage(), e);
        }

        return dataJobModel;

    }

    @Override
    public JobModel updateJob(JobModel jobModelDto) {
        
        JobModel jobModel = new JobModel();
        String logid = "[553F2C26-7232-307A-E044-00144F67D22C]";

        try{
            JobModel dataJobModel = jobRepository.getJobData(jobModelDto.getEmpl_job_code());

            jobModel.setEmpl_job_code(dataJobModel.getEmpl_job_code());
            jobModel.setEmpl_job_desc(jobModelDto.getEmpl_job_desc());
            jobModel.setEmpl_log_id(logid);
            jobModel.setEmpl_deleted(dataJobModel.getEmpl_deleted());
            jobModel.setEmpl_job_status(jobModelDto.getEmpl_job_status());
            jobModel.setEmpl_com_id(jobModelDto.getEmpl_com_id());
            jobModel.setEmpl_job_notes(jobModelDto.getEmpl_job_notes());
            jobModel.setEmpl_flag_pool(jobModelDto.getEmpl_flag_pool());

            jobRepository.save(jobModel);

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }

        return jobModel;
    }

    @Override
    public JobModel deleteJob(JobModel jobModelDto) {

        JobModel jobModel = jobRepository.getJobData(jobModelDto.getEmpl_job_code());

        try{

            jobModel.setEmpl_deleted(jobModelDto.getEmpl_deleted());
            jobRepository.save(jobModel);

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }

        
        return jobModel;

    }

    @Override
    public List<JobModel> getAllDataJob() {
        
        List<JobModel> listJob = new ArrayList<>();

        try{
            List<JobModel> dataJob = jobRepository.getAllJobData();

            for(JobModel j : dataJob){

                JobModel jobModel = new JobModel();

                jobModel.setEmpl_job_code(j.getEmpl_job_code());
                jobModel.setEmpl_job_desc(j.getEmpl_job_desc());
                jobModel.setEmpl_log_id(j.getEmpl_log_id());
                jobModel.setEmpl_deleted(j.getEmpl_deleted());
                jobModel.setEmpl_job_status(j.getEmpl_job_status());
                jobModel.setEmpl_com_id(j.getEmpl_com_id());
                jobModel.setEmpl_job_notes(j.getEmpl_job_notes());
                jobModel.setEmpl_flag_pool(j.getEmpl_flag_pool());

                listJob.add(jobModel);

            }

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }

        return listJob;

    }

    @Override
    public JobModel insertUpdateJob(JobModel jobModel) throws Exception {

        JobModel dataJobModel = new JobModel();
        String logid = "[553F2C26-7232-307A-E044-00144F67D22C]";
        int cekJobByCode = 0; //flag untuk cek kode job apakah sudah ada atau belum

        try{

            cekJobByCode = jobRepository.cekKodeJob(jobModel.getEmpl_job_code());
            JobModel dataJobByCode = jobRepository.getJobData(jobModel.getEmpl_job_code()); //mengambil data job dari db

            if(cekJobByCode == 1){  //kondisi saat kode job sudah ada
                dataJobModel.setEmpl_job_code(dataJobByCode.getEmpl_job_code());
                dataJobModel.setEmpl_job_desc(jobModel.getEmpl_job_desc());
                dataJobModel.setEmpl_log_id(logid);
                dataJobModel.setEmpl_deleted(dataJobByCode.getEmpl_deleted());
                dataJobModel.setEmpl_job_status(jobModel.getEmpl_job_status());
                dataJobModel.setEmpl_com_id(jobModel.getEmpl_com_id());
                dataJobModel.setEmpl_job_notes(jobModel.getEmpl_job_notes());
                dataJobModel.setEmpl_flag_pool(jobModel.getEmpl_flag_pool());
                jobRepository.save(dataJobModel);
            }else{
                dataJobModel.setEmpl_job_code(jobModel.getEmpl_job_code());
                dataJobModel.setEmpl_job_desc(jobModel.getEmpl_job_desc());
                dataJobModel.setEmpl_log_id(logid);
                dataJobModel.setEmpl_deleted(jobModel.getEmpl_deleted());
                dataJobModel.setEmpl_job_status(jobModel.getEmpl_job_status());
                dataJobModel.setEmpl_com_id(jobModel.getEmpl_com_id());
                dataJobModel.setEmpl_job_notes(jobModel.getEmpl_job_notes());
                dataJobModel.setEmpl_flag_pool(jobModel.getEmpl_flag_pool());
                jobRepository.save(dataJobModel);
            }

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }

        
        return dataJobModel;
    }
    
}
