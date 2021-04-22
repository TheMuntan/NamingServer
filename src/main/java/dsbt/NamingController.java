package dsbt;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class NamingController {


	private MapDatabase map = MapDatabase.getInstanceMap();


	@PutMapping("/addNode")
	public String addNode(@ModelAttribute("integer") int iD, @RequestParam(value = "ipAdress", defaultValue = "null") String ipAdress ){
		if(!(iD <= 0) && (!ipAdress.equals("null")) && !(iD>327680)) {
			map.addNode(iD,ipAdress);
			return ("Node has been added!");
		}
		else
			return ("The variables are not good! ");
	}

	@PutMapping("/removeNode")
	public String removeNode(@ModelAttribute("integer") int iD){
		if (!(iD <= 0)){
			map.removeNode(iD);
			return ("Node had been removed");
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