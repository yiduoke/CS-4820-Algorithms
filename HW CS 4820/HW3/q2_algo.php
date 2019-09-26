Opt-Matrix-Multiplication:
    M[][] = new matrix[m][m]
    S[][] = new matrix[m][m]

    int diff = 0
    for i = m, m-1, ... 1
        for j = 1, 2, ... i
            starting_matrix = j
            ending_matrix = j+diff
            if (diff == 0) # on the diagonal of the matrices
                M[starting_matrix][ending_matrix] = 0
                S[starting_matrix][ending_matrix] = null
            else
                middle_matrix = a number between starting_matrix (inclusive)
                                                and ending_matrix (exclusive)
                such that

                new_opt = (M[starting_matrix][middle_matrix] +
                           M[middle_matrix + 1][ending_matrix] +
                           (number of rows of starting_matrix *
                           number of columns of middle_matrix *
                           number of columns of ending_matrix))

                is minimized.

                M[starting_matrix][ending_matrix] = new_opt
                S[starting_matrix][ending_matrix] = join(S[starting_matrix][middle_matrix],
                                                         S[middle_matrix+1][ending_matrix])
                                                    .prepend(middle_matrix)
            endif
        endfor
        diff++
    endfor
    minimum_runtime = M[1][m]
    order = S[1][m]

    