def get_num_of_conflict(status):
    '''
    compute the conflict (also heuristic function value) of this chessplate
    :param status: the chessplate status that under computation
    :return: the number of conflict
    '''
    num = 0
    for i in range(len(status)):
        for j in range(i + 1, len(status)):
            if status[i] == status[j]:
                num += 1
            offset = j - i
            if abs(status[i]-status[j]) == offset:
                num += 1
    if num == 0:return num
    else:return num + 10

def  hill_climbing_first_choice(status,n):
    '''
    randomly select the next status(neighbors of current status)
    if the number of conflict is lower, take this chessplate
    otherwise, the next step remain the same
    :param status: current chessplate status
    :param n: how many queens
    :return: proper next chessplate
    '''
    global chess_status_count, path_count, effective_path
    pos = [(x, y) for x in range(n) for y in range(n)]
    random.shuffle(pos)
    for row1, row2 in pos:
        if row1 == row2:
            continue
        chess_status_count += 1
        status_copy = list(status)
        status_copy[row1], status_copy[row2] = status_copy[row2], status_copy[row1]
        # status_copy[col] = row
        if get_num_of_conflict(status_copy) < get_num_of_conflict(status):
            # status[col] = row
            status[row1], status[row2] = status[row2], status[row1]
            path_count += 1
            effective_path.append(status_copy)
            return status
    return status

def prettyprint(solution):
    '''
    formalized print the status, crossing represent queens, dots represent no-queen
    :param solution: input chessplate
    :return: nothing
    '''
    print(solution)

    def line(pos, length=len(solution)):
        return '. ' * (pos) + 'X ' + '. ' * (length - pos - 1)

    for pos in solution:
        print(line(pos))

def Queens(n):
    '''
    main procedure of hill-climbing algorithm
    calculate the conflict, if equals to zero, consider it as a success
    otherwise, consider it as a failure
    calculate the moving cost and the step it take for this random start, and the times of random start
    :param n: number of queens
    :return: the final status
    '''
    global chess_status_count, success_time, all_count, init_status, chess_move_cost, run, effective_path, path_count
    chess_status_count = 0
    chess_move_cost = 0
    path_count = 0


    next_status = []
    for i in range(n):
        next_status.append(i)

    for i in range(n):
        r = random.randint(0, 10)
        r = r % n
        next_status[r], next_status[n - r - 1] = next_status[n - r - 1], next_status[r]

    init_status = list(next_status)
    effective_path = [init_status];
    current_status = []

    while current_status != next_status:
        current_status = list(next_status)
        next_status = hill_climbing_first_choice(next_status,n)
        temp_cost = np.abs(list(map(lambda x: x[0]-x[1], zip(next_status, current_status))))
        chess_move_cost += 20 + pow(np.max(temp_cost),2)
    if get_num_of_conflict(current_status) is 0:
        success_time += 1

    # when the conflict is not zero, restart it.
    if get_num_of_conflict(current_status) != 0:
        all_count += chess_status_count
        run += 1;
        Queens(N)

    return current_status

if __name__ == '__main__':
    import random
    import numpy as np
    import datetime

    all_count = 0
    chess_status_count = 0;
    chess_move_cost = 0;
    success_time = 0;
    test_num = 1;
    run = 1;
    init_status = [];
    path_count = 0;
    effective_path = [];
    N = int(input("Enter number of queens: "));

    time_start = datetime.datetime.now();
    for test in range(test_num):
        q = Queens(N);
        all_count += chess_status_count
    time_stop = datetime.datetime.now();

    print("The Start State")
    prettyprint(init_status)
    print("Number of nodes expanded: %d" % all_count)
    print("Effective Branching Factor: %d" % (chess_status_count / path_count))
    print("Time solve the puzzle", (time_stop - time_start) / test_num)
    print("Search Cost: %d" % (chess_status_count))
    print("Sequence of moves (reverse order):", effective_path)
    print("Length of Path", len(effective_path))