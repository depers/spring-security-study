package cn.bravedawn.exception;

/**
 * @Author 冯晓
 * @Date 2020/1/7 20:04
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
