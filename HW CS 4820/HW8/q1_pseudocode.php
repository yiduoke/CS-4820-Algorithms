M'(x):
    M_m = M, but there's a a new line of code in the very
          beginning that initiates y and sets it to 0,
          everything inside main is copied to another function f,
          rewrite main to only have two things:
          call f and then set y to 1
    M_m(x) //run M_m on x

boolean haltChecker(string M, string x){
    M' = rewrite(M);
    return modifiesY(M',x);
}
