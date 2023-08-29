package doob.services;

import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.DialogRepository;
import doob.repositoryes.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DialogService {


    @Autowired
    private Utils utils;

    @Autowired
    DialogRepository dialogRepository;



    public boolean save(User sender, User recipient, String content) {
        if(content == null && sender == null && recipient == null) return false;
        File[] files = dialogRepository.getFileByUser(sender, recipient);
        File fileForSender = files[0];
        File fileForRecipient = files[1];
        return dialogRepository.save(fileForSender, fileForRecipient, content);
    }

    public Dialog getDialog(User sender, User recipient) {
        Dialog dialog = new Dialog();
        dialog.setSender(sender);
        dialog.setRecipient(recipient);
        File file = dialogRepository.getFileByUsers(sender, recipient);
        List<Message> messages = dialogRepository.convertInMessage(dialogRepository.getStringFromFile(file));
        dialog.setMessages(messages);
        return dialog;
    }


}
