package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final MemberRepository memberRepository;


    //그냥 자파
//    private EntityManager em;

  //  @Autowired
    //public SpringConfig(EntityManager em) {
     //   this.em = em;
   // }

    //jdbc
    // private DataSource dataSource;

    //@Autowired
    //public SpringConfig(DataSource dataSource) {
      //  this.dataSource = dataSource;
    //}

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    //@Bean
    //public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
       // return new jdbcMemberRepository(dataSource);
      //  return new JdbcTemplateMemberRepository(dataSource);
      //  return new JpaMemberRepository(em);
    //}
}
