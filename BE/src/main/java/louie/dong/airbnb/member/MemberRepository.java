package louie.dong.airbnb.member;

import louie.dong.airbnb.oauth.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByGitHubIdNot(String gitHubId);

}
