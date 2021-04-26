package dsbt;

public class HashFunction {

    /**
     * Get the hash value of the incoming param
     * @param message to be hashed value
     * @return hash code in range [0, 327680]
     */
    public static int getHash(String message){
        // message.hashCode() will return a hash value in range [-2^31, 2^31-1]
        // + 2^31+1 => [0, 2^31-1+2^31]
        // * (327680/(max+abs(min))) => [0, 327680]
        long hash = message.hashCode() + Math.abs((long)Integer.MIN_VALUE); // + 2^31+1 => [0, 2^31-1+2^31]
        hash = hash*327680/((long)Integer.MAX_VALUE + Math.abs((long)Integer.MIN_VALUE)); // * (327680) / (max+abs(min))) => [0, 327680]
        return (int)hash;
    }

    public static long getIntHash(int value) {
        long hash = value + Math.abs((long)Integer.MIN_VALUE); // + 2^31+1 => [0, 2^31-1+2^31]
        hash = hash*327680/((long)Integer.MAX_VALUE + Math.abs((long)Integer.MIN_VALUE)); // * (327680) / (max+abs(min))) => [0, 327680]
        return hash;
    }
}