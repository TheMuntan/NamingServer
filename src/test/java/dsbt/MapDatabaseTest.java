package dsbt;

import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;

public class MapDatabaseTest
{
    @Test
    public void saveToFileTest() throws JsonProcessingException {
        MapDatabase map = MapDatabase.getInstanceMap();

        map.addNode(1, "143.129.39.106:30126");
        map.addNode(7, "143.129.39.106:30127");
        map.addNode(21, "143.129.39.106:30128");

        Assert.assertEquals(map.saveToJsonString(), "{\"1\":\"143.129.39.106:30126\",\"7\":\"143.129.39.106:30127\",\"21\":\"143.129.39.106:30128\"}");
    }
}
