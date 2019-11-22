boolean winStrat1(allTrueConfigs){ // list of all variable assignments that makes phi true
                                   // (e.g. [[T,F], [T,T]], which means means
                                   // [[x1 = T, x2 = F], [x1 = T, x2 = T]])
    
    length = allTrueConfigs.length
    if (length == 0){
        return true;
    }
    
    trues = list of all A ∈ allTrueConfigs where A[1] is true;
    falses = list of all A ∈ allTrueConfigs where A[1] is false;
    
    if (trues[2] is only T or only F || falses[2] is only T or only F){
        // e.g. trues = [[x1 = T, x2 = F, x3 = F], [x1 = T, x2 = F, x3 = T]]
        // x2 = F only, meaning player 1 can't fend against player two when she makes x2 = T
        return false;
    }
    else{
        trueFalses = list of all A ∈ trues where A[1]==T and A[2]==F;
        trueTrues = list of all A ∈ trues where A[1]==T and A[2]==T;
        
        falseTrues = list of all A ∈ falses where A[1]==F and A[2]==T;
        falseFalses = list of all A ∈ falses where A[1]==F and A[2]==F;

        return winStrat1(winStrat1(trueFalses.sublist(3,end)) &&
                         winStrat1(trueTrues.sublist(3,end)) &&
                         winStrat1(falseTrues.sublist(3,end)) &&
                         winStrat1(falseFalses.sublist(3,end))
                         );
    }
}
