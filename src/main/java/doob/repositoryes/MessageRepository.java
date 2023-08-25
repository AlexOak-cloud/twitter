package doob.repositoryes;


import doob.entity.Message;
import doob.entity.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MessageRepository {

    private boolean fileExist(File file){
        return file.exists();
    }

    public boolean save(File file, String content){
        try(FileWriter fileWriter = new FileWriter(file,true)){
            fileWriter.write(content + "\n");
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Set<Message> getMessages(File file) {
        Set<Message> messages = new HashSet<>();
        Message message = new Message();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.readLine() != null) {
                message.setContext(bufferedReader.readLine());
                messages.add(message);
            }
            bufferedReader.close();
            return messages;
        } catch (IOException ex) {
            ex.printStackTrace();
            return messages;
        }
    }



}
