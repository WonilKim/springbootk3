package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoMysql;
import edu.pnu.dao.MemberInterface;
import edu.pnu.service.MemberService;

@Configuration
public class MemberConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
	
	@Bean
	public MemberInterface memberDao() {
		return new MemberDaoMysql();
	}
	
	@Bean 
	public LogDao logDao() {
		return new LogDao();
	}
}
