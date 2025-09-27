-- component change-history <start>

create table if not exists component_changed_event (
    id varchar(26),
    entity_type varchar(255),
    entity_id varchar(26),
    creator varchar(26),
    created timestamp,
    json_data jsonb,
    constraint pk_component_changed_event primary key (id)
);

comment on table component_changed_event is '변경이력';
comment on column component_changed_event.entity_type is '엔티티 타입';
comment on column component_changed_event.entity_id is '엔티티 id';
comment on column component_changed_event.creator is 'creator';
comment on column component_changed_event.created is 'created';
comment on column component_changed_event.json_data is 'json_data';

-- component change-history <end>

-- component attach_file <start>

create table if not exists component_attach_file (
   id char(26),
   name varchar(1000),
   path varchar(2000),
   capacity bigint,
   extension varchar(16),
   created timestamp,
   creator varchar(26),
   modified timestamp,
   modifier varchar(26),
   constraint pk_component_attach_file primary key (id)
);

create table if not exists component_attach_file_group (
   id char(26),
   entity_id char(26),
   file_id char(26),
   created timestamp,
   creator varchar(26),
   modified timestamp,
   modifier varchar(26),
   constraint pk_component_attach_file_group primary key (id),
   constraint fk_component_attach_file_group_file_id foreign key (file_id) references component_attach_file(id)
);

-- component attach_file <end>

-- system user <start>

create table if not exists system_domain_user (
    user_id varchar(26),
    username varchar(255) not null,
    password varchar(2048) not null,
    email varchar(255) not null,
    contact varchar(255),
    enabled varchar(10),
    locked varchar(10),
    failed_attempt_count smallint,
    creator varchar(26) not null,
    created timestamp,
    modifier varchar(26),
    modified timestamp,
    constraint pk_system_domain_user primary key (user_id),
    constraint unique_system_domain_user_username unique (username)
);

create table if not exists system_domain_user_attribute (
    attribute_id varchar(26),
    user_id varchar(26) not null,
    name varchar(255) not null,
    value varchar(2048) not null,
    creator varchar(26) not null,
    created timestamp,
    modifier varchar(26),
    modified timestamp,
    constraint pk_system_comain_user_attribute primary key (attribute_id),
    constraint fk_system_comain_user_attribute foreign key (user_id) references system_domain_user (user_id)
);

create table if not exists system_domain_user_pending_change (
    pending_change_id varchar(26),
    user_id varchar(26) ,
    username varchar(255) not null,
    change_type varchar(10),
    effective_date timestamp,
    status varchar(10),
    creator varchar(26) not null,
    created timestamp,
    modifier varchar(26),
    modified timestamp,
    constraint pk_system_domain_user_pending_change primary key (pending_change_id),
    constraint fk_system_domain_user_pending_change_user_id foreign key (user_id) references system_domain_user(user_id)
);

create table if not exists system_domain_user_change_detail (
    change_detail_id varchar(26),
    pending_change_id varchar(26) not null,
    attribute_name varchar(255) not null,
    old_value varchar(2048),
    new_value varchar(2048) not null,
    creator varchar(26) not null,
    created timestamp,
    modifier varchar(26),
    modified timestamp,
    constraint pk_system_domain_user_change_detail primary key (change_detail_id),
    constraint fk_system_domain_user_change_detail_pending_change_id foreign key (pending_change_id) references system_domain_user_pending_change (pending_change_id)
);

-- system user <start>