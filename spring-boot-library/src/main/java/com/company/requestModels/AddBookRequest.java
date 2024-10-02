package com.company.requestModels;

import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Data
public class AddBookRequest {

    private String title;
    private String author;
    private String description;
    private int copies;
    private String category;
    private String img;
}
