package doob.services;


import doob.entity.User;
import doob.repositoryes.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {
    @Autowired
    private FriendsRepository friendsRepository;


    public boolean addFriend(User authUser, User friend) {
        return friendsRepository.addFriend(authUser, friend);
    }

    public void createTable(User authUser) {
        friendsRepository.createTable(authUser);
    }

    public List<User> findAllByUser(User user) {
        return friendsRepository.findAllByUser(user);
    }

    public boolean isFriend(User authUser, User friend) {
        return friendsRepository.isFriend(authUser, friend);
    }


}
