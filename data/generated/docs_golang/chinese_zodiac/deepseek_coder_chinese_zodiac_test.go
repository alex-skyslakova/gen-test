package main

import (
    "testing"
)

func TestCZ(t *testing.T) {
    testCases := []struct {
        year       int
        expectedAnimal string
        expectedYinYang string
        expectedElement string
        expectedStemBranch string
        expectedCycleYear int
    }{
        {1984, "Rat", "Yang", "Wood", "甲子", 1},
        {1985, "Ox", "Yin", "Wood", "乙丑", 2},
        {1986, "Tiger", "Yang", "Fire", "丙寅", 3},
        {2022, "Tiger", "Yang", "Water", "壬寅", 39},
        {2023, "Rabbit", "Yin", "Water", "癸卯", 40},
        {2024, "Dragon", "Yang", "Wood", "甲辰", 41},
        {2025, "Snake", "Yin", "Wood", "乙巳", 42},
        {2026, "Horse", "Yang", "Fire", "丙午", 43},
        {2027, "Goat", "Yin", "Fire", "丁未", 44},
        {2028, "Monkey", "Yang", "Earth", "戊申", 45},
        {2029, "Rooster", "Yin", "Earth", "己酉", 46},
        {2030, "Dog", "Yang", "Metal", "庚戌", 47},
        {2031, "Pig", "Yin", "Metal", "辛亥", 48},
        {2032, "Rat", "Yang", "Water", "壬子", 49},
        {2033, "Ox", "Yin", "Water", "癸丑", 50},
        {2034, "Tiger", "Yang", "Wood", "甲寅", 51},
        {2035, "Rabbit", "Yin", "Wood", "乙卯", 52},
        {2036, "Dragon", "Yang", "Fire", "丙辰", 53},
        {2037, "Snake", "Yin", "Fire", "丁巳", 54},
        {2038, "Horse", "Yang", "Earth", "戊午", 55},
        {2039, "Goat", "Yin", "Earth", "己未", 56},
        {2040, "Monkey", "Yang", "Metal", "庚申", 57},
        {2041, "Rooster", "Yin", "Metal", "辛酉", 58},
        {2042, "Dog", "Yang", "Water", "壬戌", 59},
        {2043, "Pig", "Yin", "Water", "癸亥", 60},
    }

    for _, tc := range testCases {
        t.Run(fmt.Sprintf("Year %d", tc.year), func(t *testing.T) {
            animal, yinYang, element, stemBranch, cycleYear := cz(tc.year)
            if animal != tc.expectedAnimal {
                t.Errorf("Expected animal %s, got %s", tc.expectedAnimal, animal)
            }
            if yinYang != tc.expectedYinYang {
                t.Errorf("Expected yin/yang %s, got %s", tc.expectedYinYang, yinYang)
            }
            if element != tc.expectedElement {
                t.Errorf("Expected element %s, got %s", tc.expectedElement, element)
            }
            if stemBranch != tc.expectedStemBranch {
                t.Errorf("Expected stem/branch %s, got %s", tc.expectedStemBranch, stemBranch)
            }
            if cycleYear != tc.expectedCycleYear {
                t.Errorf("Expected cycle year %d, got %d", tc.expectedCycleYear, cycleYear)
            }
        })
    }
}
