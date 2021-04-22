package dsbt;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Person {

    private ArrayList<String> people = new ArrayList<>();
    private int counter = 0;

    
	@GetMapping("/person")
	public String person() {
		return "boop";
	}

	@PutMapping("/add")
	public String add(@RequestParam(value = "name", defaultValue = "Error") String name) {
        people.add(name);
		return people.get(counter++);
	}

}
