package doob.repositoryes;


import doob.mysql.Connector;
import doob.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FriendsRepository {

    private Connector connector = new Connector();

    public String generateTableName(User user) {
        return "friends_" + user.getId();
    }

    public boolean createTable(User user) {
        try (Statement statement = connector.getStatement()) {
            statement.execute("use test");
            statement.execute(
                    "CREATE TABLE if not exists " + generateTableName(user) + "(friend_id  INT UNIQUE NOT NULL," +
                            "FOREIGN KEY (friend_id) REFERENCES user(id)" +
                            ")ENGINE=INNODB ");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public List<User> findAllByUser(User user) {
        List<User> rtnList = new ArrayList<>();
        try (Statement statement = connector.getStatement()) {
            statement.execute("use test");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user join " + generateTableName(user)
                    + " WHERE user.id LIKE " + generateTableName(user) + ".friend_id");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String accountName = resultSet.getString(2);
                String email = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String name = resultSet.getString(5);
                String phone = resultSet.getString(7);
                rtnList.add(new User(id, name, lastName, accountName, email, phone));
            }
            return rtnList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean addFriend(User user, User friend) {
        try (Statement statement = connector.getStatement()) {
            statement.execute("use test");
            statement.execute("insert into " + generateTableName(user) + "(friend_id) values (" + friend.getId() + ")");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isFriend(User user, User friend) {
        boolean isFriend = false;
        try (Statement statement = connector.getStatement()) {
            statement.execute("use test");
            ResultSet resultSet = statement.executeQuery("select * from " + generateTableName(user));
            while (resultSet.next()) {
                if(resultSet.getInt(1) == friend.getId() ){
                    isFriend = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isFriend;
    }


}
