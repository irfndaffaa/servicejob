package com.muf.jobmaster.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.muf.jobmaster.model.JobModel;

@Repository
public interface JobRepo extends JpaRepository<JobModel, String> {

    @Query(value = "select count(j.empl_job_code) " +
                   "from mufparam.mst_empl_job_dev j " +
                   "where j.empl_job_code =?1", nativeQuery = true)
    int cekKodeJob(String jobCode);

    @Query(value = "select j.empl_job_code, " +
                   "j.empl_job_desc, " +
                   "j.empl_log_id, " +
                   "j.empl_deleted, " +
                   "j.empl_job_status, " +
                   "j.empl_com_id, " +
                   "j.empl_job_notes, " +
                   "j.empl_flag_pool " +
                   "from mufparam.mst_empl_job_dev j " +
                   "where j.empl_job_code = ?1", nativeQuery = true)
    JobModel getJobData(String jobCode);

    @Query(value = "select j.empl_job_code, " +
                    "j.empl_job_desc, " +
                    "j.empl_log_id, " +
                    "j.empl_deleted, " +
                    "j.empl_job_status, " +
                    "j.empl_com_id, " +
                    "j.empl_job_notes, " +
                    "j.empl_flag_pool " +
                    "from mufparam.mst_empl_job_dev j " +
                    "order by j.empl_job_code asc", nativeQuery = true)
    List<JobModel> getAllJobData();

    List<JobModel> findAllOrderByEmplJobCodeAsc();

    JobModel findByIdAndDeletedIsFalse(String jobCode);
    
}
