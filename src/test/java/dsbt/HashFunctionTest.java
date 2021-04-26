package dsbt;

import org.junit.Assert;
import org.junit.Test;

public class HashFunctionTest {
    @Test
    public void HashTest() {
        Assert.assertEquals(163840, HashFunction.getIntHash(0));
        Assert.assertEquals(0, HashFunction.getIntHash(Integer.MIN_VALUE));
        Assert.assertEquals(327680, HashFunction.getIntHash(Integer.MAX_VALUE));
        Assert.assertEquals(164111, HashFunction.getHash("test"));
    }
}
