import unittest
import csv
import os

class TestCSVDataManipulation(unittest.TestCase):

    def setUp(self):
        # Create a sample CSV file for testing
        self.test_csv_filename = 'test_csv_data_manipulation.csv'
        with open(self.test_csv_filename, 'w', newline='') as csvfile:
            writer = csv.writer(csvfile)
            writer.writerow(['C1', 'C2', 'C3', 'C4', 'C5'])
            writer.writerow([1, 5, 9, 13, 17])
            writer.writerow([2, 6, 10, 14, 18])
            writer.writerow([3, 7, 11, 15, 19])
            writer.writerow([4, 8, 12, 16, 20])

    def tearDown(self):
        # Remove the test CSV file after tests
        try:
            os.remove(self.test_csv_filename)
        except OSError:
            pass

    def test_change_value(self):
        # Change a specific value in the CSV file
        changerow, changecolumn, changevalue = 2, 4, 'Spam'
        
        with open(self.test_csv_filename, 'r') as f:
            lines = f.readlines()

        with open(self.test_csv_filename, 'w') as f:
            for i, line in enumerate(lines):
                if i == changerow - 1:
                    fields = line.rstrip().split(',')
                    fields[changecolumn - 1] = changevalue
                    line = ','.join(fields) + '\n'
                f.write(line)

        # Verify the change
        with open(self.test_csv_filename, 'r') as f:
            reader = csv.reader(f)
            rows = list(reader)
            self.assertEqual(rows[changerow][changecolumn - 1], changevalue)

    def test_add_sum_column(self):
        # Add a 'SUM' column with the sum of each row
        with open(self.test_csv_filename, 'r') as f:
            reader = csv.reader(f)
            rows = [row for row in reader]

        # Add 'SUM' header
        rows[0].append('SUM')

        # Calculate sum for each row and append
        for row in rows[1:]:
            row_sum = sum(int(value) for value in row)
            row.append(str(row_sum))

        # Write back to CSV
        with open(self.test_csv_filename, 'w', newline='') as f:
            writer = csv.writer(f)
            writer.writerows(rows)

        # Verify the 'SUM' column
        with open(self.test_csv_filename, 'r') as f:
            reader = csv.reader(f)
            rows = list(reader)
            self.assertEqual(rows[0][-1], 'SUM')
            for row in rows[1:]:
                expected_sum = sum(int(value) for value in row[:-1])
                self.assertEqual(int(row[-1]), expected_sum)

if __name__ == '__main__':
    unittest.main()
