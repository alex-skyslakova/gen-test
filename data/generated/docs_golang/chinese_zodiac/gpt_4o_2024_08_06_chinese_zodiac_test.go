package main

import (
	"testing"
)

func TestCZ(t *testing.T) {
	tests := []struct {
		year      int
		expectedAnimal string
		expectedYinYang string
		expectedElement string
		expectedStemBranch string
		expectedCycleYear int
	}{
		{1935, "Pig", "Yin", "Wood", "乙亥", 12},
		{1938, "Tiger", "Yang", "Earth", "戊寅", 15},
		{1968, "Monkey", "Yang", "Earth", "戊申", 45},
		{1972, "Rat", "Yang", "Water", "壬子", 49},
		{1976, "Dragon", "Yang", "Fire", "丙辰", 53},
		{1984, "Rat", "Yang", "Wood", "甲子", 1},
		{2022, "Tiger", "Yang", "Water", "壬寅", 39},
		{2023, "Rabbit", "Yin", "Water", "癸卯", 40},
		{2044, "Dragon", "Yang", "Wood", "甲辰", 1},
		{2045, "Snake", "Yin", "Wood", "乙巳", 2},
	}

	for _, test := range tests {
		animal, yinYang, element, stemBranch, cycleYear := cz(test.year)
		if animal != test.expectedAnimal {
			t.Errorf("For year %d, expected animal %s, got %s", test.year, test.expectedAnimal, animal)
		}
		if yinYang != test.expectedYinYang {
			t.Errorf("For year %d, expected yin/yang %s, got %s", test.year, test.expectedYinYang, yinYang)
		}
		if element != test.expectedElement {
			t.Errorf("For year %d, expected element %s, got %s", test.year, test.expectedElement, element)
		}
		if stemBranch != test.expectedStemBranch {
			t.Errorf("For year %d, expected stem-branch %s, got %s", test.year, test.expectedStemBranch, stemBranch)
		}
		if cycleYear != test.expectedCycleYear {
			t.Errorf("For year %d, expected cycle year %d, got %d", test.year, test.expectedCycleYear, cycleYear)
		}
	}
}
