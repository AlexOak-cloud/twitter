package doob.repositoryes;


import doob.entity.User;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Repository
public class MessageRepository {


    public boolean save(File file, String context){
        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(LocalDateTime.now().toString());
            fileWriter.write("\n");
            fileWriter.write(context);
            fileWriter.write("\n");
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
