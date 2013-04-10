package com.mylib.calculator;

import java.util.List;
import java.util.ArrayList;

import android.app.Activity;
import android.view.ViewGroup;

import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.CalculatorLine;
import com.mylib.calculator.ButtonColmunIndex;
import com.mylib.calculator.CalculatorCellId;

/**
 * @brief Calculator Line Class.
 */
class CalculatorFourthLine extends CalculatorLine {

    private static final String BUTTON_TEXT_CLEAR = "C";
    private static final String BUTTON_TEXT_ZERO = "0";
    private static final String BUTTON_TEXT_DOUBLE_ZERO = "00";
    private static final String BUTTON_TEXT_DIVISION = "÷";

    /**
     * @brief Constractor.
     *
     * @param activity Activity Instance.
     * @param layout ViewGroup Instance.
     */
    public CalculatorFourthLine(Activity activity, ViewGroup view) {
        super(activity, view);
    }

    /**
     * @brief Create Cells.
     */
    protected void createCalculatorCells() {
        this.calculatorCells = new ArrayList<CalculatorCell>();
        this.calculatorCells.add(new CalculatorCell(this.activity));
        this.calculatorCells.add(new CalculatorCell(this.activity));
        this.calculatorCells.add(new CalculatorCell(this.activity));
        this.calculatorCells.add(new CalculatorCell(this.activity));
    }

    /**
     * @brief Set Cells Id.
     */
    protected void setCellsId() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setId(CalculatorCellId.ID_CLEAR.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setId(CalculatorCellId.ID_ZERO.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setId(CalculatorCellId.ID_DOUBLE_ZERO.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setId(CalculatorCellId.ID_DIVISION.getCellId());
    }

    /**
     * @brief Set Text on Button.
     */
    protected void setButtonText() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setButtonText(BUTTON_TEXT_CLEAR);
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setButtonText(BUTTON_TEXT_ZERO);
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setButtonText(BUTTON_TEXT_DOUBLE_ZERO);
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setButtonText(BUTTON_TEXT_DIVISION);
    }

    /**
     * @brief Set Button Width.
     */
    protected void setButtonWidth() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setButtonWidth(CALCULATOR_BUTTON_WIDTH);
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setButtonWidth(CALCULATOR_BUTTON_WIDTH);
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setButtonWidth(CALCULATOR_BUTTON_WIDTH);
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setButtonWidth(CALCULATOR_BUTTON_WIDTH);
    }
}

