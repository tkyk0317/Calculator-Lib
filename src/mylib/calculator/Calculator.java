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
import android.widget.Button;

/**
 * @brief Calculator Class.
 */
public class Calculator {

    private Activity activity = null;
    private AlertDialog calculatorDialog = null;
    private LinearLayout calculatorLayout = null;
    private TextView calculatorTextView = null;
    private EditText calculatorValue = null;
    private List<CalculatorCell> calculatorCells = null;
    private List<RelativeLayout> calculatorRelativeLayout = null;
    private static final String DEFAULT_TITLE = "電卓";
    private static final int VERTICAL = 1;
    private static final int DEFAULT_TITLE_SIZE = 24;
    private static final int CALCULATOR_TEXT_SIZE = 24;
    private static final int CALCULATOR_ROW_NUM = 5;
    private static final int CALCULATOR_COLUMN_NUM = 4;
    private static final int CALCULATOR_BUTTON_WIDTH = 60;
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

        // create layout.
        createLayout();

        // create calculator.
        createLayoutIncludeCalculatorCell();
        createCalculatorCells();

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
        this.calculatorValue = new EditText(this.activity);

        // set meta data.
        setMetaData();

        // add view.
        this.calculatorLayout.addView(this.calculatorTextView);
        this.calculatorLayout.addView(this.calculatorValue);
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
        this.calculatorValue.setTextSize(CALCULATOR_TEXT_SIZE);
        this.calculatorValue.setWidth(LayoutParams.MATCH_PARENT);
        this.calculatorValue.setMaxWidth(CALCULATOR_VALUE_LENGTH);
        this.calculatorValue.setPadding(0, CALCULATOR_PADDING, 0, CALCULATOR_PADDING);
        this.calculatorValue.setFocusable(false);
        this.calculatorValue.setClickable(false);
        this.calculatorValue.setLongClickable(false);
    }

    /**
     * @brief Create Layout Instances include CalculatorCell.
     */
    private void createLayoutIncludeCalculatorCell() {
        this.calculatorRelativeLayout = new ArrayList<RelativeLayout>();

        for( int i = 0 ; i < CALCULATOR_ROW_NUM ; ++i ) {
            RelativeLayout relative_layout = new RelativeLayout(this.activity);

            // set layout params.
            LayoutParams layout_params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            // add layout.
            this.calculatorRelativeLayout.add(relative_layout);
            this.calculatorLayout.addView(relative_layout);
        }
    }

    /**
     * @brief Create Calculator Cells.
     */
    private void createCalculatorCells() {
        this.calculatorCells = new ArrayList<CalculatorCell>();

        for( int i = 0 ; i < CALCULATOR_COLUMN_NUM * CALCULATOR_ROW_NUM ; ++i ) {
            CalculatorCell calculator_cell =  new CalculatorCell(this.activity);
            calculator_cell.setId(i);

            // set meta data.
            RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(CALCULATOR_BUTTON_WIDTH, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if( 0 != i ) {
                // child align right.
                layout_params.addRule(RelativeLayout.RIGHT_OF, i - 1);
            }

            // set text value.
            calculator_cell.setText(String.valueOf(i));

            // add relative layout.
            int index = i / CALCULATOR_COLUMN_NUM;
            this.calculatorCells.add(calculator_cell);
            this.calculatorRelativeLayout.get(index).addView(calculator_cell, layout_params);
        }
    }

    /**
     * @brief Calculator Cell Class.
     */
    private class CalculatorCell extends Button {

        private Activity activity = null;
        private String displayString = null;

        /**
         * @brief Consutractor.
         *
         * @param display_string display string.
         */
        public CalculatorCell(Activity activity) {
            super(activity);
            this.activity = activity;
        }
    }
}

