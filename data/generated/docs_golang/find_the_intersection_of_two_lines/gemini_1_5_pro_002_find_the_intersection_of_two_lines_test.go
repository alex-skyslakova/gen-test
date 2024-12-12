package main

import (
	"errors"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestCreateLine(t *testing.T) {
	a := Point{4, 0}
	b := Point{6, 10}
	expected := Line{5, -20}
	actual := CreateLine(a, b)
	assert.Equal(t, expected, actual)

	c := Point{0, 3}
	d := Point{10, 7}
	expected = Line{0.4, 3}
	actual = CreateLine(c, d)
	assert.Equal(t, expected, actual)
}


func TestEvalX(t *testing.T) {
	l := Line{5, -20}
	x := 5.0
	expected := 5.0
	actual := EvalX(l, x)
	assert.Equal(t, expected, actual)

    l = Line{0.4, 3}
    x = 5.0
    expected = 5.0
    actual = EvalX(l, x)
    assert.Equal(t, expected, actual)

}

func TestIntersection(t *testing.T) {
	l1 := CreateLine(Point{4, 0}, Point{6, 10})
	l2 := CreateLine(Point{0, 3}, Point{10, 7})
	expected := Point{5, 5}
	actual, err := Intersection(l1, l2)
	assert.Nil(t, err)
	assert.Equal(t, expected, actual)


	l3 := CreateLine(Point{0, 0}, Point{1, 1})
	l4 := CreateLine(Point{0, 1}, Point{1, 2})
	_, err = Intersection(l3, l4)

	assert.Equal(t, errors.New("The lines do not intersect"), err)



	l5 := CreateLine(Point{0, 0}, Point{0, 1}) // Vertical line
	l6 := CreateLine(Point{1, 0}, Point{1, 1}) // Vertical line

	_, err = Intersection(l5, l6)
	assert.Equal(t, errors.New("The lines do not intersect"), err)


}



