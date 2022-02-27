package models;

import com.google.gson.annotations.SerializedName;

public class ProjectBuilder {
    @SerializedName("title")
    private String name;
    private String code;
    private String description;

    public static class Builder {
        private ProjectBuilder newProject;

        public Builder() {
            newProject = new ProjectBuilder();
        }

        public ProjectBuilder.Builder withName(String username) {
            newProject.name = username;
            return this;
        }

        public ProjectBuilder.Builder withCode(String code) {
            newProject.code = code;
            return this;
        }

        public ProjectBuilder.Builder withDescription(String description) {
            newProject.description = description;
            return this;
        }

        public ProjectBuilder build() {
            return newProject;
        }
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
