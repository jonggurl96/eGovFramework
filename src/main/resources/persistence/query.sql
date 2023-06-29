create table post (
                      post_id varchar(255) not null,
                      created_time datetime not null,
                      modified_time datetime not null,
                      modifier_ip_address varchar(255),
                      writer_ip_address varchar(255),
                      password varchar(255),
                      post_content varchar(255) not null,
                      post_title varchar(255) not null,
                      primary key (post_id)
);

CREATE TABLE db_log (
                        id INT NOT NULL AUTO_INCREMENT,
                        event_date TIMESTAMP NOT NULL,
                        logger VARCHAR(45) NULL,
                        level VARCHAR(45) NOT NULL,
                        message TEXT NULL,
                        exception VARCHAR(45) NULL,
                        PRIMARY KEY (id))
COMMENT = 'egovframework 4.1.0 JDBCAppender test table';