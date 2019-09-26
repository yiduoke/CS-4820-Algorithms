Get-Negative-Cycle(G,v)
    run Shortest-Path(G,v,v)'s algorithm
    Define x = the first column where M[x,v] != 0
    return M[x,v]

Shortest-Path(G,s,t)
    Array M[0 ... n, V]
    Array S[0 ... n, V]
    Define M[0,t] = 0 and M[0,v] = ∞ for all over v ∈ V
    Define S[0,t] = [[t]] and S[0,v] = [[]] for all over v ∈ V
    For i = 1, ..., n
        For v ∈ V in any order 
            node w = the node that minimizes M[i-1,w] + edge_weight(v,w)
            if (M[i-1,w] + edge_weight(v,w) < M[i-1,v)]) then
                M[i,v] = M[i-1,w] + edge_weight(v,w)
                S[i,v] = v prepended to all of S[i-1,w]'s lists
            else if (M[i-1,w] + edge_weight(v,w) > M[i-1,v])
                M[i,v] = M[i-1,v]
                S[i,v] = S[i-1,v]
            else 
                M[i,v] = M[i-1,v]
                S[i,v] = join(S[i-1,v], v prepended to all of S[i-1,w]'s lists)
            endif
        Endfor
    Endfor 

    For v ∈ V in any order
        if M[n,v] != M[n-1,v] then 
            return Get-Negative-Cycle(G,v)
    Endfor
    Return S[n-1,s]