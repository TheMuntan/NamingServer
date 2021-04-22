package dsbt;

import java.util.HashMap;

public class MapDatabase {

    private static MapDatabase singleToneMap = null;
    private HashMap<Integer,String> map;


    public MapDatabase() {
        HashMap<Integer,String> map = new HashMap<>();
    }

    public static  MapDatabase getInstanceMap(){
        if (singleToneMap == null)
            singleToneMap = new MapDatabase();
        return singleToneMap;
    }

    public void addNode(int iD, String ipAdress){
        map.put(iD,ipAdress);
    }

    public void removeNode(int iD){
        map.remove(iD);
    }


}
