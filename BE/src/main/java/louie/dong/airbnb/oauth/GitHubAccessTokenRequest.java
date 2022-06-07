package louie.dong.airbnb.oauth;

import static louie.dong.airbnb.oauth.GithubOAuthUtils.*;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GitHubAccessTokenRequest {

    private final String code;
    private final String clientId = CLIENT_ID;
    private final String clientSecret = CLIENT_SECRET;

    public GitHubAccessTokenRequest(String code) {
        this.code = code;
    }
}
