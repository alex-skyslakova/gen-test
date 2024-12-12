package main

import (
    "testing"
)

func TestCycloPoly(t *testing.T) {
    expectedPolynomials := []string{
        "x - 1",
        "x + 1",
        "x^2 + x + 1",
        "x^2 + 1",
        "x^4 + x^3 + x^2 + x + 1",
        "x^2 - x + 1",
        "x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^4 + 1",
        "x^6 + x^3 + 1",
        "x^4 - x^3 + x^2 - x + 1",
        "x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^4 - x^2 + 1",
        "x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^6 - x^3 + 1",
        "x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^8 - x^7 + x^6 - x^5 + x^4 - x^3 + x^2 - x + 1",
        "x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^6 - x^3 + 1",
        "x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^8 - x^6 + x^4 - x^2 + 1",
        "x^12 - x^9 + x^6 - x^3 + 1",
        "x^10 - x^5 + 1",
        "x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^12 - x^6 + 1",
        "x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^24 + x^23 + x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^18 - x^9 + 1",
        "x^26 + x^25 + x^24 + x^23 + x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^28 + x^27 + x^26 + x^25 + x^24 + x^23 + x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
        "x^16 - x^8 + 1",
    }

    for i := 1; i <= 30; i++ {
        p := cycloPoly(i)
        if p.String() != expectedPolynomials[i-1] {
            t.Errorf("Expected CP[%d] = %s, but got %s", i, expectedPolynomials[i-1], p.String())
        }
    }
}

func TestCycloPolyWithCoefAbs(t *testing.T) {
    expectedOrders := []int{3, 4, 7, 9, 11, 13, 15, 18, 21, 22}

    n := 0
    for i := 1; i <= 10; i++ {
        for {
            n++
            cyclo := cycloPoly(n)
            if cyclo.hasCoefAbs(i) {
                if n != expectedOrders[i-1] {
                    t.Errorf("Expected smallest order for coefficient magnitude %d is %d, but got %d", i, expectedOrders[i-1], n)
                }
                n--
                break
            }
        }
    }
}
