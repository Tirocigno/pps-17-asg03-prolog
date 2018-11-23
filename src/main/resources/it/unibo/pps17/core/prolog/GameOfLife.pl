%Position of a cell inside the board from the topmost cell to the left.
pos(X,Y).

%Fact to represent board dimension.
board(50,50).

%Generation passed since the beginning of the game.
generation(0).

%Current state of the game board,saved as list of alive cells.
alive_cells([]).


%----BOARD INITIALIZATION------

%Initialize a cell to a random value.
initialize_cell(P,TL,AL) :- random(N,2),set_pos(P,TL,N,AL).

%Initialize every cell inside the board.
generate_board(50,_, TL, TL) :- !.
generate_board(X, 50, TL, AL) :- X1 is X + 1,Y1 is 0, generate_board(X1,Y1,TL,AL), !.
generate_board(X, Y, TL, AL) :- initialize_cell(pos(X,Y),TL,TL2),Y1 is Y+1, generate_board(X,Y1,TL2,AL).
generate_board(AL) :- generate_board(0,0,[],AL).

%Setup board and retreive the list of alive cells.
setup_board(AL):- generate_board(AL), set_alive_cells(AL).

%----GENERATIONS CALCULATOR------

%Check that a number is in range defined between lower bound and upper bound.
range(N,LB,UB):- N >= LB, N < UB.
range(N,UB):- range(N,0,UB).

%Check that a position is inside the given borders.
check_border(pos(X,Y)) :- board(MAX_X, MAX_Y), range(X,MAX_X), range(Y, MAX_Y).

%Delete cells outside the borders.
delete_out_board([],LO).
delete_out_board([H|T],LO):-not(check_border(H)),delete_out_board(T,LO),!.
delete_out_board([H|T],LO):-check_border(H),delete_out_board(T,LI), append_to_head(LI,H,LO).

%Find all the neigbor cells inside the board to a given position.
neighborhood(P,L):-P=pos(X,Y),
XLOW is X+1,XTOP is X-1,YLOW is Y+1,YTOP is Y-1, 
L1=[pos(XLOW,Y), pos(XTOP,Y), pos(X,YLOW), pos(X,YTOP), pos(XLOW, YLOW), pos(XLOW, YTOP), pos(XTOP, YTOP), pos(XTOP,YLOW)],
delete_out_board(L1,L).

%Compute the value of a cell based on his neighborhood.
compute_cell(P,TL,TL2) :-  member(P,AL),neighborhood(P,NL), alive_cells(AL),common_elements(NL, AL, C), C >= 2, C <= 3, set_pos(P,TL, 1, TL2),!.
compute_cell(P,TL,TL2) :- neighborhood(P,NL), alive_cells(AL),common_elements(NL, AL, C), C == 3, set_pos(P,TL, 1, TL2),!.
compute_cell(_,TL,TL).

%Compute the whole board game on new status.
compute_board(50,_, TL, TL) :- !.
compute_board(X, 50, TL, AL) :- X1 is X + 1,Y1 is 0, compute_board(X1,Y1,TL,AL), !.
compute_board(X, Y, TL, AL) :- compute_cell(pos(X,Y),TL,TL2),Y1 is Y+1, compute_board(X,Y1,TL2,AL).
compute_board(AL) :- compute_board(0,0,[],AL).


%-----COMMONS------

%Compute the size of a list.
size([],0).
size([_|T],M):-size(T,N), M is N+1.

%Append an element in the head of the list.
append_to_head([],H,[H]):- !.
append_to_head(T,H,[H|T]).

%Set the specified cell inside the alive cell list if the specified values is one.
set_pos(P,TL,S, TL):- S == 0,!.
set_pos(P,TL,S,AL) :- S == 1, append_to_head(TL,P,AL). 

%Generate a random number in range from 0 to LIMIT - 1.
random(N, LIMIT) :- rand_int(LIMIT,N).

%Check how many elements two lists have in common.
common_elements([],[],0).
common_elements([],L,0).
common_elements([H|T],L,N) :- member(H,L), common_elements(T,L,N1), N is N1+1,!.
common_elements([H|T],L,N) :- common_elements(T,L,N). 

%Update the alive cells fact.
set_alive_cells(AL) :- retract(alive_cells(_)),assert(alive_cells(AL)).



