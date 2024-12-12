package main

import (
    "bytes"
    "html/template"
    "testing"
)

func TestCreateHTMLTable(t *testing.T) {
    // Define the expected HTML output
    expectedHTML := `<table>
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
</table>
`

    // Create the template
    ct := template.Must(template.New("").Parse(tmpl))

    // Make up data
    data := make([]row, 4)
    for r := range data {
        data[r] = row{r * 3, r*3 + 1, r*3 + 2}
    }

    // Execute the template and capture the output
    var buf bytes.Buffer
    if err := ct.Execute(&buf, data); err != nil {
        t.Fatalf("Failed to execute template: %v", err)
    }

    // Compare the output with the expected HTML
    actualHTML := buf.String()
    if actualHTML != expectedHTML {
        t.Errorf("Unexpected HTML output.\nExpected:\n%s\nGot:\n%s", expectedHTML, actualHTML)
    }
}
