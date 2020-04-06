import java.util.Scanner;

import static java.lang.System.identityHashCode;
import static java.lang.System.out;

public class Wumpus {
    static int scream = 0;
    static int score = 0;
    static int complete = 0;

    // Fungsi untuk cek tilesnya ada breeze atau stench
    static boolean check(Tiles t){
        int temp=t.sense();
        if (temp==1 || temp == 2) return false;     // temp 1 = breeze | temp 2 = stench

        return true;
    }


    //main program
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Environtmen envi = new Environtmen();
        String w[][] = new String[5][5];
        envi.accept(w);
        out.println("\n\nMencari Solusi\n");

        // Buat board, terus ditandai bisa jalan kemana aja
        Tiles tiles[]=new Tiles[17];
        int c=1;
        out:
        for (int i = 1; i <=4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (c > 16) break out;

                tiles[c] = new Tiles(w[i][j], c);
                ++c;
            }
        }
        
        // Posisi 13 sudah dikunjungi oleh Agent
        // Sehingga aman dan visited
        tiles[13].safe=1;
        tiles[13].visited=1;

        int pos=13;     // Posisi awal agent
        int condition;
        int limit=0;
        String temp1,temp2;

        do {
            ++limit;
            condition=-1;
            
            // Kalau di posisi agent ada gold
            // , game selesai
            if (tiles[pos].env.contains("G")){
                complete=1;     // Flag game selesai
                System.out.println("Gold ditemukan!!\n");
                break;
            }
            
            // Move ke kanan
            if (tiles[pos].br != 1 && tiles[pos].r != 1 && tiles[pos+1].doubt_pit<1 && tiles[pos+1].doubt_wump<1 &&
                    tiles[pos+1].pit!=1 && tiles[pos+1].wump!=1 && !(tiles[pos].back.contains("r") && (tiles[pos].l!=1
                    || tiles[pos].u!=1 || tiles[pos].d!=1) && check(tiles[pos]))){
                
                // Flag kiri
                temp1 = "l";

                tiles[pos].r=1;     // Tandai di posisi tersebut kalau sudah ke kanan
                ++pos;  // pos = 14
                System.out.println("\nMaju (Kanan) ke posisi = " + pos);
                ++score;

                //tiles[pos].visited=1;
                tiles[pos].back+=temp1;     // pos[14] sebelumnya adalah left

                condition=tiles[pos].sense();   // Cek isi pos[14]
                if (condition==3){  // Kalau isinya Gold,
                    complete=1;     // Game selesai
                    break;
                }
                else if (condition==1 && tiles[pos].visited==0){    // Kalau isinya Breeze dan pos sekarang belum dikunjungi
                    if (tiles[pos].br!=1 && tiles[pos+1].safe!=1)   // Kalau bisa ke kanan dan tiles kanan nya gak aman
                        tiles[pos+1].doubt_pit+=1;      // , meragukan ada pit++
                    if (tiles[pos].bu!=1 && (pos-4)>=1 && tiles[pos-4].safe!=1)     // Cek bisa ke atas dan atas gak aman, dan error handling kalau atasnya diluar World
                         tiles[pos-4].doubt_pit+=1;     // , meragukan ada pit++
                    if (tiles[pos].bl!=1 && tiles[pos-1].safe!=1 )  // Cek kiri
                         tiles[pos-1].doubt_pit+=1;     // , meragukan ada pit++
                    if (tiles[pos].bd!=1 && (pos+4)<=16 && tiles[pos+4].safe!=1)    // Cek bawah
                        tiles[pos+4].doubt_pit+=1;      // , meragukan ada pit++
                    
                    // pos sekarang aman.
                    tiles[pos].safe=1;
                }
                else if (condition==2 && tiles[pos].visited==0){    // Kalau isinya Stench dan posisi sekarang belum dikunjungi
                    // Cek adjacent nya apakah bisa dan aman untuk dikunjungi
                    // Kalau bisa dan gak aman, meragukan ada Wumpus++
                    if(tiles[pos].br!=1 && tiles[pos+1].safe!=1)
                        tiles[pos+1].doubt_wump+=1;
                    if(tiles[pos].bu!=1 && (pos-4)>=1 && tiles[pos-4].safe!=1)
                        tiles[pos-4].doubt_wump+=1;
                    if(tiles[pos].bl!=1 && tiles[pos-1].safe!=1)
                        tiles[pos-1].doubt_wump+=1;
                    if(tiles[pos].bd!=1 && (pos+4)<=16 &&  tiles[pos+4].safe!=1)
                        tiles[pos+4].doubt_wump+=1;
                    
                    // pos sekarang aman.
                    tiles[pos].safe=1;
                }
                else if (condition==0) tiles[pos].safe=1;   // Kalau isinya kosong, pos sekarang aman

                tiles[pos].visited=1;   // Pos sekarang telah dikunjungi
            }

            // Move ke kiri
            else if (tiles[pos].bl!=1 && tiles[pos].l!=1 && tiles[pos-1].doubt_pit<1 && tiles[pos-1].doubt_wump<1 &&
                    tiles[pos-1].pit!=1 && tiles[pos-1].wump!=1 && !(tiles[pos].back.contains("l") &&
                    (tiles[pos].r!=1 || tiles[pos].u!=1 || tiles[pos].d!=1) && check(tiles[pos]))){

                temp1="r";

                tiles[pos].l=1;
                pos=pos-1;
                System.out.println("\nMundur (Kiri) ke posisi= "+pos);
                ++score;

                ////
                tiles[pos].back+=temp1;
                ////
                condition=tiles[pos].sense();
                if(condition==3){
                    complete=1;
                    break;
                }
                else if (condition  == 1 && tiles[pos].visited==0) {
                    if(tiles[pos].br!=1 && tiles[pos+1].safe!=1)
                        tiles[pos+1].doubt_pit+=1;
                    if(tiles[pos].bu!=1 && (pos-4)>=1 &&  tiles[pos-4].safe!=1)
                        tiles[pos-4].doubt_pit+=1;
                    if(tiles[pos].bl!=1 && tiles[pos-1].safe!=1)
                        tiles[pos-1].doubt_pit+=1;
                    if(tiles[pos].bd!=1 && (pos+4)<=16 &&  tiles[pos+4].safe!=1)
                        tiles[pos+4].doubt_pit+=1;

                    tiles[pos].safe=1;
                }
                else if (condition==2 && tiles[pos].visited==0){
                    if(tiles[pos].br!=1 && tiles[pos+1].safe!=1)
                        tiles[pos+1].doubt_wump+=1;
                    if(tiles[pos].bu!=1 && (pos-4)>=1 &&  tiles[pos-4].safe!=1)
                        tiles[pos-4].doubt_wump+=1;
                    if(tiles[pos].bl!=1 && tiles[pos-1].safe!=1)
                        tiles[pos-1].doubt_wump+=1;
                    if(tiles[pos].bd!=1 && (pos+4)<=16 && tiles[pos+4].safe!=1)
                        tiles[pos+4].doubt_wump+=1;

                    tiles[pos].safe=1;
                }
                else if (condition==0) tiles[pos].safe=1;

                tiles[pos].visited=1;
            }

            // Move ke atas
            else if (tiles[pos].bu!=1 && tiles[pos].u!=1 && (pos-4)>=1 && tiles[pos-4].doubt_pit<1 &&
                    tiles[pos-4].doubt_wump<1 && tiles[pos-4].pit!=1 && tiles[pos-1].wump!=1 &&
                    !(tiles[pos].back.contains("u") && (tiles[pos].l!=1 || tiles[pos].r!=1 || tiles[pos].d!=1)
                    && check(tiles[pos]))){

                temp1="d";

                tiles[pos].u=1;
                pos=pos-4;
                System.out.println("\nNaik ke posisi = "+pos);
                ++score;

                ///
                tiles[pos].back+=temp1;
                ///
                condition=tiles[pos].sense();
                if(condition==3){
                    complete=1;
                    break;
                }
                else if(condition==1 && tiles[pos].visited==0){
                    if(tiles[pos].br!=1 && tiles[pos+1].safe!=1)
                        tiles[pos+1].doubt_pit+=1;
                    if(tiles[pos].bu!=1 && (pos-4)>=1 && tiles[pos-4].safe!=1)
                        tiles[pos-4].doubt_pit+=1;
                    if(tiles[pos].bl!=1 && tiles[pos-1].safe!=1)
                        tiles[pos-1].doubt_pit+=1;
                    if(tiles[pos].bd!=1 && (pos+4)<=16 && tiles[pos+4].safe!=1)
                        tiles[pos+4].doubt_pit+=1;


                    tiles[pos].safe=1;
                }
                else if(condition==2 && tiles[pos].visited==0)
                {
                    if(tiles[pos].br!=1 && tiles[pos+1].safe!=1)
                        tiles[pos+1].doubt_wump+=1;
                    if(tiles[pos].bu!=1 && (pos-4)>=1 &&  tiles[pos-4].safe!=1)
                        tiles[pos-4].doubt_wump+=1;
                    if(tiles[pos].bl!=1 && tiles[pos-1].safe!=1)
                        tiles[pos-1].doubt_wump+=1;
                    if(tiles[pos].bd!=1 && (pos+4)<=16 &&  tiles[pos+4].safe!=1)
                        tiles[pos+4].doubt_wump+=1;

                    tiles[pos].safe=1;
                }
                else if(condition==0)
                    tiles[pos].safe=1;

                tiles[pos].visited=1;
            }
            
            // Move ke bawah
            else if(tiles[pos].bd!=1 && tiles[pos].d!=1 && (pos+4)<=16 &&  tiles[pos+4].doubt_pit<1
                    && tiles[pos+4].doubt_wump<1 && tiles[pos+4].pit!=1 && tiles[pos+4].wump!=1){

                temp1 = "u";

                tiles[pos].d=1;
                pos=pos+4;
                System.out.println("\nTurun ke posisi = "+pos);
                ++score;

                ///
                tiles[pos].back+=temp1;
                ///
                condition=tiles[pos].sense();
                if (condition==3){
                    complete=1;
                    break;
                }
                else if (condition==1 && tiles[pos].visited==0){
                    if(tiles[pos].br!=1 && tiles[pos+1].safe!=1)
                        tiles[pos+1].doubt_pit+=1;
                    if(tiles[pos].bu!=1 && (pos-4)>=1 && tiles[pos-4].safe!=1)
                        tiles[pos-4].doubt_pit+=1;
                    if(tiles[pos].bl!=1 && tiles[pos-1].safe!=1)
                        tiles[pos-1].doubt_pit+=1;
                    if(tiles[pos].bd!=1 && (pos+4)<=16 && tiles[pos+4].safe!=1)
                        tiles[pos+4].doubt_pit+=1;

                    tiles[pos].safe=1;
                }
                else if (condition==2 && tiles[pos].visited==0){
                    if(tiles[pos].br!=1 && tiles[pos+1].safe!=1)
                        tiles[pos+1].doubt_wump+=1;
                    if(tiles[pos].bu!=1 && (pos-4)>=1 && tiles[pos-4].safe!=1)
                        tiles[pos-4].doubt_wump+=1;
                    if(tiles[pos].bl!=1 && tiles[pos-1].safe!=1)
                        tiles[pos-1].doubt_wump+=1;
                    if(tiles[pos].bd!=1 && (pos+4)<=16 && tiles[pos+4].safe!=1)
                        tiles[pos+4].doubt_wump+=1;

                    tiles[pos].safe=1;
                }
                else if (condition ==0)
                    tiles[pos].safe=1;

                tiles[pos].visited=1;
            }
            else if (limit>50){
                int temp3=pos;
                int flag_1=0,flag2=0,flag3=0,flag4=0;

                System.out.println("Berada pada posisi "+temp3+ ".\nThinking...");

                while (tiles[pos].visited==1 && tiles[pos].br!=1){
                    ++pos;
                    ++score;
                }

                if(tiles[pos].pit==1 || tiles[pos].wump==1 || (tiles[pos].br==1 && tiles[pos].visited==1 && tiles[pos].safe!=1))
                {
                    //System.out.println("\nUnsuccessful at pos "+pos);
                    pos=temp3;
                    //System.out.println("\nBack at pos "+pos);
                    flag_1=1;
                }

                if (flag_1==0)
                    tiles[pos].back+="l";

                while (pos+4>=1 && tiles[pos].bu!=1 && tiles[pos].visited==1){
                    pos-=4;
                    ++score;
                }

                if(tiles[pos].pit==1 || tiles[pos].wump==1 || (tiles[pos].bu==1 && tiles[pos].visited==1  && tiles[pos].safe!=1))
                {
                    //System.out.println("\nUnsuccessful at pos "+pos);
                    pos=temp3;
                    //System.out.println("\nBack at pos "+pos);
                    flag3=1;
                }


                if (flag3==0)
                    tiles[pos].back+="d";

                while(tiles[pos].visited==1 && tiles[pos].bl!=1)
                {
                    --pos;
                    ++score;
                }

                if(tiles[pos].pit==1 || tiles[pos].wump==1 || (tiles[pos].bl==1 && tiles[pos].visited==1
                        && tiles[pos].safe!=1))
                {
                    //System.out.println("\nUnsuccessful at pos "+pos);
                    pos=temp3;
                    //System.out.println("\nBack at pos "+pos);
                    flag2=1;
                }

                if(flag2==0)
                    tiles[pos].back+="r";

                //if(!(t[pos].back.contains("d") && (t[pos].l!=1 || t[pos].r!=1 || t[pos].u!=1) && check(t[pos])  ))
                while(pos+4<=16 && tiles[pos].bd!=1 && tiles[pos].visited==1)
                {
                    pos+=4;
                    ++score;
                }

                if(tiles[pos].pit==1 || tiles[pos].wump==1 || (tiles[pos].bd==1 && tiles[pos].visited==1 &&
                        tiles[pos].safe!=1))
                {
                    //System.out.println("\nUnsuccessful at pos "+pos);
                    pos=temp3;
                    //System.out.println("\nBack at pos "+pos);
                    flag4=1;
                }


                if (flag4==0)
                    tiles[pos].back+="u";

                tiles[pos].safe=1;
                tiles[pos].visited=1;
                System.out.println("reached at position "+pos);
                limit=0;
            }
            if(tiles[pos].env.contains("W") && scream!=1) {
                score += 100;
                scream = 1;
                tiles[pos].safe = 1;
                System.out.println("\n\nWumpus killed >--0-->");
                tiles[pos].env.replace("W", " ");

                for (int l = 1; l <= 16; l++) {
                    tiles[l].doubt_wump = 0;
                    tiles[l].env.replace("S", " ");
                }
            }

            if(tiles[pos].env.contains("P")) {
                score+=50;
                tiles[pos].pit=1;
                System.out.println("\n\nFallen in pit of position "+pos+".");
            }

            for(int k=1;k<=16;++k) {
                if(tiles[k].doubt_pit==1 && tiles[k].doubt_wump==1){
                    tiles[k].doubt_pit=0;
                    tiles[k].doubt_wump=0;
                    tiles[k].safe=1;
                }
            }
            for(int y=1;y<=16;++y) {
                if(tiles[y].doubt_wump>1){
                    tiles[y].wump=1;
                    for(int h=1;h<=16;++h){
                        if(h!=y) {
                            tiles[h].doubt_wump=0;
                            tiles[h].env.replace("S"," ");
                        }
                    }

                }

            }

            for(int y=1;y<=16;++y){
                if(tiles[y].doubt_pit>1) {
                    tiles[y].pit=1;
                    //System.out.println("\nPit confirmed at position "+y);
                }
            }

            try{Thread.sleep(200);}catch(Exception p){}


        }
        while(complete==0);
        if (complete==1){
            score*=-1;
            score+=1000;
        }
        System.out.println("The score of the agent till he reaches gold is "+score+".\nNow he will return back following the best explored path.");
    }
}
