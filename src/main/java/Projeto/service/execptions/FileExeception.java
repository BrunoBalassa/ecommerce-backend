package Projeto.service.execptions;

public class FileExeception extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public FileExeception(String msg){
        super(msg);
    }
    public FileExeception(String msg, Throwable cause){
        super(msg, cause);
    }
}
