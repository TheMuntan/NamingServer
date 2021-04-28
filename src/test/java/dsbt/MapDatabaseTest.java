package dsbt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the saveToFile function
 * Will probably fail because elements are out of order but matches can be seen manually
 */


public class MapDatabaseTest
{
    @Test
    public void saveToFileTest() throws JsonProcessingException {
        MapDatabase map = MapDatabase.getInstanceMap();

        map.addNode(1, "143.129.39.106:30126");
        map.addNode(7, "143.129.39.106:30127");
        map.addNode(21, "143.129.39.106:30128");

        Assert.assertEquals("{\"1\":\"143.129.39.106:30126\",\"21\":\"143.129.39.106:30128\",\"7\":\"143.129.39.106:30127\"}", map.saveToJsonString());
    }
}
