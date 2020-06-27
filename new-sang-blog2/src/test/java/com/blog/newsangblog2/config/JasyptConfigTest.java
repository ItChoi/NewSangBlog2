package com.blog.newsangblog2.config;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JasyptConfigTest {


    @Test
    public void testtest() {

    }

	/*@Autowired
	ApplicationContext appCtx;
	

	@Test
	public void test() {
		// TODO::: 깃 캐시 삭제하고 다시 푸시 필요
		
		  StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		  jasypt.setPassword("m2sj"); 
		  jasypt.setAlgorithm("PBEWithMD5AndDES");
		  String encryptPassword = jasypt.encrypt("Tkdgus1818"); 
		  String originPassword = jasypt.decrypt(encryptPassword); 
		  System.out.println("encryptPassword: " + encryptPassword); 
		  System.out.println("originPassword: " + originPassword);
		 
	}
	
	@Test
	public void 테스트() {
		Environment env = appCtx.getBean(Environment.class);
		System.out.println("------");
		String a = env.getProperty("encryptedv3.property");
		System.out.println("------@@@");
		System.out.println("111: " + env.getProperty("encryptedv3.property"));
		assertEquals("itchoi0429", env.getProperty("encryptedv3.property"));
		System.out.println("222");
	}
	
	@Test
	public void 테스트1() {
		System.out.println("---000!!!");
		// System.setProperty("jasypt.encryptor.password", "m2sj");
		// PropertyServiceForJasyptStarter service = appCtx.getBean(PropertyServiceForJasyptStarter.class);
		// assertEquals("asdasdasd", service.getProperty());
		
		Environment env = appCtx.getBean(Environment.class);
		assertEquals("asdasasd", env.getProperty("spring.datasource.password"));
		System.out.println("---111!!!");
		System.out.println("---222!!!");
	}*/

}
