package com.blog.newsangblog2.manager.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.domain.UserRole;
import com.blog.newsangblog2.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.manager.user.support.ManagerDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/manager/user")
@Controller
public class ManagerUserController {

	private ManagerUserRepository managerRepository;
	
	@GetMapping("/list")
	public String managerList(Model model) {
		// TODO::: JPA 리스트 / https://jojoldu.tistory.com/251 / 2-3. Controller & DTO 구현				
		List<Manager> list = managerRepository.findAll();
		
		return "/manager/user/list";
		
	}
	
	@GetMapping("/create")
	public String createManager() {
		
		return "";
	}
	
	@PostMapping("/create")
	public String createManager(@RequestBody ManagerDto managerDto) {
		managerRepository.save(managerDto.toEntity());
		return "redirect:/manager/user/login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		
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
	@GetMapping("/denied")
	public String accessDeny() {
		return "/denied";
	}
	
	// 내 정보 상세 페이지
	@GetMapping("/info")
	public String managerInfo() {
		return "/manager/user/info";
	}
	
	
}
