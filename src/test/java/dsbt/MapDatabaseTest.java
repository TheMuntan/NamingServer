package dsbt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Test for the saveToFile function
 * Will probably fail because elements are out of order but matches can be seen manually
 */


public class MapDatabaseTest
{
    @Test
    public void saveStringTest() throws JsonProcessingException {
        MapDatabase map = MapDatabase.getInstanceMap();

        map.addNode(1, "143.129.39.106:30126");
        map.addNode(7, "143.129.39.106:30127");
        map.addNode(21, "143.129.39.106:30128");

        Assert.assertEquals("{\"1\":\"143.129.39.106:30126\",\"21\":\"143.129.39.106:30128\",\"7\":\"143.129.39.106:30127\"}", map.saveToJsonString());
    }

    @Test
    public void saveToFileTest() throws JsonProcessingException, FileNotFoundException {
        MapDatabase map = MapDatabase.getInstanceMap();

        map.addNode(1, "143.129.39.106:30126");
        map.addNode(7, "143.129.39.106:30127");
        map.addNode(21, "143.129.39.106:30128");

        map.saveToJsonString();

        File json = new File("jsondatabase.json");
        Scanner reader = new Scanner(json);
        String jsonString = reader.nextLine();
        reader.close();

        Assert.assertEquals("{\"1\":\"143.129.39.106:30126\",\"21\":\"143.129.39.106:30128\",\"7\":\"143.129.39.106:30127\"}", jsonString);
    }
}
