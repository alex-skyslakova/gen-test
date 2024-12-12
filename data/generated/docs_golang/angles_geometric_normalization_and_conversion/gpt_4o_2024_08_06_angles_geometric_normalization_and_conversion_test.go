package main

import (
    "math"
    "testing"
)

func TestD2D(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 358},
        {-1, 359},
        {0, 0},
        {1, 1},
        {2, 2},
        {6.2831853, 6.2831853},
        {16, 16},
        {57.2957795, 57.2957795},
        {359, 359},
        {399, 39},
        {6399, 279},
        {1000000, 280},
    }

    for _, test := range tests {
        result := d2d(test.input)
        if result != test.expected {
            t.Errorf("d2d(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestG2G(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 398},
        {-1, 399},
        {0, 0},
        {1, 1},
        {2, 2},
        {6.2831853, 6.2831853},
        {16, 16},
        {57.2957795, 57.2957795},
        {359, 359},
        {399, 399},
        {6399, 399},
        {1000000, 0},
    }

    for _, test := range tests {
        result := g2g(test.input)
        if result != test.expected {
            t.Errorf("g2g(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestM2M(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 6398},
        {-1, 6399},
        {0, 0},
        {1, 1},
        {2, 2},
        {6.2831853, 6.2831853},
        {16, 16},
        {57.2957795, 57.2957795},
        {359, 359},
        {399, 399},
        {6399, 6399},
        {1000000, 6400},
    }

    for _, test := range tests {
        result := m2m(test.input)
        if result != test.expected {
            t.Errorf("m2m(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestR2R(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 4.283185307179586},
        {-1, 5.283185307179586},
        {0, 0},
        {1, 1},
        {2, 2},
        {6.2831853, 0.000000006283185},
        {16, 3.716814692820414},
        {57.2957795, 0.283185307179586},
        {359, 5.26548245743669},
        {399, 0.017453292519943},
        {6399, 5.26548245743669},
        {1000000, 0.973536158445789},
    }

    for _, test := range tests {
        result := r2r(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("r2r(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestD2G(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 397.77777777777777},
        {-1, 398.8888888888889},
        {0, 0},
        {1, 1.1111111111111112},
        {2, 2.2222222222222223},
        {6.2831853, 6.981317},
        {16, 17.77777777777778},
        {57.2957795, 63.66197722222223},
        {359, 398.8888888888889},
        {399, 43.333333333333336},
        {6399, 310.6666666666667},
        {1000000, 311.1111111111111},
    }

    for _, test := range tests {
        result := d2g(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("d2g(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestD2M(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 6377.777777777778},
        {-1, 6388.888888888889},
        {0, 0},
        {1, 11.11111111111111},
        {2, 22.22222222222222},
        {6.2831853, 19.94722222222222},
        {16, 177.77777777777777},
        {57.2957795, 1021.3333333333333},
        {359, 6388.888888888889},
        {399, 688.8888888888889},
        {6399, 4978.666666666667},
        {1000000, 4977.777777777778},
    }

    for _, test := range tests {
        result := d2m(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("d2m(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestD2R(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, -0.03490658503988659},
        {-1, -0.017453292519943295},
        {0, 0},
        {1, 0.017453292519943295},
        {2, 0.03490658503988659},
        {6.2831853, 0.1096622711232151},
        {16, 0.2792526803190927},
        {57.2957795, 1.0000000000000002},
        {359, 6.265732014659643},
        {399, 0.6806784082777885},
        {6399, 4.870053496669288},
        {1000000, 4.886921905584122},
    }

    for _, test := range tests {
        result := d2r(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("d2r(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestG2D(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 358.2},
        {-1, 359.1},
        {0, 0},
        {1, 0.9},
        {2, 1.8},
        {6.2831853, 5.654866770000001},
        {16, 14.4},
        {57.2957795, 51.56620155},
        {359, 323.1},
        {399, 359.1},
        {6399, 323.1},
        {1000000, 0},
    }

    for _, test := range tests {
        result := g2d(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("g2d(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestG2M(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 6382},
        {-1, 6391},
        {0, 0},
        {1, 9},
        {2, 18},
        {6.2831853, 100.5308648},
        {16, 144},
        {57.2957795, 918.932472},
        {359, 5742},
        {399, 6391},
        {6399, 5742},
        {1000000, 0},
    }

    for _, test := range tests {
        result := g2m(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("g2m(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestG2R(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, -0.031415926535897934},
        {-1, -0.015707963267948967},
        {0, 0},
        {1, 0.015707963267948967},
        {2, 0.031415926535897934},
        {6.2831853, 0.1096622711232151},
        {16, 0.25132741228718347},
        {57.2957795, 0.8999999999999999},
        {359, 5.654866776461628},
        {399, 0.015707963267948967},
        {6399, 5.654866776461628},
        {1000000, 0},
    }

    for _, test := range tests {
        result := g2r(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("g2r(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestM2D(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 359.8875},
        {-1, 359.94375},
        {0, 0},
        {1, 0.05625},
        {2, 0.1125},
        {6.2831853, 0.353125},
        {16, 0.9},
        {57.2957795, 3.225},
        {359, 20.08125},
        {399, 22.44375},
        {6399, 359.94375},
        {1000000, 0},
    }

    for _, test := range tests {
        result := m2d(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("m2d(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestM2G(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 399.8611111111111},
        {-1, 399.93055555555554},
        {0, 0},
        {1, 0.06944444444444445},
        {2, 0.1388888888888889},
        {6.2831853, 0.4350694444444445},
        {16, 1},
        {57.2957795, 5.375},
        {359, 22.3125},
        {399, 24.875},
        {6399, 399.93055555555554},
        {1000000, 0},
    }

    for _, test := range tests {
        result := m2g(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("m2g(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestM2R(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 6.280369834735101},
        {-1, 6.281777570686674},
        {0, 0},
        {1, 0.0009817477042468103},
        {2, 0.0019634954084936206},
        {6.2831853, 0.006135923151542565},
        {16, 0.015707963267948967},
        {57.2957795, 0.056548667764616276},
        {359, 0.3518583772020568},
        {399, 0.3916790481341308},
        {6399, 6.281777570686674},
        {1000000, 0},
    }

    for _, test := range tests {
        result := m2r(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("m2r(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestR2D(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 245.66370614359172},
        {-1, 304.3362938564083},
        {0, 0},
        {1, 57.29577951308232},
        {2, 114.59155902616465},
        {6.2831853, 0.000000360000000},
        {16, 298.1980515339464},
        {57.2957795, 327.2957795},
        {359, 359},
        {399, 399},
        {6399, 6399},
        {1000000, 280},
    }

    for _, test := range tests {
        result := r2d(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("r2d(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestR2G(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 273.3333333333333},
        {-1, 336.66666666666663},
        {0, 0},
        {1, 63.66197723675813},
        {2, 127.32395447351626},
        {6.2831853, 0.0000004},
        {16, 331.331978926607},
        {57.2957795, 363.66197723675813},
        {359, 399},
        {399, 399},
        {6399, 399},
        {1000000, 0},
    }

    for _, test := range tests {
        result := r2g(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("r2g(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}

func TestR2M(t *testing.T) {
    tests := []struct {
        input    float64
        expected float64
    }{
        {-2, 4377.777777777778},
        {-1, 5188.888888888889},
        {0, 0},
        {1, 1018.5916357881302},
        {2, 2037.1832715762604},
        {6.2831853, 0.00000064},
        {16, 5298.198051533946},
        {57.2957795, 5837.2957795},
        {359, 6399},
        {399, 6399},
        {6399, 6399},
        {1000000, 0},
    }

    for _, test := range tests {
        result := r2m(test.input)
        if math.Abs(result-test.expected) > 1e-9 {
            t.Errorf("r2m(%f) = %f; expected %f", test.input, result, test.expected)
        }
    }
}
