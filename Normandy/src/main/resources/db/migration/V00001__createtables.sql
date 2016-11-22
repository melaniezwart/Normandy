create schema if not exists 'n7'

create table n7.NORMANDY (
    id bigint not null primary key,
    captain_id bigint not null,
    heat int,
    armorbay_id bigint,
    missilebay_id bigint,
    laserbay_id bigint,
    generatorbay_id bigint,
    shieldbay_id bigint
);

create sequence n7.PLAYER_SEQ increment by 1 start with 100000 maxvalue 9223372036854775807 no cycle;
