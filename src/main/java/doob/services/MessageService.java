package doob.services;


import doob.entity.Dialog;
import doob.entity.User;
import doob.repositoryes.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    public boolean save(String context, User sender, User recipient) {
        if (context == null && sender == null && recipient == null) return false;
        messageRepository.save(context, sender, recipient);
        return true;
    }

    public Dialog getDialog(User sender, User recipient){
        return messageRepository.buildDialog(sender, recipient);
    }
}
