package doob.services;


import doob.entity.Twit;
import doob.entity.User;
import doob.repositoryes.TwitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TwitService {

    @Autowired
    private TwitRepository twitRepository;


    public void save(Twit twit) {
        twitRepository.save(twit);
    }

    public List<Twit> findAllByUser(User user) {
        return twitRepository.findAllByUser(user);
    }

    public List<Twit> findAll() {
        return twitRepository.findAll();

    }

    public Twit findById(int id) {
        Optional<Twit> user = twitRepository.findById(id);
        return user.get();
    }

    public void delete(Twit twit) {
        twitRepository.delete(twit);
    }

    public void deleteById(int id) {
        twitRepository.deleteById(id);
    }

    public List<Twit> sortedListByDateTime(List<Twit> localList) {
        Collections.sort(localList);
        return localList;
    }

    public List<Twit> reverseList(List<Twit> list){
        Collections.reverse(list);
        return list;
    }


}
