package ua.snippets.core.persistence.user;

import jakarta.persistence.Column;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 *  User authentication data
 */
public abstract class AuthenticationData implements UserDetails {

    protected static final int DAYS_TO_AUTH_EXPIRE = 7;

    @Column
    protected String username;

    @Column
    protected String password;

    @Column(name = "account_enabled")
    protected boolean accountEnabled;

    @Column(name = "authorization_date")
    protected LocalDateTime lastAuthorizationDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        Duration duration = Duration.between(LocalDateTime.now(), lastAuthorizationDate);
        long days = duration.toDays();
        return days <= DAYS_TO_AUTH_EXPIRE;
    }

    @Override
    public boolean isEnabled() {
        return accountEnabled;
    }
}
