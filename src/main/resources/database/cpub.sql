CREATE USER IF NOT EXISTS SA SALT '914a3cac4f269bd6' HASH '91662c7fa0ffb0d6d56b863d2d11e6505fff5ce63e2de8d77faacc10f8b88300' ADMIN;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_DFB296AA_C6FA_46CC_942D_61ED76688D36 START WITH 1396976357577 BELONGS_TO_TABLE;
CREATE CACHED TABLE PUBLIC.COURT_CASE_PLAN(
    ID VARCHAR(32) NOT NULL,
    CASE_ID VARCHAR(32) DEFAULT NULL,
    JUDGE_ID VARCHAR(32) DEFAULT NULL,
    JUDGE_NAME VARCHAR(64) DEFAULT NULL,
    CLERK_ID VARCHAR(32) DEFAULT NULL,
    CLERK_NAME VARCHAR(64) DEFAULT NULL,
    COURT_ID VARCHAR(32) DEFAULT NULL,
    COURT_ROOM_ID VARCHAR(32) DEFAULT NULL,
    COURT_ROOM_NAME VARCHAR(64) DEFAULT NULL,
    PLAN_START_TIME DATETIME DEFAULT NULL,
    PLAN_END_TIME DATETIME DEFAULT NULL,
    START_TIME DATETIME DEFAULT NULL,
    REST_TIME DATETIME DEFAULT NULL,
    END_TIME DATETIME DEFAULT NULL,
    ARCH_TIME DATETIME DEFAULT NULL,
    TRAIL_STATUS VARCHAR(16) DEFAULT NULL,
    ACCESS_URL VARCHAR(255) DEFAULT NULL,
    PUB_ACCESS_URL VARCHAR(255) DEFAULT NULL,
    VOD_ACCESS_URL VARCHAR(255) DEFAULT NULL,
    VOD_FILES TEXT,
    ALLOW_LIVE_FLAG TINYINT DEFAULT '0',
    COURT_REC VARCHAR(255) DEFAULT NULL,
    BURST_STATUS TINYINT DEFAULT '0',
    DEL_FLAG BIT DEFAULT NULL,
    CREATE_USER VARCHAR(32) DEFAULT NULL,
    CREATE_USER_ID VARCHAR(32) DEFAULT NULL,
    CREATE_TIME DATETIME DEFAULT NULL,
    UPDATE_USER VARCHAR(32) DEFAULT NULL,
    UPDATE_USER_ID VARCHAR(32) DEFAULT NULL,
    UPDATE_TIME DATETIME DEFAULT NULL,
    ROOT_ORG_ID VARCHAR(32) DEFAULT NULL,
    ORG_ID VARCHAR(32) DEFAULT NULL,
    OPT_LOCK INT DEFAULT NULL
);
ALTER TABLE PUBLIC.COURT_CASE_PLAN ADD CONSTRAINT PUBLIC.CONSTRAINT_3 PRIMARY KEY(ID);

CREATE CACHED TABLE PUBLIC.COURT_LIVE_VOD(
    ID VARCHAR(32) NOT NULL,
    CASE_NUM VARCHAR(64),
    PARENT_COURT_ID VARCHAR(64),
    COURT_ID VARCHAR(64),
    COURT_NAME VARCHAR(32),
    COURT_ROOM_ID VARCHAR(32),
    COURT_ROOM_NAME VARCHAR(32),
    JUDGE_ID VARCHAR(32),
    JUDGE_NAME VARCHAR(32),
    CASE_TYPE_ID VARCHAR(32),
    CASE_TYPE_NAME VARCHAR(64),
    CASE_NAME VARCHAR(255),
    CASE_DESC TEXT,
    ACCUSER VARCHAR(255),
    ACCUSER_LAWER VARCHAR(255),
    ACCUSED VARCHAR(255),
    ACCUSERD_LAWER VARCHAR(255),
    REGISTER_TIME DATETIME,
    PARTY TEXT,
    LIVE_URL VARCHAR(255),
    VOD_URL VARCHAR(255),
    VOD_FILE_PATH VARCHAR(255),
    HEAR_TIME DATETIME,
    TRAIL_STATUS VARCHAR(32),
    DEL_FLAG TINYINT,
    CREATE_USER VARCHAR(32),
    CREATE_USER_ID VARCHAR(32),
    CREATE_TIME DATETIME,
    UPDATE_USER VARCHAR(32),
    UPDATE_USER_ID VARCHAR(32),
    UPDATE_TIME DATETIME,
    ROOT_ORG_ID VARCHAR(32)
);
ALTER TABLE PUBLIC.COURT_LIVE_VOD ADD CONSTRAINT PUBLIC.CONSTRAINT_A7 PRIMARY KEY(ID);

