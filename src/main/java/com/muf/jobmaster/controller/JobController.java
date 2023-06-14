package com.muf.jobmaster.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muf.jobmaster.model.JobModel;
import com.muf.jobmaster.model.JobModelDTO;
import com.muf.jobmaster.repository.JobRepo;
import com.muf.jobmaster.service.JobMasterService;
import com.muf.jobmaster.utils.ApplicationException;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobMasterService jobService;
    private final JobRepo jobRepository;

    public JobController(JobMasterService jobService, JobRepo jobRepository){
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String testConnection() throws Exception{
		String index = "";
		
		try {
			index = "Service MDM - Job Master";
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return index;
	}

    @RequestMapping(value = "/insertJob", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> insertJobMaster(@RequestBody JobModel jobModel) throws Exception{
        
        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();
        int cekJobCode = jobRepository.cekKodeJob(jobModel.getEmpl_job_code());
        
        try{
            if(cekJobCode != 0){
                result.put("message", "Data job sudah ada");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            } else {
                dataJobMaster = jobService.insertJob(jobModel);
                result.put("message", "Data berhasil ditambahkan");
                result.put("data", dataJobMaster);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }catch(ApplicationException e){
            result.put("status", false);
			result.put("message", e.getMessage());
			return new org.springframework.http.ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateJobMaster(@RequestBody JobModel jobModel){

        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();

        try{
            dataJobMaster = jobService.updateJob(jobModel);
            result.put("message", "Data berhasil diubah");
            result.put("data", dataJobMaster);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(ApplicationException e){
            result.put("status", false);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> deleteJob(@RequestBody JobModel jobModel){

        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();

        try{
            dataJobMaster = jobService.deleteJob(jobModel);
            result.put("message", "Data berhasil dihapus");
            result.put("data", dataJobMaster);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(ApplicationException e){
            result.put("status", false);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/allJob", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllJob(){

        Map<String, Object> result = new HashMap<>();
        List<JobModel> listJob = new ArrayList<>();

        try{
            listJob = jobService.getAllDataJob();
            result.put("status", true);
            result.put("data", listJob);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(ApplicationException e){
            result.put("status", false);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/insertUpdate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> insertUpdateJob(@RequestBody JobModel jobModel) throws Exception{

        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();
        int dataJobByCode = 0;

        try{

            dataJobByCode = jobRepository.cekKodeJob(jobModel.getEmpl_job_code());

            if(dataJobByCode == 1){
                dataJobMaster = jobService.insertUpdateJob(jobModel);
                result.put("message", "Data berhasil diubah");
                result.put("data", dataJobMaster);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                dataJobMaster = jobService.insertUpdateJob(jobModel);
                result.put("message", "Data berhasil ditambahkan");
                result.put("data", dataJobMaster);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }

            
        }catch(ApplicationException e){
            result.put("status", false);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }
    
}
