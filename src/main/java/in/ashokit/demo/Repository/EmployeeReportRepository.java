package in.ashokit.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.demo.Entity.EmployeeReportEntity;

public interface EmployeeReportRepository extends JpaRepository <EmployeeReportEntity,Integer> {

}
