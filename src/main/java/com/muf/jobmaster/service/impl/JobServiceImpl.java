package com.muf.jobmaster.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muf.jobmaster.dto.JobDTO;
import com.muf.jobmaster.model.JobModel;
import com.muf.jobmaster.model.LogModel;
import com.muf.jobmaster.repository.JobRepo;
import com.muf.jobmaster.repository.LogRepo;
import com.muf.jobmaster.service.JobMasterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl implements JobMasterService {

    private final JobRepo jobRepository;
    private final LogRepo logRepository;
    private final Environment env;

    public JobServiceImpl (JobRepo jobRepository, LogRepo logRepository, Environment env){
        this.jobRepository = jobRepository;
        this.logRepository = logRepository;
        this.env = env;
    }

    @Override
    public JobModel insertJob(JobDTO jobModelDto) throws Exception {
        
        JobModel dataJobModel = new JobModel();
        LogModel dataLog = new LogModel();
        
        UUID uuid = UUID.randomUUID(); // generate new random uuid
        String logid = "[" +uuid.toString().toUpperCase() + "]"; // convert uuid into string as logid
        String memo = "I|CONTROLLER_INSERT_JOB_MASTER|PROSES INSERT JOB|INSERT JOB DENGAN KODE JOB = " + jobModelDto.getEmplJobCode() + " & DESKRIPSI JOB = " + jobModelDto.getEmplJobDesc() + " |TABLE MST_EMPL_JOB";

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

            dataLog.setLogid(logid);
            dataLog.setUserid(jobModelDto.getUserId());
            dataLog.setMemo(memo);
            dataLog.setIpAddr(jobModelDto.getIpAddr());
            dataLog.setBranchCode(jobModelDto.getBranchCode());

            logRepository.save(dataLog);

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
	public JobModel updateJob(JobDTO jobModelDTO) throws Exception{
		
		JobModel dataJobModel = jobRepository.getJobData(jobModelDTO.getEmplJobCode());
        LogModel dataLog = new LogModel();

        UUID uuid = UUID.randomUUID(); // generate new random uuid
        String logid = "[" +uuid.toString().toUpperCase() + "]"; // convert uuid into string as logid
        String memo = "U|CONTROLLER_UPDATE_JOB_MASTER|PROSES UPDATE DETAIL JOB|UPDATE JOB DENGAN KODE JOB = " + jobModelDTO.getEmplJobCode() + " & DESKRIPSI JOB = " + jobModelDTO.getEmplJobDesc() + " |TABLE MST_EMPL_JOB";
		try {
		
            dataJobModel.setEmplJobDesc(jobModelDTO.getEmplJobDesc());
            dataJobModel.setEmplLogId(logid);
            dataJobModel.setEmplJobStatus(jobModelDTO.getEmplJobStatus());
            dataJobModel.setEmplComId(jobModelDTO.getEmplComId());
            dataJobModel.setEmplJobNotes(jobModelDTO.getEmplJobNotes());
            dataJobModel.setEmplFlagPool(jobModelDTO.getEmplFlagPool());
                
            jobRepository.save(dataJobModel);

            dataLog.setLogid(logid);
            dataLog.setUserid(jobModelDTO.getUserId());
            dataLog.setMemo(memo);
            dataLog.setIpAddr(jobModelDTO.getIpAddr());
            dataLog.setBranchCode(jobModelDTO.getBranchCode());

            logRepository.save(dataLog);

    	} catch(Exception e){
    		log.error(e.getMessage(), e);
    	}
		return dataJobModel;
	}
    
    @Override
   	public JobModel activationJob(JobDTO jobModelDTO){
   		
   		JobModel dataJobModel = jobRepository.getJobData(jobModelDTO.getEmplJobCode());
        LogModel dataLog = new LogModel();

        UUID uuid = UUID.randomUUID(); // generate new random uuid
        String logid = "[" +uuid.toString().toUpperCase() + "]"; // convert uuid into string as logid
        String memo = "U|CONTROLLER_DELETE_JOB_MASTER|PROSES DELETE ATAU AKTIVASI JOB|PERUBAHAN STATUS AKTIF JOB DENGAN KODE JOB = " + jobModelDTO.getEmplJobCode() + " & DESKRIPSI JOB = " + jobModelDTO.getEmplJobDesc() + " |TABLE MST_EMPL_JOB";
   		
   		try {
   			
            dataJobModel.setEmplDeleted(jobModelDTO.getEmplDeleted());
            jobRepository.save(dataJobModel);

            dataLog.setLogid(logid);
            dataLog.setUserid(jobModelDTO.getUserId());
            dataLog.setMemo(memo);
            dataLog.setIpAddr(jobModelDTO.getIpAddr());
            dataLog.setBranchCode(jobModelDTO.getBranchCode());

            logRepository.save(dataLog);
   		 } catch(Exception e){
   	            log.error(e.getMessage(), e);
   	        }
   		
   		return dataJobModel;
   	}

    @Override
    public JobModel getJobByCode(JobDTO jobModelDTO) {
        return jobRepository.findByEmplJobCodeAndEmplDeletedIsFalse(jobModelDTO.getEmplJobCode());
    }

    
}
