package com.muf.jobmaster.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muf.jobmaster.dto.JobDTO;
import com.muf.jobmaster.model.JobModel;
import com.muf.jobmaster.repository.JobRepo;
import com.muf.jobmaster.service.JobMasterService;
import com.muf.jobmaster.utils.ApplicationException;

@RestController
@RequestMapping("/jobMaster")
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

    @PostMapping
    public ResponseEntity<Map<String, Object>> insertJobMaster(@RequestBody JobDTO jobModelDto) throws Exception{
        
        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();
        int cekJobCode = jobRepository.cekKodeJob(jobModelDto.getEmplJobCode());
        int cekJobDesc = jobRepository.countByEmplJobDesc(jobModelDto.getEmplJobDesc());
        JobModel jobDesc = jobRepository.findByEmplJobDescAndEmplDeletedIsFalse(jobModelDto.getEmplJobDesc());
        
        try{
            if(cekJobCode != 0){
                result.put("app", "MDM Job Master");
                result.put("message", "Kode job sudah terdaftar");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            } else if(cekJobDesc != 0){
                result.put("app", "MDM Job Master");
                result.put("message", "Job "+ jobModelDto.getEmplJobDesc() + " telah terdaftar dengan kode job " + jobDesc.getEmplJobCode());
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            } else {
                dataJobMaster = jobService.insertJob(jobModelDto);
                result.put("app", "MDM Job Master");
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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllJob(){

        Map<String, Object> result = new HashMap<>();
        List<JobModel> listJob = new ArrayList<>();

        try{
            listJob = jobService.getAllDataJob();
            result.put("app", "MDM Job Master");
            result.put("status", true);
            result.put("data", listJob);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(ApplicationException e){
            result.put("status", false);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    // @PutMapping
	public ResponseEntity<Map<String, Object>> updateJob(@RequestBody JobDTO jobM) throws Exception{

		Map<String, Object> result = new HashMap<>();
		JobModel jm = new JobModel();
        int cekJobDesc = jobRepository.countByEmplJobDesc(jobM.getEmplJobDesc());
        JobModel jobDesc = jobRepository.findByEmplJobDescAndEmplDeletedIsFalse(jobM.getEmplJobDesc());

		try{
			
			if(cekJobDesc != 0 && jobM.getEmplJobCode().equals(jobDesc.getEmplJobCode())){
				jm = jobService.updateJob(jobM);
				result.put("status", true);
				result.put("data", jm);
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else if(cekJobDesc != 0) {
                result.put("app", "MDM Job Master");
				result.put("message", "Job "+ jobM.getEmplJobDesc() + " telah terdaftar dengan kode job " + jobDesc.getEmplJobCode());
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			} else {
                jm = jobService.updateJob(jobM);
				result.put("status", true);
				result.put("data", jm);
				return new ResponseEntity<>(result, HttpStatus.OK);
            }
			
		}catch (ApplicationException e) {
			result.put("message", e.getMessage());
			result.put("status", false);
			return new org.springframework.http.ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}

    @RequestMapping(value = "/activationJob", method = RequestMethod.POST)
    // @DeleteMapping
	public ResponseEntity<Map<String, Object>> activationJob(@RequestBody JobDTO jobM) throws Exception{

		Map<String, Object> result = new HashMap<>();
		JobModel jm = new JobModel();

		try{
			jm = jobService.activationJob(jobM);
			result.put("status", true);
			result.put("data", jm);
			 return new ResponseEntity<>(result, HttpStatus.OK);
		}catch (ApplicationException e) {
			result.put("message", e.getMessage());
			result.put("status", false);
			return new org.springframework.http.ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}

    @RequestMapping(value = "/getByCode", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getJobByCode(@RequestBody JobDTO jobModelDTO){

        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();

        try{
            dataJobMaster = jobService.getJobByCode(jobModelDTO);
            result.put("app", "MDM Job Master");
            result.put("message", "Data berhasil ditambahkan");
            result.put("data", dataJobMaster);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(ApplicationException e){
            result.put("message", e);
            result.put("status", false);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }
    
}
