package main

import (
    "reflect"
    "testing"
)

func TestDeconv(t *testing.T) {
    // Test case 1: Provided example
    h := []float64{-8, -9, -3, -1, -6, 7}
    f := []float64{-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1}
    g := []float64{24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96,
        96, 31, 55, 36, 29, -43, -7}

    // Test deconv(g, f) = h
    resultH := deconv(g, f)
    if !reflect.DeepEqual(resultH, h) {
        t.Errorf("deconv(g, f) = %v; want %v", resultH, h)
    }

    // Test deconv(g, h) = f
    resultF := deconv(g, h)
    if !reflect.DeepEqual(resultF, f) {
        t.Errorf("deconv(g, h) = %v; want %v", resultF, f)
    }

    // Test case 2: Edge case with shorter sequences
    h2 := []float64{1, 2}
    f2 := []float64{1, 2, 3}
    g2 := []float64{1, 4, 7, 6}

    // Test deconv(g2, f2) = h2
    resultH2 := deconv(g2, f2)
    if !reflect.DeepEqual(resultH2, h2) {
        t.Errorf("deconv(g2, f2) = %v; want %v", resultH2, h2)
    }

    // Test deconv(g2, h2) = f2
    resultF2 := deconv(g2, h2)
    if !reflect.DeepEqual(resultF2, f2) {
        t.Errorf("deconv(g2, h2) = %v; want %v", resultF2, f2)
    }

    // Test case 3: Edge case with single element sequences
    h3 := []float64{1}
    f3 := []float64{1}
    g3 := []float64{1}

    // Test deconv(g3, f3) = h3
    resultH3 := deconv(g3, f3)
    if !reflect.DeepEqual(resultH3, h3) {
        t.Errorf("deconv(g3, f3) = %v; want %v", resultH3, h3)
    }

    // Test deconv(g3, h3) = f3
    resultF3 := deconv(g3, h3)
    if !reflect.DeepEqual(resultF3, f3) {
        t.Errorf("deconv(g3, h3) = %v; want %v", resultF3, f3)
    }

    // Test case 4: Edge case with zero sequences
    h4 := []float64{0}
    f4 := []float64{0}
    g4 := []float64{0}

    // Test deconv(g4, f4) = h4
    resultH4 := deconv(g4, f4)
    if !reflect.DeepEqual(resultH4, h4) {
        t.Errorf("deconv(g4, f4) = %v; want %v", resultH4, h4)
    }

    // Test deconv(g4, h4) = f4
    resultF4 := deconv(g4, h4)
    if !reflect.DeepEqual(resultF4, f4) {
        t.Errorf("deconv(g4, h4) = %v; want %v", resultF4, f4)
    }
}
