package com.example.chess;


public class Bishop extends Piece {
    public Bishop(int i, int j, Piece[][] board){
        super(i,j,board);

    }
    public boolean HasPiece(){
        return true;
    }
    public void ChangeNum(boolean b, int j){
        if(b){
            j++;
        }
        else{
            j--;
        }
    }
    public boolean CanMove(Piece piece){
        if(IsPinned(super.GetI(), super.GetJ(), this)){
            return false;
        }
        boolean ibool = false;
        boolean jbool = false;
        int i = super.GetI();
        int j = super.GetJ();
        if(i < piece.GetI()){
            ibool = true;
        }
        else
            ibool = false;
        if(j < piece.GetJ())
            jbool = true;
        else
            jbool = false;
        while(i >= 0 && j >= 0 && i < 8 && j < 8){
            if(i == piece.GetI() && j == piece.GetJ()){
                return true;
            }
            if(super.GetBoard()[i][j].HasPiece()){
                return false;
            }
            ChangeNum(ibool, i);
            ChangeNum(jbool, j);

        }
        return false;
    }
    public void Move(Piece piece){

    }
}
