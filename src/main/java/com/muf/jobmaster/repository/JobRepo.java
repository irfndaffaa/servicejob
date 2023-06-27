package com.muf.jobmaster.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.muf.jobmaster.model.JobModel;

@Repository
public interface JobRepo extends JpaRepository<JobModel, String> {

    List<JobModel> findAllByOrderByEmplJobCodeAsc();
    JobModel findByEmplJobCodeAndEmplDeletedIsFalse(String jobCode);
    JobModel findByEmplJobDescAndEmplDeletedIsFalse(String jobDesc);
    JobModel findByEmplJobCode(String jobCode);
    int countByEmplJobDesc(String jobDesc);

    @Query(value = "select count(j.empl_job_code) " +
                   "from mufparam.mst_empl_job_dev j " +
                   "where j.empl_job_code =?1", nativeQuery = true)
    int cekKodeJob(String jobCode);
}
