package Less6OutPut;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.*;

public class AppData implements Serializable {
    String[] header;
    int[][] data;

    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
    }

    public static void main(String[] args) {
        String[] header = new String[]{"Value 1", "Value 2", "Value 3"};
        int[][] data = new int[][]{{100, 200, 123}, {300, 400, 500}};
        AppData appData = new AppData(header, data);
        //сериализация
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test.csv"))) {
            objectOutputStream.writeObject(appData);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //десериализация
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test.csv"))) {
            AppData appDataResulted = (AppData) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(appDataResulted);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();

        }

    }
}

/*
Java Core для тестировщиков
Урок 5. Средства ввода-вывода
 Билалов Ильдар тестер Гикбреинс

1. Реализовать сохранение данных в csv файл;
2. Реализовать загрузку данных из csv файла. Файл читается целиком.
Структура csv файла:
| Строка заголовок с набором столбцов |
| Набор строк с целочисленными значениями |
| * Разделитель между столбцами - символ точка с запятой (;) |

Пример:
Value 1;Value 2;Value 3
100;200;123
300;400;500
Для хранения данных использовать класс вида:
public class AppData {
  private String[] header;
  private int[][] data;

 // ...
}
Если выполняется save(AppData data), то старые данные в файле полностью перезаписываются.
 */