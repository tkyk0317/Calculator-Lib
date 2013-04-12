package com.mylib.calculator;

import java.lang.ArithmeticException;

import android.util.Log;

import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.OperationState;
import com.mylib.calculator.OperationData;
import com.mylib.calculator.OperationNormalStateImpl;

/**
 * @brief Operation Division State Class.
 */
class OperationDivisionStateImpl extends OperationNormalStateImpl {

    /**
     * @brief Constractor.
     *
     * @param operation_data OperationData instance.
     */
    public OperationDivisionStateImpl(OperationData operation_data) {
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
        // check valid value.
        if( false == isValidNumber(cell) ) return true;

        String current_text = String.valueOf(this.operationData.getCalculateRightValue());
        current_text += cell.getButtonText();

        // set operation data.
        this.operationData.setDisplayText(current_text);
        this.operationData.setCalculateRightValue(Integer.valueOf(current_text));
        this.operationData.setOperationNextState(OperationState.DIVISION);
        return true;
    }

    /**
     * @brief Click Plus Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickPlusEvent(CalculatorCell cell) {
        calculate();
        this.operationData.setOperationNextState(OperationState.PLUS);
        return true;
    }

    /**
     * @brief Click Minus Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickMinusEvent(CalculatorCell cell) {
        calculate();
        this.operationData.setOperationNextState(OperationState.MINUS);
        return true;
    }

    /**
     * @brief Click Multiple Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickMultipleEvent(CalculatorCell cell) {
        calculate();
        this.operationData.setOperationNextState(OperationState.MULTIPLE);
        return true;
    }

    /**
     * @brief Click Division Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickDivisionEvent(CalculatorCell cell) {
        calculate();
        this.operationData.setOperationNextState(OperationState.DIVISION);
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
        calculate();
        this.operationData.setOperationNextState(OperationState.EQUAL);
        return true;
    }

    /**
     * @brief Calculate.
     */
    private void calculate() {
        int calculate_result = 0;
        try {
            calculate_result = this.operationData.getCalculateLeftValue() / this.operationData.getCalculateRightValue();
        } catch(ArithmeticException exception) {
            calculate_result = this.operationData.getCalculateLeftValue();
        }
        this.operationData.setCalculateLeftValue(calculate_result);
        this.operationData.setCalculateRightValue(0);
        this.operationData.setDisplayText(String.valueOf(calculate_result));
    }
}

