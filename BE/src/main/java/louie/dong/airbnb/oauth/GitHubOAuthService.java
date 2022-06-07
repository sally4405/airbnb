package louie.dong.airbnb.oauth;

import static louie.dong.airbnb.oauth.GithubOAuthUtils.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GitHubOAuthService {

    private final WebClient webClient = WebClient.create();

    public Member requestUserInfo(GitHubAccessToken gitHubAccessToken) {
        GitHubUser gitHubUser = webClient.get()
            .uri(USER_API_URL)
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, gitHubAccessToken.authorizationHeaderValue())
            .retrieve()
            .bodyToMono(GitHubUser.class)
            .blockOptional()
            .orElseThrow(IllegalStateException::new);

        return gitHubUser.toEntity(gitHubAccessToken);
    }

    public GitHubAccessToken requestAccessToken(String code) {
        return webClient.post()
            .uri(ACCESSTOKEN_API_URL)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(new GitHubAccessTokenRequest(code))
            .retrieve()
            .bodyToMono(GitHubAccessToken.class)
            .blockOptional()
            .orElseThrow(IllegalStateException::new);
    }
}
