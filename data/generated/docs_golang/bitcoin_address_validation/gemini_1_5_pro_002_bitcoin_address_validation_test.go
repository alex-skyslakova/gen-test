import (
	"bytes"
	"crypto/sha256"
	"errors"
	"testing"
)

// Tests for ValidA58 function
func TestValidA58(t *testing.T) {
	testCases := []struct {
		input    string
		expected bool
		err      error
	}{
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", true, nil}, // Valid address
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j", false, nil}, // Invalid checksum
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62", false, errors.New("too long")},      // Too short
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62ii", false, errors.New("too long")},     // Too long
		{"0AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", false, errors.New("not version 0")}, // Invalid version
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62o", false, nil},                         // Invalid character 'o'
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW620", true, nil},                            // Valid with '0'
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I", false, nil},                        // Invalid character 'I'
		{"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62l", false, nil},                        // Invalid character 'l'
        {"xAGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", false, errors.New("bad char")},   // Invalid char 'x'

	}

	for _, tc := range testCases {
		t.Run(tc.input, func(t *testing.T) {
			ok, err := ValidA58([]byte(tc.input))
			if ok != tc.expected {
				t.Errorf("Expected %v, got %v", tc.expected, ok)
			}
			if !equalError(err, tc.err) {
				t.Errorf("Expected error %v, got %v", tc.err, err)

			}
		})
	}
}


// Helper function for comparing errors since errors.Is doesn't always work
func equalError(e1, e2 error) bool {

    if e1 == nil && e2 == nil {
        return true
    }

    if e1 == nil || e2 == nil {
        return false
    }

    return e1.Error() == e2.Error()
}

// Test for A25.Set58
func TestA25Set58(t *testing.T) {

    testCases := []struct {
        input    string
        expected error
    }{
        {"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", nil},
        {"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62", errors.New("too long")},
        {"1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62ii", errors.New("too long")},
        {"xAGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", errors.New("bad char")},

    }
    for _, tc := range testCases {
        t.Run(tc.input, func(t *testing.T) {
            var a A25
            err := a.Set58([]byte(tc.input))


            if !equalError(err, tc.expected) {
                t.Errorf("For input %s: Expected error '%v', but got '%v'", tc.input, tc.expected, err)
            }
        })

    }


}


// Tests for DoubleSHA256
func TestDoubleSHA256(t *testing.T) {
	var a A25
	copy(a[:], []byte("hello world\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00"))

	expected, _ := hex.DecodeString("e9d71f5ee7c92d6dc7d2ee0ea6516ba0d13afa6c0c13aa13137153e0301f7801") //precomputed hash

	if !bytes.Equal(a.doubleSHA256(), expected) {
		t.Errorf("DoubleSHA256 incorrect")

	}


}


