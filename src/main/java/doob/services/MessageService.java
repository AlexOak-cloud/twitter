package doob.services;


import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;



    public List<Message> findAll(){
      return messageRepository.findAll();
    }


    public Message  save(Message message, User sender, User recipient){
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setDateTime(LocalDateTime.now());
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
