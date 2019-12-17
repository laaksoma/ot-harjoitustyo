package battleships.ui;

import battleships.domain.Game;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * The MainClass for Battleships.
 */
public class BattleshipsMain {

    /**
     * Connects to database, creates the {@link Game} instance and calls for
     * {@link Game#beginStartMethod()} Game beginStartMethod.
     *
     * @param args The default parameter for Main
     * @throws Exception In case of {@link Game} instance Singleton exception.
     */
    public static void main(String[] args) throws Exception {
        UserInterface gameUserInterface = new GraphicalUserInterface().getInstance();
        Game battleships = Game.getInstance();
        battleships.beginStartMethod();
    }
}
