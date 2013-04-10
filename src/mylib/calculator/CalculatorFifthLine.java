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
class CalculatorFifthLine extends CalculatorLine {

    private static final String BUTTON_TEXT_AC = "AC";
    private static final String BUTTON_TEXT_EQUAL = "＝";

    /**
     * @brief Constractor.
     *
     * @param activity Activity Instance.
     * @param layout ViewGroup Instance.
     */
    public CalculatorFifthLine(Activity activity, ViewGroup view) {
        super(activity, view);
    }

    /**
     * @brief Create Cells.
     */
    protected void createCalculatorCells() {
        this.calculatorCells = new ArrayList<CalculatorCell>();
        this.calculatorCells.add(new CalculatorCell(this.activity));
        this.calculatorCells.add(new CalculatorCell(this.activity));
    }

    /**
     * @brief Set Cells Id.
     */
    protected void setCellsId() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setId(CalculatorCellId.ID_AC.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setId(CalculatorCellId.ID_EQUAL.getCellId());
    }

    /**
     * @brief Set Text on Button.
     */
    protected void setButtonText() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setButtonText(BUTTON_TEXT_AC);
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setButtonText(BUTTON_TEXT_EQUAL);
    }

    /**
     * @brief Set Button Width.
     */
    protected void setButtonWidth() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setButtonWidth(CALCULATOR_BUTTON_WIDTH * 2);
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setButtonWidth(CALCULATOR_BUTTON_WIDTH * 2);
    }
}

