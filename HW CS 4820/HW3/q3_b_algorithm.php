Get-Negative-Cycle(G,v)
    run Shortest-Path(G,v,t)'s algorithm allowing n edges
    go through M[n,v] to find two occurences of one node
    return the sequence between the two occurences (one end inclusive, the other side exclusive)

Shortest-Path(G,s,t)
    Array M[0 ... n, V]
    Array S[0 ... n, V]
    Define M[0,t] = 0 and M[0,v] = ∞ for all over v ∈ V
    Define S[0,t] = [[t]] and S[0,v] = [[]] for all over v ∈ V
    For i = 1, ..., n
        For v ∈ V in any order
            nodes ws = a list of nodes that equally minimizes M[i-1,w] + edge_weight(v,w),
                , w being any element from ws
            if (M[i-1,w] + edge_weight(v,w) < M[i-1,v)]) then
                M[i,v] = M[i-1,w] + edge_weight(v,w)

                For w ∈ ws
                    S[i,v] = v prepended to each of S[i-1,w]'s lists
                Endfor
            else if (M[i-1,w] + edge_weight(v,w) > M[i-1,v])
                M[i,v] = M[i-1,v]

                S[i,v] = S[i,v]
            else 
                M[i,v] = M[i-1,v]
                S[i,v] = join(S[i-1,v], v prepended to each of S[i-1,w]'s lists)
            endif
        Endfor
    Endfor 

    For v ∈ V in any order
        if M[n,v] != M[n-1,v] then 
            return Get-Negative-Cycle(G,v)
    Endfor
    Return S[n-1,s]
