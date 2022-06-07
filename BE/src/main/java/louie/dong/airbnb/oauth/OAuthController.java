package louie.dong.airbnb.oauth;

import static louie.dong.airbnb.oauth.GithubOAuthUtils.LOCATION;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.dong.airbnb.login.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OAuthController {

    private final GitHubOAuthService gitHubOAuthService;
    private final LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity redirectOAuthPage() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .location(URI.create(LOCATION))
            .build();
    }

    @GetMapping("/login/callback")
    public void login(String code, HttpServletResponse response) {
        GitHubAccessToken gitHubAccessToken = gitHubOAuthService.requestAccessToken(code);

        log.debug("gitHubAccessToken = {}", gitHubAccessToken);
        Member member = gitHubOAuthService.requestUserInfo(gitHubAccessToken);

        loginService.login(member);

        JWT.create()
            .withIssuer("louie-03")
            .withSubject("GitHub OAuth Refresh Token")
            .withAudience("Airbnb Client")
            .withExpiresAt(Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant()))
            .withNotBefore(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
            .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
            .sign(Algorithm.HMAC256("secret-key"));

        response.setHeader("Access-Token", gitHubAccessToken.getAccessToken());
        response.setHeader("Refresh-Token", gitHubAccessToken.getRefreshToken());
    }


}
