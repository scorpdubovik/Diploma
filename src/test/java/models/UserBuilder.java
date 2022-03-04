package models;

public class UserBuilder {
    private String email;
    private String name;
    private String roleTitle;

    public static class Builder {
        private UserBuilder newUser;

        public Builder() {
            newUser = new UserBuilder();
        }

        public UserBuilder.Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public UserBuilder.Builder withName(String username) {
            newUser.name = username;
            return this;
        }

        public UserBuilder.Builder withRoleTitle(String roleTitle) {
            newUser.roleTitle = roleTitle;
            return this;
        }

        public UserBuilder build() {
            return newUser;
        }
    }

    public String getEmail() { return email;}

    public String getName() {
        return name;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

}
