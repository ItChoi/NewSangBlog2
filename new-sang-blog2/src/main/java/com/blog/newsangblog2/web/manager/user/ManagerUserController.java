package com.blog.newsangblog2.web.manager.user;

import com.blog.newsangblog2.common.enumeration.CommonMessage;
import com.blog.newsangblog2.common.exception.DuplicationException;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.common.utils.UserUtils;
import com.blog.newsangblog2.web.manager.menu.service.ManagerMenuService;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.service.ManagerUserService;
import com.blog.newsangblog2.web.manager.user.service.UserService;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manager/user")
@Controller
public class ManagerUserController {

	private final ManagerUserRepository managerRepository;

	private final ManagerUserService managerUserService;

	private final UserService userService;

	private final ManagerMenuService managerMenuService;

	private final ModelMapper modelMapper;

	@GetMapping("/list")
	public String managerList(Model model) {
		List<Manager> list = managerUserService.findAll();
		List<ManagerDto> dtos = list.stream().map(manager -> modelMapper.map(manager, ManagerDto.class)).collect(Collectors.toList());

		model.addAttribute("list", dtos);
		return "/manager/user/list";
	}


	// 회원 가입 페이지
	@GetMapping("/create")
	public String createManager(Model model) {
		managerUserService.settingBaseInfo(model);
		model.addAttribute("managerInfo", new ManagerDto());
		return "/manager/user/form";
	}
	
	@PostMapping("/create")
	public String createManager(@Valid ManagerDto managerDto, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			userService.createManager(managerDto);
		}

		return "redirect:/manager/user/login";
	}

	@GetMapping("/edit")
	public String editManager(Model model) {
		managerUserService.settingBaseInfo(model);
		Manager managerInfo = managerUserService.findManagerBy(UserUtils.getLoginId()).orElseThrow(() -> new UserNotFoundException());
		ManagerDto responseDto = modelMapper.map(managerInfo, ManagerDto.class);
		responseDto.settingPhonNumber();
		model.addAttribute("managerInfo", responseDto);
		return "/manager/user/form";
	}

	@PostMapping("/edit")
	public String editManager(@Valid ManagerDto managerDto, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			userService.updateManager(managerDto);
		}

		return "redirect:/manager/user/edit";
	}

	
	@GetMapping("/login")
	public String login(Model model, String error) {
		if (!StringUtils.isEmpty(error)) {
			model.addAttribute("error", error);
		}
		
		return "manager/user/login";
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

	@GetMapping("/duplicate-info-check")
	public ResponseEntity<String> duplicateLoginIdCheck(ManagerDto managerDto) {
		ResponseEntity entity;

		try {
			userService.checkDuplicationValue(managerDto);
			entity = new ResponseEntity<>(CommonMessage.ID_IS_AVAILABLE, HttpStatus.OK);
		} catch (DuplicationException e) {
			log.error("error: {}", e.getMessage());
			entity = new ResponseEntity<>(CommonMessage.ALREADY_EXISTS_ID, HttpStatus.BAD_REQUEST);
		}

		return entity;

	}

}
