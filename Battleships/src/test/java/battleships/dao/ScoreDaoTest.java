package battleships.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import battleships.domain.Score;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import java.net.InetSocketAddress;

public class ScoreDaoTest {

    private MongoServer server;
    MongoClient client;
    ScoreDao dao;

    @Before
    public void setUp() {
        server = new MongoServer(new MemoryBackend());
        InetSocketAddress serverAddress = server.bind();
        ServerAddress sa = new ServerAddress(serverAddress);
        dao = new ScoreDao("mongodb://" + sa);
    }

    @After
    public void tearDown() {
        server.shutdown();
    }

    @Test
    public void scoreDaoAddsWinnerAndPointsCorrectly() {
        dao.addWinner(new Score("Jane", 10));
        assertEquals(1, dao.getHighScores().size());
    }

    @Test
    public void scoreDaoAddsWinnerCorrectly() {
        dao.addWinner(new Score("Mike", 100));
        assertEquals(1, dao.getHighScores().size());
    }
}
