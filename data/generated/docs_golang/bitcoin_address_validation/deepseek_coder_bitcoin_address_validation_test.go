package main

import (
    "testing"
)

func TestValidA58(t *testing.T) {
    tests := []struct {
        name    string
        address string
        wantOk  bool
        wantErr bool
    }{
        {
            name:    "Valid Address",
            address: "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i",
            wantOk:  true,
            wantErr: false,
        },
        {
            name:    "Invalid Address - Bad Char",
            address: "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!",
            wantOk:  false,
            wantErr: true,
        },
        {
            name:    "Invalid Address - Wrong Version",
            address: "2AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i",
            wantOk:  false,
            wantErr: true,
        },
        {
            name:    "Invalid Address - Wrong Checksum",
            address: "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j",
            wantOk:  false,
            wantErr: false,
        },
        {
            name:    "Invalid Address - Too Long",
            address: "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i1234567890123456789012345678901234567890",
            wantOk:  false,
            wantErr: true,
        },
        {
            name:    "Invalid Address - Empty",
            address: "",
            wantOk:  false,
            wantErr: true,
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            ok, err := ValidA58([]byte(tt.address))
            if ok != tt.wantOk {
                t.Errorf("ValidA58() ok = %v, want %v", ok, tt.wantOk)
            }
            if (err != nil) != tt.wantErr {
                t.Errorf("ValidA58() error = %v, wantErr %v", err, tt.wantErr)
            }
        })
    }
}
