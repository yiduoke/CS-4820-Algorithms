initialize R to be the set of all availabilities;
initialize ordered list A to be empty;
while (R is not yet empty):
    get i∈R that ends the earliest;
    remove i from R;
    get availability j∈R that overlaps with i and that ends as early as possible;
    add i and j to the end of A;;
endwhile
return set A as set of all partners (every two consecutive elements are partners)

