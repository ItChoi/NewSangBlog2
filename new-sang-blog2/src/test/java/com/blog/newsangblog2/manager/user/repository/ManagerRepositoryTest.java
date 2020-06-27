package com.blog.newsangblog2.manager.user.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ManagerRepositoryTest {

	/*@Autowired
	ManagerUserRepository managerRepository;
	
	@Autowired
	ManagerUserService managerUserService;

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@AfterAll
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
		Assertions.assertEquals(managerInfo.get().getLoginId(), is("enffl"));
		Assertions.assertEquals(managerInfo.isPresent(), is(true));
	}
	
	@Test
	public void 컴포넌트_스캔_테스트() {
		System.out.println("managerUserService: " + managerUserService);
	}
	
	@Test
	public void JPA_지연로딩_테스트() {
		Manager manager = managerUserService.findManagerBy("enffl18").get();
		System.out.println("------------------1: " + manager.getClass());
		Assertions.assertEquals("enffl18", manager.getLoginId());
		
		System.out.println("1111111111111111111111111");
			System.out.println("------------------2: " + manager.getUserRole().getClass());
			System.out.println("------------------3: " + manager.getUserRole().getClass());
			System.out.println("result: " + manager.getUserRole().getAuthority());
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
	}*/
	
	@Test
	public void testtest() {
		System.out.println("test");
	}

}
