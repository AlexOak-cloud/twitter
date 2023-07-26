package doob.repositoryes;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.mysql.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MessageRepository {

    @Autowired
    private Connector connector;


    public boolean save(String context, File file) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(LocalDateTime.now() + ": ");
            fw.write(context);
            fw.write("\n");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Set<Message> getMessagesByUsers(User sender, User recipient, File file) {
        Set<Message> messages = new HashSet<>();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.readLine() != null) {
                Message message = new Message();
                message.setContext(bufferedReader.readLine());
                messages.add(message);
            }
            bufferedReader.close();
            return messages;
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptySet();
        }
    }

}
