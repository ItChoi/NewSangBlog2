package com.blog.newsangblog2.manager.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.repository.ManagerRepository;
import com.blog.newsangblog2.manager.user.support.ManagerDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/manager/user")
public class ManagerRestController {

	private ManagerRepository managerRepository;
	
	@GetMapping("/list")
	public List<Manager> managerList() {
		// TODO::: JPA 리스트 / https://jojoldu.tistory.com/251 / 2-3. Controller & DTO 구현				
		return managerRepository.findAll();
	}
	
	@PostMapping("/create")
	public void createManager(@RequestBody ManagerDto managerDto) {
		managerRepository.save(managerDto.toEntity());
	}
	
}
