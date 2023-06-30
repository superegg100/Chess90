package com.example.chess;

import android.widget.ImageView;

public class Rook extends Piece{
    private int NumOfMoves;
    public Rook(int i, int j, Piece[][] board,String color, ImageView imageView){
        super(i,j,board,color, imageView);
        if (super.GetColor() == "white"){
            super.GetImageView().setImageResource(R.drawable.whiteroock);
        }
        else{
        super.GetImageView().setImageResource(R.drawable.blackroock);}
    }
    @Override
    public boolean HasPiece(){
        return true;
    }
    public int GetNumOfMoves(){
        return this.NumOfMoves;
    }
    @Override
    public boolean CanMove(Piece piece) {
        if (super.GetI() != piece.GetI() && super.GetJ() != piece.GetJ()){ return false; }
        if (IsPinned(piece.GetI(), piece.GetJ(), this)) { return false;}
        if (piece.GetColor() == super.GetColor()){
            return false;
        }
        if (piece.GetI()<super.GetI()) {return CanMoveDown(piece);}
        if (piece.GetI()>super.GetI()) {return CanMoveUp(piece);}
        if (piece.GetJ()<super.GetJ()) {return CanMoveLeft(piece);}
        if (piece.GetJ()>super.GetJ()) {return CanMoveRight(piece);}
        return true;
    }
    public boolean CanMoveLeft(Piece piece) {
        for (int x = super.GetJ(); x > piece.GetJ(); x--){
            if (super.GetBoard()[piece.GetI()][x].HasPiece()) { return false; }
        }
        return true;
    }
    public boolean CanMoveRight(Piece piece) {
        for (int x = super.GetJ(); x < piece.GetJ(); x++){
            if (super.GetBoard()[piece.GetI()][x].HasPiece()) { return false; }
        }
        return true;
    }
    public boolean CanMoveDown(Piece piece) {
        for (int x = super.GetI(); x > piece.GetI(); x--){
            if (super.GetBoard()[x][piece.GetJ()].HasPiece()) { return false; }
        }
        return true;
    }
    public boolean CanMoveUp(Piece piece) {
        for (int x = super.GetI(); x < piece.GetI(); x++){
            if (super.GetBoard()[x][piece.GetJ()].HasPiece()) { return false; }
        }
        return true;
    }
    public void Move(Piece piece, int i, int j){
        this.GetBoard()[piece.GetI()][piece.GetJ()] = new Piece(piece.GetI(), piece.GetJ(), piece.GetBoard(),"gray", GetImageView());;
        this.GetBoard()[i][j] = piece;
        piece.SetI(i);
        piece.SetJ(j);
        super.IncNumOfMovesInGame();
        this.NumOfMoves++;
    }

    @Override
    public boolean CanEscapeCheckMate() {
        for (int j = 1; j <= 8-super.GetJ(); j++){
            if (super.GetBoard()[super.GetI()][super.GetJ()+j].HasPiece()){
                if (super.GetColor() == (super.GetBoard()[super.GetI()][super.GetJ()+j].GetColor())){
                    break;
                }
            }
            if (!IsPinned(super.GetI(),super.GetJ()+j,this)){
                return true;
            }
        }
        for (int j = super.GetJ()-1; j >= 0; j--){
            if (super.GetBoard()[super.GetI()][j].HasPiece()){
                if (super.GetColor() == (super.GetBoard()[super.GetI()][j].GetColor())){
                    break;
                }
            }
            if (!IsPinned(super.GetI(),j,this)){
                return true;
            }
        }
        for (int i = 1; i <= 8-super.GetI(); i++){
            if (super.GetBoard()[super.GetI()+i][super.GetJ()].HasPiece()){
                if (super.GetColor() == (super.GetBoard()[super.GetI()+i][super.GetJ()].GetColor())){
                    break;
                }
            }
            if (!IsPinned(super.GetI()+i,super.GetJ(),this)){
                return true;
            }
        }
        for (int i = super.GetI()-1; i >= 0; i--){
            if (super.GetBoard()[i][super.GetJ()].HasPiece()){
                if (super.GetColor() == (super.GetBoard()[i][super.GetJ()].GetColor())){
                    break;
                }
            }
            if (!IsPinned(i,super.GetJ(),this)){
                return true;
            }
        }
        return false;
    }

}