CREATE CACHED TABLE PUBLIC.TP_ORG(
    ID VARCHAR(32) NOT NULL,
    ROOT_ID VARCHAR(32),
    PARENT_ID VARCHAR(32),
    ORG_NAME VARCHAR(64) NOT NULL,
    ORG_BRIEF_NAME VARCHAR(32),
    ORG_SEQ_NO VARCHAR(32),
    SELF_FLAG BIT,
    ROOT_EQ_FLAG BIT,
    CASCADE_ORG_ID VARCHAR(32),
    IDENTIFY_CODE VARCHAR(16),
    TREE_TYPE VARCHAR(16),
    PROVINCE VARCHAR(32),
    CITY VARCHAR(32),
    ADDRESS VARCHAR(128),
    POST_CODE VARCHAR(16),
    DIST_NUM VARCHAR(16),
    PHONE VARCHAR(16),
    DYN_INFO BLOB,
    DEL_FLAG BIT,
    CREATE_USER VARCHAR(32),
    CREATE_USER_ID VARCHAR(32),
    CREATE_TIME DATETIME,
    UPDATE_USER VARCHAR(32),
    UPDATE_USER_ID VARCHAR(32),
    UPDATE_TIME DATETIME,
    ROOT_ORG_ID VARCHAR(32),
    ORG_ID VARCHAR(32),
    OPT_LOCK INT
);
ALTER TABLE PUBLIC.TP_ORG ADD CONSTRAINT PUBLIC.CONSTRAINT_9 PRIMARY KEY(ID);

CREATE CACHED TABLE PUBLIC.COURT_CASE(
    ID VARCHAR(32) NOT NULL,
    CASE_NUM VARCHAR(64) DEFAULT NULL,
    COURT_ID VARCHAR(32) DEFAULT NULL,
    CASE_TYPE_ID VARCHAR(32) DEFAULT NULL,
    CASE_TYPE_NAME VARCHAR(64) DEFAULT NULL,
    CASE_NAME VARCHAR(255) DEFAULT NULL,
    CASE_DESC TEXT,
    ACCUSER VARCHAR(255) DEFAULT NULL,
    ACCUSER_LAWER VARCHAR(255) DEFAULT NULL,
    ACCUSED VARCHAR(255) DEFAULT NULL,
    ACCUSED_LAWER VARCHAR(255) DEFAULT NULL,
    REGISTER_TIME DATETIME DEFAULT NULL,
    PARTY TEXT,
    DEL_FLAG BIT DEFAULT NULL,
    CREATE_USER VARCHAR(32) DEFAULT NULL,
    CREATE_USER_ID VARCHAR(32) DEFAULT NULL,
    CREATE_TIME DATETIME DEFAULT NULL,
    UPDATE_USER VARCHAR(32) DEFAULT NULL,
    UPDATE_USER_ID VARCHAR(32) DEFAULT NULL,
    UPDATE_TIME DATETIME DEFAULT NULL,
    ROOT_ORG_ID VARCHAR(32) DEFAULT NULL,
    ORG_ID VARCHAR(32) DEFAULT NULL,
    OPT_LOCK INT DEFAULT NULL
);

ALTER TABLE PUBLIC.COURT_CASE ADD CONSTRAINT PUBLIC.CONSTRAINT_B PRIMARY KEY(ID);

