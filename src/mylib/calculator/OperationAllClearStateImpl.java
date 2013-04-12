package com.mylib.calculator;

import android.util.Log;

import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.OperationState;
import com.mylib.calculator.OperationData;
import com.mylib.calculator.OperationNormalStateImpl;

/**
 * @brief Operation AllClear State Class.
 */
class OperationAllClearStateImpl extends OperationNormalStateImpl {

    /**
     * @brief Constractor.
     *
     * @param operation_data OperationData instance.
     */
    public OperationAllClearStateImpl(OperationData operation_data) {
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

        String current_text = String.valueOf(this.operationData.getCalculateLeftValue());
        current_text += cell.getButtonText();

        // set operation data.
        this.operationData.setDisplayText(current_text);
        this.operationData.setCalculateLeftValue(Integer.valueOf(current_text));
        this.operationData.setOperationNextState(OperationState.NORMAL);
        return true;
    }

    /**
     * @brief Click Equal Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickEqualEvent(CalculatorCell cell) {
        return false;
    }
}

