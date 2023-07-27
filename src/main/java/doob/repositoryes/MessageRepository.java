package doob.repositoryes;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MessageRepository {



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

    public Set<Message> getMessages(File file){
        Set<Message> rtnSet = new HashSet<>();
        try(FileReader fileReader = new FileReader(file)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.readLine() != null){
                Message message = new Message();
                message.setContext(bufferedReader.readLine());
                rtnSet.add(message);
            }
            return rtnSet;
        }catch (IOException ex){
            ex.printStackTrace();
            return Collections.emptySet();
        }
    }







}
