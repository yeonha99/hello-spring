package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//generate누르고 controctor하기

    //회원가입
    public Long join(Member member) {
       //long start = System.currentTimeMillis();
       // try {
            validateDuplicateMember(member);//중복회원 검증
            memberRepository.save(member);
            return member.getId();
        //}finally {
         //   long finish=System.currentTimeMillis();
           // long timeMs=finish-start;
           // System.out.println("join = "+timeMs+"ms");
       // }
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                    .ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    public List<Member> findMembers(){
       // long start = System.currentTimeMillis();
        //try {
            return memberRepository.findAll();
       // }finally {
         //   long finish=System.currentTimeMillis();
           // long timeMs=finish-start;
           // System.out.println("join = "+timeMs+"ms");
       // }

    }
    public Optional<Member> findOne(Long memberid){
        return memberRepository.findByid(memberid);
    }
}
