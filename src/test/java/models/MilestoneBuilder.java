package models;

import com.google.gson.annotations.SerializedName;

public class MilestoneBuilder {
    @SerializedName("title")
    private String name;
    private String description;
    @SerializedName("due_date")
    private int dueDate;
    private String status;

    public static class Builder {
        private MilestoneBuilder newMilestone;

        public Builder() {
            newMilestone = new MilestoneBuilder();
        }

        public Builder withName(String milestoneName) {
            newMilestone.name = milestoneName;
            return this;
        }

        public Builder withDescription(String description) {
            newMilestone.description = description;
            return this;
        }

        public Builder withStatus(String statusType) {
            newMilestone.status = statusType;
            return this;
        }

        public Builder withDueDate(int dueDate) {
            newMilestone.dueDate = dueDate;
            return this;
        }

        public MilestoneBuilder build() {
            return newMilestone;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int dueDate() {
        return dueDate;
    }
}
