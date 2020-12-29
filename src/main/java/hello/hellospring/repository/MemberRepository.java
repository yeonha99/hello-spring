package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByid(long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
