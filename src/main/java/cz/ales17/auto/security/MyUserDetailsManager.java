package cz.ales17.auto.security;

import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import static cz.ales17.auto.security.SecurityUtil.getPrincipal;

@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsernameIs(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        UserEntity authenticatedUser = getPrincipal();
        if(!encoder.matches(oldPassword, authenticatedUser.getPassword()))
        {
            throw new RuntimeException("Hesla se neshodují");
        }
        authenticatedUser.setPassword(encoder.encode(newPassword));
        userRepository.save(authenticatedUser);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
