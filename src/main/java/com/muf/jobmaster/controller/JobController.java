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

    // @GetMapping
	// public String testConnection() throws Exception{
	// 	String index = "";
		
	// 	try {
	// 		index = "Service MDM - Job Master";
	// 	} catch(Exception e) {
	// 		e.printStackTrace();
	// 	}
		
	// 	return index;
	// }

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

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateJobMaster(@RequestBody JobDTO jobModelDto){

        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();
        JobModel cekJobDeleted = jobRepository.findByEmplJobCode(jobModelDto.getEmplJobCode());
        int cekJobDesc = jobRepository.countByEmplJobDesc(jobModelDto.getEmplJobDesc());
        JobModel jobDesc = jobRepository.findByEmplJobDescAndEmplDeletedIsFalse(jobModelDto.getEmplJobDesc());


        try{
            if(cekJobDesc != 0){
                result.put("app", "MDM Job Master");
                result.put("message", "Job "+ jobModelDto.getEmplJobDesc() + " sudah ada dengan kode job " + jobDesc.getEmplJobCode());
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            } else {
                dataJobMaster = jobService.updateJob(jobModelDto);
                result.put("app", "MDM Job Master");
                result.put("message", "Data berhasil diubah");
                result.put("data", dataJobMaster);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }catch(ApplicationException e){
            result.put("status", false);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteJob(@RequestBody JobDTO jobModelDto){

        Map<String, Object> result = new HashMap<>();
        JobModel dataJobMaster = new JobModel();
        JobModel cekJob = jobRepository.findByEmplJobCodeAndEmplDeletedIsFalse(jobModelDto.getEmplJobCode());

        try{
            if(cekJob == null){
                result.put("message", "Data tidak ada");
                return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
            }
            dataJobMaster = jobService.deleteJob(jobModelDto);
            result.put("message", "Data berhasil dihapus");
            result.put("data", dataJobMaster);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(ApplicationException e){
            result.put("status", false);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }
    
}
