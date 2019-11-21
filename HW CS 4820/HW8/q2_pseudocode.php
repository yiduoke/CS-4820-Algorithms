rewrite(M):
    M' = M, but rewritten so that
        all recursion is turned into iteration;
        turn all non-boolean variables into boolean array
        representations //we can do this with some rules,
        //like turning integers into their binary representations
        //then turning 1's into trues and 0's into falses and
        //prepending [T,T,F] onto all representations of integers
    return M'

boolean haltChecker(string M, string x){
    M' = rewrite(M);
    return limMemHalt(M',x);
}

