package doob.repositoryes;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.mysql.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MessageRepository {

    @Autowired
    private Connector connector;
    public static final String path = "E:/Code/messages/";


    public String getPath(User sender, User recipient) {
        String pathForCreate = path + sender.getId() + "_" + recipient.getId();
        File folder = new File(pathForCreate);
        if (!folder.exists()) {
            folder.mkdir();
        }
        return pathForCreate;
    }

    public boolean save(String context, User sender, User recipient) {
        File file = new File(getPath(sender, recipient));
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(LocalDateTime.now().toString() + ": ");
            writer.write(context);
            writer.write("\n");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Dialog buildDialog(User sender, User recipient) {
        Set<Message> messages = new HashSet<>();
        try (FileReader fr = new FileReader(getPath(sender, recipient))) {
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                Message message = new Message();
                message.setContext(br.readLine());
                message.setSender(sender);
                message.setRecipient(recipient);
                messages.add(message);
            }
            Dialog dialog = new Dialog();
            dialog.setMessages(messages);
            return dialog;
        } catch (IOException e) {
            e.printStackTrace();
            return new Dialog();
        }

    }
}
