package dsbt;

import com.fasterxml.jackson.core.JsonProcessingException;
import dsbt.Exceptions.MissingHostname;
import dsbt.ResponseBody.ReturnMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class NamingController {


	private final MapDatabase map = MapDatabase.getInstanceMap();


	@PutMapping("/addNode")
	public ReturnMessage addNode(@RequestParam (value = "hostName",defaultValue = "null") String hostName, HttpServletRequest request) throws MissingHostname, JsonProcessingException {
		// Logging
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		System.out.println("[" + formatter.format(date) + "] Request to add node {Name: "+hostName+" Ip: " + request.getRemoteAddr() + "} [  ]");

		if(!(hostName.equals("null"))) {
			map.addNode(HashFunction.getHash(hostName),request.getRemoteAddr());
			// Logging
			date.setTime(System.currentTimeMillis());
			System.out.print("\r[" + formatter.format(date) + "] Request to add node {Name: "+hostName+" Ip: " + request.getRemoteAddr() + "} [OK]\n");
			map.saveToFile();
			return new ReturnMessage("Node has been added!");
		}
		else
			throw new MissingHostname();
	}

	@DeleteMapping("/removeNode")
	public ReturnMessage removeNode(@RequestParam (value = "hostname",defaultValue = "null") String hostName) throws MissingHostname, JsonProcessingException {
		// Logging
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		System.out.println("[" + formatter.format(date) + "] Request to remove node {Name: "+hostName+"} [  ]");
		if (!(hostName.equals("null"))){
			System.out.println("\r[" + formatter.format(date) + "] Request to remove node {Name: "+hostName+"} [OK]");
			map.removeNode(HashFunction.getHash(hostName));
			map.saveToFile();
			return new ReturnMessage("Node had been removed");
		}
		else
			throw new MissingHostname();
	}

	@GetMapping("/getFile")
	public String getFile(@RequestParam (value = "filename", defaultValue = "null") String fileName){
		if (!fileName.equals("null")){

			int hashedFile = HashFunction.getHash(fileName);
			ArrayList<Integer> possibleNodes = new ArrayList<>();
			ArrayList<Integer> lowerNodes = new ArrayList<>();
			ArrayList<Integer> higherNodes = new ArrayList<>();
			// smallestDifference moet een grote waarde hebben, in dit systeem zal er nooit een kleiner
			// verschil mogelijk zijn dan -999999999
			int smallestDifference = Integer.MAX_VALUE;


			for (Map.Entry<Integer,String> entry : map.entrySet()){
				int temp = entry.getKey();
				possibleNodes.add(temp);
			}

			// de waarde van iter.next moet in een variabele gestoken worden als deze meerdere keren gebruikt moet worden
			// anders zal iter.next elke keer de volgende waarde pakken telkens als deze aangeroepen wordt
			for (int nodeHash : possibleNodes) {
				if (nodeHash < hashedFile) {
					lowerNodes.add(nodeHash);
					int temp = hashedFile - nodeHash;
					if (temp < smallestDifference) {
						smallestDifference = temp;
					}
				} else
					higherNodes.add(nodeHash);
			}

			if (lowerNodes.size() == 0){
				Integer max = Collections.max(higherNodes);
				return map.getIp(max);
			}
			else {
				// Hier moet ook collections max gebruikt worden om de juiste waarde uit de map te kunnen halen
				return map.getIp(Collections.max(lowerNodes));
			}

		}
		else
			return null;
	}
}