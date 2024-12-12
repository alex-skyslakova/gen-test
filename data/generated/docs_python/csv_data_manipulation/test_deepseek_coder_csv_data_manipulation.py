import unittest
import csv
import os
import fileinput

class TestCSVDataManipulation(unittest.TestCase):

    def setUp(self):
        # Create a test CSV file
        self.test_csv_path = 'test_csv_data_manipulation.csv'
        with open(self.test_csv_path, 'w', newline='') as csvfile:
            csvwriter = csv.writer(csvfile)
            csvwriter.writerow(['C1', 'C2', 'C3', 'C4', 'C5'])
            csvwriter.writerow(['1', '5', '9', '13', '17'])
            csvwriter.writerow(['2', '6', '10', '14', '18'])
            csvwriter.writerow(['3', '7', '11', '15', '19'])
            csvwriter.writerow(['4', '8', '12', '16', '20'])

    def tearDown(self):
        # Clean up the test CSV file
        if os.path.exists(self.test_csv_path):
            os.remove(self.test_csv_path)

    def test_change_value_in_csv(self):
        changerow, changecolumn, changevalue = 2, 4, '"Spam"'

        with fileinput.input(self.test_csv_path, inplace=True) as f:
            for line in f:
                if fileinput.filelineno() == changerow:
                    fields = line.rstrip().split(',')
                    fields[changecolumn-1] = changevalue
                    line = ','.join(fields) + '\n'
                print(line, end='')

        # Read the modified CSV file and verify the change
        with open(self.test_csv_path, 'r') as csvfile:
            csvreader = csv.reader(csvfile)
            rows = list(csvreader)
            self.assertEqual(rows[1][3], changevalue)

    def test_add_sum_column(self):
        # Add a 'SUM' column with the sums of the rows
        with open(self.test_csv_path, 'r') as csvfile:
            csvreader = csv.reader(csvfile)
            rows = list(csvreader)

        # Modify the rows to include the sum
        for i in range(1, len(rows)):
            row_sum = sum(int(value) for value in rows[i])
            rows[i].append(str(row_sum))

        # Write the modified rows back to the CSV file
        with open(self.test_csv_path, 'w', newline='') as csvfile:
            csvwriter = csv.writer(csvfile)
            csvwriter.writerows(rows)

        # Read the modified CSV file and verify the 'SUM' column
        with open(self.test_csv_path, 'r') as csvfile:
            csvreader = csv.reader(csvfile)
            rows = list(csvreader)
            self.assertEqual(rows[0][-1], 'SUM')
            self.assertEqual(rows[1][-1], '45')
            self.assertEqual(rows[2][-1], '50')
            self.assertEqual(rows[3][-1], '55')
            self.assertEqual(rows[4][-1], '60')

if __name__ == '__main__':
    unittest.main()
