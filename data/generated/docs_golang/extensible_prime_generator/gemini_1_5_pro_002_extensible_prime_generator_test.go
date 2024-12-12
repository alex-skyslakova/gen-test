import (
	"container/heap"
	"testing"
)

func TestNewP(t *testing.T) {
	p := newP()

	// First twenty primes
	firstTwenty := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71}
	for i := 0; i < 20; i++ {
		if p() != firstTwenty[i] {
			t.Errorf("Expected %d, got %d", firstTwenty[i], p())
		}
	}

	// Primes between 100 and 150
	primesBetween100And150 := []int{101, 103, 107, 109, 113, 127, 131, 137, 139, 149}
	p = newP()
	n := p()
	for n <= 100 {
		n = p()
	}
	for i := 0; n < 150; n = p() {
		if n != primesBetween100And150[i] {
			t.Errorf("Expected %d, got %d", primesBetween100And150[i], n)
		}
		i++

	}

	// Number of primes between 7700 and 8000
	p = newP()
	n = p()
	for n <= 7700 {
		n = p()
	}
	c := 0
	for ; n < 8000; n = p() {
		c++
	}
	if c != 16 {
		t.Errorf("Expected 16 primes between 7700 and 8000, got %d", c)
	}

	// 10,000th prime
	p = newP()
	for i := 1; i < 10000; i++ {
		p()
	}
	if p() != 104729 {
		t.Errorf("Expected 104729 as the 10,000th prime, got %d", p())
	}

	//Test Heap functions
    pq := make(pQueue, 0)
    heap.Init(&pq)
    heap.Push(&pq,&pMult{prime:2,pMult:4})
    heap.Push(&pq,&pMult{prime:3,pMult:9})
    if pq.Len() != 2 {
        t.Errorf("Expected len 2, got %d", pq.Len())
    }

    if pq[0].prime!=2{
        t.Errorf("Expected top element prime 2, got %d", pq[0].prime)
    }

    popped := heap.Pop(&pq).(*pMult)
    if popped.prime != 2 {
         t.Errorf("Expected popped element prime 2, got %d", popped.prime)
    }



}

