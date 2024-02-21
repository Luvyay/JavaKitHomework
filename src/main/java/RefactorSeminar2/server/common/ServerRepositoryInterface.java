package RefactorSeminar2.server.common;

public interface ServerRepositoryInterface {
    void saveInLog(String text);
    String readLog();
}
