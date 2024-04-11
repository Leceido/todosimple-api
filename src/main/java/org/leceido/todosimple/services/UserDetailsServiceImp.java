package org.leceido.todosimple.services;

import org.leceido.todosimple.models.User;
import org.leceido.todosimple.repositories.UserRepository;
import org.leceido.todosimple.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("usuario não encotrado " + username);
        }
        return new UserSpringSecurity(user.getId(), user.getUsername(), user.getPassword(), user.getProfiles());
    }
}
