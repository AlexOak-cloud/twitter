package doob.services;


import doob.Mysql.Connector;
import doob.entity.Role;
import doob.entity.User;
import doob.repositoryes.UserRepository;
import doob.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Connector connector = new Connector();


    public void save(User user) {
        String encodePassword = WebSecurityConfig.encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));

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

    public boolean createTableFriends(User user){
        try(Statement statement = connector.getStatement()){
            statement.execute("use test");
            statement.execute(
                    "CREATE TABLE frinds_"+ user.getId() + " ( friend_id INT UNIQUE NOT NULL," +
                            "FOREIGN KEY (friend_id) REFERENCES user(id)" +
                            ")ENGINE=INNODB ");
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

}
