package main

import (
	"testing"
)

func TestDegrees2CompassPoint(t *testing.T) {
	tests := []struct {
		degree  float32
		point   string
	}{
		{0.0, "North"},
		{16.87, "North by east"},
		{16.88, "North-northeast"},
		{33.75, "Northeast by north"},
		{50.62, "Northeast"},
		{50.63, "Northeast by east"},
		{67.5, "East-northeast"},
		{84.37, "East by north"},
		{84.38, "East"},
		{101.25, "East by south"},
		{118.12, "East-southeast"},
		{118.13, "Southeast by east"},
		{135.0, "Southeast"},
		{151.87, "Southeast by south"},
		{151.88, "South-southeast"},
		{168.75, "South by east"},
		{185.62, "South"},
		{185.63, "South by west"},
		{202.5, "South-southwest"},
		{219.37, "Southwest by south"},
		{219.38, "Southwest"},
		{236.25, "Southwest by west"},
		{253.12, "West-southwest"},
		{253.13, "West by south"},
		{270.0, "West"},
		{286.87, "West by north"},
		{286.88, "West-northwest"},
		{303.75, "Northwest by west"},
		{320.62, "Northwest"},
		{320.63, "Northwest by north"},
		{337.5, "North-northwest"},
		{354.37, "North by west"},
		{354.38, "North"},
	}

	for _, test := range tests {
		t.Run("", func(t *testing.T) {
			if got := degrees2compasspoint(test.degree); got != test.point {
				t.Errorf("degrees2compasspoint(%v) = %v; want %v", test.degree, got, test.point)
			}
		})
	}
}
