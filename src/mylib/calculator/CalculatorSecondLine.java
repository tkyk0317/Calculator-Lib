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
class CalculatorSecondLine extends CalculatorLine {

    private static final String BUTTON_TEXT_FOUR = "4";
    private static final String BUTTON_TEXT_FIVE = "5";
    private static final String BUTTON_TEXT_SIX = "6";
    private static final String BUTTON_TEXT_MINUS = "-";

    /**
     * @brief Constractor.
     *
     * @param activity Activity Instance.
     * @param layout ViewGroup Instance.
     */
    public CalculatorSecondLine(Activity activity, ViewGroup view) {
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
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setId(CalculatorCellId.ID_4.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setId(CalculatorCellId.ID_5.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setId(CalculatorCellId.ID_6.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setId(CalculatorCellId.ID_MINUS.getCellId());
    }

    /**
     * @brief Set Text on Button.
     */
    protected void setButtonText() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setButtonText(BUTTON_TEXT_FOUR);
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setButtonText(BUTTON_TEXT_FIVE);
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setButtonText(BUTTON_TEXT_SIX);
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setButtonText(BUTTON_TEXT_MINUS);
    }
}


