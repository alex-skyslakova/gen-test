package main

import (
    "math/big"
    "testing"
)

func TestLeftFactorials(t *testing.T) {
    // Helper function to calculate left factorial
    leftFactorial := func(n int64) *big.Int {
        one := big.NewInt(1)
        f := big.NewInt(1)
        l := big.NewInt(0)
        for k := int64(1); k <= n; k++ {
            f.Mul(f, big.NewInt(k))
            l.Add(l, f)
        }
        return l
    }

    // Test cases for !0 through !10
    expectedValues := []string{
        "0", "1", "2", "4", "10", "34", "154", "874", "5914", "46234", "409114",
    }
    for i := 0; i <= 10; i++ {
        result := leftFactorial(int64(i))
        expected := new(big.Int)
        expected.SetString(expectedValues[i], 10)
        if result.Cmp(expected) != 0 {
            t.Errorf("!%d: expected %s, got %s", i, expected, result)
        }
    }

    // Test cases for !20 through !110 by tens
    expectedValuesByTens := []string{
        "6749977105224", "128425485935180314", "9157958657951075573395",
        "20935051082417771847631371547939998232420940314",
        "620960027832821612639424806694551108812720525606160920420940314",
        "141074930726669571000530822087000522211656242116439949000980378746128920420940314",
        "173639511802987526699717162409282876065556519849603157850853034644815111221599509216528920420940314",
        "906089587987695346534516804650290637694024830011956365184327674619752094289696314882008531991840922336528920420940314",
        "16695570072624210767034167688394623360733515163575864136345910335924039962404869510225723072235842668787507993136908442336528920420940314",
        "942786239765826579160595268206839381354754349601050974345395410407078230249590414458830117442618180732911203520208889371641659121356556442336528920420940314",
        "145722981061585297004706728001906071948635199234860720988658042536179281328615541936083296163475394237524337422204397431927131629058103519228197429698252556442336528920420940314",
    }
    for i := 20; i <= 110; i += 10 {
        result := leftFactorial(int64(i))
        expected := new(big.Int)
        expected.SetString(expectedValuesByTens[(i-20)/10], 10)
        if result.Cmp(expected) != 0 {
            t.Errorf("!%d: expected %s, got %s", i, expected, result)
        }
    }

    // Test cases for the length of !1000 through !10000 by thousands
    expectedLengths := []int{
        2565, 5733, 9128, 12670, 16322, 20062, 23875, 27749, 31678, 35656,
    }
    for i := 1000; i <= 10000; i += 1000 {
        result := leftFactorial(int64(i))
        expectedLength := expectedLengths[(i-1000)/1000]
        if len(result.String()) != expectedLength {
            t.Errorf("Length of !%d: expected %d, got %d", i, expectedLength, len(result.String()))
        }
    }
}
