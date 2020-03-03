package com.blog.newsangblog2.manager.user.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.domain.UserRole;
import com.blog.newsangblog2.manager.user.service.ManagerUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerRepositoryTest {
	
	@Autowired
	ManagerUserRepository managerRepository;
	
	@Autowired
	ManagerUserService managerUserService;

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@After
	public void cleanUp() {
		//managerRepository.deleteAll();
	}
	
	@Test
	public void 유저저장_불러오기() {
		// given
		managerRepository.save(
				Manager.builder()
					.loginId("enffl")
					.password("패뜨워드")
					.phoneNumber("010-0000-0000")
					.name("최쌍씨")
					.introduce("소개 입니다...")
					.lastLoginDate("2010040341730")
					.build()
		);
		
		// when
		List<Manager> managerList = managerRepository.findAll();
		Optional<Manager> managerInfo = managerRepository.findManagerByLoginId("enffl");
		
		
		
		// then
		assertThat(managerInfo.get().getLoginId(), is("enffl"));
		assertThat(managerInfo.isPresent(), is(true));
	}
	
	@Test
	public void 컴포넌트_스캔_테스트() {
		System.out.println("managerUserService: " + managerUserService);
	}
	
	@Test
	public void JPA_지연로딩_테스트() {
		Manager manager = managerUserService.findManagerBy("enffl18").get();
		assertEquals("enffl18", manager.getLoginId());
		
		System.out.println("1111111111111111111111111");
		for (UserRole ur : manager.getUserRoles()) {
			System.out.println("result: " + ur.getAuthority());
		}
		System.out.println("2222222222222222222222222");
		//System.out.println("roleTest: " + role);
	}
	
	@Test
	public void JPA_지연로딩_테스트_롤() {
		System.out.println("---------------------------");
		UserRole userRole = userRoleRepository.findUserRoleById(1L).get();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("userRole.getId(): " + userRole.getAuthority());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
		
		System.out.println("mmmmm: " + userRole.getManager().getLoginId());
	}
	
	@Test
	public void testtest() {
		System.out.println("test");
	}

}
