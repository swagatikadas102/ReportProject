package in.ashokit.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.demo.Entity.EmployeeReportEntity;
import in.ashokit.demo.Repository.EmployeeReportRepository;

@Service
public class EmployeeReportServiceImplement implements EmployeeReportService {

	@Autowired
	private EmployeeReportRepository repo;
	@Override
	public List<EmployeeReportEntity> getAllREports() {
		return repo.findAll();
	}

	@Override
	public EmployeeReportEntity getEmployeeData(Integer empid) {
		return  repo.findById(empid).orElse(null);
	}
	
	@Override
	public EmployeeReportEntity registerEmployee(EmployeeReportEntity emp) {
		return repo.save(emp);
		
	}
	
	@Override
	public EmployeeReportEntity updateEmployee(EmployeeReportEntity emp) {
		return repo.save(emp);
		
	}

	@Override
	public Boolean deleteEmployee(int id) {
	    if (repo.existsById(id)) {
	        repo.deleteById(id);
	        return true; 
	    }
	    return false;  
	}

}
