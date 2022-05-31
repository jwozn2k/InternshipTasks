package restAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restAPI.Model.Parser;
import restAPI.Model.Person;
import restAPI.serivce.Filter;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class FilterController {

    final private Filter filter;
    private Parser parser;

    public FilterController(Filter filter) {
        try {
            parser = new Parser();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        this.filter = filter;
        this.filter.setPersons(parser.getPersons());
    }

    @GetMapping("/filter")
    public List<Person> person(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = false) String phone) {

        return filter.filter(name,surname,phone);
    }

}
