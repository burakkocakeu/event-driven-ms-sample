CREATE KEYSPACE sample WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
} AND DURABLE_WRITES = false;

CREATE TABLE IF NOT EXISTS  sample.user (
    id UUID,
    name varchar,
    surname varchar,
    followerList text,
    date date,
    primary key((id))
);

CREATE TABLE IF NOT EXISTS  sample.follower (
    id UUID,
    followerList text,
    primary key((id))
);
