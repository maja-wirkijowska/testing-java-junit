package noSpringPetClinic.controllers;

public class IndexController {
    public String index(){

        return "index";
    }

    public String oopsHandler() throws ValueNotFoundException {

        throw new ValueNotFoundException();
    }
}
