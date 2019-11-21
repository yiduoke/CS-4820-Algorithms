M'(x):
    M_m = M, but with a a new line of code that sets y
          to 1 at the end of the main function
    M_m(x) //run M_m on x

boolean haltChecker(string M, string x){
    M' = rewrite(M);
    return modifiesY(M',x);
}
