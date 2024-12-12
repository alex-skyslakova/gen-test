package main

import (
    "math"
    "testing"

    "github.com/fogleman/gg"
    "github.com/stretchr/testify/assert"
    "github.com/stretchr/testify/mock"
)

// MockContext is a mock implementation of gg.Context
type MockContext struct {
    mock.Mock
    WidthVal  int
    HeightVal int
}

func (m *MockContext) SetRGB255(r, g, b int) {
    m.Called(r, g, b)
}

func (m *MockContext) DrawRectangle(x, y, width, height float64) {
    m.Called(x, y, width, height)
}

func (m *MockContext) Fill() {
    m.Called()
}

func (m *MockContext) Width() int {
    return m.WidthVal
}

func (m *MockContext) Height() int {
    return m.HeightVal
}

func TestGreyBars(t *testing.T) {
    tests := []struct {
        name        string
        width       int
        height      int
        expectedBars []struct {
            x, y, width, height float64
            color                int
        }
    }{
        {
            name:   "640x320 display",
            width:  640,
            height: 320,
            expectedBars: []struct {
                x, y, width, height float64
                color                int
            }{
                {0, 0, 80, 80, 0},
                {80, 0, 80, 80, 36},
                {160, 0, 80, 80, 73},
                {240, 0, 80, 80, 109},
                {320, 0, 80, 80, 146},
                {400, 0, 80, 80, 182},
                {480, 0, 80, 80, 219},
                {560, 0, 80, 80, 255},
                {0, 80, 40, 80, 255},
                {40, 80, 40, 80, 239},
                {80, 80, 40, 80, 223},
                {120, 80, 40, 80, 207},
                {160, 80, 40, 80, 191},
                {200, 80, 40, 80, 175},
                {240, 80, 40, 80, 159},
                {280, 80, 40, 80, 143},
                {320, 80, 40, 80, 127},
                {360, 80, 40, 80, 111},
                {400, 80, 40, 80, 95},
                {440, 80, 40, 80, 79},
                {480, 80, 40, 80, 63},
                {520, 80, 40, 80, 47},
                {560, 80, 40, 80, 31},
                {600, 80, 40, 80, 15},
                {0, 160, 20, 80, 0},
                {20, 160, 20, 80, 7},
                {40, 160, 20, 80, 15},
                {60, 160, 20, 80, 23},
                {80, 160, 20, 80, 31},
                {100, 160, 20, 80, 39},
                {120, 160, 20, 80, 47},
                {140, 160, 20, 80, 55},
                {160, 160, 20, 80, 63},
                {180, 160, 20, 80, 71},
                {200, 160, 20, 80, 79},
                {220, 160, 20, 80, 87},
                {240, 160, 20, 80, 95},
                {260, 160, 20, 80, 103},
                {280, 160, 20, 80, 111},
                {300, 160, 20, 80, 119},
                {320, 160, 20, 80, 127},
                {340, 160, 20, 80, 135},
                {360, 160, 20, 80, 143},
                {380, 160, 20, 80, 151},
                {400, 160, 20, 80, 159},
                {420, 160, 20, 80, 167},
                {440, 160, 20, 80, 175},
                {460, 160, 20, 80, 183},
                {480, 160, 20, 80, 191},
                {500, 160, 20, 80, 199},
                {520, 160, 20, 80, 207},
                {540, 160, 20, 80, 215},
                {560, 160, 20, 80, 223},
                {580, 160, 20, 80, 231},
                {600, 160, 20, 80, 239},
                {620, 160, 20, 80, 247},
                {0, 240, 10, 80, 255},
                {10, 240, 10, 80, 247},
                {20, 240, 10, 80, 239},
                {30, 240, 10, 80, 231},
                {40, 240, 10, 80, 223},
                {50, 240, 10, 80, 215},
                {60, 240, 10, 80, 207},
                {70, 240, 10, 80, 199},
                {80, 240, 10, 80, 191},
                {90, 240, 10, 80, 183},
                {100, 240, 10, 80, 175},
                {110, 240, 10, 80, 167},
                {120, 240, 10, 80, 159},
                {130, 240, 10, 80, 151},
                {140, 240, 10, 80, 143},
                {150, 240, 10, 80, 135},
                {160, 240, 10, 80, 127},
                {170, 240, 10, 80, 119},
                {180, 240, 10, 80, 111},
                {190, 240, 10, 80, 103},
                {200, 240, 10, 80, 95},
                {210, 240, 10, 80, 87},
                {220, 240, 10, 80, 79},
                {230, 240, 10, 80, 71},
                {240, 240, 10, 80, 63},
                {250, 240, 10, 80, 55},
                {260, 240, 10, 80, 47},
                {270, 240, 10, 80, 39},
                {280, 240, 10, 80, 31},
                {290, 240, 10, 80, 23},
                {300, 240, 10, 80, 15},
                {310, 240, 10, 80, 7},
                {320, 240, 10, 80, 0},
            },
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            dc := &MockContext{WidthVal: tt.width, HeightVal: tt.height}
            dc.On("SetRGB255", mock.Anything, mock.Anything, mock.Anything).Return()
            dc.On("DrawRectangle", mock.Anything, mock.Anything, mock.Anything, mock.Anything).Return()
            dc.On("Fill").Return()

            greyBars(dc)

            calls := dc.Calls
            assert.Equal(t, len(tt.expectedBars), len(calls)/3, "Number of bars drawn does not match expected")

            for i, expected := range tt.expectedBars {
                setRGBCall := calls[i*3]
                drawRectCall := calls[i*3+1]
                fillCall := calls[i*3+2]

                assert.Equal(t, "SetRGB255", setRGBCall.Method, "Method call mismatch")
                assert.Equal(t, expected.color, setRGBCall.Arguments[0].(int), "Color mismatch")

                assert.Equal(t, "DrawRectangle", drawRectCall.Method, "Method call mismatch")
                assert.Equal(t, expected.x, drawRectCall.Arguments[0].(float64), "X position mismatch")
                assert.Equal(t, expected.y, drawRectCall.Arguments[1].(float64), "Y position mismatch")
                assert.Equal(t, expected.width, drawRectCall.Arguments[2].(float64), "Width mismatch")
                assert.Equal(t, expected.height, drawRectCall.Arguments[3].(float64), "Height mismatch")

                assert.Equal(t, "Fill", fillCall.Method, "Method call mismatch")
            }
        })
    }
}
