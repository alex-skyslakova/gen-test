package main

import (
    "math/big"
    "testing"
)

func TestDCString(t *testing.T) {
    tests := []struct {
        dc     DC
        want   string
    }{
        {0, "0.00"},
        {1, "0.01"},
        {100, "1.00"},
        {123, "1.23"},
        {-123, "-1.23"},
    }
    for _, tt := range tests {
        if got := tt.dc.String(); got != tt.want {
            t.Errorf("DC.String() = %v, want %v", got, tt.want)
        }
    }
}

func TestDCExtend(t *testing.T) {
    tests := []struct {
        dc     DC
        n      int
        want   DC
    }{
        {0, 10, 0},
        {1, 10, 10},
        {100, 10, 1000},
        {123, 10, 1230},
    }
    for _, tt := range tests {
        if got := tt.dc.Extend(tt.n); got != tt.want {
            t.Errorf("DC.Extend(%v) = %v, want %v", tt.n, got, tt.want)
        }
    }
}

func TestParseDC(t *testing.T) {
    tests := []struct {
        s      string
        wantDC DC
        wantOk bool
    }{
        {"0.00", 0, true},
        {"0.01", 1, true},
        {"1.00", 100, true},
        {"1.23", 123, true},
        {"invalid", 0, false},
        {"1.234", 0, false},
    }
    for _, tt := range tests {
        if gotDC, gotOk := ParseDC(tt.s); gotDC != tt.wantDC || gotOk != tt.wantOk {
            t.Errorf("ParseDC(%v) = (%v, %v), want (%v, %v)", tt.s, gotDC, gotOk, tt.wantDC, tt.wantOk)
        }
    }
}

func TestTRSetString(t *testing.T) {
    tests := []struct {
        s      string
        wantTR TR
        wantOk bool
    }{
        {"0.00", TR{big.NewRat(0, 1)}, true},
        {"0.01", TR{big.NewRat(1, 100)}, true},
        {"1.00", TR{big.NewRat(1, 1)}, true},
        {"0.0765", TR{big.NewRat(765, 10000)}, true},
        {"invalid", TR{}, false},
    }
    for _, tt := range tests {
        tr := NewTR()
        if gotTR, gotOk := tr.SetString(tt.s); gotTR != tt.wantTR || gotOk != tt.wantOk {
            t.Errorf("TR.SetString(%v) = (%v, %v), want (%v, %v)", tt.s, gotTR, gotOk, tt.wantTR, tt.wantOk)
        }
    }
}

func TestTRTax(t *testing.T) {
    tests := []struct {
        tr     TR
        dc     DC
        want   DC
    }{
        {TR{big.NewRat(0, 1)}, 100, 0},
        {TR{big.NewRat(1, 100)}, 100, 1},
        {TR{big.NewRat(765, 10000)}, 100, 8},
        {TR{big.NewRat(765, 10000)}, 123, 10},
    }
    for _, tt := range tests {
        if got := tt.tr.Tax(tt.dc); got != tt.want {
            t.Errorf("TR.Tax(%v) = %v, want %v", tt.dc, got, tt.want)
        }
    }
}

func TestMainExample(t *testing.T) {
    hamburgerPrice, ok := ParseDC("5.50")
    if !ok {
        t.Fatal("Invalid hamburger price")
    }
    milkshakePrice, ok := ParseDC("2.86")
    if !ok {
        t.Fatal("Invalid milkshake price")
    }
    taxRate, ok := NewTR().SetString("0.0765")
    if !ok {
        t.Fatal("Invalid tax rate")
    }

    totalBeforeTax := hamburgerPrice.Extend(4000000000000000) +
        milkshakePrice.Extend(2)
    tax := taxRate.Tax(totalBeforeTax)
    total := totalBeforeTax + tax

    wantTotalBeforeTax := "22000000000000005.72"
    wantTax := "1683000000000000.44"
    wantTotal := "23683000000000006.16"

    if totalBeforeTax.String() != wantTotalBeforeTax {
        t.Errorf("Total before tax = %v, want %v", totalBeforeTax, wantTotalBeforeTax)
    }
    if tax.String() != wantTax {
        t.Errorf("Tax = %v, want %v", tax, wantTax)
    }
    if total.String() != wantTotal {
        t.Errorf("Total = %v, want %v", total, wantTotal)
    }
}
