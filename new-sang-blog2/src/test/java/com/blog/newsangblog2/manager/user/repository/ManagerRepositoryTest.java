package com.blog.newsangblog2.manager.user.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.newsangblog2.manager.user.domain.Manager;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerRepositoryTest {
	
	@Autowired
	ManagerRepository ManagerRepository;

	@After
	public void cleanUp() {
		ManagerRepository.deleteAll();
	}
	
	@Test
	public void 유저저장_불러오기() {
		// given
		ManagerRepository.save(
				Manager.builder()
					.loginId("로그인 아이디")
					.password("패뜨워드")
					.phoneNumber("010-0000-0000")
					.name("최쌍씨")
					.introduce("소개 입니다...")
					.lastLoginDate("2010040341730")
					.build()
		);
		
		// when
		List<Manager> managerList = ManagerRepository.findAll();
		
		// then
		Manager manager = managerList.get(0);
		assertThat(manager.getLoginId(), is("로그인 아이디"));
	}

}
