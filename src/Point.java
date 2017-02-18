public class Point {
    private int col;// 行号
    private int row;// 列号
    private boolean flag;// 真为未设置。
    private int value;
    // 构造点
    public Point(int col, int row, boolean flag, int value) {
        super();
        this.col = col;
        this.row = row;
        this.flag = flag;
        this.value = value;
    }

    public void changeFlag() {
        flag = !flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean canHere(Point[][] pArr) {
        boolean cb = canCol(pArr);
        boolean cr = canRow(pArr);
        boolean cminiArr = canMiniArr(pArr);
        return cb && cr && cminiArr;
    }
    //判断在小3*3格子里是否有相同元素
    private boolean canMiniArr(Point[][] pArr) {
        int coltemp = this.col % 3;
        int rowtemp = this.row % 3;

        for (int i = this.col - coltemp; i < col + (3 - coltemp); i++) {
            for (int j = this.row - rowtemp; j < row + (3 - rowtemp); j++) {
                if(i == this.col && j == this.row){
                    continue;
                }else{
                    if(this.value == pArr[i][j].getValue()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 判断列上是否有相同元素
    private boolean canRow(Point[][] pArr) {
        for (int i = 0; i < 9; i++) {
            if (i == this.col) {
                continue;
            } else {
                if (this.value == pArr[i][this.row].value) {// 行变，列不变
                    return false;
                }
            }
        }
        return true;
    }

    // 判断行上是否有相同元素
    private boolean canCol(Point[][] pArr) {
        for (int i = 0; i < 9; i++) {
            if (i == this.row) {
                continue;
            } else {
                if (this.value == pArr[this.col][i].value) {// 列边，行不变
                    return false;
                }
            }
        }
        return true;
    }
}
