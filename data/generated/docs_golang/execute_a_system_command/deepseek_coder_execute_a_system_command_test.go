package main

import (
	"errors"
	"os"
	"os/exec"
	"testing"

	"github.com/stretchr/testify/mock"
	"github.com/stretchr/testify/assert"
)

// MockCommand is a mock implementation of exec.Command
type MockCommand struct {
	mock.Mock
}

// Command is a mock function that replaces exec.Command
func (m *MockCommand) Command(name string, arg ...string) *exec.Cmd {
	args := m.Called(name, arg)
	return args.Get(0).(*exec.Cmd)
}

// TestExecuteSystemCommand tests the main function with mocked exec.Command
func TestExecuteSystemCommand(t *testing.T) {
	// Save the original exec.Command function
	originalCommand := exec.Command
	defer func() { exec.Command = originalCommand }()

	// Create a mock command
	mockCommand := new(MockCommand)

	// Replace exec.Command with the mock command
	exec.Command = mockCommand.Command

	// Test case 1: Successful execution
	cmd := exec.Command("ls", "-l")
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	mockCommand.On("Command", "ls", []string{"-l"}).Return(cmd)
	main()
	mockCommand.AssertExpectations(t)

	// Test case 2: Execution with error
	cmdWithError := exec.Command("ls", "-l")
	cmdWithError.Stdout = os.Stdout
	cmdWithError.Stderr = os.Stderr
	cmdWithError.Run = func() error {
		return errors.New("command failed")
	}
	mockCommand.On("Command", "ls", []string{"-l"}).Return(cmdWithError)
	assert.Panics(t, main, "The code did not panic")
	mockCommand.AssertExpectations(t)
}
