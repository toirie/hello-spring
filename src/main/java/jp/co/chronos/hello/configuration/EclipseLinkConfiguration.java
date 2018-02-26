package jp.co.chronos.hello.configuration;

public enum EclipseLinkConfiguration {
    TEMPORAL_MUTABLE("eclipselink.temporal.mutable"),
    CACHE_SHARED_DEFAULT("eclipselink.cache.shared.default"),
    CACHE_USAGE("eclipselink.cache-usage"),
    TARGET_DATABASE("eclipselink.target-database"),
    WEAVING("eclipselink.weaving"),
    LOGGING_LEVEL("eclipselink.logging.level"),
    JDBC_FETCH_SIZE("eclipselink.jdbc.fetch-size");
    private String id;

    private EclipseLinkConfiguration(String id) {
        this.id = id;
    }

    public String getId() {
        return id;

    }
}
