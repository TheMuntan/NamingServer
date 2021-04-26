package dsbt;

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
	public ReturnMessage addNode(@RequestParam (value = "hostName",defaultValue = "null") String hostName, HttpServletRequest request) throws MissingHostname {
		// Logging
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		System.out.println("[" + formatter.format(date) + "] Request to add node {Name: "+hostName+" Ip: " + request.getRemoteAddr() + "} [  ]");

		if(!(hostName.equals("null"))) {
			map.addNode(HashFunction.getHash(hostName),request.getRemoteAddr());
			// Logging
			date.setTime(System.currentTimeMillis());
			System.out.print("\r[" + formatter.format(date) + "] Request to add node {Name: "+hostName+" Ip: " + request.getRemoteAddr() + "} [OK]\n");

			return new ReturnMessage("Node has been added!");
		}
		else
			throw  new MissingHostname();
	}

	@DeleteMapping("/removeNode")
	public String removeNode(@RequestParam (value = "hostname",defaultValue = "null") String hostname){
		if (!(hostname.equals("null"))){
			map.removeNode(HashFunction.getHash(hostname));
			return ("Node had been removed");
		}
		else
			return ("Please provide a host name");

	}

	@GetMapping("/getFile")
	public String getFile(@RequestParam(value = "filename", defaultValue = "null") String fileName){
		if (!fileName.equals("null")){


			int hashedFile = HashFunction.getHash(fileName);
			ArrayList<Integer> possibleNodes = new ArrayList<>();
			ArrayList<Integer> lowerNodes = new ArrayList<>();
			ArrayList<Integer> higherNodes = new ArrayList<>();
			int smallestDifference = -100;



			for (Map.Entry<Integer,String> entry : map.entrySet()){
				int temp = entry.getKey();
				possibleNodes.add(temp);
			}

			Iterator<Integer> iter = possibleNodes.iterator();

			while (iter.hasNext()){
				if (iter.next()< hashedFile){
					lowerNodes.add(iter.next());
					int temp = hashedFile - iter.next();
					if (temp < smallestDifference){
						smallestDifference = temp;
					}
				}
				else
					higherNodes.add(iter.next());
			}

			if (lowerNodes.size() == 0){
				Integer max = Collections.max(higherNodes);
				String ip = map.getIp(max);
				return ip;
			}
			else {
				return map.getIp(smallestDifference);
			}

/*
			Set<Integer> set = map.returnKeySet();
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){

				possibleNodes.add(it);
			}





			for (int i=hashedFile; i>0;i--){
				if (map.containsID(i)){
					closestUnder = map.getIp(i);
					break;
				}
			}

			if (closestUnder==null){

				Set<Integer> set = map.returnSet();
				Iterator<Integer> i =set.iterator();
				while(i.hasNext()){

				}
			}
*/
			// file -> hashfunctie, de hashfunctie vergelijken met de ID's , ip van server returnen


		}
		else
			return null;
	}
}