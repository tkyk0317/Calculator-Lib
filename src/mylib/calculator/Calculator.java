package com.mylib.calculator;

import java.util.List;
import java.util.ArrayList;

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
    private String displayCalculateLeft = null;
    private String displayCalculateRight = null;
    private int calculateLeftValue = 0;
    private int calculateRightValue = 0;
    private List<CalculatorLine> calculatorLine = null;
    private CalculatorInputMode inputMode = CalculatorInputMode.INPUT_MODE_LEFT;
    private CalculatorOperation calculatorOperation = CalculatorOperation.UNKNOWN;

    private static final String DEFAULT_TITLE = "電卓";
    private static final int VERTICAL = 1;
    private static final int DEFAULT_TITLE_SIZE = 24;
    private static final int CALCULATOR_TEXT_SIZE = 24;
    private static final int CALCULATOR_VALUE_LENGTH = 10;
    private static final int CALCULATOR_PADDING = 10;

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
    public void appear() {
        this.calculatorDialog.show();
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
        this.displayCalculateLeft = new String();
        this.displayCalculateRight = new String();

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
        parseClickEvent((CalculatorCell)event);
    }

    /**
     * @brief Parse Click Event.
     *
     * @param cell CalculatorCell Instance.
     */
    private void parseClickEvent(CalculatorCell cell) {
        int id = cell.getId();

        if( id <= CalculatorCellId.ID_DOUBLE_ZERO.getCellId() ) {
            displayInputValue(cell);
        } else if( CalculatorCellId.ID_AC.getCellId() == id ) {
            // clcik ac button.
            clearCalculatorValue();
        } else if( CalculatorCellId.ID_CLEAR.getCellId() == id ) {
            // click clear button.
            this.calculateRightValue = 0;
        } else if( CalculatorCellId.ID_PLUS.getCellId() == id ) {
            // click plus button.
            transitionInputMode();
            this.calculatorOperation = CalculatorOperation.PLUS;
        } else if( CalculatorCellId.ID_MINUS.getCellId() == id ) {
            // click minus button.
            transitionInputMode();
            this.calculatorOperation = CalculatorOperation.MINUS;
        } else if( CalculatorCellId.ID_MULTIPLE.getCellId() == id ) {
            // click multiple button.
            transitionInputMode();
            this.calculatorOperation = CalculatorOperation.MULTIPLE;
        } else if( CalculatorCellId.ID_DIVISION.getCellId() == id ) {
            // click division button.
            transitionInputMode();
            this.calculatorOperation = CalculatorOperation.DIVISION;
        } else if( CalculatorCellId.ID_EQUAL.getCellId() == id ) {
            // click equal button.
            calculate();
            this.displayCalculateLeft = "";
            this.displayCalculateRight = "";
            this.calculatorEdit.setText(String.format("%,d", this.calculateLeftValue));
            this.calculatorOperation = CalculatorOperation.UNKNOWN;
        }
    }

    /**
     * @brief Display Input Value.
     *
     * @param cell CalculatorCell Instance.
     */
    private void displayInputValue(CalculatorCell cell) {
        if( this.calculatorEdit.getText().toString().length() >= CALCULATOR_VALUE_LENGTH ) return;

        // get calculator text.
        if( this.inputMode == CalculatorInputMode.INPUT_MODE_LEFT ) {
            this.displayCalculateLeft += cell.getButtonText();
            this.calculateLeftValue = Integer.valueOf(this.displayCalculateLeft);

            // reflection edit text view.
            this.calculatorEdit.setText(String.format("%,d", this.calculateLeftValue));
        } else {
            this.displayCalculateRight += cell.getButtonText();
            this.calculateRightValue = Integer.valueOf(this.displayCalculateRight);

            // reflection edit text view.
            this.calculatorEdit.setText(String.format("%,d", this.calculateRightValue));
        }
    }

    /**
     * @brief Clean Calculator Values.
     */
    private void clearCalculatorValue() {
        this.displayCalculateLeft = "";
        this.displayCalculateRight = "";
        this.calculatorEdit.setText(this.displayCalculateLeft);
        this.calculateLeftValue = 0;
        this.calculateRightValue = 0;
        this.inputMode = CalculatorInputMode.INPUT_MODE_LEFT;
    }

    /**
     * @brief Transition Input Mode.
     */
    private void transitionInputMode() {
        if( CalculatorInputMode.INPUT_MODE_LEFT == this.inputMode ) {
            this.inputMode = CalculatorInputMode.INPUT_MODE_RIGHT;
        }
    }

    /**
     * @brief Calculate Process.
     */
    private void calculate() {
        if( this.calculatorOperation == CalculatorOperation.PLUS ) {
            this.calculateLeftValue += this.calculateRightValue;
        } else if( this.calculatorOperation == CalculatorOperation.MINUS ) {
            this.calculateLeftValue -= this.calculateRightValue;
        } else if( this.calculatorOperation == CalculatorOperation.MULTIPLE ) {
            this.calculateLeftValue *= this.calculateRightValue;
        } else if( this.calculatorOperation == CalculatorOperation.DIVISION ) {
            this.calculateLeftValue /= this.calculateRightValue;
        }
    }

    /**
     * @brief Input Mode Enum Class.
     */
    private enum CalculatorInputMode {
        INPUT_MODE_LEFT, INPUT_MODE_RIGHT;
    }

    /**
     * @brief Operation Enum Class.
     */
    private enum CalculatorOperation {
        UNKNOWN, PLUS, MINUS, MULTIPLE, DIVISION;
    }
}

