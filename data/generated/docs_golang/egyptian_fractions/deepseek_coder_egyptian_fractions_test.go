package main

import (
    "math/big"
    "testing"
)

func TestToEgyptian(t *testing.T) {
    testCases := []struct {
        input    *big.Rat
        expected []string
    }{
        {big.NewRat(43, 48), []string{"1/2", "1/3", "1/16"}},
        {big.NewRat(5, 121), []string{"1/25", "1/757", "1/763309", "1/873960180913", "1/1527612795642093418846225"}},
        {big.NewRat(2014, 59), []string{"[34]", "1/8", "1/95", "1/14947", "1/670223480"}},
        {big.NewRat(0, 1), []string{"0"}},
        {big.NewRat(1, 1), []string{"1"}},
        {big.NewRat(2, 1), []string{"[2]"}},
        {big.NewRat(3, 2), []string{"[1]", "1/2"}},
        {big.NewRat(7, 4), []string{"[1]", "1/2", "1/4"}},
    }

    for _, tc := range testCases {
        result := toEgyptian(tc.input)
        if len(result) != len(tc.expected) {
            t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
            continue
        }
        for i, r := range result {
            if r.String() != tc.expected[i] {
                t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
                break
            }
        }
    }
}

func TestToEgyptianRecursive(t *testing.T) {
    testCases := []struct {
        input    *big.Rat
        expected []string
    }{
        {big.NewRat(1, 2), []string{"1/2"}},
        {big.NewRat(2, 3), []string{"1/2", "1/6"}},
        {big.NewRat(3, 4), []string{"1/2", "1/4"}},
        {big.NewRat(4, 5), []string{"1/2", "1/4", "1/20"}},
    }

    for _, tc := range testCases {
        result := toEgyptianRecursive(tc.input, []*big.Rat{})
        if len(result) != len(tc.expected) {
            t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
            continue
        }
        for i, r := range result {
            if r.String() != tc.expected[i] {
                t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
                break
            }
        }
    }
}

func TestToEgyptianWithImproperFractions(t *testing.T) {
    testCases := []struct {
        input    *big.Rat
        expected []string
    }{
        {big.NewRat(2, 1), []string{"[2]"}},
        {big.NewRat(3, 1), []string{"[3]"}},
        {big.NewRat(4, 1), []string{"[4]"}},
        {big.NewRat(5, 1), []string{"[5]"}},
        {big.NewRat(6, 1), []string{"[6]"}},
    }

    for _, tc := range testCases {
        result := toEgyptian(tc.input)
        if len(result) != len(tc.expected) {
            t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
            continue
        }
        for i, r := range result {
            if r.String() != tc.expected[i] {
                t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
                break
            }
        }
    }
}

func TestToEgyptianWithZero(t *testing.T) {
    testCases := []struct {
        input    *big.Rat
        expected []string
    }{
        {big.NewRat(0, 1), []string{"0"}},
    }

    for _, tc := range testCases {
        result := toEgyptian(tc.input)
        if len(result) != len(tc.expected) {
            t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
            continue
        }
        for i, r := range result {
            if r.String() != tc.expected[i] {
                t.Errorf("For input %v, expected %v, got %v", tc.input, tc.expected, result)
                break
            }
        }
    }
}
