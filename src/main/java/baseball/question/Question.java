package baseball.question;

public interface Question<T> {

    void create();

    void grade(T t);

    boolean check();

}
