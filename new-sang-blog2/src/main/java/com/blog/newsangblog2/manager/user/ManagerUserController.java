package com.blog.newsangblog2.manager.user;

import java.util.List;

import com.blog.newsangblog2.common.enumeration.PreNumber;
import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.manager.user.service.ManagerUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.domain.UserRole;
import com.blog.newsangblog2.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.manager.user.support.ManagerDto;

import lombok.AllArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/manager/user")
@Controller
public class ManagerUserController {

	private final ManagerUserRepository managerRepository;
	private final ManagerUserService managerUserService;
	
	@GetMapping("/list")
	public String managerList(Model model) {
		List<Manager> list = managerRepository.findAll();
		model.addAttribute("list", list);
		return "/manager/user/list";
	}

	// 회원 가입 페이지
	@GetMapping("/create")
	public String createManager(Model model) {
		model.addAttribute("prePhoneNumber", PreNumber.PHONE.getPreNumber());
		model.addAttribute("userRoles", UserRoleType.values());

		return "/manager/user/form";
	}
	
	@PostMapping("/create")
	public String createManager(ManagerDto managerDto) {

		managerUserService.createManager(managerDto);

		return "redirect:/manager/user/login";
	}
	
	@GetMapping("/login")
	public String login(Model model, String error) {
		
		if (!StringUtils.isEmpty(error)) {
			model.addAttribute("error", error);
		}
		
		return "/manager/user/login";
	}
	
	// 로그인 결과 페이지
	@GetMapping("/login/result")
	public String loginResult() {
		
		return "/loginSuccess";
	}

	// 로그아웃 결과 페이지
	@GetMapping("/logout/result")
	public String logoutResult() {
		return "/logout";
	}
	
	// 접근 거부 페이지
	@GetMapping("/access-denied")
	public String accessDeny() {
		System.out.println("access-denied page!!!!");
		return "/manager/user/access-denied";
	}
	
	// 내 정보 상세 페이지
	@GetMapping("/info")
	public String managerInfo() {
		return "/manager/user/info";
	}


}
