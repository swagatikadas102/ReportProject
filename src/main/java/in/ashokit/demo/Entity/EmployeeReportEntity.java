package in.ashokit.demo.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@SQLDelete(sql = "UPDATE employee_report_entity SET status = false WHERE empid = ?")
@SQLRestriction("status = true")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empid;
	private String empname;
	private String empjob;
	private BigDecimal  empsalary;
	private String empphno;
	private String empemail;
	@CreatedDate
	private LocalDateTime  createddate;
	@LastModifiedDate
	private LocalDateTime  modifieddate;
	private Boolean status=true;
	private String country;
	private String gender;
	@Lob
	private byte[] emppassphoto;
}
