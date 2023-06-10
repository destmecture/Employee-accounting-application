package ru.skypro.lessons.springboot.springboot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.springboot.dto.UserDTO;
import ru.skypro.lessons.springboot.springboot.security.SecurityUserPrincipal;
import ru.skypro.lessons.springboot.springboot.repository.UserRepository;
import ru.skypro.lessons.springboot.springboot.pojo.User;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDTO userDTO;
    private final SecurityUserPrincipal securityUserPrincipal;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO1 = userRepository.findByUsername(username)
                .map(user -> userDTO.fromUser(user))
                .orElseThrow(()->new UsernameNotFoundException("Username %s not found"));
        securityUserPrincipal.setUserDTO(userDTO1);
        return securityUserPrincipal;
    }
}
