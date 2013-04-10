package com.mylib.calculator;

/**
 * @brief Button Index Enum Class.
 */
enum ButtonColmunIndex {
    ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4);

    private final int index;

    private ButtonColmunIndex(int index) { this.index = index; }
    public int getIndex() { return this.index; }
}


