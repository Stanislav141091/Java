package api;

public enum CodeResponse {
    OK(200),
    NOTFOUND(404);

    private Integer code;

    CodeResponse(Integer code){
        this.code = code;
    }

    public int getCodeResponse(){
        return code;
    }
}
