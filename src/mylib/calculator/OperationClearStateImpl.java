package com.mylib.calculator;

import android.util.Log;

import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.OperationState;
import com.mylib.calculator.OperationData;
import com.mylib.calculator.OperationNormalStateImpl;

/**
 * @brief Operation Clear State Class.
 */
class OperationClearStateImpl extends OperationNormalStateImpl {

    /**
     * @brief Constractor.
     *
     * @param operation_data OperationData instance.
     */
    public OperationClearStateImpl(OperationData operation_data) {
        super(operation_data);
    }

    /**
     * @brief Click Event of NormalNumber Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickNumberEvent(CalculatorCell cell) {
        // checl valid number.
        if( false == isValidNumber(cell) ) return true;

        String current_text = this.getDisplayText();
        current_text += cell.getButtonText();

        // set operation data.
        this.operationData.setDisplayText(current_text);
        this.operationData.setCalculateRightValue(Integer.valueOf(current_text));
        this.operationData.setOperationNextState(OperationState.MULTIPLE);
        return true;
    }
}

