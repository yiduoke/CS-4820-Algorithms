initialize R to be the set of all shifts;
initialize T to be empty;
while (R is not yet empty):
    choose a shift i∈R that ends the earliest;
    add f(i) to T;
    delete all shifts from R that overlap with shift i;
endwhile
return set T as the boss's visit times