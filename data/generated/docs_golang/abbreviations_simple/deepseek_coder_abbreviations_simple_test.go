package main

import (
	"reflect"
	"strings"
	"testing"
)

func TestReadTable(t *testing.T) {
	table := "" +
		"add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3 " +
		"compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate " +
		"3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2 " +
		"forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load " +
		"locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2 " +
		"msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3 " +
		"refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left " +
		"2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1 "

	expectedCommands := []string{
		"add", "alter", "backup", "bottom", "Cappend", "change", "Schange", "Cinsert", "Clast",
		"compress", "copy", "count", "Coverlay", "cursor", "delete", "Cdelete", "down", "duplicate",
		"xEdit", "expand", "extract", "find", "Nfind", "Nfindup", "NfUP", "Cfind", "findUP", "fUP",
		"forward", "get", "help", "hexType", "input", "powerInput", "join", "split", "spltJOIN", "load",
		"locate", "Clocate", "lowerCase", "upperCase", "Lprefix", "macro", "merge", "modify", "move",
		"msg", "next", "overlay", "parse", "preserve", "purge", "put", "putD", "query", "quit",
		"read", "recover", "refresh", "renum", "repeat", "replace", "Creplace", "reset", "restore",
		"rgtLEFT", "right", "left", "save", "set", "shift", "si", "sort", "sos", "stack", "status",
		"top", "transfer", "type", "up",
	}

	expectedMinLens := []int{
		1, 3, 2, 1, 2, 1, 0, 2, 3, 4, 2, 3, 3, 3, 3, 2, 1, 3, 1, 3, 3, 1, 2, 6, 3, 2, 3, 2,
		2, 1, 1, 4, 1, 3, 1, 2, 2, 1, 1, 2, 3, 3, 2, 3, 2, 1, 1, 4, 3, 1, 1, 1, 3, 3, 1, 2, 3,
		3, 1, 2, 3, 3, 1, 2, 2, 2, 1, 1, 3, 4, 1, 1,
	}

	commands, minLens := readTable(table)

	if !reflect.DeepEqual(commands, expectedCommands) {
		t.Errorf("Expected commands %v, but got %v", expectedCommands, commands)
	}

	if !reflect.DeepEqual(minLens, expectedMinLens) {
		t.Errorf("Expected minLens %v, but got %v", expectedMinLens, minLens)
	}
}

func TestValidateCommands(t *testing.T) {
	commands := []string{
		"add", "alter", "backup", "bottom", "Cappend", "change", "Schange", "Cinsert", "Clast",
		"compress", "copy", "count", "Coverlay", "cursor", "delete", "Cdelete", "down", "duplicate",
		"xEdit", "expand", "extract", "find", "Nfind", "Nfindup", "NfUP", "Cfind", "findUP", "fUP",
		"forward", "get", "help", "hexType", "input", "powerInput", "join", "split", "spltJOIN", "load",
		"locate", "Clocate", "lowerCase", "upperCase", "Lprefix", "macro", "merge", "modify", "move",
		"msg", "next", "overlay", "parse", "preserve", "purge", "put", "putD", "query", "quit",
		"read", "recover", "refresh", "renum", "repeat", "replace", "Creplace", "reset", "restore",
		"rgtLEFT", "right", "left", "save", "set", "shift", "si", "sort", "sos", "stack", "status",
		"top", "transfer", "type", "up",
	}

	minLens := []int{
		1, 3, 2, 1, 2, 1, 0, 2, 3, 4, 2, 3, 3, 3, 3, 2, 1, 3, 1, 3, 3, 1, 2, 6, 3, 2, 3, 2,
		2, 1, 1, 4, 1, 3, 1, 2, 2, 1, 1, 2, 3, 3, 2, 3, 2, 1, 1, 4, 3, 1, 1, 1, 3, 3, 1, 2, 3,
		3, 1, 2, 3, 3, 1, 2, 2, 2, 1, 1, 3, 4, 1, 1,
	}

	tests := []struct {
		name     string
		words    []string
		expected []string
	}{
		{
			name:     "Valid and invalid words",
			words:    []string{"riG", "rePEAT", "copies", "put", "mo", "rest", "types", "fup.", "6", "poweRin"},
			expected: []string{"RIGHT", "REPEAT", "*error*", "PUT", "MOVE", "RESTORE", "*error*", "*error*", "*error*", "POWERINPUT"},
		},
		{
			name:     "All invalid words",
			words:    []string{"invalid", "words", "here"},
			expected: []string{"*error*", "*error*", "*error*"},
		},
		{
			name:     "All valid words",
			words:    []string{"add", "alter", "backup", "bottom"},
			expected: []string{"ADD", "ALTER", "BACKUP", "BOTTOM"},
		},
		{
			name:     "Mixed case valid words",
			words:    []string{"aDd", "AlTeR", "bAcKuP", "bOtToM"},
			expected: []string{"ADD", "ALTER", "BACKUP", "BOTTOM"},
		},
		{
			name:     "Empty input",
			words:    []string{},
			expected: []string{},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			results := validateCommands(commands, minLens, tt.words)
			if !reflect.DeepEqual(results, tt.expected) {
				t.Errorf("Expected %v, but got %v", tt.expected, results)
			}
		})
	}
}

func TestPrintResults(t *testing.T) {
	words := []string{"riG", "rePEAT", "copies", "put", "mo", "rest", "types", "fup.", "6", "poweRin"}
	results := []string{"RIGHT", "REPEAT", "*error*", "PUT", "MOVE", "RESTORE", "*error*", "*error*", "*error*", "POWERINPUT"}

	// Capture the output of printResults
	oldStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	printResults(words, results)

	w.Close()
	out, _ := io.ReadAll(r)
	os.Stdout = oldStdout

	expectedOutput := "user words:\triG\trePEAT\tcopies\tput\tmo\trest\ttypes\tfup.\t6\tpoweRin\n" +
		"full words:\tRIGHT\tREPEAT\t*error*\tPUT\tMOVE\tRESTORE\t*error*\t*error*\t*error*\tPOWERINPUT\n"

	if string(out) != expectedOutput {
		t.Errorf("Expected output:\n%s\nBut got:\n%s", expectedOutput, string(out))
	}
}
