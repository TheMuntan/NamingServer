package dsbt;

import java.util.HashMap;

public class MapDatabase {

    private static MapDatabase singletonMap = null;
    private HashMap<Integer,String> map;


    private MapDatabase() {
        this.map = new HashMap<>();
    }

    public static MapDatabase getInstanceMap(){
        if (singletonMap == null)
            singletonMap = new MapDatabase();
        return singletonMap;
    }

    public void addNode(int iD, String ipAddress){
        map.put(iD,ipAddress);
    }

    public void removeNode(int iD){
        map.remove(iD);
    }

    public String getIp(int iD){
        return map.get(iD);
    }

    public void saveToFile(){

    }

}
