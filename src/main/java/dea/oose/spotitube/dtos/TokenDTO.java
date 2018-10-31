package dea.oose.spotitube.dtos;

public class TokenDTO {

    private String user;
    private String token;

    public void setUser(String user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public TokenDTO(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
