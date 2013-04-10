package com.mylib.calculator;

/**
 * @brief Calculator Cell Id Enum Class.
 */
enum CalculatorCellId {
    ID_1(1), ID_2(2), ID_3(3), ID_4(4), ID_5(5), ID_6(6), ID_7(7), ID_8(8), ID_9(9),
    ID_ZERO(10), ID_DOUBLE_ZERO(11), ID_AC(12), ID_CLEAR(13), ID_PLUS(14),
    ID_MINUS(15), ID_MULTIPLE(16), ID_DIVISION(17), ID_EQUAL(18);

    private final int cell_id;

    private CalculatorCellId(int cell_id) { this.cell_id = cell_id; }
    public int getCellId() { return this.cell_id; }
}

