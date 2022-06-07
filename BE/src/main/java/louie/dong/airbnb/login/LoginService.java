package louie.dong.airbnb.login;

import lombok.RequiredArgsConstructor;
import louie.dong.airbnb.member.MemberRepository;
import louie.dong.airbnb.oauth.Member;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public void login(Member member) {
        if (memberRepository.existsByGitHubIdNot(member.getGitHubId())) {
            memberRepository.save(member);
        }
    }
}
