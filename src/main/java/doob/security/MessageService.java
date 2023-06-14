package doob.security;


import doob.entity.Message;
import doob.repositoryes.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;



    public List<Message> findAll(){
      return messageRepository.findAll();
    }


    public Message  save(Message message){
        return messageRepository.save(message);
    }


    public Message findById(int  id){
       return findById(id);
    }


    public void delete(Message message){
      messageRepository.delete(message);
    }


    public void deleteById(int id){
        messageRepository.deleteById(id);
    }
}
