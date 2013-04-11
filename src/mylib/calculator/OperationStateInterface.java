package com.mylib.calculator;

import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.OperationState;

/**
 * @brief Operation State Interface.
 */
interface OperationStateInterface {

    /**
     * @brief Click Event of NormalNumber Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickNumberEvent(CalculatorCell cell);

    /**
     * @brief Click All Clear Buton Event.
     *
     * @param cell CalculatorCell Instance.
     */
    public boolean clickAllClearEvent(CalculatorCell cell);

    /**
     * @brief Click Clear Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickClearEvent(CalculatorCell cell);

    /**
     * @brief Click Plus Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickPlusEvent(CalculatorCell cell);

    /**
     * @brief Click Minus Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickMinusEvent(CalculatorCell cell);

    /**
     * @brief Click Multiple Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickMultipleEvent(CalculatorCell cell);

    /**
     * @brief Click Division Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickDivisionEvent(CalculatorCell cell);

    /**
     * @brief Click Equal Button Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    public boolean clickEqualEvent(CalculatorCell cell);

    /**
     * @brief Get NextState.
     *
     * @return Next State.
     */
    public OperationState getNextState();

    /**
     * @brief Get Display Text.
     *
     * @return display text string.
     */
    public String getDisplayText();

    /**
     * @brief Set Display Text.
     *
     * @param display_text display text.
     */
    public void setDisplayText(String display_text);

    /**
     * @brief Set Calculate Left Value.
     *
     * @param value integer value.
     */
    public void setCalculateLeftValue(int value);

    /**
     * @brief Clear Display Text.
     */
    public void clearDisplayText();
}

