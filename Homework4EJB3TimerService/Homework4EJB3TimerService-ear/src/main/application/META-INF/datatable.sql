CREATE TABLE EJB__TIMER__TBL (
        `CREATIONTIMERAW`      BIGINT        NOT NULL,
        `BLOB`                 BLOB,
        `TIMERID`              VARCHAR(255)  NOT NULL,
        `CONTAINERID`          BIGINT        NOT NULL,
        `OWNERID`              VARCHAR(255)  NULL,
        `STATE`                INTEGER       NOT NULL,
        `PKHASHCODE`           INTEGER       NOT NULL,
        `INTERVALDURATION`     BIGINT        NOT NULL,
        `INITIALEXPIRATIONRAW` BIGINT        NOT NULL,
        `LASTEXPIRATIONRAW`    BIGINT        NOT NULL,
        `SCHEDULE`             VARCHAR(255)  NULL,
        `APPLICATIONID`        BIGINT        NOT NULL,
        CONSTRAINT `PK_EJB__TIMER__TBL` PRIMARY KEY (`TIMERID`)
);