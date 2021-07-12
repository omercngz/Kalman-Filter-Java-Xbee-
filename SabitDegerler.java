/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalmanfilterexample;

import java.util.ArrayList;

/**
 *
 * @author Ömer CENGİZ
 */
public class SabitDegerler {
    public double [][] A;
    public double [][] B;
    public double [][] I;
    public double [][] C;
    public double [][] H;
    public double [][] Atranspoz;
    public double [][] Htranspoz;
    public ArrayList <Double> X;
    public ArrayList <Double> Vx;
    public double Δt;
    public double ΔX;
    public double ΔV;
    public double ΔPx;
    public double ΔPVx;
    
    public SabitDegerler(ArrayList<Double> X,ArrayList<Double> Vx,double [][] Atranspoz,double [][] Htranspoz,double [][] A,double [][] B,double [][] I,double [][] C,double [][] H,double Δt,double ΔX,double ΔVx,double ΔPx, double ΔPVx){
    
        this.A=A;
        this.B=B;
        this.I=I;
        this.C=C;
        this.H=H;
        this.Atranspoz=Atranspoz;
        this.Htranspoz=Htranspoz;
        this.X=X;
        this.Vx=Vx;
        this.Δt=Δt;
        this.ΔX=ΔX;
        this.ΔV=ΔV;
        this.ΔPx=ΔPx;
        this.ΔPVx=ΔPVx;
    }
    public double[][] getA() {
        return A;
    }

    public void setA(double[][] A) {
        this.A = A;
    }

    public double[][] getB() {
        return B;
    }

    public void setB(double[][] B) {
        this.B = B;
    }

    public double[][] getI() {
        return I;
    }

    public void setI(double[][] I) {
        this.I = I;
    }

    public double[][] getC() {
        return C;
    }

    public void setC(double[][] C) {
        this.C = C;
    }

    public double[][] getH() {
        return H;
    }

    public void setH(double[][] H) {
        this.H = H;
    }

    public double[][] getAtranspoz() {
        double At[][]=new double[2][2];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < 2; j++) {
                    double gecici=A[i][j];
                    At[i][j]=A[j][i];
                    At[j][i]=gecici;
                        
            }
        }
       
        return At;
    }

    public void setAtranspoz(double[][] Atranspoz) {
        this.Atranspoz = Atranspoz;
    }

    public double[][] getHtranspoz() {
        double Ht[][]=new double[2][2];
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < 2; j++) {
                    double gecici=H[i][j];
                    Ht[i][j]=H[j][i];
                    Ht[j][i]=gecici;
                        
            }
        }
       
        return Ht;
    }

    public void setHtranspoz(double[][] Htranspoz) {
        this.Htranspoz = Htranspoz;
    }
    
    public ArrayList<Double> getX() {
        return X;
    }

    public void setX(ArrayList<Double> X) {
        this.X = X;
    }

    
    public ArrayList<Double> getVx() {
        return Vx;
    }

    public void setVx(ArrayList<Double> Vx) {
        this.Vx = Vx;
    }
    public double getΔt() {
        return Δt;
    }

    public void setΔt(double Δt) {
        this.Δt = Δt;
    }

    public double getΔX() {
        return ΔX;
    }

    public void setΔX(double ΔX) {
        this.ΔX = ΔX;
    }

    public double getΔV() {
        return ΔV;
    }

    public void setΔV(double ΔV) {
        this.ΔV = ΔV;
    }

    public double getΔPx() {
        return ΔPx;
    }

    public void setΔPx(double ΔPx) {
        this.ΔPx = ΔPx;
    }

    public double getΔPVx() {
        return ΔPVx;
    }

    public void setΔPVx(double ΔPVx) {
        this.ΔPVx = ΔPVx;
    }
    
    
}
