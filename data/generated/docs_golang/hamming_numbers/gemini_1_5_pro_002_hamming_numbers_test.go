import (
	"math/big"
	"testing"
)

func TestHamming(t *testing.T) {
	h := hamming(20)
	expected := []*big.Int{
		big.NewInt(1), big.NewInt(2), big.NewInt(3), big.NewInt(4), big.NewInt(5),
		big.NewInt(6), big.NewInt(8), big.NewInt(9), big.NewInt(10), big.NewInt(12),
		big.NewInt(15), big.NewInt(16), big.NewInt(18), big.NewInt(20), big.NewInt(24),
		big.NewInt(25), big.NewInt(27), big.NewInt(30), big.NewInt(32), big.NewInt(36),
	}
	for i := range h {
		if h[i].Cmp(expected[i]) != 0 {
			t.Errorf("Mismatch at index %d: expected %s, got %s", i, expected[i], h[i])
		}
	}

	h1691 := hamming(1691)
	expected1691 := big.NewInt(230)
    if h1691[1690].Cmp(expected1691) != 0 {
        t.Errorf("Mismatch at index 1690: expected %s, got %s", expected1691, h1691[1690])
    }


	hMillion := hamming(1000000)
	expectedMillion := new(big.Int)
    expectedMillion.SetString("51931278044838873608958984375000000000000000000000000000000000000000000000000000000", 10)
	if hMillion[len(hMillion)-1].Cmp(expectedMillion) != 0 {
		t.Errorf("Mismatch at index 1000000-1: expected %s, got %s", expectedMillion, hMillion[len(hMillion)-1])
	}

	// Test edge case: n=1
	h1 := hamming(1)
	if h1[0].Cmp(big.NewInt(1)) != 0 {
		t.Errorf("Mismatch for n=1: expected 1, got %s", h1[0])
	}
}


func TestMin(t *testing.T) {
	a := big.NewInt(5)
	b := big.NewInt(3)
	minVal := min(a, b)
	if minVal.Cmp(b) != 0 {
		t.Errorf("Min function failed: expected %s, got %s", b, minVal)
	}

	a = big.NewInt(2)
	b = big.NewInt(2)
	minVal = min(a,b)
	if minVal.Cmp(a) != 0 {
		t.Errorf("Min function failed for equal values: expected %s, got %s", a, minVal)
	}

}
