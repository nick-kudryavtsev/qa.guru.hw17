package hw17.requestBodies;

public class CreateUserRequestBody {
    private String
            username,
            job;

    public CreateUserRequestBody(String username, String job) {
        this.username = username;
        this.job = job;
    }
}
