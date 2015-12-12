package gl8080.lifegame.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 二重ループをラムダで実行できるようにするためのクラス。
 */
public class NestedLoop {
    
    /**
     * 指定したサイズの二乗回分の二重ループを実行し、サプライヤが返した値を二重 {@code List} にまとめて返す。
     * @param size サイズ
     * @param supplier 外側のループで繰り返し呼び出される処理
     * @return サプライヤが提供した値を持った二重の {@code List}
     */
    public static <T> List<List<T>> collectList(int size, BiIntSupplier<T> supplier) {
        List<List<T>> matrix = new ArrayList<>();
        
        for (int i=0; i<size; i++) {
            List<T> row = new ArrayList<>();
            for (int j=0; j<size; j++) {
                row.add(supplier.get(i, j));
            }
            matrix.add(row);
        }
        return matrix;
    }
}
