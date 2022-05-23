package ru.liga.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.liga.crud.entity.User;
import ru.liga.crud.repository.UserRepository;
import ru.liga.crud.support.UserDetailsImpl;

@Service(value = "customUserDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    //todo перенести в основной пакет service
    // done
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName + "not found"));

        return new UserDetailsImpl(user);
    }
}
