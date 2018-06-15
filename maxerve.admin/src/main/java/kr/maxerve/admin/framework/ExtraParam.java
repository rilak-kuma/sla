package kr.maxerve.admin.framework;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class ExtraParam extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;

	private final String userId;
	private final String providerId;
	private final String providerUserId;
	private final String displayName;
	private final String profileUrl;
	private final String secret;
	private final String imageUrl;
	private final String accessToken;
	private final String refreshToken;
	private final String expireTime;

	public ExtraParam(HttpServletRequest request) {
		super(request);
		this.userId = request.getParameter("userId");
		this.providerId = request.getParameter("providerId");
		this.providerUserId = request.getParameter("providerUserId");
		this.displayName = request.getParameter("displayName");
		this.secret = request.getParameter("secret");
		this.profileUrl = request.getParameter("profileUrl");
		this.imageUrl = request.getParameter("imageUrl");
		this.accessToken = request.getParameter("accessToken");
		this.refreshToken = request.getParameter("refreshToken");
		this.expireTime = request.getParameter("expireTime");
	}

	public String getUserId() {
		return userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public String getSecret() {
		return secret;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public String getExpireTime() {
		return expireTime;
	}

	
}