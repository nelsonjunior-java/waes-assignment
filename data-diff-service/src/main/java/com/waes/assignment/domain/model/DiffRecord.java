package com.waes.assignment.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * This class abstracts all the information for a diff record that will be stored on the database
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiffRecord {

    @Id
    private Long id;

    @Setter(AccessLevel.NONE)
    private String leftValue;

    @Setter(AccessLevel.NONE)
    private String rightValue;

    private DiffRecordStatus diffRecordStatus;

    private List<Difference> differences;

    public DiffRecord(Long id, String leftValue, String rightValue) {
        this.id = id;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        changeStatusToWaitingEvaluation();
    }

    /**
     * This method was created to make some validations before changing the rightValue
     * It checks if the value is being set for the first time, if it is not then it will check if this value
     * is different from the previous value, if so the status will return to WAITING_EVALUATION
     * in order to call the method responsible for the evaluations again on the next endpoint evaluation request
     * @param rightValue string value
     */
    public void changeRightValue(String rightValue){

        if(this.rightValue == null){ //Checks if the right value is being set for the first time
            this.rightValue = rightValue;
        }
        else if(!this.rightValue.equals(rightValue)){ //If rightValue is already set then check if the new value is different, if so the status return to waiting_evaluation
            this.rightValue = rightValue;
            changeStatusToWaitingEvaluation();
        }
    }

    /**
     * This method was created to make some validations before changing the leftValue
     * It checks if the value is being set for the first time, if it is not then it will check if this value
     * is different from the previous value, if so the status will return to WAITING_EVALUATION
     * in order to call the method responsible for the evaluations again on the next endpoint evaluation request
     * @param leftValue string value
     */
    public void changeLeftValue(String leftValue){

        if(this.leftValue == null){ //Checks if the leftValue value is being set for the first time
            this.leftValue = leftValue;
        }
        else if(!this.leftValue.equals(leftValue)){ //If leftValue is already set then check if the new value is different, if so the status return to waiting_evaluation
            this.leftValue = leftValue;
            changeStatusToWaitingEvaluation();
        }
    }

    /**
     * This method was created to change the DiffReord status in order to avoid
     * repeated code through the class
     */
    public void changeStatusToWaitingEvaluation(){
        this.setDiffRecordStatus(DiffRecordStatus.WAITING_EVALUATION);
    }


}
