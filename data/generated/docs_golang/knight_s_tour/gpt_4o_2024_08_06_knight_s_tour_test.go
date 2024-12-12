package main

import (
	"testing"
)

func TestKnightTour(t *testing.T) {
	// Test that the knightTour function completes a tour
	t.Run("CompleteTour", func(t *testing.T) {
		if !knightTour() {
			t.Error("Expected knightTour to complete a tour, but it did not.")
		}
	})

	// Test that all squares are visited exactly once
	t.Run("AllSquaresVisitedOnce", func(t *testing.T) {
		board := make([][]int, 8)
		for i := range board {
			board[i] = make([]int, 8)
		}
		if !knightTour() {
			t.Fatal("Expected knightTour to complete a tour, but it did not.")
		}
		visited := make(map[int]bool)
		for _, row := range board {
			for _, move := range row {
				if move == 0 {
					t.Error("Expected all squares to be visited, but found an unvisited square.")
				}
				if visited[move] {
					t.Errorf("Expected each square to be visited once, but found duplicate visit for move %d.", move)
				}
				visited[move] = true
			}
		}
	})

	// Test that all moves are legal knight moves
	t.Run("LegalKnightMoves", func(t *testing.T) {
		board := make([][]int, 8)
		for i := range board {
			board[i] = make([]int, 8)
		}
		if !knightTour() {
			t.Fatal("Expected knightTour to complete a tour, but it did not.")
		}
		positions := make([]struct{ r, c int }, 64)
		for r, row := range board {
			for c, move := range row {
				positions[move-1] = struct{ r, c int }{r, c}
			}
		}
		for i := 0; i < 63; i++ {
			r1, c1 := positions[i].r, positions[i].c
			r2, c2 := positions[i+1].r, positions[i+1].c
			dr, dc := r2-r1, c2-c1
			if !((dr == 2 && dc == 1) || (dr == 2 && dc == -1) || (dr == 1 && dc == 2) || (dr == 1 && dc == -2) ||
				(dr == -1 && dc == 2) || (dr == -1 && dc == -2) || (dr == -2 && dc == 1) || (dr == -2 && dc == -1)) {
				t.Errorf("Illegal knight move from (%d, %d) to (%d, %d)", r1, c1, r2, c2)
			}
		}
	})
}
