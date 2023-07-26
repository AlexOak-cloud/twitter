package doob.services;


import doob.entity.Dialog;
import doob.entity.User;
import doob.repositoryes.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public static final String path = "E:/Code/messages/";

    public String buildPath(User sender, User recipient) {
        return path + sender.getId() + "_" + recipient.getId();
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


}
