package restAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import restAPI.Model.Parser;
import restAPI.Model.Person;
import restAPI.serivce.Filter;

import java.io.FileNotFoundException;
import java.util.List;


@RestController
public class ListController {

    final private Filter filter;
    private Parser parser;

    public ListController(Filter filter) {
        try {
            parser = new Parser();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        this.filter = filter;
        this.filter.setPersons(parser.getPersons());
    }


    @GetMapping("/list")
    public List<Person> person() {
        return filter.getPersons();
    }

}
