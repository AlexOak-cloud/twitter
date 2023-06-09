package doob.services;


import doob.entity.Role;
import doob.entity.User;
import doob.repositoryes.UserRepository;
import doob.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public boolean save(User user) {
        String encodePassword = WebSecurityConfig.encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        return true;
    }


    private List<User> findAll() {
        return userRepository.findAll();

    }

    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    private User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    private void delete(User user) {
        userRepository.delete(user);
    }

    private void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }

}
