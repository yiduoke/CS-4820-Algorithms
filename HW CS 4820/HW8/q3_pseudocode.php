M1(x){
    M(x);
    accept;
}

M2(x){
    M(x);
    reject;
}

boolean neverHaltChecker(M,x){
    M1 = rewrite1(M,x);
    M2 = rewrite2(M,x);
    
    return agreeChecker(M1,M2,x);
}
