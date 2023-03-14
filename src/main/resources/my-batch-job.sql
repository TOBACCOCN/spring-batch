create table my_cron_job
(
    JOB_ID     bigint       not null
        primary key,
    VERSION    bigint       null comment '版本',
    JOB_NAME   varchar(100) not null comment 'job 名称',
    CRON       varchar(32)  not null comment 'cron 表达式',
    constraint JOB_INST_UN
        unique (JOB_NAME)
);

INSERT INTO test.my_cron_job (JOB_ID, VERSION, JOB_NAME, CRON) VALUES (1, 1, 'myBatchJob', '0/10 * * * * ?');
INSERT INTO test.my_cron_job (JOB_ID, VERSION, JOB_NAME, CRON) VALUES (1, 1, 'myBatchJob1', '0/10 * * * * ?');