package com.web.circle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.circle.model.BranchTableDataModel;
import com.web.circle.model.entity.Branch;
import com.web.circle.model.entity.Organizations;
import com.web.circle.repository.BranchRepository;
import com.web.circle.repository.DepartmentRepository;
import com.web.circle.repository.OrganizationRepository;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Branch controller
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 */
@Slf4j
@Controller
@RequestMapping("apps/branch")
public class BranchController extends BaseController {
	
	public BranchController(UserRepository userRepository, PersonRepository personRepository,
			OrganizationRepository organizationRepository, DepartmentRepository departmentRepository,
			BranchRepository branchRepository) {
		super(userRepository, personRepository, organizationRepository, departmentRepository, branchRepository);
	}

	/**
	 * Display the list of organizations
	 * on client page.
	 * */
	@GetMapping("index")
    public String index(Model model) {
		// Branch list
		List<Branch> branch = branchRepository.getBranchList();
		ArrayList<Object> branchList = new ArrayList<Object>();
		for(Branch b: branch) {
			BranchTableDataModel btdm = new BranchTableDataModel();
			btdm.setBranchId(b.getBranchId());
			btdm.setBranchName(b.getBranchName());
			btdm.setLogoUrl(fileDownloadUrl(b.getUploadFile().getFileName(), "/media/download/"));
			branchList.add(btdm);
		}
		model.addAttribute("branchList",branchList);
		
		return "apps/branch/index";
	}
	
	/**
	 * Display the branche data.
	 * 
	 * @param branchId
	 * */
	@RequestMapping("page-sidebar")
	public String pageSideBar(Model model, @RequestParam(value="branchId") long branchId) {
		// Branch data.
		Branch b = branchRepository.findById(branchId).get();
		model.addAttribute("branchId", b.getBranchId());		
		model.addAttribute("branchName", b.getBranchName());
		model.addAttribute("address", b.getAddress());
		model.addAttribute("email", b.getEmail());
		model.addAttribute("postalCode", b.getPostalCode());
		model.addAttribute("telephone", b.getTelephoneNo());
		model.addAttribute("website", b.getWebsite());
		
		//model.addAttribute("company_heading", or.getCompanyHeading());
		//model.addAttribute("founded",or.getFounded());
		//model.addAttribute("executive",or.getExecutive());
		//model.addAttribute("annualBudget",or.getAnnualBudget());
		//model.addAttribute("predecessor",or.getPredecessor());
		//model.addAttribute("type",or.getType());
		//model.addAttribute("jurisdiction",or.getJurisdiction());
		//model.addAttribute("dissolved",or.getDissolved());
		//model.addAttribute("circle_key",or.getLicense().getCircleKey());
		return "apps/branch/page_sidebar :: page-sidebar";
	}
}
