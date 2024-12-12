package main

import (
	"github.com/fogleman/gg"
	"github.com/stretchr/testify/assert"
	"image/color"
	"testing"
)

func TestPinstripe(t *testing.T) {
	// Test cases with different dimensions
	testCases := []struct {
		width  int
		height int
	}{
		{900, 600},
		{300, 200},
		{100, 100},
	}

	for _, tc := range testCases {
		dc := gg.NewContext(tc.width, tc.height)
		pinstripe(dc)

		// Verify stripe widths and colors
		h := tc.height / 4
		for b := 1; b <= 4; b++ {
			for x, ci := 0, 0; x < tc.width; x, ci = x+b, ci+1 {
				expectedColor := color.RGBA{
					R: uint8(parseHex(palette[ci%8][0:2])),
					G: uint8(parseHex(palette[ci%8][2:4])),
					B: uint8(parseHex(palette[ci%8][4:6])),
					A: 255,
				}

				y := h * (b - 1)
				for i := 0; i < b; i++ {
					for j := 0; j < h; j++ {
						actualColor := dc.Image().At(x+i, y+j)
                        r, g, b, a := actualColor.RGBA()
                        actualColorRGBA := color.RGBA{uint8(r >> 8), uint8(g >> 8), uint8(b >> 8), uint8(a >> 8)}

						assert.Equal(t, expectedColor, actualColorRGBA, "Color mismatch at (%d, %d) for stripe width %d", x+i, y+j, b)
					}
				}


			}
		}

	}
}

func parseHex(s string) int {
	var val int
	for i := 0; i < len(s); i++ {
		var digit int
		if s[i] >= '0' && s[i] <= '9' {
			digit = int(s[i] - '0')
		} else if s[i] >= 'a' && s[i] <= 'f' {
			digit = int(s[i] - 'a' + 10)
		} else if s[i] >= 'A' && s[i] <= 'F' {
			digit = int(s[i] - 'A' + 10)
		}
		val = val*16 + digit

	}
	return val

}


