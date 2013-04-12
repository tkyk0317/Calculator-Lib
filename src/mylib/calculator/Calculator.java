package com.mylib.calculator;

import java.util.List;
import java.util.ArrayList;
import java.lang.NumberFormatException;

import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.EditText;
import android.view.Gravity;

import com.mylib.calculator.CalculatorLine;
import com.mylib.calculator.CalculatorSecondLine;
import com.mylib.calculator.CalculatorThirdLine;
import com.mylib.calculator.CalculatorFourthLine;
import com.mylib.calculator.CalculatorFifthLine;
import com.mylib.calculator.CalculatorCell;
import com.mylib.calculator.CalculatorCellId;
import com.mylib.calculator.OperationState;
import com.mylib.calculator.OperationData;
import com.mylib.calculator.OperationStateInterface;
import com.mylib.calculator.OperationNormalStateImpl;
import com.mylib.calculator.OperationPlusStateImpl;
import com.mylib.calculator.OperationMinusStateImpl;
import com.mylib.calculator.OperationMultipleStateImpl;
import com.mylib.calculator.OperationDivisionStateImpl;
import com.mylib.calculator.OperationAllClearStateImpl;
import com.mylib.calculator.OperationClearStateImpl;
import com.mylib.calculator.OperationEqualStateImpl;
import com.mylib.calculator.observer.ClickObserverInterface;

/**
 * @brief Calculator Class.
 */
public class Calculator implements ClickObserverInterface {

    private Activity activity = null;
    private AlertDialog calculatorDialog = null;
    private LinearLayout calculatorLayout = null;
    private TextView calculatorTextView = null;
    private EditText calculatorEdit = null;
    private List<CalculatorLine> calculatorLine = null;
    private ClickObserverInterface observer = null;
    private OperationData operationData = new OperationData();
    private OperationStateInterface currentOperationState = null;
    private OperationStateInterface normalOperationState = new OperationNormalStateImpl(this.operationData);
    private OperationStateInterface plusOperationState = new OperationPlusStateImpl(this.operationData);
    private OperationStateInterface minusOperationState = new OperationMinusStateImpl(this.operationData);
    private OperationStateInterface multipleOperationState = new OperationMultipleStateImpl(this.operationData);
    private OperationStateInterface divisionOperationState = new OperationDivisionStateImpl(this.operationData);
    private OperationStateInterface allClearOperationState = new OperationAllClearStateImpl(this.operationData);
    private OperationStateInterface clearOperationState = new OperationClearStateImpl(this.operationData);
    private OperationStateInterface equalOperationState = new OperationEqualStateImpl(this.operationData);

    private static final String DEFAULT_TITLE = "電卓";
    private static final String ZERO = "0";
    private static final int VERTICAL = 1;
    private static final int DEFAULT_TITLE_SIZE = 24;
    private static final int CALCULATOR_TEXT_SIZE = 24;
    private static final int CALCULATOR_VALUE_LENGTH = 10;
    private static final int CALCULATOR_PADDING = 10;
    private static final String CALCULATOR_MONEY_DELIMITER = ",";

    /**
     * @brief Constractor.
     *
     * @param activity Activity Instance.
     */
    public Calculator(Activity activity) {
        this.activity = activity;

        // create resource.
        createCalculatorResources();
    }

    /**
     * @brief Appear the Calculator.
     */
    public void appear(String appear_string) {
        int calculate_value = 0;
        try {
            calculate_value = Integer.valueOf(appear_string);
        } catch(NumberFormatException exception) {
            calculate_value = 0;
        }

        // set calculate value.
        displayInputValue(calculate_value);
        this.currentOperationState = this.normalOperationState;
        this.currentOperationState.clearAll();
        this.currentOperationState.setCalculateResult(calculate_value);
        this.currentOperationState.setCalculateLeftValue(calculate_value);

        // show calculator.
        this.calculatorDialog.show();
    }

    /**
     * @brief Dismiss Calculator Dialog.
     */
    public void disAppear() {
        this.calculatorDialog.dismiss();
    }

