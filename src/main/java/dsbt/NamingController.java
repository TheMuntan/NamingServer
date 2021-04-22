package dsbt;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NamingController {


	private MapDatabase map = MapDatabase.getInstanceMap();


	@PutMapping("/addNode")
	public String addNode(@RequestParam (value = "integer",defaultValue = "-1") int iD, @RequestParam(value = "ip", defaultValue = "null") String ipAdress ){
		if(!(iD ==-1) && (!ipAdress.equals("null"))) {
			map.addNode(iD,ipAdress);
			return ("Node has been added!");
		}
		else
			return ("The variables are not good! ");
	}

	@PutMapping("/removeNode")
	public String removeNode(@RequestParam (value = "integer",defaultValue = "-1") int iD){
		if (!(iD ==-1)){
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