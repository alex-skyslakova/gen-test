package main

import (
	"testing"
	"time"

	"github.com/gdamore/tcell"
	"github.com/stretchr/testify/assert"
)

// MockScreen for testing purposes
type MockScreen struct {
	tcell.Screen
	Content       []rune
	Events        []tcell.Event
	CurrentEvent int
	SizeX        int
	SizeY        int
}

func (ms *MockScreen) Init() error {
	ms.Content = make([]rune, ms.SizeX*ms.SizeY)
	return nil
}

func (ms *MockScreen) SetContent(x, y int, mainc rune, combc []rune, style tcell.Style) {
	ms.Content[y*ms.SizeX+x] = mainc
}

func (ms *MockScreen) Show() {

}

func (ms *MockScreen) PollEvent() tcell.Event {

	if ms.CurrentEvent < len(ms.Events) {

		ev := ms.Events[ms.CurrentEvent]
		ms.CurrentEvent++
		return ev

	}
	// block indefinitely when no more events
	time.Sleep(time.Hour)
	return nil

}

func (ms *MockScreen) Fini() {

}


func (ms *MockScreen) Size() (int, int) {
	return ms.SizeX, ms.SizeY
}

func (ms *MockScreen) EnableMouse() {
	//do nothing
}


func NewMockScreen(events []tcell.Event, sizeX, sizeY int) *MockScreen {
	return &MockScreen{Events: events, SizeX: sizeX, SizeY: sizeY}

}

func TestAnimation(t *testing.T) {

	// Test initial display
	s := NewMockScreen([]tcell.Event{}, 100, 10)
	s.Init()
    runAnimation(s, []tcell.Event{}, 0)

	assert.Equal(t, []rune(msg), []rune(string(s.Content[y0*s.SizeX+x0:y0*s.SizeX+x0+len(msg)])), "Initial display incorrect")



	// Test rotation
	s = NewMockScreen([]tcell.Event{}, 100, 10)
	s.Init()
    runAnimation(s, []tcell.Event{}, 1)

	expected := " Hello World!"
	assert.Equal(t, []rune(expected), []rune(string(s.Content[y0*s.SizeX+x0:y0*s.SizeX+x0+len(msg)])), "Rotation incorrect")



	// Test click reverse
	clickEvent := &tcell.EventMouse{Buttons: tcell.Button1, Position: func() (int, int) { return x0, y0 }}
    s = NewMockScreen([]tcell.Event{clickEvent}, 100, 10)
    s.Init()
    runAnimation(s, []tcell.Event{clickEvent}, 1)


	expectedAfterClick := msg // Back to original after one click reversal and 1 shift
	assert.Equal(t, []rune(expectedAfterClick), []rune(string(s.Content[y0*s.SizeX+x0:y0*s.SizeX+x0+len(msg)])), "Click reverse incorrect")



	// Test multiple clicks to exit
	clickEvents := make([]tcell.Event, clicksToExit)
	for i := 0; i < clicksToExit; i++ {
		clickEvents[i] = &tcell.EventMouse{Buttons: tcell.Button1, Position: func() (int, int) { return x0, y0 }}
	}
	s = NewMockScreen(clickEvents, 100, 10)
    s.Init()

    runAnimation(s, clickEvents, 0) // Should exit after clicksToExit clicks



}


func runAnimation(s tcell.Screen, events []tcell.Event, numTicks int) {

	s.Clear()
	tick := time.Tick(time.Second / shiftsPerSecond)
	click := make(chan bool)

    go func() {
		for _, ev := range events {
			switch em := ev.(type) {
			case *tcell.EventMouse:
				if em.Buttons()&0xFF == tcell.ButtonNone {
					continue
				}

				mx, my := em.Position()
				if my == y0 && mx >= x0 && mx < x0+len(msg) {
					click <- true
				}

			}
		}
	}()

	for inc, shift, clicks := 1, 0, 0; ; {
		select {
        case <-tick:
			if numTicks == 0 {
				continue
			}
            shift = (shift + inc) % len(msg)
            for i, r := range msg {
                s.SetContent(x0+((shift+i)%len(msg)), y0, r, nil, 0)
            }
            s.Show()
			numTicks--
			if numTicks==0 {
				return
			}
		
        case <-click:
            clicks++
            if clicks == clicksToExit {
                s.Fini()
                return
            }
            inc = len(msg) - inc

		}


	}

}

