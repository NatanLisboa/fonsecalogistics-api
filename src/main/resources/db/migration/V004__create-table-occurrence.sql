create table occurrence (
	id bigint not null auto_increment,
    delivery_id bigint not null,
    description text not null,
    register_date datetime not null,
    
    primary key (id),
    foreign key (delivery_id) references delivery(id)
);