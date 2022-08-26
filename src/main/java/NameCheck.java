import java.util.concurrent.atomic.AtomicInteger;

public class NameCheck {
    // Статические поля для хранения кол-ва красивых слов из 3,4,5 -ти символов
    static AtomicInteger beautyNames3Count = new AtomicInteger(0);
    static AtomicInteger beautyNames4Count = new AtomicInteger(0);
    static AtomicInteger beautyNames5Count = new AtomicInteger(0);

    // Метод инкрементит переменную в стат.поле (далее счетчик) соответствующему длине слова
    public void incrementCount(String name) {
        switch (name.length()) {
            case 3:
                beautyNames3Count.getAndIncrement();
                break;
            case 4:
                beautyNames4Count.getAndIncrement();
                break;
            case 5:
                beautyNames5Count.getAndIncrement();
                break;
            default:
                break;
        }
    }

    // методы возвращают булево значение красивое слово или нет
    public boolean isOneLetter(String name) {
        char firstChar = name.charAt(0);
        return name.chars().allMatch(c -> c == firstChar);
    }

    public boolean isPalindrome(String name) {
        return name.equals(new StringBuffer(name).reverse().toString());
    }

    public boolean isAlphabetOrder(String name) {
        char[] nameChars = name.toCharArray();
        for (int i = 1; i < nameChars.length; i++) {
            if ((nameChars[i - 1] > nameChars[i])) {
                return false;
            }
        }
        return true;
    }

    // Методы обращаются к счетчику если слово красивое
    public void countOneLetter(String[] names) {
        for (String name : names) {
            if (isOneLetter(name)) {
                incrementCount(name);
            }
        }
    }

    // В этих методах добавлено доп. условие !isOneLetter(name). Т.к. слова из одной буквы являются и палиндромами и словами
    // с буквами в алфавитном порядке (в моем решении), то их нужно исключить.
    public void countPalindroms(String[] names) {
        for (String name : names) {
            if (isPalindrome(name) && !isOneLetter(name)) {
                incrementCount(name);
            }
        }
    }

    public void countAlphabetOrder(String[] names) {
        for (String name : names) {
            if (isAlphabetOrder(name) && !isOneLetter(name)) {
                incrementCount(name);
            }
        }
    }
}
