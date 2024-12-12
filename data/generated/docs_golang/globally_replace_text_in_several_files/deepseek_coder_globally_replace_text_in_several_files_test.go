package main

import (
    "bytes"
    "errors"
    "os"
    "testing"

    "github.com/stretchr/testify/assert"
    "github.com/stretchr/testify/mock"
)

// MockFile is a mock implementation of the os.File interface.
type MockFile struct {
    mock.Mock
    data []byte
}

func (m *MockFile) OpenFile(name string, flag int, perm os.FileMode) (*os.File, error) {
    args := m.Called(name, flag, perm)
    return args.Get(0).(*os.File), args.Error(1)
}

func (m *MockFile) ReadAll(f *os.File) ([]byte, error) {
    args := m.Called(f)
    return args.Get(0).([]byte), args.Error(1)
}

func (m *MockFile) Truncate(f *os.File, size int64) error {
    args := m.Called(f, size)
    return args.Error(0)
}

func (m *MockFile) WriteAt(f *os.File, b []byte, off int64) (int, error) {
    args := m.Called(f, b, off)
    return args.Int(0), args.Error(1)
}

func (m *MockFile) Close(f *os.File) error {
    args := m.Called(f)
    return args.Error(0)
}

func TestGRepNFiles(t *testing.T) {
    assert := assert.New(t)

    // Create a mock file system
    mockFile := new(MockFile)

    // Mock the file operations
    mockFile.On("OpenFile", "a.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte("Goodbye London!"), nil)
    mockFile.On("Truncate", &os.File{}, int64(0)).Return(nil)
    mockFile.On("WriteAt", &os.File{}, []byte("Hello New York!"), int64(0)).Return(14, nil)
    mockFile.On("Close", &os.File{}).Return(nil)

    mockFile.On("OpenFile", "b.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte("Goodbye London!"), nil)
    mockFile.On("Truncate", &os.File{}, int64(0)).Return(nil)
    mockFile.On("WriteAt", &os.File{}, []byte("Hello New York!"), int64(0)).Return(14, nil)
    mockFile.On("Close", &os.File{}).Return(nil)

    mockFile.On("OpenFile", "c.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte("Goodbye London!"), nil)
    mockFile.On("Truncate", &os.File{}, int64(0)).Return(nil)
    mockFile.On("WriteAt", &os.File{}, []byte("Hello New York!"), int64(0)).Return(14, nil)
    mockFile.On("Close", &os.File{}).Return(nil)

    // Replace the original functions with the mock functions
    originalOpenFile := os.OpenFile
    originalReadAll := ioutil.ReadAll
    originalTruncate := os.File.Truncate
    originalWriteAt := os.File.WriteAt
    originalClose := os.File.Close

    os.OpenFile = mockFile.OpenFile
    ioutil.ReadAll = mockFile.ReadAll
    os.File.Truncate = mockFile.Truncate
    os.File.WriteAt = mockFile.WriteAt
    os.File.Close = mockFile.Close

    // Run the function to be tested
    gRepNFiles("Goodbye London!", "Hello New York!", []string{"a.txt", "b.txt", "c.txt"})

    // Verify the expectations
    mockFile.AssertExpectations(t)

    // Restore the original functions
    os.OpenFile = originalOpenFile
    ioutil.ReadAll = originalReadAll
    os.File.Truncate = originalTruncate
    os.File.WriteAt = originalWriteAt
    os.File.Close = originalClose
}

func TestGRepFile(t *testing.T) {
    assert := assert.New(t)

    // Create a mock file system
    mockFile := new(MockFile)

    // Mock the file operations
    mockFile.On("OpenFile", "a.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte("Goodbye London!"), nil)
    mockFile.On("Truncate", &os.File{}, int64(0)).Return(nil)
    mockFile.On("WriteAt", &os.File{}, []byte("Hello New York!"), int64(0)).Return(14, nil)
    mockFile.On("Close", &os.File{}).Return(nil)

    // Replace the original functions with the mock functions
    originalOpenFile := os.OpenFile
    originalReadAll := ioutil.ReadAll
    originalTruncate := os.File.Truncate
    originalWriteAt := os.File.WriteAt
    originalClose := os.File.Close

    os.OpenFile = mockFile.OpenFile
    ioutil.ReadAll = mockFile.ReadAll
    os.File.Truncate = mockFile.Truncate
    os.File.WriteAt = mockFile.WriteAt
    os.File.Close = mockFile.Close

    // Run the function to be tested
    err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), "a.txt")

    // Verify the expectations
    assert.NoError(err)
    mockFile.AssertExpectations(t)

    // Restore the original functions
    os.OpenFile = originalOpenFile
    ioutil.ReadAll = originalReadAll
    os.File.Truncate = originalTruncate
    os.File.WriteAt = originalWriteAt
    os.File.Close = originalClose
}

func TestGRepFile_OpenError(t *testing.T) {
    assert := assert.New(t)

    // Create a mock file system
    mockFile := new(MockFile)

    // Mock the file operations
    mockFile.On("OpenFile", "a.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, errors.New("open error"))

    // Replace the original functions with the mock functions
    originalOpenFile := os.OpenFile

    os.OpenFile = mockFile.OpenFile

    // Run the function to be tested
    err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), "a.txt")

    // Verify the expectations
    assert.Error(err)
    mockFile.AssertExpectations(t)

    // Restore the original functions
    os.OpenFile = originalOpenFile
}

func TestGRepFile_ReadAllError(t *testing.T) {
    assert := assert.New(t)

    // Create a mock file system
    mockFile := new(MockFile)

    // Mock the file operations
    mockFile.On("OpenFile", "a.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte{}, errors.New("read error"))

    // Replace the original functions with the mock functions
    originalOpenFile := os.OpenFile
    originalReadAll := ioutil.ReadAll

    os.OpenFile = mockFile.OpenFile
    ioutil.ReadAll = mockFile.ReadAll

    // Run the function to be tested
    err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), "a.txt")

    // Verify the expectations
    assert.Error(err)
    mockFile.AssertExpectations(t)

    // Restore the original functions
    os.OpenFile = originalOpenFile
    ioutil.ReadAll = originalReadAll
}

