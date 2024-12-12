package main

import (
	"testing"
	"strings"
	"unicode"
)

// TestCreateFen checks if the generated FEN string meets the specified requirements.
func TestCreateFen(t *testing.T) {
	for i := 0; i < 1000; i++ { // Run multiple times to check randomness
		fen := createFen()
		parts := strings.Split(fen, " ")

		if len(parts) != 6 {
			t.Errorf("FEN string should have 6 parts, got %d: %s", len(parts), fen)
		}

		board := parts[0]
		if !strings.HasSuffix(fen, " w - - 0 1") {
			t.Errorf("FEN string should end with 'w - - 0 1', got: %s", fen)
		}

		if !validateKings(board) {
			t.Errorf("Kings are not placed correctly in FEN: %s", fen)
		}

		if !validatePawns(board) {
			t.Errorf("Pawns are incorrectly placed in FEN: %s", fen)
		}

		if !validatePieceCount(board) {
			t.Errorf("Piece count exceeds 32 in FEN: %s", fen)
		}
	}
}

// validateKings checks if there is exactly one white king and one black king, and they are not adjacent.
func validateKings(board string) bool {
	rows := strings.Split(board, "/")
	var whiteKingPos, blackKingPos [2]int
	whiteKingFound, blackKingFound := false, false

	for r, row := range rows {
		col := 0
		for _, ch := range row {
			if unicode.IsDigit(ch) {
				col += int(ch - '0')
			} else {
				if ch == 'K' {
					if whiteKingFound {
						return false
					}
					whiteKingPos = [2]int{r, col}
					whiteKingFound = true
				} else if ch == 'k' {
					if blackKingFound {
						return false
					}
					blackKingPos = [2]int{r, col}
					blackKingFound = true
				}
				col++
			}
		}
	}

	if !whiteKingFound || !blackKingFound {
		return false
	}

	// Check if kings are adjacent
	if abs(whiteKingPos[0]-blackKingPos[0]) <= 1 && abs(whiteKingPos[1]-blackKingPos[1]) <= 1 {
		return false
	}

	return true
}

// validatePawns checks if there are no pawns on the first or eighth rank.
func validatePawns(board string) bool {
	rows := strings.Split(board, "/")
	for _, ch := range rows[0] {
		if ch == 'p' {
			return false
		}
	}
	for _, ch := range rows[7] {
		if ch == 'P' {
			return false
		}
	}
	return true
}

// validatePieceCount checks if the total number of pieces does not exceed 32.
func validatePieceCount(board string) bool {
	count := 0
	for _, ch := range board {
		if unicode.IsLetter(ch) {
			count++
		}
	}
	return count <= 32
}

// abs returns the absolute value of an integer.
func abs(i int) int {
	if i >= 0 {
		return i
	}
	return -i
}
