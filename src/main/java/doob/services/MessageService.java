package doob.services;


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


    private static final String PATH = "E:/Code/messages/";


    public File createPathByUsers(User sender, User recipient) {
        File folder = new File(PATH + sender.getId() + "/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder.getPath() + "/" + sender.getId() + "_" + recipient.getId());
        return file;
    }


    public boolean save(User sender, User recipient, String context) {
        File rtnFile = null;
        File file = createPathByUsers(sender, recipient);
        File file1 = createPathByUsers(recipient, sender);
        if (file.exists()) {
            rtnFile = file;
        }
        if (rtnFile == null) {
            rtnFile = file1;
        }
        try {
            if (rtnFile == null) {
                file.createNewFile();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return messageRepository.save(file, context);
    }


}
