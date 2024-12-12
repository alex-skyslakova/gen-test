package main

import "testing"

func TestAnimalInheritance(t *testing.T) {
    // Test Lab inheritance
    labPet := lab{}
    labPet.alive = true
    labPet.obedienceTrained = true
    labPet.color = "black"

    if !labPet.alive {
        t.Error("Expected labPet to be alive")
    }
    if !labPet.obedienceTrained {
        t.Error("Expected labPet to be obedience trained")
    }
    if labPet.color != "black" {
        t.Errorf("Expected labPet color to be black, got %s", labPet.color)
    }

    // Test Collie inheritance
    colliePet := collie{}
    colliePet.alive = true
    colliePet.obedienceTrained = false
    colliePet.catchesFrisbee = true

    if !colliePet.alive {
        t.Error("Expected colliePet to be alive")
    }
    if colliePet.obedienceTrained {
        t.Error("Expected colliePet not to be obedience trained")
    }
    if !colliePet.catchesFrisbee {
        t.Error("Expected colliePet to catch frisbee")
    }

    // Test Cat inheritance
    catPet := cat{}
    catPet.alive = true
    catPet.litterBoxTrained = true

    if !catPet.alive {
        t.Error("Expected catPet to be alive")
    }
    if !catPet.litterBoxTrained {
        t.Error("Expected catPet to be litter box trained")
    }
}
