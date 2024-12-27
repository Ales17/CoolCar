package cz.ales17.auto.security;

import cz.ales17.auto.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SecurityUtil {

    public static String getSessionUsername() {
        return Objects.requireNonNull(getPrincipal()).getUsername();
    }


    public static UserEntity getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserEntity) authentication.getPrincipal();
        }
        return null;
    }


}
