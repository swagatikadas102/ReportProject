package in.ashokit.demo.Service;

import java.util.List;

import in.ashokit.demo.Entity.EmployeeReportEntity;

public interface EmployeeReportService {

	public List<EmployeeReportEntity> getAllREports();
	public EmployeeReportEntity getEmployeeData(Integer empid);
	public EmployeeReportEntity registerEmployee(EmployeeReportEntity emp);
	public EmployeeReportEntity updateEmployee(EmployeeReportEntity emp);
	public Boolean deleteEmployee(int id);
}
 