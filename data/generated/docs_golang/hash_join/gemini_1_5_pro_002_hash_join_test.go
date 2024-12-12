import (
	"reflect"
	"testing"
)

func TestHashJoin(t *testing.T) {
	tableA := []struct {
		Age  int
		Name string
	}{
		{27, "Jonah"},
		{18, "Alan"},
		{28, "Glory"},
		{18, "Popeye"},
		{28, "Alan"},
	}
	tableB := []struct {
		Character string
		Nemesis   string
	}{
		{"Jonah", "Whales"},
		{"Jonah", "Spiders"},
		{"Alan", "Ghosts"},
		{"Alan", "Zombies"},
		{"Glory", "Buffy"},
	}

	expected := []struct {
		Age      int
		Name     string
		Character string
		Nemesis   string
	}{
		{27, "Jonah", "Jonah", "Whales"},
		{27, "Jonah", "Jonah", "Spiders"},
		{18, "Alan", "Alan", "Ghosts"},
		{18, "Alan", "Alan", "Zombies"},
		{28, "Glory", "Glory", "Buffy"},
		{28, "Alan", "Alan", "Ghosts"},
		{28, "Alan", "Alan", "Zombies"},
	}

	actual := hashJoin(tableA, tableB, "Name", "Character")


    // Check if the lengths are equal, fail early if not.
    if len(actual) != len(expected) {
        t.Fatalf("Expected %d results, but got %d", len(expected), len(actual))
    }


    // Convert to map for easier comparison, ignoring order.
    expectedMap := make(map[interface{}]bool)
    for _, exp := range expected {
        expectedMap[exp] = true
    }
    
    for _, act := range actual {
        if _, ok := expectedMap[act]; !ok {
            t.Errorf("Unexpected result: %+v", act)
        }
    }


}




func hashJoin(tableA interface{}, tableB interface{}, joinColumnA string, joinColumnB string) interface{} {
	// ... implementation (same as provided in the problem description)
    // ...  However, ensure it returns a slice of structs rather than printing.


    // Example Implementation (adapt to your actual struct types)
    aVal := reflect.ValueOf(tableA)
    bVal := reflect.ValueOf(tableB)

    h := make(map[string][]int)
    for i := 0; i < aVal.Len(); i++ {
        key := aVal.Index(i).FieldByName(joinColumnA).String()
        h[key] = append(h[key], i)
    }
    
    var results []interface{}

    for i := 0; i < bVal.Len(); i++ {
        key := bVal.Index(i).FieldByName(joinColumnB).String()
        for _, aIndex := range h[key] {


           // Construct the combined result dynamically
            result := make(map[string]interface{})

            for j := 0; j < aVal.Index(aIndex).NumField(); j++ {
                result[aVal.Index(aIndex).Type().Field(j).Name] = aVal.Index(aIndex).Field(j).Interface()
            }

            for j := 0; j < bVal.Index(i).NumField(); j++ {
                result[bVal.Index(i).Type().Field(j).Name] = bVal.Index(i).Field(j).Interface()
            }

            results = append(results, result)



        }
    }
   
    return results




}

