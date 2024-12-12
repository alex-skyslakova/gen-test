package main

import (
	"bytes"
	"html/template"
	"regexp"
	"strings"
	"testing"
)

func TestCreateHTMLTable(t *testing.T) {
	tests := []struct {
		name     string
		rows     int
		expected string
	}{
		{
			name: "ThreeRows",
			rows: 3,
			expected: `<table>
    <tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>
    <tr><td>0</td>
        <td>0</td>
        <td>1</td>
        <td>2</td></tr>
    <tr><td>1</td>
        <td>3</td>
        <td>4</td>
        <td>5</td></tr>
    <tr><td>2</td>
        <td>6</td>
        <td>7</td>
        <td>8</td></tr>
</table>
`,
		},
		{
			name: "FiveRows",
			rows: 5,
			expected: `<table>
    <tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>
    <tr><td>0</td>
        <td>0</td>
        <td>1</td>
        <td>2</td></tr>
    <tr><td>1</td>
        <td>3</td>
        <td>4</td>
        <td>5</td></tr>
    <tr><td>2</td>
        <td>6</td>
        <td>7</td>
        <td>8</td></tr>
    <tr><td>3</td>
        <td>9</td>
        <td>10</td>
        <td>11</td></tr>
    <tr><td>4</td>
        <td>12</td>
        <td>13</td>
        <td>14</td></tr>
</table>
`,
		},		{
			name: "ZeroRows", // Test with zero rows
			rows: 0,
			expected: `<table>
    <tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>
</table>
`,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// create template
			ct := template.Must(template.New("").Parse(tmpl))

			// make up data
			data := make([]row, tt.rows)
			for r := range data {
				data[r] = row{r * 3, r*3 + 1, r*3 + 2}
			}

			// Execute template and capture output
			var buf bytes.Buffer
			err := ct.Execute(&buf, data)
			if err != nil {
				t.Fatalf("template execution failed: %v", err)
			}
			actual := buf.String()



			// Normalize whitespace for comparison
			re := regexp.MustCompile(`\s+`)
			expected := re.ReplaceAllString(strings.TrimSpace(tt.expected), " ")
			actual = re.ReplaceAllString(strings.TrimSpace(actual), " ")


			if actual != expected {
				t.Errorf("output mismatch:\nExpected:\n%s\nActual:\n%s", expected, actual)
			}

		})
	}
}

