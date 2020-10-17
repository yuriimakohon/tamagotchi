package world.ucode.control;

import org.sqlite.SQLiteDataSource;
import world.ucode.model.pet.Pet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveManager {
    private static final SQLiteDataSource dataSource = new SQLiteDataSource();
    private static Connection connection;
    private static Statement statement;
    private static int currentSaveId;

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
            updateCurrentSaveId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePetSave(Pet pet) {
        try {
            statement.execute("UPDATE pets SET" + generatePetUpdate(pet) + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadPet(int saveId, Pet pet) {
        try (ResultSet result = statement.executeQuery("SELECT * FROM pets WHERE id = " + saveId + ";")) {
            if (result.next()) {
                Pet.Species type = Pet.Species.valueOf(result.getString("type"));
                pet.init(result.getString("name"), type, result.getInt("max_health"));
                pet.getHealth().setValue(result.getFloat("health"));
                pet.getHunger().setMaxValue(result.getFloat("max_hunger"));
                pet.getHunger().setValue(result.getFloat("hunger"));
                pet.getThirst().setMaxValue(result.getFloat("max_thirst"));
                pet.getThirst().setValue(result.getFloat("thirst"));
                pet.getHappiness().setMaxValue(result.getFloat("max_happiness"));
                pet.getHappiness().setValue(result.getFloat("happiness"));
                pet.getCleanliness().setMaxValue(result.getFloat("max_cleanliness"));
                pet.getCleanliness().setValue(result.getFloat("cleanliness"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateCurrentSaveId(saveId);
    }

    private static void updateCurrentSaveId() {
        try (ResultSet result = statement.executeQuery("SELECT last_insert_rowid();")) {
            result.next();
            currentSaveId = result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateCurrentSaveId(int id) {
        currentSaveId = id;
    }

    private static String generatePetValues(Pet pet) {
        String values = " (name, type, max_health, max_hunger, max_thirst, max_happiness, " +
                "max_cleanliness, health, hunger, thirst, happiness, cleanliness) VALUES " +
                "(" + "'" + pet.getName() + "', " +
                "'" + pet.getType().toString() + "', " +
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

    private static String generatePetUpdate(Pet pet) {
        String update = " health = " + pet.getHealth().getValue() + "," +
                "hunger = " + pet.getHunger().getValue() + "," +
                "thirst = " + pet.getThirst().getValue() + "," +
                "happiness = " + pet.getHappiness().getValue() + "," +
                "cleanliness = " + pet.getCleanliness().getValue() +
                " WHERE id = " + currentSaveId;
        return update;
    }

    private static void createTable() {
        try {
            statement.execute("CREATE TABLE pets (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(25)," +
                    "type VARCHAR(10)," +
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
        } catch (SQLException e) {
        }
    }
}
