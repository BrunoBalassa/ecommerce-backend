package Projeto.service.execptions;

public class DataIntegrituExcepetion extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrituExcepetion(String msg){
        super(msg);
    }
    public DataIntegrituExcepetion(String msg, Throwable cause){
        super(msg, cause);
    }
}
