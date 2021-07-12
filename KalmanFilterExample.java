
package kalmanfilterexample;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ömer CENGİZ
 */
public class KalmanFilterExample {
  
    public  void Xkp_Calculation(ArrayList<Double> X,ArrayList<Double> Vx,double [][] Atranspoz,double [][] Htranspoz,double [][] A,double [][] B,double [][] I,double[][] C,double [][] H,double Xo,double Vxo,double Δt,double ΔX,double ΔVx,double ΔPx,double ΔPVx,double Uk,double sum,int sayac){
        double [][]XkSonuc={{Xo},{Vxo}};
        double XkpSonuc[][]=new double[A.length][XkSonuc[0].length]; 
        double [][] Xb=new double [A.length][XkSonuc[0].length];
        double [][] Xi=new double [B.length][1];
        //Xb=A*Xk_
        for (int i = 0; i <A.length  ; i++) {
            for (int j = 0; j <XkSonuc[0].length ; j++) {
                for (int k = 0; k <A[0].length; k++) {
                    sum=sum+A[i][k]*XkSonuc[k][j];
                }
                Xb[i][j]=sum;
                sum=0;        
            }
        }
        
        //Xi=B*Uk
        
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
               
                    Xi[i][j]=B[i][j]*Uk;   
            }
        }
        for (int i = 0; i < Xb.length; i++) {
            for (int j = 0; j < Xb[0].length; j++) {
                XkpSonuc[i][j]=Xb[i][j]+Xi[i][j];
            }
        }
        double [][] Xkp=XkpSonuc;
        double [][] Pk_1=Pk_1_Calculation(ΔPx, ΔPVx);
        double [][] Pkp=Pkp_Calculation(A, Pk_1, Atranspoz, sum);
        double [][] Kk=Kk_Calculation(Pkp, Htranspoz, H, ΔX, ΔVx, sum);
        double [][] Yk=Yk_Calculation(C, X, Vx, sayac, sum);
        double [][] Xk=Xk_Calculation(Xkp, Kk, Yk, H, sum);
        double [][] Pk=Pk_Calculation(I, Kk, H, Pkp,sum);

        for (int i = 0; i < Xk.length; i++) {
            for (int j = 0; j < Xk[0].length; j++) {
                System.out.println("Deger "+i+") "+Xk[i][j] );
            }
        }
        System.out.println("****************");
        if(sayac<X.size()-1){
            Xkp_Calculation(X, Vx, Atranspoz, Htranspoz, A, B, I, C, H, Xk[0][0], Xk[1][0], Δt, ΔX, ΔVx, ΔPx, ΔPVx, Uk, sum, sayac+1);
        }
        else{
            System.exit(0);
        }
    }
    public static double [][] Pk_1_Calculation(double ΔPx,double ΔPVx){
       double[][] Pk_1Matrix= new double[2][2];
       Pk_1Matrix[0][0]=ΔPx*ΔPx;
       Pk_1Matrix[0][1]=0;
       Pk_1Matrix[1][0]=0;
       Pk_1Matrix[1][1]=ΔPVx*ΔPVx;
       
       return Pk_1Matrix;
    }
    public static double [][] Pkp_Calculation(double [][] A,double [][] Pk_1,double [][] Atranspoz,double sum){
       
        double [][]Pkpsonuc=new double[A.length][Pk_1[0].length];
        double [][]Pkpi=new double[A.length][Pk_1[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < Pk_1[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                   sum=sum+A[i][k]*Pk_1[k][j];
                }
                Pkpi[i][j]=sum;
                sum=0;
            }
        }
        for (int i = 0; i < Pkpi.length; i++) {
            for (int j = 0; j < Atranspoz[0].length; j++) {
                for (int k = 0; k < Pkpi[0].length; k++) {
                    sum=sum+Pkpi[i][k]*Atranspoz[k][j];
                }
                Pkpsonuc[i][j]=sum;
                sum=0;
            }
        }
        return Pkpsonuc;
    }
     public static double [][] Kk_Calculation(double [][] Pkp,double [][] Htranspoz,double [][] H,double ΔX,double ΔVx,double sum){
        double [][] R_Matrix={{ΔX*ΔX,0},{0,ΔVx*ΔVx}};
        double [][] Kkb=new double [Pkp.length][Htranspoz[0].length];
        double [][] Kki=new double [Kkb.length][Htranspoz[0].length];
        double [][] Kkü=new double [Kki.length][R_Matrix[0].length];
        double [][] KkSonuc=new double[Kkb.length][Kkü[0].length];
         for (int i = 0; i < Pkp.length; i++) {
             for (int j = 0; j < Htranspoz[0].length; j++) {
                 for (int k = 0; k < Pkp[0].length; k++) {
                     sum=sum+Pkp[i][k]*Htranspoz[k][j];
                 }
                 Kkb [i][j]=sum;
                 sum=0;
             }
         }
         for (int i = 0; i < Kkb.length; i++) {
             for (int j = 0; j < H[0].length; j++) {
                 for (int k = 0; k < Kkb[0].length; k++) {
                     sum=sum+Kkb[i][k]*H[k][j];
                 }
                 Kki[i][j]=sum;
                 sum=0;
             }
         }
        
        for (int i = 0; i < Kki.length; i++) {
            for (int j = 0; j < R_Matrix[0].length; j++) {
                Kkü[i][j]=Kki[i][j]+R_Matrix[i][j];
            }
        }
        /*KkSonuc[0][0]=Kkb[0][0]/Kkü[0][0];
        KkSonuc[1][1]=Kkb[1][1]/Kkü[1][1];
        KkSonuc[1][0]=0;
        KkSonuc[0][1]=0;*/
       for (int i = 0; i < Kkb.length; i++) { 
            for (int j = 0; j < Kkü[0].length; j++) {
                
                KkSonuc[i][j]=Kkb[i][j]/Kkü[i][j];            
            }
        }
        KkSonuc[1][0]=0;
        KkSonuc[0][1]=0;
        return KkSonuc;
        
    }
     public static double [][] Yk_Calculation(double [][] C,ArrayList<Double> X,ArrayList<Double> Vx,int sayaç,double sum){
        double [][] Yk={{X.get(sayaç)},{Vx.get(sayaç)}};
        double [][] YkSonuc=new double[C.length][Yk[0].length];
         for (int i = 0; i < C.length; i++) {
             for (int j = 0; j < Yk[0].length; j++) {
                 for (int k = 0; k < C[0].length; k++) {
                     sum=sum+C[i][k]*Yk[k][j];
                 }
                 YkSonuc[i][j]=sum;
                 sum=0;
             }
         }
         return YkSonuc;
     }
     public static double [][] Xk_Calculation(double [][] Xkp,double [][] Kk,double [][] Yk,double [][] H,double sum){
       
       double [][] Xkb=new double[H.length][Xkp[0].length];
       double [][] Xki=new double[Yk.length][Xkb[0].length];
       double [][] Xkü=new double[Kk.length][Xki[0].length];
       double [][] XkSonuc=new double [Xkp.length][Xkü[0].length];
       
         for (int i = 0; i < H.length; i++) {
             for (int j = 0; j < Xkp[0].length; j++) {
                 for (int k = 0; k < H[0].length; k++) {
                     sum=sum+H[i][k]*Xkp[k][j];
                 }
                 Xkb[i][j]=sum;
                 sum=0;
             }
         }
         for (int i = 0; i < Yk.length; i++) {
             for (int j = 0; j < Xkb[0].length; j++) {
                 Xki[i][j]=Yk[i][j]-Xkb[i][j];
             }
         }
         for (int i = 0; i < Kk.length; i++) {
             for (int j = 0; j < Xki[0].length; j++) {
                 for (int k = 0; k < Kk[0].length; k++) {
                    sum=sum+Kk[i][k]*Xki[k][j];
                 }
                 Xkü[i][j]=sum;
                 sum=0;
             }
         }
         for (int i = 0; i < Xkp.length; i++) {
             for (int j = 0; j < Xkü[0].length; j++) {
                 XkSonuc[i][j]=Xkp[i][j]+Xkü[i][j];
             }
         }
       return XkSonuc; 
    }
     public static double [][] Pk_Calculation(double [][] I,double [][] Kk,double [][] H,double [][] Pkp,double sum){
        
        double [][] Pkb=new double[Kk.length][H[0].length];
        double [][] Pki=new double[I.length][Pkb[0].length];
        double [][] PkSonuc=new double[Pki.length][Pkp[0].length];
         for (int i = 0; i < Kk.length; i++) {
             for (int j = 0; j < H[0].length; j++) {
                 for (int k = 0; k < Kk[0].length; k++) {
                     sum=sum+Kk[i][k]*H[k][j];
                 }
                 Pkb[i][j]=sum;
                 sum=0;
             }
         }
        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j <Pkb[0].length ; j++) {
                Pki[i][j]=I[i][j]-Pkb[i][j];
                
            }
        }
        for (int i = 0; i < Pki.length; i++) {
            for (int j = 0; j < Pkp[0].length; j++) {
                for (int k = 0; k < Pki[0].length; k++) {
                  sum=sum+Pki[i][k]*Pkp[k][j];  
                }
                PkSonuc[i][j]=sum;
                sum=0;
            }
        }
        return PkSonuc;
    }
    public static void main(String[] args) {
        Scanner klavye=new Scanner(System.in);
        double Δt=1;
        double ΔX=25;
        double ΔVx=6;
        double ΔPx=20;
        double ΔPVx=5;
        int sayac=1;
        double Uk=2;
        double sum=0;
        ArrayList <Double> X =new ArrayList<Double>();
        ArrayList <Double> Vx=new ArrayList<Double>();
        double A[][]={{1,Δt},{0,1}};
        double B[][]={{(Δt*Δt)/2},{Δt}};
        double H[][]={{1,0},{0,1}};
        double C[][]={{1,0},{0,1}};
        double I[][]={{1,0},{0,1}};
        
        for (int i = 1; i <= 8; i++) {
            System.out.println(i+". ölçülen mesafe degeri = ");
            X.add(klavye.nextDouble());
        }
        for (int i = 1; i <= 8; i++) {
            System.out.println(i+". ölçülen hız degeri = ");
            Vx.add(klavye.nextDouble());
        }
    //   X= {4000,4260,4550,4860,5110};
        //double Vx[]={280,282,285,286,290};
        
        
        SabitDegerler sabit1=new SabitDegerler(X,Vx,A,H,A, B, I, C, H, Δt, ΔX, ΔVx, ΔPx, ΔPVx);
        KalmanFilterExample nesneKF=new KalmanFilterExample();
        
       nesneKF.Xkp_Calculation(X,Vx,sabit1.getAtranspoz(),sabit1.getHtranspoz(),A,B,I,C,H,X.get(0),Vx.get(0),Δt,ΔX,ΔVx,ΔPx,ΔPVx,Uk,sum,sayac);
    }
    
}
