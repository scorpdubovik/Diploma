package models;

public class CaseBuilder {
    private String title;
    private String description;

    public static class Builder {
        private CaseBuilder newCase;

        public Builder() {
            newCase = new CaseBuilder();
        }

        public CaseBuilder.Builder withTitle(String title) {
            newCase.title = title;
            return this;
        }

        public CaseBuilder.Builder withDescription(String description) {
            newCase.description = description;
            return this;
        }

        public CaseBuilder build() {
            return newCase;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
