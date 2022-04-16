package com.ankit.demo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StudentRepo extends JpaRepository<Student, Long>{
	@Modifying
	@Query(" update Student s set s.studentName = :studentName , s.gender =:gender , s.address =:address , s.city =:city   where s.studentId =:studentId ")
	void updateStudentProfile(@Param("studentName") String studentName,@Param("gender") String gender,@Param("address") String address,@Param("city") String city,@Param("studentId") Long studentId);

	@Query("SELECT u FROM Student u WHERE u.emailId = :emailId")
    public Student getStudentByMailId(@Param("emailId") String emailId);
	
	@Query("SELECT u FROM Student u WHERE u.emailId like %:param% or u.studentName like %:param% ")
    public List<Student> getStudentByMailIdOrName(@Param("param") String param);
	
	
}
