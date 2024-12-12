package main

import (
	"reflect"
	"testing"
)

func TestMktrees(t *testing.T) {
	list = []tree{}
	offset = [32]uint{1: 1}
	mktrees(1)
	expectedList := []tree{1}
	if !reflect.DeepEqual(list, expectedList) {
		t.Errorf("For n=1, expected %v, got %v", expectedList, list)
	}
	if offset[2] != 1 {
		t.Errorf("For n=1, expected offset[2]=1, got %d", offset[2])
	}

	list = []tree{}
	offset = [32]uint{1: 1}
	mktrees(2)
	expectedList = []tree{1, 3}
	if !reflect.DeepEqual(list, expectedList) {
		t.Errorf("For n=2, expected %v, got %v", expectedList, list)
	}
	if offset[3] != 2 {
		t.Errorf("For n=2, expected offset[3]=2, got %d", offset[3])
	}


	list = []tree{}
	offset = [32]uint{1: 1}
	mktrees(3)
	expectedList = []tree{1, 3, 7, 5}
	if !reflect.DeepEqual(list, expectedList) {
		t.Errorf("For n=3, expected %v, got %v", expectedList, list)
	}
	if offset[4] != 4 {
		t.Errorf("For n=3, expected offset[4]=4, got %d", offset[4])
	}

	list = []tree{}
	offset = [32]uint{1: 1}
	mktrees(4)
	expectedList = []tree{1, 3, 7, 5, 15, 13, 11, 9}
	if !reflect.DeepEqual(list, expectedList) {
		t.Errorf("For n=4, expected %v, got %v", expectedList, list)
	}
	if offset[5] != 8 {
		t.Errorf("For n=4, expected offset[5]=8, got %d", offset[5])
	}
}



func TestShow(t *testing.T) {
    // Test cases with expected outputs
    testCases := []struct {
        input tree
        l     uint
        want  string
    }{
        {0, 2, "()"},
        {1, 2, "()"},
		{3, 4, "(())"},
        {7, 6, "((()))"},
        {5, 6, "(()())"},
    }
 
    for _, tc := range testCases {
        // Capture the output
        //got := captureOutput(func() { show(tc.input, tc.l) })  Requires helper function, simpler approach below
		gotBytes := []byte{}
		fmt = mockFmt{&gotBytes}
		show(tc.input, tc.l)
		got := string(gotBytes)
        if got != tc.want {
            t.Errorf("show(%v, %v) = %q; want %q", tc.input, tc.l, got, tc.want)
        }
		fmt = defaultFmt{} //restore original fmt

    }
}


type mockFmt struct {
	dest *[]byte
}

func (m mockFmt) Printf(format string, a ...any) (n int, err error) {
	s := fmt.Sprintf(format, a...)
	*m.dest = append(*m.dest, []byte(s)...)
	return len(s), nil

}



var defaultFmt fmtInterface = defaultFmtImpl{}

type fmtInterface interface {
	Printf(format string, a ...any) (n int, err error)
}

type defaultFmtImpl struct{}

func (d defaultFmtImpl) Printf(format string, a ...any) (n int, err error) {
	return fmt.Printf(format, a...)
}


var fmt fmtInterface = defaultFmt



func TestListTrees(t *testing.T) {
	list = []tree{1, 3, 7, 5}
	offset = [32]uint{1, 1, 2, 4}

	gotBytes := []byte{}
	fmt = mockFmt{&gotBytes}
	listTrees(3)
	got := string(gotBytes)
	want := "((()))\n(()())\n"

    if got != want {
        t.Errorf("listTrees(3) = %q; want %q", got, want)
    }

	fmt = defaultFmt{} //restore original fmt
}

// TestAssemble is complex to test directly due to recursion. It's effectiveness is
// implicitly tested via TestMktrees which uses it.  Direct test would involve mocking add()
