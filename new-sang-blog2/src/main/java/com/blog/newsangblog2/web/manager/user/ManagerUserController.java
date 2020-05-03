package com.blog.newsangblog2.web.manager.user;

import com.blog.newsangblog2.common.enumeration.PreNumber;
import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.service.ManagerUserService;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

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


	@PostMapping("/check-duplication")
	public ResponseEntity<Boolean> checkDuplication(@RequestBody ManagerDto managerDto, BindingResult bindingResult) {
		ResponseEntity entity = null;
		managerUserService.checkDuplicationValue(managerDto);

		if (bindingResult.hasErrors()) {
			entity = new ResponseEntity(false, HttpStatus.BAD_REQUEST);
		} else {
			entity = new ResponseEntity(true, HttpStatus.OK);
		}

		return entity;
	}

}
