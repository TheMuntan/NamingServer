package dsbt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NamingController {


	private final MapDatabase map = MapDatabase.getInstanceMap();


	@GetMapping("/addNode")
	public String addNode(@RequestParam (value = "hostName",defaultValue = "null") String hostName, HttpServletRequest request){
		if(!(hostName.equals("null"))) {
			map.addNode(HashFunction.getHash(hostName),request.getRemoteAddr());
			return ("Node has been added!");
		}
		else
			return ("Please provide a hostname");
	}

	@PutMapping("/removeNode")
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
			int serverID = -1;
			// file -> hashfunctie, de hashfunctie vergelijken met de ID's , ip van server returnen
			return map.getIp(serverID);
		}
		else
			return null;
	}
}