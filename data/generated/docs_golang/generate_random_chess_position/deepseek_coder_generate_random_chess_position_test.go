package main

import (
    "strings"
    "testing"
)

func TestPlaceKings(t *testing.T) {
    placeKings()
    var whiteKing, blackKing bool
    for r := 0; r < 8; r++ {
        for c := 0; c < 8; c++ {
            if grid[r][c] == 'K' {
                whiteKing = true
            }
            if grid[r][c] == 'k' {
                blackKing = true
            }
        }
    }
    if !whiteKing || !blackKing {
        t.Errorf("Expected one white king and one black king, got whiteKing: %v, blackKing: %v", whiteKing, blackKing)
    }
}

func TestPlacePieces(t *testing.T) {
    placePieces("PPPPPPPP", true)
    placePieces("pppppppp", true)
    placePieces("RNBQBNR", false)
    placePieces("rnbqbnr", false)

    for r := 0; r < 8; r++ {
        for c := 0; c < 8; c++ {
            if grid[r][c] == 'P' && r == 7 {
                t.Errorf("White pawn found on promotion square (8th rank)")
            }
            if grid[r][c] == 'p' && r == 0 {
                t.Errorf("Black pawn found on promotion square (1st rank)")
            }
        }
    }
}

func TestToFen(t *testing.T) {
    fen := toFen()
    if !strings.HasSuffix(fen, " w - - 0 1") {
        t.Errorf("FEN does not end with ' w - - 0 1', got: %s", fen)
    }
}

func TestCreateFen(t *testing.T) {
    fen := createFen()
    if !strings.Contains(fen, "K") || !strings.Contains(fen, "k") {
        t.Errorf("FEN does not contain both kings, got: %s", fen)
    }
    if strings.Contains(fen, "P/") || strings.Contains(fen, "/P") {
        t.Errorf("White pawn found on promotion square (8th rank), got: %s", fen)
    }
    if strings.Contains(fen, "p/") || strings.Contains(fen, "/p") {
        t.Errorf("Black pawn found on promotion square (1st rank), got: %s", fen)
    }
}
