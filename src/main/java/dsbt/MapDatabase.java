package dsbt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public String saveToJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }

    public void saveToFile() throws JsonProcessingException {
        String json = saveToJsonString();
        String localDir = System.getProperty("user.dir");

        try {
            File file = new File("jsondatabase.json");
            if(file.createNewFile()) {
                FileWriter writer = new FileWriter("jsondatabase.json");
                writer.write(json);
                writer.close();
                System.out.println("File Saved");
            }
        } catch(IOException ioe) {
            System.out.println("Saving failed");
            ioe.printStackTrace();
        }
    }


    public Set<Map.Entry<Integer, String>> entrySet(){
        return map.entrySet();
    }




}
