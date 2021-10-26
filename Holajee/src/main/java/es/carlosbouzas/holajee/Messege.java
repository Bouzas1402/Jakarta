package es.carlosbouzas.holajee;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Messege {

    private static final String HELLO_MESSAGE = "JaKarta EE rocks!";

    public String get(){
        return HELLO_MESSAGE;
    }

}
