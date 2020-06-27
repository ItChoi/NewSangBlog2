package com.blog.newsangblog2;

import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestJUnit5Class {

	@Autowired
	private ManagerUserRepository managerUserRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Test
	void test() {
		ManagerDto managerDto = new ManagerDto();
		managerDto.setEmail("asdasdasd");
		managerDto.setName("asdadasdasd");

		Manager manager = modelMapper.map(managerDto, Manager.class);

		System.out.println("result: " + manager.getEmail());
		System.out.println("result: " + manager.getName());
	}

}
