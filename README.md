# Determinant_Recursive

Matrix Determinant in codewars(4kyu)：https://www.codewars.com/kata/52a382ee44408cea2500074c/train/java

Write a function that accepts a square matrix (N x N 2D array) and returns the determinant of the matrix.
How to take the determinant of a matrix -- it is simplest to start with the smallest cases:

A 1x1 matrix |a| has determinant a.
A 2x2 matrix [ [a, b], [c, d] ] or

|a  b|

|c  d|

has determinant: a*d - b*c.

The determinant of an n x n sized matrix is calculated by reducing the problem to the calculation of the determinants of n matrices ofn-1 x n-1 size.

For the 3x3 case, [ [a, b, c], [d, e, f], [g, h, i] ] or

|a b c|  

|d e f|  

|g h i|  

the determinant is: a * det(a_minor) - b * det(b_minor) + c * det(c_minor) 
where det(a_minor) refers to taking the determinant of the 2x2 matrix created by crossing out the row and column in which the element a occurs:

|- - -|

|- e f|

|- h i|  

Note the alternation of signs.

The determinant of larger matrices are calculated analogously, e.g. if M is a 4x4 matrix with first row [a, b, c, d], then:
det(M) = a * det(a_minor) - b * det(b_minor) + c * det(c_minor) - d * det(d_minor)

Check answer：

determinant(new int[][]{{1,  2, 3, 4, 5,6, 7},
			                  {11,12,13,14, 1,2, 8},
			                  {10,13,14, 1, 2,3, 9},
		                  	{9, 12, 7, 8, 3,4,10},
			                  {8, 11 ,6, 5, 4,5,11},
			                  {7, 10, 9, 8, 7,6,12},
			                  {6,  5, 4, 3, 2,1,13}}));//expected：-29904
      
determinant(new int[][]{{9,2,3,9,9},
                        {4,5,6,4,8},
                        {7,8,9,1,7},
                        {1,2,3,4,6},
                        {1,2,3,4,5}});//expected：-72
                        
determinant(new int[][]{{2,5,3,41},
                        {5,6,7,82},
                        {4,3,2,13},
                        {2,1,11,10}}));//expected：-5895	                        
                        
determinant(new int[][]{{1,1,1, 1, 1},
                        {1,2,5, 3,41},
                        {1,5,6, 7,82},
                        {1,4,3, 2,13},
                        {1,2,1,11,10}});//expected：-3553	   




