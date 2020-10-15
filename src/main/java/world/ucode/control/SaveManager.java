package world.ucode.control;

import org.sqlite.SQLiteDataSource;
import world.ucode.model.pet.Pet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveManager {
    private static final SQLiteDataSource dataSource = new SQLiteDataSource();
    private static Connection connection;
    private static Statement statement;

    private SaveManager() {
    }

    public static void setDbUrl(String url) {
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            connection.isValid(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savePet(Pet pet) {
        try {
            try {
                statement.execute("INSERT INTO pets");
            } catch (SQLException e) {
                createTable();
            }
            statement.execute("INSERT INTO pets" + generatePetValues(pet) + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String generatePetValues(Pet pet) {
        String values = " (name, max_health, max_hunger, max_thirst, max_happiness, " +
                "max_cleanliness, health, hunger, thirst, happiness, cleanliness) VALUES " +
                "(" + "'" + pet.getName() + "', " +
                pet.getHealth().getMaxValue() + ", " +
                pet.getHunger().getMaxValue() + ", " +
                pet.getThirst().getMaxValue() + ", " +
                pet.getHappiness().getMaxValue() + ", " +
                pet.getCleanliness().getMaxValue() + ", " +
                pet.getHealth().getValue() + ", " +
                pet.getHunger().getValue() + ", " +
                pet.getThirst().getValue() + ", " +
                pet.getHappiness().getValue() + ", " +
                pet.getCleanliness().getValue() +
                ")";
        return values;
    }

    private static void createTable() {
        try {
            statement.execute("CREATE TABLE pets (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(20)," +
                    "max_health FLOAT," +
                    "max_hunger FLOAT," +
                    "max_thirst FLOAT," +
                    "max_happiness FLOAT," +
                    "max_cleanliness FLOAT," +
                    "health FLOAT," +
                    "hunger FLOAT," +
                    "thirst FLOAT," +
                    "happiness FLOAT," +
                    "cleanliness FLOAT" +
                    ");");
        } catch (SQLException throwables) {
        }
    }
}
