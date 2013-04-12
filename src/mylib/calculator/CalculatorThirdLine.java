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
class CalculatorThirdLine extends CalculatorLine {

    private static final String BUTTON_TEXT_ONE = "1";
    private static final String BUTTON_TEXT_TWO = "2";
    private static final String BUTTON_TEXT_THREE = "3";
    private static final String BUTTON_TEXT_MINUS = "-";

    /**
     * @brief Constractor.
     *
     * @param activity Activity Instance.
     * @param layout ViewGroup Instance.
     */
    public CalculatorThirdLine(Activity activity, ViewGroup view) {
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
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setId(CalculatorCellId.ID_1.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setId(CalculatorCellId.ID_2.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setId(CalculatorCellId.ID_3.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setId(CalculatorCellId.ID_MINUS.getCellId());
    }

    /**
     * @brief Set Text on Button.
     */
    protected void setButtonText() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setButtonText(BUTTON_TEXT_ONE);
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setButtonText(BUTTON_TEXT_TWO);
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setButtonText(BUTTON_TEXT_THREE);
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setButtonText(BUTTON_TEXT_MINUS);
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

