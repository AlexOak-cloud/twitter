package doob.services;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public static final String path = "E:/Code/messages/";

    public String buildPath(User sender, User recipient) {
        return path + sender.getId() + "/" + sender.getId() + "_" + recipient.getId();
    }

    public boolean save(String context, User sender, User recipient) throws IOException {
        if (context == null && sender == null && recipient == null) return false;
        File forSave = null;
        File fileS = new File(buildPath(sender, recipient));
        File fileR = new File(buildPath(recipient, sender));
        if (fileS.exists()) forSave = fileS;
        if (fileR.exists()) forSave = fileR;
        if(!fileS.exists() && !fileR.exists()){
            fileS.createNewFile();
            forSave = fileS;
        }
        return messageRepository.save(context,forSave);
    }

    public Set<Message> getMessagesByUsers(User sender, User recipient){
        File file = new File(buildPath(sender,recipient));
        return messageRepository.getMessages(file);
    }

    public Dialog buildDialog(Set<Message> set){
        Dialog dialog = new Dialog();
        dialog.setMessages(set);
        return dialog;
    }


}
