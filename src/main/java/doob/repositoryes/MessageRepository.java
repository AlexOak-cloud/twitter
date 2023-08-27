package doob.repositoryes;


import doob.entity.Message;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public class MessageRepository {

    public boolean save(File file, String content){
        try(FileWriter fileWriter = new FileWriter(file,true)){
            fileWriter.write(content + "\n");
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Set<Message> getMessages(File file){
        Set<Message> messages = new HashSet<>();
        Message message = new Message();
        try(FileReader fileReader = new FileReader(file)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                message.setContext(line);
                messages.add(message);
                line = bufferedReader.readLine();
            }
            return messages;
        }catch (IOException ex){
            ex.printStackTrace();
            return Collections.emptySet();
        }
    }



}
