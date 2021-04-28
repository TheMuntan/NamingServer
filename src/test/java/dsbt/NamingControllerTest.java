package dsbt;

import org.junit.Assert;
import org.junit.Test;

public class NamingControllerTest {

    @Test
    public void getFile() {
        NamingController nc = new NamingController();
        MapDatabase map = MapDatabase.getInstanceMap();
        map.addNode(20000, "10.0.0.1");
        map.addNode(110000, "10.0.0.2");
        map.addNode(200000, "10.0.0.3");
        map.addNode(210000, "10.0.0.4");
        map.addNode(130000, "10.0.0.5");

        System.out.println(HashFunction.getHash("test")); // hash("test") = 164111
        Assert.assertEquals("10.0.0.5", nc.getFile("test"));
    }
}