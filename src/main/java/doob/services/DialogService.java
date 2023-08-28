package doob.services;

import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.DialogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DialogService {


    public static final String PATH = "E:/Code/messages/";

    @Autowired
    DialogRepository dialogRepository;

    private File[] getFileByUser(User sender, User recipient) {
        File folderSender = new File(PATH + sender.getId());
        File folderRecipient = new File(PATH + recipient.getId());
        File fileForSender = new File(folderSender.getPath() + "/" + recipient.getId() + ".txt");
        File fileForRecipient = new File(folderRecipient.getPath() + "/" + sender.getId() + ".txt");
        if (!folderSender.exists()) folderSender.mkdir();
        if (!folderRecipient.exists()) folderRecipient.mkdir();
        try {
            if (!fileForSender.exists()) fileForSender.createNewFile();
            if (!fileForRecipient.exists()) fileForRecipient.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File[] files = new File[2];
        files[0] = fileForSender;
        files[1] = fileForRecipient;
        return files;
    }

    public boolean save(User sender, User recipient, String content) {
        File[] files = getFileByUser(sender, recipient);
        File fileForSender = files[0];
        File fileForRecipient = files[1];
        return dialogRepository.save(fileForSender, fileForRecipient, content);
    }


}
