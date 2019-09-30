Max-Profit-Helper(start_date, end_date, dates)
    if start_date == end_date:
        return (start_date, start_date)
    else:
        middle_date = (start_date + end_date) / 2
        (left_buy_date, left_sell_date) = Max-Profit(0, middle_date)
        (right_buy_date, right_sell_date) = Max-Profit(middle_date + 1, end_date)

        left_lowest = date with lowest price from dates[start_date … middle_date]
        right_highest = date with highest price from dates[middle_date + 1 … middle_date]
        left_half_answer = (left_buy_date, left_sell_date)
        right_half_answer = (right_buy_date, right_sell_date)
        across_answer = (left_lowest, right_highest)
        return the pair of maximum profit between left_half_answer, right_half_answer, and across_answer
