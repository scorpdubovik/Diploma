package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data

public class ProjectBuilder {
    private String name;
    private String code;
    private String description;
}
