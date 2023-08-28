package doob.repositoryes;


import doob.entity.Message;
import doob.entity.User;
import doob.services.MessageService;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

@Repository
public class MessageRepository {



    @Autowired
    private UserService userService;

    public boolean save(File file, String content) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(content + "\n");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<String> getStringFromFile(File file) {
        List<String> strings = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                strings.add(line);
                line = bufferedReader.readLine();
            }
            return strings;
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Message> convertInMessage(List<String> strings) {
        List<Message> messages = new ArrayList<>();
        for (String tmp : strings) {
            String[] words = tmp.split("\s");
            User sender = userService.findById(Integer.parseInt(words[0]));
            LocalDateTime dateTime = LocalDateTime.parse(words[1], ISO_DATE_TIME);
            String context = words[2];
            messages.add(new Message(0,context,sender,dateTime));
        }
        return messages;
    }


}
