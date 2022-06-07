package louie.dong.airbnb.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitHubUser {

    @JsonProperty("login")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    public Member toEntity(GitHubAccessToken gitHubAccessToken) {
        return new Member(userId, name, gitHubAccessToken.getRefreshToken());
    }
}
