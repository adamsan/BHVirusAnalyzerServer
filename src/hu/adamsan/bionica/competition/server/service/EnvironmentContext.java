package hu.adamsan.bionica.competition.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import hu.adamsan.bionica.competition.server.dao.CompetitionResultArrayListDao;
import hu.adamsan.bionica.competition.server.dao.DAO;
import hu.adamsan.bionica.competition.server.dao.sqlite.AnswerSqliteDAO;
import hu.adamsan.bionica.competition.server.dao.sqlite.CompetitionResultSQLiteDAO;
import hu.adamsan.bionica.competition.server.dao.sqlite.QuestionSqliteDAO;
import hu.adamsan.bionica.competition.server.model.Answer;
import hu.adamsan.bionica.competition.server.model.CompetitionResult;
import hu.adamsan.bionica.competition.server.model.Question;

public class EnvironmentContext {
    public enum StorageType {
        MEMORY, SQLITE, POSTGRES
    };

    private static StorageType storageType;

    private static Map<Class, DAO> storages = new HashMap<>();

    public static void init() {
        Properties properties = new Properties();
        try (InputStream resourceAsStream = EnvironmentContext.class.getResourceAsStream("dbconfig.properties")) {
            properties.load(resourceAsStream);
            String property = properties.getProperty("database_method", "memory");
            switch (property) {
            case "postgres":
                System.out.println("Using database: Postgres");
                storageType = StorageType.POSTGRES;
                break;
            case "sqlite":
                storageType = StorageType.SQLITE;
                System.out.println("Using database: SQLite");
                storages.put(CompetitionResult.class, CompetitionResultSQLiteDAO.INSTANCE);
                storages.put(Answer.class, AnswerSqliteDAO.INSTANCE);
                storages.put(Question.class, QuestionSqliteDAO.INSTANCE);
                break;
            default:
                System.out.println("Using database: Memory");
                storageType = StorageType.MEMORY;
                storages.put(CompetitionResult.class, CompetitionResultArrayListDao.INSTANCE);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DAO<CompetitionResult> getCompetitionResultDAO() {
        return getDAO(CompetitionResult.class);
    }

    public static <T> DAO<T> getDAO(Class forModel) {
        if (storageType == null) {
            init();
        }
        return storages.get(forModel);
    }

}
