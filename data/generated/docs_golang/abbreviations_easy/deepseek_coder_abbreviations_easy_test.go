package main

import (
    "strings"
    "testing"
)

func TestValidate(t *testing.T) {
    table := "Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy " +
        "COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find " +
        "NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput " +
        "Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO " +
        "MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT " +
        "READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT " +
        "RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up "

    table = strings.TrimSpace(table)
    commands := strings.Fields(table)
    clen := len(commands)
    minLens := make([]int, clen)
    for i := 0; i < clen; i++ {
        count := 0
        for _, c := range commands[i] {
            if c >= 'A' && c <= 'Z' {
                count++
            }
        }
        minLens[i] = count
    }

    testCases := []struct {
        input    string
        expected string
    }{
        {"riG rePEAT copies put mo rest types fup. 6 poweRin", "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT"},
        {"", ""},
        {"aLt", "ALTER"},
        {"AL", "*error*"},
        {"ALF", "*error*"},
        {"ALTERS", "*error*"},
        {"TER", "*error*"},
        {"A", "*error*"},
        {"o", "OVERLAY"},
        {"ov", "OVERLAY"},
        {"oVe", "OVERLAY"},
        {"over", "OVERLAY"},
        {"overL", "OVERLAY"},
        {"overla", "OVERLAY"},
        {"ovERLay", "OVERLAY"},
        {"ovERLayS", "*error*"},
        {"ovERLaySs", "*error*"},
        {"ovERLaySsS", "*error*"},
    }

    for _, tc := range testCases {
        words := strings.Fields(tc.input)
        results := validate(commands, words, minLens)
        actual := strings.Join(results, " ")
        if actual != tc.expected {
            t.Errorf("For input '%s', expected '%s' but got '%s'", tc.input, tc.expected, actual)
        }
    }
}
