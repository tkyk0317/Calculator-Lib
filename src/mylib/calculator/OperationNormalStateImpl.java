package com.mylib.calculator;

import java.lang.NumberFormatException;

import android.util.Log;

import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.OperationStateInterface;
import com.mylib.calculator.OperationState;
import com.mylib.calculator.OperationData;

/**
 * @brief Operation Normal State Class.
 */
class OperationNormalStateImpl implements OperationStateInterface {

    protected OperationData operationData = null;
    protected static final String EMPTY = "";
    protected static final String ZERO = "0";
    protected static final String DOUBLE_ZERO = "00";
    protected static final int CALCULATE_MAX_LENGTH = 10;

    /**
     * @brief Constractor.
     *
     * @param operation_data OperationData instance.
     */
    public OperationNormalStateImpl(OperationData operation_data) {
        this.operationData = operation_data;
        this.operationData.setDisplayText(EMPTY);
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

        String current_text = this.getDisplayText();
        current_text += cell.getButtonText();

        // set operation data.
        this.operationData.setDisplayText(current_text);
        this.operationData.setCalculateLeftValue(Integer.valueOf(current_text));
        this.operationData.setOperationNextState(OperationState.NORMAL);
        return true;
    }

    /**
     * @brief Check Valid Number.
     *
     * @param cell CalculatorCell Instance.
     */
    protected boolean isValidNumber(CalculatorCell cell) {
        // check text length.
        if( this.operationData.getDisplayText().length() >= CALCULATE_MAX_LENGTH ) {
            return false;
        }
        // check first input string equal zero.
        if( 0 == this.operationData.getDisplayText().length() &&
            true == cell.getButtonText().equals(ZERO) ) {
            return false;
        }
        // check first input string equal double zero.
        if( 0 == this.operationData.getDisplayText().length() &&
            cell.getButtonText().equals(DOUBLE_ZERO) ) {
            return false;
        }
        // check overflow integer value.
        try {
            String input_value = this.operationData.getDisplayText();
            input_value += cell.getButtonText();
            int checked_value = Integer.valueOf(input_value);
        } catch(NumberFormatException exception) {
            return false;
        }
        return true;
    }

    /**
     * @brief Click All Clear Buton Event.
     *
     * @param cell
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickAllClearEvent(CalculatorCell cell) {
        clearAll();
        this.operationData.setOperationNextState(OperationState.ALL_CLEAR);
        return true;
    }

    /**
     * @brief Clear All Value.
     */
    protected void clearAll() {
        this.operationData.setDisplayText(ZERO);
        this.operationData.setCalculateLeftValue(0);
        this.operationData.setCalculateRightValue(0);
    }

    /**
     * @brief Click Clear Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickClearEvent(CalculatorCell cell) {
        this.operationData.setDisplayText(EMPTY);
        this.operationData.setCalculateRightValue(0);
        this.operationData.setOperationNextState(OperationState.CLEAR);
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
        this.operationData.setOperationNextState(OperationState.EQUAL);
        return true;
    }

    /*
     * @brief Get Next State.
     *
     * @return Next State.
     */
    public OperationState getNextState() {
        return this.operationData.getOperationNextState();
    }

    /**
     * @brief Get Display Text.
     *
     * @return display text string.
     */
    public String getDisplayText() {
        return this.operationData.getDisplayText();
    }

    /**
     * @brief Set Display Text.
     *
     * @param display_text display text.
     */
    public void setDisplayText(String display_text) {
        this.operationData.setDisplayText(display_text);
    }

    /**
     * @brief Set Calculate Left Value.
     *
     * @param value integer value.
     */
    public void setCalculateLeftValue(int value) {
        this.operationData.setCalculateLeftValue(value);
    }

    /**
     * @brief Clear Display Text.
     */
    public void clearDisplayText() {
        this.operationData.setDisplayText(EMPTY);
    }
}

