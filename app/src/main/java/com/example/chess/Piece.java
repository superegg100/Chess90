package com.example.chess;

import android.service.controls.actions.BooleanAction;
import android.transition.Transition;

public class Piece {
    private int i;
    private int j;
    private String color;
    private Piece[][] board;
    public Piece(){

    }
    public Piece(int i, int j, Piece[][] board) {
        this.i = i;
        this.j = j;
        this.board = board;
    }

    public int GetI() {
        return this.i;
    }
    public int GetJ() {
        return this.j;
    }
    public String GetColor() {
        return this.color;
    }
    public Piece[][] GetBoard(){
        return this.board;
    }
    public void SetI(int i) {
        this.i = i;
    }
    public void SetJ(int j) {
        this.j = j;
    }
    public boolean HasPiece() {
        return false;
    }
    public boolean CanMove(int i, int j) {
        return false;
    }
    public boolean IsChecked(Piece king) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j].GetColor() != king.GetColor()) {
                    if (this.board[i][j].CanMove(king.GetI(), king.GetJ())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Piece FindKingoCrimason(String color){
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] instanceof King && this.board[i][j].GetColor() == color){
                    return this.board[i][j];
                }
            }
        }
        return null;
    }
    public void Move(Piece piece, int i, int j){
        this.board[piece.GetI()][piece.GetJ()] = new Piece();
        this.board[i][j] = piece;
        piece.SetI(i);
        piece.SetJ(j);
    }
    public boolean IsPinned(int i, int j, Piece piece){
        boolean ischecked;
        int saveI = piece.GetI(),saveJ = piece.GetJ();
        Piece king = FindKingoCrimason(piece.GetColor());
        Piece temp = this.board[i][j];
        Move(piece, i, j);
        ischecked = IsChecked(king);
        Move(piece, saveI, saveJ);
        this.board[i][j] = temp;
        if (ischecked) return true;
        return false;
    }
}
