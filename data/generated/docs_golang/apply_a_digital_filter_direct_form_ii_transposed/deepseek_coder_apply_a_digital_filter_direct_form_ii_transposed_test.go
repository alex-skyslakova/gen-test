package main

import (
    "reflect"
    "testing"
)

func TestFilter(t *testing.T) {
    // Define the expected output for the given signal and filter coefficients
    expectedOutput := []float64{
        -0.152973986441, 0.023664129799, 0.200894839137, 0.031714465735,
        -0.110395149162, -0.167834134157, -0.067451178946, 0.133413720841,
        0.123916681643, 0.168484200287, 0.123587925868, 0.046306945866,
        0.066805574706, -0.034766559767, -0.028807017274, -0.022386016049,
        0.004321723308, 0.081684331594, 0.091565203585, 0.150786643150,
    }

    // Create a new filter instance with the given coefficients
    f := filter{
        a: []float64{1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17},
        b: []float64{0.16666667, 0.5, 0.5, 0.16666667},
    }

    // Apply the filter to the signal
    filteredSignal := f.filter(sig)

    // Compare the filtered signal with the expected output
    if !reflect.DeepEqual(filteredSignal, expectedOutput) {
        t.Errorf("Filtered signal does not match expected output.\nExpected: %v\nGot: %v", expectedOutput, filteredSignal)
    }
}
