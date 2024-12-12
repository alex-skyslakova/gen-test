package main

import (
    "testing"
)

func TestDLList_String(t *testing.T) {
    tests := []struct {
        name string
        list *dlList
        want string
    }{
        {
            name: "Empty list",
            list: &dlList{},
            want: "<nil>",
        },
        {
            name: "Single element list",
            list: func() *dlList {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                return dll
            }(),
            want: "[A]",
        },
        {
            name: "Multiple elements list",
            list: func() *dlList {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                dll.insertTail(&dlNode{string: "B"})
                dll.insertTail(&dlNode{string: "C"})
                return dll
            }(),
            want: "[A B C]",
        },
    }
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            if got := tt.list.String(); got != tt.want {
                t.Errorf("dlList.String() = %v, want %v", got, tt.want)
            }
        })
    }
}

func TestDLList_InsertTail(t *testing.T) {
    tests := []struct {
        name string
        list *dlList
        node *dlNode
        want string
    }{
        {
            name: "Insert into empty list",
            list: &dlList{},
            node: &dlNode{string: "A"},
            want: "[A]",
        },
        {
            name: "Insert into non-empty list",
            list: func() *dlList {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                return dll
            }(),
            node: &dlNode{string: "B"},
            want: "[A B]",
        },
    }
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            tt.list.insertTail(tt.node)
            if got := tt.list.String(); got != tt.want {
                t.Errorf("dlList.insertTail() = %v, want %v", got, tt.want)
            }
        })
    }
}

func TestDLList_InsertAfter(t *testing.T) {
    tests := []struct {
        name     string
        list     *dlList
        existing *dlNode
        insert   *dlNode
        want     string
    }{
        {
            name: "Insert after head",
            list: func() *dlList {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                dll.insertTail(&dlNode{string: "B"})
                return dll
            }(),
            existing: func() *dlNode {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                dll.insertTail(&dlNode{string: "B"})
                return dll.head
            }(),
            insert: &dlNode{string: "C"},
            want:   "[A C B]",
        },
        {
            name: "Insert after tail",
            list: func() *dlList {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                dll.insertTail(&dlNode{string: "B"})
                return dll
            }(),
            existing: func() *dlNode {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                dll.insertTail(&dlNode{string: "B"})
                return dll.tail
            }(),
            insert: &dlNode{string: "C"},
            want:   "[A B C]",
        },
    }
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            tt.list.insertAfter(tt.existing, tt.insert)
            if got := tt.list.String(); got != tt.want {
                t.Errorf("dlList.insertAfter() = %v, want %v", got, tt.want)
            }
        })
    }
}

func TestDLList_TraverseFromTail(t *testing.T) {
    tests := []struct {
        name string
        list *dlList
        want string
    }{
        {
            name: "Empty list",
            list: &dlList{},
            want: "",
        },
        {
            name: "Single element list",
            list: func() *dlList {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                return dll
            }(),
            want: "A",
        },
        {
            name: "Multiple elements list",
            list: func() *dlList {
                dll := &dlList{}
                dll.insertTail(&dlNode{string: "A"})
                dll.insertTail(&dlNode{string: "B"})
                dll.insertTail(&dlNode{string: "C"})
                return dll
            }(),
            want: "C B A",
        },
    }
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            var got string
            for p := tt.list.tail; p != nil; p = p.prev {
                if got != "" {
                    got += " "
                }
                got += p.string
            }
            if got != tt.want {
                t.Errorf("Traverse from tail = %v, want %v", got, tt.want)
            }
        })
    }
}
