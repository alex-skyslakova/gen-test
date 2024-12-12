package main

import (
	"regexp"
	"strings"
	"testing"
)

func TestCreateFen(t *testing.T) {
	for i := 0; i < 100; i++ { // Test a reasonable number of random positions
		fen := createFen()
		testFen(fen, t)
	}
}

func testFen(fen string, t *testing.T) {
	parts := strings.Split(fen, " ")
	if len(parts) != 6 {
		t.Errorf("Invalid FEN format: %s", fen)
		return
	}

	board := parts[0]
	ranks := strings.Split(board, "/")
	if len(ranks) != 8 {
		t.Errorf("Invalid number of ranks: %s", fen)
		return
	}

	kingCount := map[byte]int{'K': 0, 'k': 0}
	totalPieces := 0

	for r, rank := range ranks {
		fileCount := 0
		for _, char := range rank {
			if char >= '1' && char <= '8' {
				fileCount += int(char - '0')
			} else {
				fileCount++
				totalPieces++

				if char == 'K' || char == 'k' {
					kingCount[byte(char)]++
				}

				if char == 'P' && r == 7 || char == 'p' && r == 0 {
					t.Errorf("Pawn in promotion square: %s", fen)
					return
				}
			}

		}
		if fileCount != 8 {
			t.Errorf("Invalid number of files in rank %d: %s", r+1, fen)
			return

		}
	}


	if kingCount['K'] != 1 || kingCount['k'] != 1 {
		t.Errorf("Incorrect number of kings: %s, K count %d k count %d", fen, kingCount['K'], kingCount['k'])
		return
	}

	if totalPieces > 32 {

		t.Errorf("Too many pieces: %s", fen)
		return
	}
	if parts[1] != "w" || parts[2] != "-" || parts[3] != "-" || parts[4] != "0" || parts[5] != "1" {
		t.Errorf("Incorrect FEN suffix: %s", fen)
		return
	}

	// Check for adjacent kings
	if regexp.MustCompile(`Kk|kK`).MatchString(board) {
		t.Errorf("Adjacent kings found")
		return
	}
	// checking for adjacent kings with space between them
	for i := 0; i < 7; i++ {
		if regexp.MustCompile(`K\d?k|k\d?K`).MatchString(ranks[i] + ranks[i+1]) {
			t.Errorf("Adjacent kings found")
			return
		}
	}
}

// Helper function to reset the grid for each test. It needs to be in the same package
func resetGrid() {
	for i := 0; i < 8; i++ {
		for j := 0; j < 8; j++ {
			grid[i][j] = 0
		}
	}
}

// Test example usage - to run this comment out the main() function below it first.

//func TestMain(m *testing.M) {
//	rand.Seed(time.Now().UnixNano())
//	fmt.Println("Running Tests now")
//	code := m.Run()
//	fmt.Println("Tests are complete")
//	os.Exit(code)
//}

// Comment this block to run the tests.
func main() {
	rand.Seed(time.Now().UnixNano())
	fmt.Println(createFen())
}

