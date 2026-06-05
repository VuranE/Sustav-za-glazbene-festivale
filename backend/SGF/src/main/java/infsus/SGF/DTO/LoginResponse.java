package infsus.SGF.DTO;

public class LoginResponse {

    private boolean success;
    private int type;

    public LoginResponse(boolean success, int type) {
        this.success = success;
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}