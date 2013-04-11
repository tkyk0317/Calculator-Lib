package com.mylib.calculator;

import com.mylib.calculator.OperationState;

/**
 * @brief Operation Data.
 */
class OperationData {

    private String displayText = new String();
    private int calculateLeftValue = 0;
    private int calculateRightValue = 0;
    private OperationState nextState = OperationState.UNKNOWN;

    // setter.
    public void setDisplayText(String text) { this.displayText = text; }
    public void setCalculateLeftValue(int value) { this.calculateLeftValue = value; }
    public void setCalculateRightValue(int value) { this.calculateRightValue = value; }
    public void setOperationNextState(OperationState state) { this.nextState = state; }

    // getter.
    public String getDisplayText() { return this.displayText; }
    public int getCalculateLeftValue() { return this.calculateLeftValue; }
    public int getCalculateRightValue() { return this.calculateRightValue; }
    public OperationState getOperationNextState() { return this.nextState; }
}

