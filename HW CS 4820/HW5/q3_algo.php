Fair-Flow
Let f(e)=0 for all e
Let s_new be a new node
Let edge (s_new, s) have capacity C1 + C2
Let G1 = graph whose source is s_new and sink is t1
Let G2 = graph whose source is s_new and sink is t2
Let f1 = flow going into t1
Let f2 = flow going into t2
Let iteration = 0
while ((G1 has an s_new -> t1 path) or (G2 has an s_new -> t2 path)) and
    (f1 and f2 don't differ by more than 1)
        if iteration is even
            set up G2
            find an s_new -> t2 path P2 in G2
            delta = 1 //instead of what we had before with the min(min ce ...
            f(e) = f(e) + delta for forward edges in P2
            f(e) = f(e) - delta for backward edges in P2
            f2 = f2 + delta
        else
             set up G1
             find an s_new -> t1 path P1 in G1
             delta = 1 //instead of what we had before with the min(min ce ...
             f(e) = f(e) + delta for forward edges in P1
             f(e) = f(e) - delta for backward edges in P1
             f1 = f1 + delta
        iteration = iteration + 1
endwhile


