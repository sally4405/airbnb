package louie.dong.airbnb.oauth;

public class GithubOAuthUtils {

	public static final String CALLBACK_URL = "http://localhost:8080/login/callback";
	public static final String CLIENT_ID = "ebf7e41d085539697987";
	public static final String CLIENT_SECRET = "a072819ee77324c09c77a8a1d1192ae8fdd40ac9";
	public static final String LOCATION = "https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID +
		"&redirect_uri=" + CALLBACK_URL;
	public static final String ACCESSTOKEN_API_URL	= "https://github.com/login/oauth/access_token";
	public static final String USER_API_URL	= "https://api.github.com/user";
}
