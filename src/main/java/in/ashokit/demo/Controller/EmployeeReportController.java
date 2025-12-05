package in.ashokit.demo.Controller;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ashokit.demo.Entity.EmployeeReportEntity;
import in.ashokit.demo.Service.EmployeeReportServiceImplement;

@Controller
public class EmployeeReportController {

	@Autowired
	private EmployeeReportServiceImplement eser;

	@GetMapping("/")
	public String pageloder() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String adminname, String adminpassword, Model model) {
		if (adminname.equals("Admin") && adminpassword.equals("admin")) {
			return "redirect:/report";
		}
		model.addAttribute("error", "Invalid inputs");
		return "login";
	}

	@GetMapping("/report")
	public String reports(Model model) {
		List<EmployeeReportEntity> allREports = eser.getAllREports();
		if (allREports != null && !allREports.isEmpty()) {
			model.addAttribute("allreports", allREports);
		} else {
			model.addAttribute("errormsg", "No reports present in Database");
		}
		return "reports";
	}

	@GetMapping("/register")
	public String registerEmployee(Model model) {
		model.addAttribute("emp", new EmployeeReportEntity());
		return "register";

	}

	@PostMapping("/register")
	public String registerEmployee(@ModelAttribute EmployeeReportEntity employee, Model model,
			@RequestParam("passphoto") MultipartFile file) {

		try {
			if (!file.isEmpty()) {
				employee.setEmppassphoto(file.getBytes());
			}
		} catch (Exception e) {
			model.addAttribute("error", "Photo upload failed!");
			return "register";
		}
		// Set created and modified dates manually
		LocalDateTime now = LocalDateTime.now();
		employee.setCreateddate(now);
		employee.setModifieddate(now);

		EmployeeReportEntity registerEmployee = eser.registerEmployee(employee);
		if (registerEmployee != null) {
			model.addAttribute("success", "Data registered successfully..");
		} else {
			model.addAttribute("error", "Try Again..");

		}
		return "redirect:/register";
	}

	@GetMapping("/profile")
	public String profileEmployee(@RequestParam("empid") int empid, Model model) {
		EmployeeReportEntity employeeData = eser.getEmployeeData(empid);
		if (employeeData != null) {
			model.addAttribute("employee", employeeData);
			if (employeeData.getEmppassphoto() != null) {
				String base64 = Base64.getEncoder().encodeToString(employeeData.getEmppassphoto());
				model.addAttribute("photo", base64);
			}
		}
		return "profile";
	}

	@GetMapping("/logout")
	public String Logout() {
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String editProfile(@RequestParam("empid") int eid, Model model) {
		EmployeeReportEntity empData = eser.getEmployeeData(eid);
		model.addAttribute("emp", empData);
		return "register";

	}

	@PostMapping("/update")
	public String updateProfile(@ModelAttribute EmployeeReportEntity ent, RedirectAttributes redirect) {
		eser.updateEmployee(ent);
		System.out.println(ent);
		redirect.addFlashAttribute("success", "Employee Updated Successfully!");

		return "redirect:/profile";
	}

	@PostMapping("/delete")
	public String deleteEmployee(@RequestParam("empid") Integer id, RedirectAttributes redirect) {
		boolean deleted = eser.deleteEmployee(id);
		if (deleted) {
			redirect.addFlashAttribute("success", "Employee deleted successfully!");
		} else {
			redirect.addFlashAttribute("error", "Employee not found!");
		}
		return "redirect:/report";
	}
}
