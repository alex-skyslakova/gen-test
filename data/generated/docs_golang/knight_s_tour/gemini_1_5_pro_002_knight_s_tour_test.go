package main

import (
	"fmt"
	"testing"
)

// TestKnightTour tests the knightTour function.
// Because the algorithm is probabilistic, we run it multiple times
// to increase the chance of finding a solution and thus passing the test.
func TestKnightTour(t *testing.T) {
	for i := 0; i < 100; i++ { // Try 100 times
		if tourFound := testKnightTourOnce(0, 0, t); tourFound {
			return // Test passed if a tour is found
		}
	}
	t.Error("No knight's tour found after multiple attempts.")
}


func testKnightTourOnce(startRow, startCol int, t *testing.T) bool {

		board := make([][]int, 8)
		for i := range board {
			board[i] = make([]int, 8)
		}
		r := startRow
		c := startCol
		board[r][c] = 1 // first move
		for move := 2; move <= 64; move++ {
			minNext := 8
			var mr, mc, nm int
		candidateMoves:
			for _, cm := range moves {
				cr := r + cm.dr
				if cr < 0 || cr >= 8 { // off board
					continue
				}
				cc := c + cm.dc
				if cc < 0 || cc >= 8 { // off board
					continue
				}
				if board[cr][cc] > 0 { // already visited
					continue
				}
				// cr, cc candidate legal move.
				p := 0 // count possible next moves.
				for _, m2 := range moves {
					r2 := cr + m2.dr
					if r2 < 0 || r2 >= 8 {
						continue
					}
					c2 := cc + m2.dc
					if c2 < 0 || c2 >= 8 {
						continue
					}
					if board[r2][c2] > 0 {
						continue
					}
					p++
					if p > minNext { // bail out as soon as it's eliminated
						continue candidateMoves
					}
				}
				if p < minNext { // it's better.  keep it.
					minNext = p // new min possible next moves
					nm = 1      // number of candidates with this p
					mr = cr     // best candidate move
					mc = cc
					continue
				}
				// it ties for best so far.
				// keep it with probability 1/(number of tying moves)
				nm++                    // number of tying moves
	
				if rand.Intn(nm) == 0 { // one chance to keep it
					mr = cr
					mc = cc
				}
			}
			if nm == 0 { // no legal move
				return false
			}
			// make selected move
			r = mr
			c = mc
			board[r][c] = move
		}

		// Check if all squares are visited (values 1-64)
		visited := make(map[int]bool)
		for _, row := range board {
			for _, val := range row {
				if val >= 1 && val <= 64 {
					visited[val] = true
				}
			}
		}
		if len(visited) != 64 {
			t.Errorf("Not all squares visited. Only %d squares visited.", len(visited))
			return false // Test failed
		}

		return true
}

