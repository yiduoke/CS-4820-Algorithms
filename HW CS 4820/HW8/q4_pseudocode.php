boolean winStrat1(allTrueConfigs){ // list of all variable assignments that makes phi true
                                   // (e.g. [[T,F], [T,T]], which means means
                                   // [[x1 = T, x2 = F], [x1 = T, x2 = T]])
    
    length = allTrueConfigs.length
    if (length == 0){ // base case; game over
        return true;
    }
    
    trues = list of all A ∈ allTrueConfigs where A[0] == True;
    falses = list of all A ∈ allTrueConfigs where A[0] == False;
    
    if ((∀ x ∈ trues,  x[1] is the same) &&
        (∀ x ∈ falses, x[1] is the same)){
        // e.g. trues  = [[x1 = T, x2 = F, x3 = F], [x1 = T, x2 = F, x3 = T]]
        // and  falses = [[x1 = F, x2 = T, x3 = F], [x1 = F, x2 = T, x3 = T]]
        // then when x1 == T, then x2 = F only, and
        // when x1 == F, then x2 = T only, meaning player 1 can't fend against
        // player two's two available options in either case
        
        return false;
    }
    else{
        falseTrues = list of all x ∈ falses where x[0]==F and x[1]==T;
        falseFalses = list of all x ∈ falses where x[0]==F and x[1]==F;
        trueFalses = list of all x ∈ trues where x[0]==T and x[1]==F;
        trueTrues = list of all x ∈ trues where x[0]==T and x[1]==T;
        
        if (∀ x ∈ trues,  x[1] is the same){ //then player 1 can't set that x to True
            return winStrat1(falseTrues.sublist(2,end)) &&
                   winStrat1(falseFalses.sublist(2,end))
        }
        else if (∀ x ∈ falses,  x[1] is the same){ //then player 1 can't set that x to False
            return winStrat1(trueFalses.sublist(2,end)) &&
                   winStrat1(trueTrues.sublist(2,end))
        }
        else{ //then player 1 can set that x to either True or False -- for now
            return (winStrat1(falseTrues.sublist(2,end)) &&
                   winStrat1(falseFalses.sublist(2,end))) ||
                   (winStrat1(trueFalses.sublist(2,end)) &&
                    winStrat1(trueTrues.sublist(2,end)))
        }
    }
}
