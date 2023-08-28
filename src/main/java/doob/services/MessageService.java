package doob.services;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.MessageRepository;
import org.hibernate.validator.internal.util.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    private String path = "E:/Code/messages/";

    @Autowired
    private MessageRepository messageRepository;

    private File getFileByUser(User sender, User recipient) {
        File rtnFile = null;
        File folder = new File("E:/Code/messages/" + sender.getId());
        File fileForCheck = new File("E:/Code/messages/" + recipient.getId() + "/" + sender.getId() + ".txt");
        File file = new File("E:/Code/messages/" + sender.getId() + "/" + recipient.getId() + ".txt");
        if (fileForCheck.exists()) rtnFile = fileForCheck;
        if (file.exists()) rtnFile = file;
        if(!fileForCheck.exists() & !file.exists()){
            folder.mkdir();
            try {
                file.createNewFile();
                rtnFile = file;
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return rtnFile;
    }

    public boolean save(User sender, User recipient,String content) {
        File file = getFileByUser(sender,recipient);
        return messageRepository.save(file,content);
    }

}
