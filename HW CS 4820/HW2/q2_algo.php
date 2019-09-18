initialize R to be the set of all availabilities;
sort R by increasing finish time;
initialize ordered list A to be empty;
while (R is not yet empty):
    get i∈R; #notice i ends the earliest out of all of R
    remove i from R;
    if ∃ j∈R such that i and j overlap by some time:
        get the first j∈R; #notice j ends the earliest out of all of R
        add i and j to the end of A;
endwhile
return A as set of all partners (every two consecutive elements are partners)

