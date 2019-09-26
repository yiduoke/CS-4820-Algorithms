Shortest-Path(G,s,t,k)
    Array M[0 ... k, V]
    Define M[0,t] = 0 and M[0,v] = ∞ for all over v ∈ V
    For i = 1, ..., k 
        For v ∈ V in any order 
            node w = the node that minimizes OPT(i-1,w) + edge_weight(c,w)
            M[i,v] = min⁡(OPT(i-1,v), ⁡OPT(i-1,w) + edge_weight(c,w))
        Endfor
    Endfor 
    Return M[k,s]