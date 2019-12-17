package battleships.dao;

import battleships.domain.Score;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import org.bson.Document;

/**
 * DAO class for player scores.
 */
public class ScoreDao {

    private MongoClient client;
    private MongoDatabase database;

    public ScoreDao(String address) {
        try {
            client = MongoClients.create(address);
        } catch (Exception e) {
            System.out.println("Creating ScoreDao did not succeed.");
            e.printStackTrace();
        }

//        MongoCollection<Document> collection = database.getCollection("test");
//        
//        Document doc = new Document("name", "MongoDB").append("type", "database");
//        collection.insertOne(doc);
//        System.out.println(collection.countDocuments());
    }

    public MongoCollection<Document> getCollection() {
        database = client.getDatabase("Studies-db");
        MongoCollection<Document> collection = database.getCollection("BattleshipHighScores");
        return collection;
    }

    /**
     * Method that takes the information of the game winner and passes it
     * forward to the database.
     *
     * @param score The Score object containing information of the winner
     */
    public void addWinner(Score score) {
        Document doc = new Document("name", score.getName())
                .append("points", score.getPoints());

        getCollection().insertOne(doc);
        System.out.println("Winner added!");
    }

    /**
     * @return List of ten highest scores
     */
    public ArrayList<Score> getHighScores() {
        
        return getAll();
    }

    private ArrayList<Score> getAll() {
        ArrayList<Score> highScores = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document nextDoc = cursor.next();
                String name = nextDoc.getString("name");
                int points = nextDoc.getInteger("points");
                Score score = new Score(name, points);
                highScores.add(score);
            }
        } finally {
            cursor.close();
        }
        
        return highScores;
    }

}
