ISBN Validator

Receives input from user and determines whether or not the input is a valid ISBN.

ISBN Criteria:  ISBN must include 9 integers, 0 or 3 dashes, and a valid check sum.

The checksum is calculated as follows:  Each digit value is multiplied by its 1-based index value (not including dashes) in the ISBN.  
The sum of each of these calculations is totaled, then the checksum is equal to the total sum % 11.  If the checksum is equal to 10,
the value 10 is replaced with X (for example, a valid ISBN is 123-45-6-789X or 123456789X)

Checksum Calculation Example:  ISBN 1-239-2-6589
1 * 1 = 1
2 * 2 = 4
3 * 3 = 9
4 * 9 = 36
5 * 2 = 10
6 * 6 = 36
7 * 5 = 35
8 * 8 = 64
9 * 9 = 81
Checksum = 276 % 11 = 1