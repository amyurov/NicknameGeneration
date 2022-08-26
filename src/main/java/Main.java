import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        NameCheck nameCheck = new NameCheck();
        new Thread(null, () -> nameCheck.countOneLetter(texts), "Поток-1").start();
        new Thread(null, () -> nameCheck.countPalindroms(texts), "Поток-2").start();
        new Thread(null, () -> nameCheck.countAlphabetOrder(texts), "Поток-3").start();

        System.out.println("Красивых слов длины 3: " + NameCheck.beautyNames3Count);
        System.out.println("Красивых слов длины 4: " + NameCheck.beautyNames4Count);
        System.out.println("Красивых слов длины 5: " + NameCheck.beautyNames5Count);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

}
