package main

import (
	"bytes"
	"html/template"
	"testing"
)

type row struct {
	X, Y, Z int
}

var tmpl = `<table>
    <tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>
{{range $ix, $row := .}}    <tr><td>{{$ix}}</td>
        <td>{{$row.X}}</td>
        <td>{{$row.Y}}</td>
        <td>{{$row.Z}}</td></tr>
{{end}}</table>
`

func TestHTMLTableGeneration(t *testing.T) {
	tests := []struct {
		name     string
		data     []row
		expected string
	}{
		{
			name: "Test with 3 rows",
			data: []row{
				{X: 1000, Y: 2000, Z: 3000},
				{X: 4000, Y: 5000, Z: 6000},
				{X: 7000, Y: 8000, Z: 9000},
			},
			expected: `<table>
    <tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>
    <tr><td>0</td>
        <td>1000</td>
        <td>2000</td>
        <td>3000</td></tr>
    <tr><td>1</td>
        <td>4000</td>
        <td>5000</td>
        <td>6000</td></tr>
    <tr><td>2</td>
        <td>7000</td>
        <td>8000</td>
        <td>9000</td></tr>
</table>
`,
		},
		{
			name: "Test with no rows",
			data: []row{},
			expected: `<table>
    <tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>
</table>
`,
		},
		{
			name: "Test with 1 row",
			data: []row{
				{X: 123, Y: 456, Z: 789},
			},
			expected: `<table>
    <tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>
    <tr><td>0</td>
        <td>123</td>
        <td>456</td>
        <td>789</td></tr>
</table>
`,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			ct := template.Must(template.New("").Parse(tmpl))
			var buf bytes.Buffer
			if err := ct.Execute(&buf, tt.data); err != nil {
				t.Fatalf("Failed to execute template: %v", err)
			}
			if buf.String() != tt.expected {
				t.Errorf("Expected:\n%s\nGot:\n%s", tt.expected, buf.String())
			}
		})
	}
}
