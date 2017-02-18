import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Number99 {

    public static void main(String[] args) throws IOException{
        Point[][] numMat = new Point[9][9];
        ArrayList<Point> al = new ArrayList<Point>();

        initNumMat(numMat,al);

        setNum(numMat,al);
        printMat(numMat);
    }

    private static void setNum(Point[][] numMat,ArrayList<Point> al) {
        int i = 0;
        int j = 0;
        do{
            if (numMat[i][j].getFlag()) {
                for (int v = numMat[i][j].getValue()+1; v <= 9; v++) {//给回退到的位置的值加一。
                    numMat[i][j].setValue(v);
                    if (numMat[i][j].canHere(numMat)) {//满足条件，不冲突。
                        numMat[i][j].changeFlag();//改变标记为假。表示已设置过。
                        break;
                    }else{//满足不条件，冲突。value值自加一次
                    }

                    while(v == 9){//如果1-9都不能满足要求，则先将本位重置为0，并回退一格,给回退到的位置的值加一（当回退位置的值不为9时,并且保证回退到的位置不是九宫格原本的点）。
                        numMat[i][j].setValue(0);
                        j--;
                        if(j==-1){
                            i--;j=8;
                        }
                        while(al.contains(numMat[i][j])){//如果回退到的位置为九宫格本来的点时，继续回退，直到不是本身的点时跳出while。
                            j--;
                            if(j==-1){
                                i--;j=8;
                            }
                        }
                        numMat[i][j].changeFlag();//将标记
                        v = numMat[i][j].getValue();
                    }
                }
            }
            j++;
            if(j==9){
                j=0;i++;//此处i++ 可能使i自加为9，故下面需要i!=9判断
            }
            if(i!=9){
                while(al.contains(numMat[i][j])){
                    j++;
                    if(j==9){
                        j=0;i++;
                    }
                }
            }
        }while(i!=9);

    }

    public static void initNumMat(Point[][] numMat,ArrayList<Point> al) throws IOException {
        for (int i = 0; i < numMat.length; i++) {
            for (int j = 0; j < numMat[i].length; j++) {
                numMat[i][j] = new Point(i, j, true, 0);
            }
        }
        initNumMat2(numMat, al);

    }

    public static void initNumMat2(Point[][] numMat, ArrayList<Point> al) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] p = new String[3];
        String line=null;
        System.out.println("请按格式输入点信息（i行号, j列号 v值）,输入结束输入over: i j v ");

        while((line = br.readLine())!=null){
            if(line.equals("over"))
                break;
            p = line.trim().split(" +");
            numMat[Integer.parseInt(p[0])][Integer.parseInt(p[1])].setValue(Integer.parseInt(p[2]));
            numMat[Integer.parseInt(p[0])][Integer.parseInt(p[1])].changeFlag();
            al.add(numMat[Integer.parseInt(p[0])][Integer.parseInt(p[1])]);
        }
    }

    public static void printMat(Point[][] numMat) {
        System.out.println("--------┬---------┬---------┐");

        for (int i = 0; i < numMat.length; i++) {
            for (int j = 0; j < numMat[i].length; j++) {
                if ((j + 1) % 3 == 0)
                    System.out.print(numMat[i][j].getValue() + " | ");
                else
                    System.out.print(numMat[i][j].getValue() + "  ");
            }
            if ((i + 1) % 3 == 0)
                System.out.println("\r\n--------┼---------┼---------┤");
            else
                System.out.println();
        }
    }

}

/**
0 0 8
1 2 3
1 3 6
2 1 7
2 4 9
2 6 2
3 1 5
3 5 7
4 4 4
4 5 5
4 6 7
5 3 1
5 7 3
6 2 1
6 7 6
6 8 8
7 2 8
7 3 5
7 7 1
8 1 9
8 6 4
*/
