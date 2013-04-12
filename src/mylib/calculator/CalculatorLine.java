package com.mylib.calculator;

import java.util.List;
import java.util.ArrayList;

import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.observer.ClickObserverInterface;
import com.mylib.calculator.ButtonColmunIndex;
import com.mylib.calculator.CalculatorCellId;

/**
 * @brief Calculator Line Class.
 */
class CalculatorLine implements ClickObserverInterface {

    protected Activity activity = null;
    protected RelativeLayout lineLayout = null;
    protected List<CalculatorCell> calculatorCells = null;
    protected ClickObserverInterface observer = null;
    protected static final int CALCULATOR_BUTTON_WIDTH = 70;
    protected static final int CALCULATOR_BUTTON_TEXT_SIZE = 20;
    private static final String BUTTON_TEXT_SEVEN = "7";
    private static final String BUTTON_TEXT_EIGHT = "8";
    private static final String BUTTON_TEXT_NINE = "9";
    private static final String BUTTON_TEXT_DIVISION = "÷";

    /**
     * @brief Constractor.
     *
     * @param activity Activity Instance.
     * @param layout ViewGroup Instance.
     */
    public CalculatorLine(Activity activity, ViewGroup view) {
        this.activity = activity;
        createLineLayout(view);
        createCalculator();
    }

    /**
     * @brief Attach Observer.
     *
     * @param observer ClickObserverInterface Instance.
     */
    public void attachObserver(ClickObserverInterface observer) {
        this.observer = observer;
    }

    /**
     * @brief Create Line Layout.
     *
     * @param layout ViewGroup Instance.
     */
    protected void createLineLayout(ViewGroup view) {
        this.lineLayout = new RelativeLayout(this.activity);
        this.lineLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        // set layout params.
        LayoutParams layout_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        // add layout.
        view.addView(this.lineLayout, layout_params);
    }

    /**
     * @brief Create Calculator Cells.
     */
    protected void createCalculator() {
        // create cells.
        createCalculatorCells();

        // attach observer.
        attachObservertoCell();

        // set cell id.
        setCellsId();

        // set button text.
        setButtonText();

        // set button width.
        setButtonWidth();

        // set button text size.
        setButtonTextSize();

        // add layout.
        addIntoLayout();
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
     * @brief Attach Observer to Cell Instances.
     */
    protected void attachObservertoCell() {
        for( CalculatorCell cell : this.calculatorCells ) {
            cell.attachObserver(this);
        }
    }

    /**
     * @brief Set Cells Id.
     */
    protected void setCellsId() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setId(CalculatorCellId.ID_7.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setId(CalculatorCellId.ID_8.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setId(CalculatorCellId.ID_9.getCellId());
        this.calculatorCells.get(ButtonColmunIndex.FOUR.getIndex()).setId(CalculatorCellId.ID_DIVISION.getCellId());
    }

    /**
     * @brief Set Text on Button.
     */
    protected void setButtonText() {
        this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).setButtonText(BUTTON_TEXT_SEVEN);
        this.calculatorCells.get(ButtonColmunIndex.TWO.getIndex()).setButtonText(BUTTON_TEXT_EIGHT);
        this.calculatorCells.get(ButtonColmunIndex.THREE.getIndex()).setButtonText(BUTTON_TEXT_NINE);
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

    /**
     * @brief Set Text Size on Button.
     */
    protected void setButtonTextSize() {
        for( CalculatorCell cell : this.calculatorCells ) {
            cell.setButtonTextSize(CALCULATOR_BUTTON_TEXT_SIZE);
        }
    }

    /**
     * @brief Add into Layout.
     */
    protected void addIntoLayout() {
        RelativeLayout.LayoutParams params_one = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                                                 RelativeLayout.LayoutParams.WRAP_CONTENT);
        params_one.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        this.lineLayout.addView(this.calculatorCells.get(ButtonColmunIndex.ONE.getIndex()).getButtonView(), params_one);

        for( int i = 1 ; i < this.calculatorCells.size() ; ++i ) {
            RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
            layout_params.addRule(RelativeLayout.RIGHT_OF, this.calculatorCells.get(i - 1).getId());
            this.lineLayout.addView(this.calculatorCells.get(i).getButtonView(), layout_params);
        }
    }

    /**
     * @brief Notify Click Event.
     *
     * @param event CalculatorCell Instance.
     */
    @Override
    public void notifyClick(Object event) {
        if( null != this.observer ) this.observer.notifyClick(event);
    }
}

