package dsbt;

public class HashFunction {

    /**
     * Get the hash value of the incoming param
     * @param message to be hashed value
     * @return hash code in range [0, 327680]
     */
    public static int getHash(String message){
        // message.hashCode() will return a hash value in range [-2^31, 2^31-1]
        // + 2^31 => [0, 2^31-1+2^31]
        // % 327680 => [0, 327680]
        return (int)((message.hashCode() + Math.pow(2, 31))%327680);
    }
}
