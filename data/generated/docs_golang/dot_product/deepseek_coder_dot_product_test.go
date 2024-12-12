package main

import (
    "errors"
    "testing"
)

func TestDotProduct(t *testing.T) {
    // Test case 1: Vectors of the same length
    v1 := []int{1, 3, -5}
    v2 := []int{4, -2, -1}
    expectedResult := 3
    result, err := dot(v1, v2)
    if err != nil {
        t.Errorf("Expected no error, but got %v", err)
    }
    if result != expectedResult {
        t.Errorf("Expected %d, but got %d", expectedResult, result)
    }

    // Test case 2: Vectors of different lengths
    v3 := []int{1, 2, 3}
    v4 := []int{4, 5}
    _, err = dot(v3, v4)
    if err == nil {
        t.Error("Expected an error, but got none")
    }
    if !errors.Is(err, errors.New("incompatible lengths")) {
        t.Errorf("Expected 'incompatible lengths' error, but got %v", err)
    }

    // Test case 3: Vectors with zero length
    v5 := []int{}
    v6 := []int{}
    expectedResult = 0
    result, err = dot(v5, v6)
    if err != nil {
        t.Errorf("Expected no error, but got %v", err)
    }
    if result != expectedResult {
        t.Errorf("Expected %d, but got %d", expectedResult, result)
    }

    // Test case 4: Vectors with negative numbers
    v7 := []int{-1, -2, -3}
    v8 := []int{-4, -5, -6}
    expectedResult = 32
    result, err = dot(v7, v8)
    if err != nil {
        t.Errorf("Expected no error, but got %v", err)
    }
    if result != expectedResult {
        t.Errorf("Expected %d, but got %d", expectedResult, result)
    }

    // Test case 5: Vectors with mixed positive and negative numbers
    v9 := []int{1, -2, 3}
    v10 := []int{-4, 5, -6}
    expectedResult = -24
    result, err = dot(v9, v10)
    if err != nil {
        t.Errorf("Expected no error, but got %v", err)
    }
    if result != expectedResult {
        t.Errorf("Expected %d, but got %d", expectedResult, result)
    }
}