    /**
     * @brief Get Calculate Result Value.
     *
     * @return calculate result value.
     */
    public int getCalculateResult() {
        return this.currentOperationState.getCalculateResult();
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
     * @brief Set Calculator Title.
     *
     * @param title title string.
     */
    public void setCalculatorTitle(String title) {
        this.calculatorTextView.setText(title);
    }

    /**
     * @brief Create Resources.
     */
    private void createCalculatorResources() {
        this.calculatorDialog = new AlertDialog.Builder(this.activity).create();

        // create layout.
        createLayout();

        // set title.
        setCalculatorTitle(DEFAULT_TITLE);

        // add layout into dialog.
        this.calculatorDialog.setView(this.calculatorLayout);
    }

    /**
     * @brief Create Layout.
     */
    private void createLayout() {
        this.calculatorLayout = new LinearLayout(this.activity);
        this.calculatorTextView = new TextView(this.activity);
        this.calculatorEdit = new EditText(this.activity);

        // set meta data.
        setMetaData();

        // add view.
        this.calculatorLayout.addView(this.calculatorTextView);
        this.calculatorLayout.addView(this.calculatorEdit);

        // create each line.
        createEachLine();
    }

    /**
     * @brief Set Meta Data for View Instances.
     */
    private void setMetaData() {
        // linear layout.
        LayoutParams layout_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.calculatorLayout.setLayoutParams(layout_params);
        this.calculatorLayout.setOrientation(VERTICAL);

        // title text view.
        this.calculatorTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        this.calculatorTextView.setTextSize(DEFAULT_TITLE_SIZE);

        // value view.
        this.calculatorEdit.setTextSize(CALCULATOR_TEXT_SIZE);
        this.calculatorEdit.setWidth(LayoutParams.MATCH_PARENT);
        this.calculatorEdit.setHeight(LayoutParams.MATCH_PARENT);
        this.calculatorEdit.setMaxWidth(CALCULATOR_VALUE_LENGTH);
        this.calculatorEdit.setGravity(Gravity.RIGHT);
        this.calculatorEdit.setPadding(0, CALCULATOR_PADDING, 0, CALCULATOR_PADDING);
        this.calculatorEdit.setFocusable(false);
        this.calculatorEdit.setClickable(false);
        this.calculatorEdit.setLongClickable(false);
    }

    /**
     * @brief Create Each Line.
     */
    private void createEachLine() {
        this.calculatorLine = new ArrayList<CalculatorLine>();
        this.calculatorLine.add(new CalculatorLine(this.activity, this.calculatorLayout));
        this.calculatorLine.add(new CalculatorSecondLine(this.activity, this.calculatorLayout));
        this.calculatorLine.add(new CalculatorThirdLine(this.activity, this.calculatorLayout));
        this.calculatorLine.add(new CalculatorFourthLine(this.activity, this.calculatorLayout));
        this.calculatorLine.add(new CalculatorFifthLine(this.activity, this.calculatorLayout));

        // attach.
        for( CalculatorLine line : this.calculatorLine ) {
            line.attachObserver(this);
        }
    }

    /**
     * @brief Notify Click Event.
     *
     * @param event CalculatorCell Instance.
     */
    @Override
    public void notifyClick(Object event) {
        if( true == parseClickEvent((CalculatorCell)event) ) {
            // reflection display.
            String display_text = this.currentOperationState.getDisplayText();
            displayInputValue(Integer.valueOf(display_text));

            // notify observer.
            notifyObserver();

            // transition state.
            transitionOperationState();
        }
    }

    /**
     * @brief Parse Click Event.
     *
     * @param cell CalculatorCell Instance.
     *
     * @return true:transition next state false:not transition next state.
     */
    private boolean parseClickEvent(CalculatorCell cell) {
        int id = cell.getId();
        boolean is_next_state = false;

        if( id <= CalculatorCellId.ID_DOUBLE_ZERO.getCellId() ) {
            // click number button.
            is_next_state = this.currentOperationState.clickNumberEvent(cell);
        } else if( CalculatorCellId.ID_AC.getCellId() == id ) {
            // clcik ac button.
            is_next_state = this.currentOperationState.clickAllClearEvent(cell);
        } else if( CalculatorCellId.ID_CLEAR.getCellId() == id ) {
            // click clear button.
            is_next_state = this.currentOperationState.clickClearEvent(cell);
        } else if( CalculatorCellId.ID_PLUS.getCellId() == id ) {
            // click plus button.
            is_next_state = this.currentOperationState.clickPlusEvent(cell);
        } else if( CalculatorCellId.ID_MINUS.getCellId() == id ) {
            // click minus button.
            is_next_state = this.currentOperationState.clickMinusEvent(cell);
        } else if( CalculatorCellId.ID_MULTIPLE.getCellId() == id ) {
            // click multiple button.
            is_next_state = this.currentOperationState.clickMultipleEvent(cell);
        } else if( CalculatorCellId.ID_DIVISION.getCellId() == id ) {
            // click division button.
            is_next_state = this.currentOperationState.clickDivisionEvent(cell);
        } else if( CalculatorCellId.ID_EQUAL.getCellId() == id ) {
            // click equal button.
            is_next_state = this.currentOperationState.clickEqualEvent(cell);
        }
        return is_next_state;
    }

    /**
     * @brief Display Input Value.
     *
     * @param calculate_result calculate result value.
     */
    private void displayInputValue(int calculate_result) {
        // reflection edit text view.
        this.calculatorEdit.setText(String.format("%,d", calculate_result));
    }

    /**
     * @brief notify observer.
     */
    private void notifyObserver() {
        if( null != this.observer ) this.observer.notifyClick(this);
    }

    /**
     * @brief Transition OperationState.
     */
    private void transitionOperationState() {
        OperationState next_state = this.currentOperationState.getNextState();

        if( next_state == OperationState.NORMAL ) {
            this.currentOperationState = this.normalOperationState;
        } else if( next_state == OperationState.PLUS ) {
            this.currentOperationState = this.plusOperationState;
        } else if( next_state == OperationState.MINUS ) {
            this.currentOperationState = this.minusOperationState;
        } else if( next_state == OperationState.MULTIPLE ) {
            this.currentOperationState = this.multipleOperationState;
        } else if( next_state == OperationState.DIVISION ) {
            this.currentOperationState = this.divisionOperationState;
        } else if( next_state == OperationState.CLEAR ) {
            this.currentOperationState = this.clearOperationState;
        } else if( next_state == OperationState.ALL_CLEAR ) {
            this.currentOperationState = this.allClearOperationState;
        } else if( next_state == OperationState.ALL_CLEAR ) {
            this.currentOperationState = this.equalOperationState;
        } else if( next_state == OperationState.EQUAL ) {
            this.currentOperationState = this.equalOperationState;
        }
    }
}