CREATE CACHED TABLE PUBLIC.COURT_TRIAL_RECORDING(
    ID VARCHAR(32) NOT NULL,
    PLAN_ID VARCHAR(32) DEFAULT NULL,
    CASE_ID VARCHAR(32) DEFAULT NULL,
    REMOTE_ID VARCHAR(32) DEFAULT NULL,
    CHANNEL_NUM INT DEFAULT NULL,
    CHANNEL_NAME VARCHAR(128) DEFAULT NULL,
    MEDIA_TYPE VARCHAR(32) DEFAULT NULL,
    RESOLUTION VARCHAR(32) DEFAULT NULL,
    START_TIME DATETIME DEFAULT NULL,
    END_TIME DATETIME DEFAULT NULL,
    CODE_RATE BIGINT DEFAULT NULL,
    FILE_NAME VARCHAR(255) DEFAULT NULL,
    FILE_SIZE BIGINT DEFAULT NULL,
    ACCESS_URL VARCHAR(255) DEFAULT NULL,
    PIVOT TEXT,
    REMOTE_FLAG TINYINT DEFAULT '0',
    TOBECALLED_FLAG TINYINT DEFAULT NULL,
    DEL_FLAG BIT DEFAULT NULL,
    CREATE_USER VARCHAR(32) DEFAULT NULL,
    CREATE_USER_ID VARCHAR(32) DEFAULT NULL,
    CREATE_TIME DATETIME DEFAULT NULL,
    UPDATE_USER VARCHAR(32) DEFAULT NULL,
    UPDATE_USER_ID VARCHAR(32) DEFAULT NULL,
    UPDATE_TIME DATETIME DEFAULT NULL,
    ROOT_ORG_ID VARCHAR(32) DEFAULT NULL,
    ORG_ID VARCHAR(32) DEFAULT NULL,
    OPT_LOCK INT DEFAULT NULL
);
ALTER TABLE PUBLIC.COURT_TRIAL_RECORDING ADD CONSTRAINT PUBLIC.CONSTRAINT_BD PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.COURT_TRIAL_RECORDING;
CREATE CACHED TABLE PUBLIC.COURT_ROOM(
    ID VARCHAR(32) NOT NULL,
    ROOM_NAME VARCHAR(64) DEFAULT NULL,
    ROOM_TYPE VARCHAR(16) DEFAULT NULL,
    WWW_LIVE_URL VARCHAR(255) DEFAULT NULL,
    ROOM_MEMO VARCHAR(255) DEFAULT NULL,
    DEL_FLAG BIT DEFAULT NULL,
    CREATE_USER VARCHAR(32) DEFAULT NULL,
    CREATE_USER_ID VARCHAR(32) DEFAULT NULL,
    CREATE_TIME DATETIME DEFAULT NULL,
    UPDATE_USER VARCHAR(32) DEFAULT NULL,
    UPDATE_USER_ID VARCHAR(32) DEFAULT NULL,
    UPDATE_TIME DATETIME DEFAULT NULL,
    ROOT_ORG_ID VARCHAR(32) DEFAULT NULL,
    ORG_ID VARCHAR(32) DEFAULT NULL,
    OPT_LOCK INT DEFAULT NULL
);
ALTER TABLE PUBLIC.COURT_ROOM ADD CONSTRAINT PUBLIC.CONSTRAINT_B4 PRIMARY KEY(ID);

CREATE CACHED TABLE PUBLIC.SYS_USER(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_DFB296AA_C6FA_46CC_942D_61ED76688D36) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_DFB296AA_C6FA_46CC_942D_61ED76688D36,
    CREATE_TIME DATETIME,
    UPDATE_TIME DATETIME,
    LAST_UPDATOR VARCHAR(32),
    CREATE_BY VARCHAR(32),
    USER_NAME VARCHAR(32),
    USER_PASSWORD VARCHAR(64),
    REAL_NAME VARCHAR(64),
    ROLES VARCHAR(255),
    COURT_ID VARCHAR(32)
);
ALTER TABLE PUBLIC.SYS_USER ADD CONSTRAINT PUBLIC.CONSTRAINT_A PRIMARY KEY(ID);