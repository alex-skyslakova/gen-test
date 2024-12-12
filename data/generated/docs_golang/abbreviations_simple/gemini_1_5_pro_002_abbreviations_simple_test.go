package main

import (
	"strings"
	"testing"
)

func TestValidateCommands(t *testing.T) {
	const table = "" +
		"add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3 " +
		"compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate " +
		"3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2 " +
		"forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load " +
		"locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2 " +
		"msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3 " +
		"refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left " +
		"2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1 "

	commands, minLens := readTable(table)

	tests := []struct {
		input    string
		expected []string
	}{
		{"riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin", []string{"RIGHT", "REPEAT", "*error*", "PUT", "MOVE", "RESTORE", "*error*", "*error*", "*error*", "POWERINPUT"}},
		{"", []string{}},
		{"add ADD aDd a", []string{"ADD", "ADD", "ADD", "*error*"}},
		{"alter alte al alt ALTERRRR", []string{"ALTER", "ALTER", "ALTER", "*error*", "*error*"}},
		{"overlay over OVERLAYYYY ove OV", []string{"OVERLAY", "OVERLAY", "*error*", "OVERLAY", "OVERLAY"}},
		{"get ge GETTTTT g", []string{"GET", "*error*", "*error*", "*error*"}},
		{"Next next NEXTT nex n", []string{"NEXT", "NEXT", "*error*", "NEXT", "*error*"}},
		{"  lower   ", []string{"*error*"}},
		{"lowerCase lower lowe low", []string{"LOWERCASE", "LOWERCASE", "LOWERCASE", "LOWERCASE"}},


	}

	for _, test := range tests {
		words := strings.Fields(test.input)
		actual := validateCommands(commands, minLens, words)
		if !stringSlicesEqual(actual, test.expected) {
			t.Errorf("Input: %q, Expected: %q, Actual: %q", test.input, test.expected, actual)
		}
	}
}


func stringSlicesEqual(a, b []string) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}

