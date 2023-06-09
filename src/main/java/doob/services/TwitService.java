package doob.services;


import doob.entity.Twit;
import doob.entity.User;
import doob.repositoryes.TwitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TwitService {

    @Autowired
    private TwitRepository twitRepository;



    public void save(Twit twit){
        twitRepository.save(twit);
    }

    public List<Twit> findAllByUser(User user){
        return twitRepository.findAllByUser(user);
    }

    private List<Twit> findAll(){
        return twitRepository.findAll();

    }

    private Twit findById(int id){
        Optional<Twit> user = twitRepository.findById(id);
        return user.get();
    }

    private void delete(Twit twit){
        twitRepository.delete(twit);
    }

    private void deleteById(int id){
        twitRepository.deleteById(id);
    }


}
