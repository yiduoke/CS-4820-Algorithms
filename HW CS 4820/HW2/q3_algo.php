Iterative-Compute-Opt
M[0]= 0
for j = 1, 2, . . . , n
    M[j]= max(vj + M[j-2], M[j − 1])
endfor
return M[n]
