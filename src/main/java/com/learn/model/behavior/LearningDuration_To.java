package com.learn.model.behavior;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearningDuration_To<T> implements Serializable {

    private String dateLD;
    private String learningDuration;

}
