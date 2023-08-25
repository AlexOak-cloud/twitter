package doob.repositoryes;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DialogRepository {

    private String path = "E:/Code/messages/";

    @Autowired
    private MessageService messageService;


   public Dialog buildDialog(Set<Message> messages, User sender, User recipient){
       Dialog rtnDialog = new Dialog();
       rtnDialog.setSender(sender);
       rtnDialog.setRecipient(recipient);
       rtnDialog.setMessages(messages);
       return rtnDialog;
   }




}





