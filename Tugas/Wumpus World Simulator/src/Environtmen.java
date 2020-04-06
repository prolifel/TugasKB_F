import java.util.Scanner;

public class Environtmen {
    Scanner scan =new Scanner(System.in);

    int jumlah_lubang; // nomer dari pits
    int pos_wumpus, gold_post;  // posisi Wumpus dan Gold
    int pos[];  // variabel temp buat simpan posisi pits
    int b_pos[]= new int[20];   // Variabel buat simpan posisi Breeze
    int s_pos[] = new int[20];  // Variabel buat simpan posisi Stench

    // Fungsi untuk User Interface input Pit, Wumpus, Gold
    void accept(String w[][]){
        for (int i = 0; i < 20; i++) {
            b_pos[i] = -1;
            s_pos[i] = -1;
        }

        // board 4x4
        for(int i = 0; i < 5 ; ++i){
            for (int j = 0; j < 5; j++) {
                w[i][j]="";     // W = world
            }
        }

        int count = 1;
        System.out.println("\n\n ~~~~~~~~~~~ Wumpus World Problem ~~~~~~~~~~~~\n\n");

        System.out.println("Posisi Sebagai Berikut");

        //memebuat peta
        for (int i = 1; i <= 4; i++) {
            System.out.println("\n-----------------------------------------------------------------");
            System.out.print("|\t"); // \t = tab

            for (int j = 1; j <= 4; j++) {
                System.out.print((count++)+"\t|\t");
            }
        }

        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("\nAgent Mulai dari 13 (1,1)");
        w[4][1]="A";
        System.out.println("Masukkan banyak lubang");
        jumlah_lubang = scan.nextInt();
        pos = new int[jumlah_lubang];
        System.out.println("\nMasukkan Posisi dari pit." +
                "\nDengan syarat sebagai berikut:" +
                "\n1. Posisi pit tidak boleh sama dengan agent" +
                "\n2. Posisi pit tidak boleh sama dengan Wumpus" +
                "\n3. Posisi pit tidak boleh sama dengan gold");
        System.out.println("\nMasukkan posisi pit");
        for (int i = 0; i < jumlah_lubang; i++) {
            pos[i] = scan.nextInt(); // insert posisi pits ke board
            show_sense(pos[i],1,w);
        }

        //get Wumpus position
        System.out.println("\nMasukkan posisi dari Wumpus");
        pos_wumpus = scan.nextInt();
        show_sense(pos_wumpus,2,w);

        //get gold position
        System.out.println("\nMasukkan posisi Gold");
        gold_post = scan.nextInt();

        // Insert Pit, Gold, Wumpus
        insert(w);
        
    }

    // Fungsi untuk memposisikan input ke dalam area(environment)
    void insert(String w[][]){
        int temp = 0;
        int count = 0;
        int flag1 = 0, flag2=0;

        for (int i = 0; i < jumlah_lubang; i++) {
            temp=pos[i];
            count = 0;
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    ++count;
                    if (count == temp){
                        w[j][k]+="P";       // P for Pit
                    }
                    else if (count == gold_post && flag1 ==0){
                        w[j][k]+="G";       // G for Gold
                        flag1= 1 ;
                    }
                    else if (count == pos_wumpus && flag2 == 0){
                        w[j][k]+="W";       // W for Wumpus
                        flag2 = 1;
                    }
                }
            }
        }

        display(w);
    }

    // Fungsi untuk insert Breeze dan Stench
    void show_sense(int a, int b, String w[][]){
        int t1,t2,t3,t4;
        t1=a-1; // kirinya kotak
        t2=a+1; // kanannya kotak
        t3=a+4; // bawahnya kotak
        t4=a-4; // atasnya kotak

        if(a==5 || a==9)    // kalau misal koordinat di batas kiri (5 dan 9), kirinya gak dianggap (0)
            t1=0;
        if (a==8|| a==12)   // kalau misal koordinat di batas kanan (8 dan 12), kanannya gak dianggap (0)
            t2=0;
        if (a==4)           // kalau misal koordinat di batas kanan (4), kanannya gak dianggap (0)
            t2 =0;
        if (a==13)          // koordinat 13 itu agent, kirinya agent itu 0
            t1=0;

        // error handling kalau table diluar batas
        if(t3>16)
            t3=0;
        if(t4<0)
            t4=0;

        // b = breeze
        // adjacent nya pit itu breeze
        if(b==1){
            b_pos[0] = t1;
            b_pos[1] = t2;
            b_pos[2] = t3;
            b_pos[3] = t4;
        }
        else if (b==2){         // s = stench
            s_pos[0] = t1;      // adjacent nya wumpus itu stench
            s_pos[1] = t2;
            s_pos[2] = t3;
            s_pos[3] = t4;
        }

        int temp1,count;

        for (int i = 0; i < 4; i++) {
            if (b==1) temp1 = b_pos[i];
            else temp1 = s_pos[i];

            count=0;
            
            // Iterasi buat ngasih tanda stench dan breeze
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    ++count;
                    if (count==temp1 && b==1 && !w[j][k].contains("B")){
                        w[j][k]+="B";
                    }
                    else if (count==temp1 && b==2 && !w[j][k].contains("s")){
                        w[j][k]+="s";
                    }
                }
            }
        }

    }

    // Fungsi untuk menampilkan World
    void display(String w[][]){
        System.out.println("\nGambaran dari area (world) yang di buat\n");
        for (int i = 1; i <= 4; i++) {
            System.out.println("\n-----------------------------------------------------------------");
            System.out.print("|\t");
            for (int j = 1; j <= 4; j++) {
                System.out.print(w[i][j]+ "\t|\t");
            }
        }

        System.out.println("\n-----------------------------------------------------------------");
    }

}
