package dsbt.Exceptions;

public class MissingHostname extends Exception{
    public MissingHostname(){
        super("Please provide a hostname");
    }
}
