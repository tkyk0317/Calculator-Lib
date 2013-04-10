package com.mylib.calculator;

import android.app.Activity;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import com.mylib.calculator.observer.ClickObserverInterface;

/**
 * @brief Calculator Cell Class.
 */
class CalculatorCell implements OnClickListener {

    private Activity activity = null;
    private String displayString = null;
    private Button calculatorButton = null;
    private ClickObserverInterface observer = null;

    /**
     * @brief Consutractor.
     *
     * @param display_string display string.
     */
    public CalculatorCell(Activity activity) {
        this.activity = activity;
        this.calculatorButton = new Button(this.activity);
        this.calculatorButton.setOnClickListener(this);
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
     * @brief Set Button Id.
     *
     * @param id view id.
     */
    public void setId(int id) {
        this.calculatorButton.setId(id);
    }

    /**
     * @brief Get Button Id.
     *
     * @return View Id.
     */
    public int getId() {
        return this.calculatorButton.getId();
    }

    /**
     * @brief Get View Instances.
     *
     * @return View Instances(Button Instances).
     */
    public View getButtonView() {
        return (View)this.calculatorButton;
    }

    /**
     * @brief Set Button Text.
     *
     * @param text displayed text on Button.
     */
    public void setButtonText(String text) {
        this.displayString = text;
        this.calculatorButton.setText(text);
    }

    /**
     * @brief Get Calculator Text.
     *
     * @return calculator text.
     */
    public String getButtonText() {
        return this.displayString;
    }

    /**
     * @brief Set Text Size on Button.
     *
     * @param size text size.
     */
    public void setButtonTextSize(int size) {
        this.calculatorButton.setTextSize(size);
    }

    /**
     * @brief Set Button Width.
     *
     * @param width Button Width.
     */
    public void setButtonWidth(int width) {
        this.calculatorButton.setWidth(width);
    }

    /**
     * @brief Set Button Height.
     *
     * @param height Button Height.
     */
    public void setButtonHeight(int height) {
        this.calculatorButton.setHeight(height);
    }

    /**
     * @brief OnClickEvent Listener.
     * @param view View Instance.
     */
    @Override
    public void onClick(View view) {
        if( null != this.observer ) this.observer.notifyClick(this);
    }
}


