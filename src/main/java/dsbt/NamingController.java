package dsbt;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class NamingController {


	private MapDatabase map = MapDatabase.getInstanceMap();



	@PutMapping("/addNode")
	//public String addNode(@ModelAttribute("integer") int iD, @RequestParam(value = "ipAdress", defaultValue = "null") String ipAdress ){
	public String addNode(@RequestParam(value = "nameNode",defaultValue = "null") String nameNode, @RequestParam(value = "ipAdress", defaultValue = "null") String ipAdress ) {

		if (!nameNode.equals("null") && !ipAdress.equals("null")) {
			int iD = HashFunction.getHash(nameNode);

			if (!(iD <= 0) && !(iD > 327680)) { // deze if moet nog aangepast worden!
				map.addNode(iD, ipAdress);
				return ("Node has been added!");
			} else
				return ("The variables are not good! ");
		}
		else
			return ("The variables are not good! ");
	}

	@PutMapping("/removeNode")
	public String removeNode(@RequestParam(value = "nameNode",defaultValue = "null") String nameNode){
		if (!nameNode.equals("null")) {
			int iD = HashFunction.getHash(nameNode);

			if (!(iD <= 0) && !(iD > 327680)) { // deze if moet nog aangepast worden!
				map.removeNode(iD);
				return ("Node had been removed");
			} else
				return ("The variable is not goed");
		}
		else
			return ("The variable is not goed");
	}

	@GetMapping("/getFile")
	public File getFile(@RequestParam(value = "filename", defaultValue = "null") String fileName){
		if (!fileName.equals("null")){
			// file -> hashfunctie, de hashfunctie vergelijken met de ID's , file eruithalen en terug geven


			return null;
		}
		else
			return null;

	}
}