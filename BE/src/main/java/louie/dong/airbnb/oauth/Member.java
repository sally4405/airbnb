package louie.dong.airbnb.oauth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gitHubId;

    @Column(name = "member_name")
    private String name;

    private String gitHubRefreshToken;

    public Member(String gitHubId, String name, String oAuthRefreshToken) {
        this.gitHubId = gitHubId;
        this.name = name;
        this.gitHubRefreshToken = oAuthRefreshToken;
    }

}