func TestGRepFile_TruncateError(t *testing.T) {
    assert := assert.New(t)

    // Create a mock file system
    mockFile := new(MockFile)

    // Mock the file operations
    mockFile.On("OpenFile", "a.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte("Goodbye London!"), nil)
    mockFile.On("Truncate", &os.File{}, int64(0)).Return(errors.New("truncate error"))

    // Replace the original functions with the mock functions
    originalOpenFile := os.OpenFile
    originalReadAll := ioutil.ReadAll
    originalTruncate := os.File.Truncate

    os.OpenFile = mockFile.OpenFile
    ioutil.ReadAll = mockFile.ReadAll
    os.File.Truncate = mockFile.Truncate

    // Run the function to be tested
    err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), "a.txt")

    // Verify the expectations
    assert.Error(err)
    mockFile.AssertExpectations(t)

    // Restore the original functions
    os.OpenFile = originalOpenFile
    ioutil.ReadAll = originalReadAll
    os.File.Truncate = originalTruncate
}

func TestGRepFile_WriteAtError(t *testing.T) {
    assert := assert.New(t)

    // Create a mock file system
    mockFile := new(MockFile)

    // Mock the file operations
    mockFile.On("OpenFile", "a.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte("Goodbye London!"), nil)
    mockFile.On("Truncate", &os.File{}, int64(0)).Return(nil)
    mockFile.On("WriteAt", &os.File{}, []byte("Hello New York!"), int64(0)).Return(0, errors.New("write error"))

    // Replace the original functions with the mock functions
    originalOpenFile := os.OpenFile
    originalReadAll := ioutil.ReadAll
    originalTruncate := os.File.Truncate
    originalWriteAt := os.File.WriteAt

    os.OpenFile = mockFile.OpenFile
    ioutil.ReadAll = mockFile.ReadAll
    os.File.Truncate = mockFile.Truncate
    os.File.WriteAt = mockFile.WriteAt

    // Run the function to be tested
    err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), "a.txt")

    // Verify the expectations
    assert.Error(err)
    mockFile.AssertExpectations(t)

    // Restore the original functions
    os.OpenFile = originalOpenFile
    ioutil.ReadAll = originalReadAll
    os.File.Truncate = originalTruncate
    os.File.WriteAt = originalWriteAt
}

func TestGRepFile_CloseError(t *testing.T) {
    assert := assert.New(t)

    // Create a mock file system
    mockFile := new(MockFile)

    // Mock the file operations
    mockFile.On("OpenFile", "a.txt", os.O_RDWR, os.FileMode(0)).Return(&os.File{}, nil)
    mockFile.On("ReadAll", &os.File{}).Return([]byte("Goodbye London!"), nil)
    mockFile.On("Truncate", &os.File{}, int64(0)).Return(nil)
    mockFile.On("WriteAt", &os.File{}, []byte("Hello New York!"), int64(0)).Return(14, nil)
    mockFile.On("Close", &os.File{}).Return(errors.New("close error"))

    // Replace the original functions with the mock functions
    originalOpenFile := os.OpenFile
    originalReadAll := ioutil.ReadAll
    originalTruncate := os.File.Truncate
    originalWriteAt := os.File.WriteAt
    originalClose := os.File.Close

    os.OpenFile = mockFile.OpenFile
    ioutil.ReadAll = mockFile.ReadAll
    os.File.Truncate = mockFile.Truncate
    os.File.WriteAt = mockFile.WriteAt
    os.File.Close = mockFile.Close

    // Run the function to be tested
    err := gRepFile([]byte("Goodbye London!"), []byte("Hello New York!"), "a.txt")

    // Verify the expectations
    assert.Error(err)
    mockFile.AssertExpectations(t)

    // Restore the original functions
    os.OpenFile = originalOpenFile
    ioutil.ReadAll = originalReadAll
    os.File.Truncate = originalTruncate
    os.File.WriteAt = originalWriteAt
    os.File.Close = originalClose
}
