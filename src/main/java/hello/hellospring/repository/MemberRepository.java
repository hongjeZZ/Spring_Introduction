package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원을 저장소에 저장
    Member save(Member member);

    // 아이디로 멤버 찾기
    Optional<Member> findById(Long id);

    // 이름으로 멤버 찾기
    Optional<Member> findByName(String name);

    // 모든 회원 반환
    List<Member> findAll();
}
