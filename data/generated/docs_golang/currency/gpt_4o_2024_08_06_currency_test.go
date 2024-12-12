package main

import (
    "testing"
)

func TestParseDC(t *testing.T) {
    tests := []struct {
        input    string
        expected DC
        valid    bool
    }{
        {"5.50", 550, true},
        {"2.86", 286, true},
        {"0.00", 0, true},
        {"invalid", 0, false},
        {"5.505", 0, false}, // Invalid due to more than two decimal places
    }

    for _, test := range tests {
        result, valid := ParseDC(test.input)
        if result != test.expected || valid != test.valid {
            t.Errorf("ParseDC(%s) = %d, %v; expected %d, %v", test.input, result, valid, test.expected, test.valid)
        }
    }
}

func TestDCString(t *testing.T) {
    tests := []struct {
        input    DC
        expected string
    }{
        {550, "5.50"},
        {286, "2.86"},
        {0, "0.00"},
        {-550, "-5.50"},
    }

    for _, test := range tests {
        result := test.input.String()
        if result != test.expected {
            t.Errorf("DC(%d).String() = %s; expected %s", test.input, result, test.expected)
        }
    }
}

func TestDCExtend(t *testing.T) {
    tests := []struct {
        price    DC
        quantity int
        expected DC
    }{
        {550, 2, 1100},
        {286, 3, 858},
        {0, 5, 0},
    }

    for _, test := range tests {
        result := test.price.Extend(test.quantity)
        if result != test.expected {
            t.Errorf("DC(%d).Extend(%d) = %d; expected %d", test.price, test.quantity, result, test.expected)
        }
    }
}

func TestTRSetString(t *testing.T) {
    tests := []struct {
        input    string
        expected bool
    }{
        {"0.0765", true},
        {"0.00", true},
        {"invalid", false},
    }

    for _, test := range tests {
        tr := NewTR()
        _, valid := tr.SetString(test.input)
        if valid != test.expected {
            t.Errorf("TR.SetString(%s) = %v; expected %v", test.input, valid, test.expected)
        }
    }
}

func TestTRTax(t *testing.T) {
    taxRate, _ := NewTR().SetString("0.0765")

    tests := []struct {
        amount   DC
        expected DC
    }{
        {22000000000000005, 1683000000000000},
        {100, 8}, // 7.65% of 100 is 7.65, rounded to 8
        {0, 0},
    }

    for _, test := range tests {
        result := taxRate.Tax(test.amount)
        if result != test.expected {
            t.Errorf("TR.Tax(%d) = %d; expected %d", test.amount, result, test.expected)
        }
    }
}

func TestMainCalculation(t *testing.T) {
    hamburgerPrice, _ := ParseDC("5.50")
    milkshakePrice, _ := ParseDC("2.86")
    taxRate, _ := NewTR().SetString("0.0765")

    totalBeforeTax := hamburgerPrice.Extend(4000000000000000) +
        milkshakePrice.Extend(2)
    tax := taxRate.Tax(totalBeforeTax)
    total := totalBeforeTax + tax

    if totalBeforeTax != 22000000000000005 {
        t.Errorf("Total before tax = %d; expected 22000000000000005", totalBeforeTax)
    }
    if tax != 1683000000000000 {
        t.Errorf("Tax = %d; expected 1683000000000000", tax)
    }
    if total != 23683000000000006 {
        t.Errorf("Total = %d; expected 23683000000000006", total)
    }
}
