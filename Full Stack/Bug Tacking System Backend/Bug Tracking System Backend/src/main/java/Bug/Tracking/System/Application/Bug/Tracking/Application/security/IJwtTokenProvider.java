package Bug.Tracking.System.Application.Bug.Tracking.Application.security;

import org.springframework.security.core.Authentication;

public interface IJwtTokenProvider
{
    String generateToken(Authentication authentication);
    String getUsername(String token); boolean validateToken(String token); }