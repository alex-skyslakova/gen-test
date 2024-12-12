package main

import (
    "testing"
    "time"
)

func TestBirthdayString(t *testing.T) {
    tests := []struct {
        bd       birthday
        expected string
    }{
        {birthday{5, 15}, "May 15"},
        {birthday{6, 17}, "June 17"},
        {birthday{7, 14}, "July 14"},
        {birthday{8, 15}, "August 15"},
    }

    for _, test := range tests {
        result := test.bd.String()
        if result != test.expected {
            t.Errorf("Expected %s, got %s", test.expected, result)
        }
    }
}

func TestMonthUniqueIn(t *testing.T) {
    choices := []birthday{
        {5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
        {7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
    }

    tests := []struct {
        bd       birthday
        expected bool
    }{
        {birthday{5, 15}, false},
        {birthday{6, 17}, false},
        {birthday{7, 14}, false},
        {birthday{8, 15}, false},
    }

    for _, test := range tests {
        result := test.bd.monthUniqueIn(choices)
        if result != test.expected {
            t.Errorf("Expected %v, got %v", test.expected, result)
        }
    }
}

func TestDayUniqueIn(t *testing.T) {
    choices := []birthday{
        {5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
        {7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
    }

    tests := []struct {
        bd       birthday
        expected bool
    }{
        {birthday{5, 15}, false},
        {birthday{5, 19}, true},
        {birthday{6, 17}, true},
        {birthday{7, 14}, false},
    }

    for _, test := range tests {
        result := test.bd.dayUniqueIn(choices)
        if result != test.expected {
            t.Errorf("Expected %v, got %v", test.expected, result)
        }
    }
}

func TestMonthWithUniqueDayIn(t *testing.T) {
    choices := []birthday{
        {5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
        {7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
    }

    tests := []struct {
        bd       birthday
        expected bool
    }{
        {birthday{5, 15}, true},
        {birthday{6, 17}, true},
        {birthday{7, 14}, false},
        {birthday{8, 15}, true},
    }

    for _, test := range tests {
        result := test.bd.monthWithUniqueDayIn(choices)
        if result != test.expected {
            t.Errorf("Expected %v, got %v", test.expected, result)
        }
    }
}

func TestMainLogic(t *testing.T) {
    choices := []birthday{
        {5, 15}, {5, 16}, {5, 19}, {6, 17}, {6, 18},
        {7, 14}, {7, 16}, {8, 14}, {8, 15}, {8, 17},
    }

    // Albert knows the month but doesn't know the day.
    var filtered []birthday
    for _, bd := range choices {
        if !bd.monthUniqueIn(choices) {
            filtered = append(filtered, bd)
        }
    }

    // Albert also knows that Bernard doesn't know the answer.
    var filtered2 []birthday
    for _, bd := range filtered {
        if !bd.monthWithUniqueDayIn(filtered) {
            filtered2 = append(filtered2, bd)
        }
    }

    // Bernard now knows the answer.
    var filtered3 []birthday
    for _, bd := range filtered2 {
        if bd.dayUniqueIn(filtered2) {
            filtered3 = append(filtered3, bd)
        }
    }

    // Albert now knows the answer too.
    var filtered4 []birthday
    for _, bd := range filtered3 {
        if bd.monthUniqueIn(filtered3) {
            filtered4 = append(filtered4, bd)
        }
    }

    if len(filtered4) != 1 {
        t.Errorf("Expected 1 result, got %d", len(filtered4))
    }

    expected := birthday{7, 16}
    if filtered4[0] != expected {
        t.Errorf("Expected %s, got %s", expected, filtered4[0])
    }
}
