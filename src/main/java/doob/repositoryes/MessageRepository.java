package doob.repositoryes;


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

        try(FileReader fileReader = new FileReader(file)){

        }catch (IOException ex){
            ex.printStackTrace();
            return Collections.emptySet();
        }


    }

}
