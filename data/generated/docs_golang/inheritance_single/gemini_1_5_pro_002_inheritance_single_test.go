package main

import "testing"

func TestAnimal(t *testing.T) {
	a := animal{alive: true}
	if !a.alive {
		t.Error("Expected animal to be alive")
	}
}

func TestDog(t *testing.T) {
	d := dog{animal: animal{alive: true}, obedienceTrained: true}
	if !d.alive {
		t.Error("Expected dog to be alive")
	}
	if !d.obedienceTrained {
		t.Error("Expected dog to be obedience trained")
	}
}


func TestCat(t *testing.T) {
	c := cat{animal: animal{alive: true}, litterBoxTrained: true}
	if !c.alive {
		t.Error("Expected cat to be alive")
	}
	if !c.litterBoxTrained {
		t.Error("Expected cat to be litter box trained")
	}
}

func TestLab(t *testing.T) {
	l := lab{dog: dog{animal: animal{alive: true}, obedienceTrained: true}, color: "yellow"}

	if !l.alive {
		t.Error("Expected lab to be alive")
	}
	if !l.obedienceTrained {
		t.Error("Expected lab to be obedience trained")
	}
	if l.color != "yellow" {
		t.Errorf("Expected lab to be yellow, got %s", l.color)
	}
}

func TestCollie(t *testing.T) {
	c := collie{dog: dog{animal: animal{alive: true}, obedienceTrained: true}, catchesFrisbee: true}
	if !c.alive {
		t.Error("Expected collie to be alive")
	}
	if !c.obedienceTrained {
		t.Error("Expected collie to be obedience trained")
	}
	if !c.catchesFrisbee {
		t.Error("Expected collie to catch frisbees")
	}
}

