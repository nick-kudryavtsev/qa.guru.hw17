package hw17.requestBodies;

public class RegisterUserRequestBody {
    private String email, password;

    public RegisterUserRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
